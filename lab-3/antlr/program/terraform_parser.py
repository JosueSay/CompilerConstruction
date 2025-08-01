import json
import os
import sys
import time
import requests
from antlr4 import *
from TerraformSubsetLexer import TerraformSubsetLexer
from TerraformSubsetParser import TerraformSubsetParser
from TerraformSubsetListener import TerraformSubsetListener

STATE_FILE = "terraform.tfstate"

class TerraformApplyListener(TerraformSubsetListener):
  def __init__(self):
    self.variables = {}
    self.provider_token_expr = None  # store raw expression (e.g., var.token)
    self.droplet_config = {}

  def enterVariable(self, ctx):
    var_name = ctx.STRING().getText().strip('"')
    for kv in ctx.body().keyValue():
      key = kv.IDENTIFIER().getText()
      if key == "default":
        value = kv.expr().getText().strip('"')
        self.variables[var_name] = value
        print(f"[var] {var_name} = {value}")

  def enterProvider(self, ctx):
    provider_name = ctx.STRING().getText().strip('"')
    if provider_name != "digitalocean":
      raise Exception("Only 'digitalocean' provider is supported.")

    for kv in ctx.body().keyValue():
      key = kv.IDENTIFIER().getText()
      expr = kv.expr().getText()
      if key == "token":
        self.provider_token_expr = expr  # store raw expr for now

  def enterResource(self, ctx):
    type_ = ctx.STRING(0).getText().strip('"')
    name = ctx.STRING(1).getText().strip('"')
    if type_ != "digitalocean_droplet":
      return

    for kv in ctx.body().keyValue():
      key = kv.IDENTIFIER().getText()
      val = kv.expr().getText().strip('"')
      self.droplet_config[key] = val

  def resolve_token(self):
    if not self.provider_token_expr:
      raise Exception("No token specified in provider block.")
    if self.provider_token_expr.startswith("var."):
      var_name = self.provider_token_expr.split(".")[1]
      if var_name in self.variables:
        return self.variables[var_name]
      else:
        raise Exception(f"Undefined variable '{var_name}' used in provider block.")
    return self.provider_token_expr.strip('"')

def create_droplet(api_token, config):
  url = "https://api.digitalocean.com/v2/droplets"
  headers = {
    "Content-Type": "application/json",
    "Authorization": f"Bearer {api_token}"
  }

  payload = {
    "name": config["name"],
    "region": config["region"],
    "size": config["size"],
    "image": config["image"],
    "ssh_keys": [],
    "backups": False,
    "ipv6": False,
    "user_data": None,
    "private_networking": None,
    "volumes": None,
    "tags": []
  }

  print("[*] Creating droplet...")
  response = requests.post(url, headers=headers, json=payload)
  response.raise_for_status()
  droplet = response.json()["droplet"]
  droplet_id = droplet["id"]
  print(f"[+] Droplet created with ID: {droplet_id}")

  print("[*] Waiting for droplet to become active and assigned an IP...")
  while True:
    resp = requests.get(f"https://api.digitalocean.com/v2/droplets/{droplet_id}", headers=headers)
    droplet_info = resp.json()["droplet"]
    networks = droplet_info["networks"]["v4"]
    public_ips = [n["ip_address"] for n in networks if n["type"] == "public"]
    if public_ips:
      return public_ips[0], droplet_id
    time.sleep(5)

def saveState(name, droplet_id, ip):
  state = {
    "name": name,
    "id": droplet_id,
    "ip": ip
  }
  with open(STATE_FILE, "w") as f:
    json.dump(state, f, indent=2)
  print(f"[SAVE] State saved to {STATE_FILE}")

def destroyDroplet(api_token):
  if not os.path.exists(STATE_FILE):
    print(f"[!] No state file found at {STATE_FILE}")
    return

  with open(STATE_FILE) as f:
    state = json.load(f)

  droplet_id = state.get("id")
  if not droplet_id:
    print("[!] No droplet ID found in state.")
    return

  url = f"https://api.digitalocean.com/v2/droplets/{droplet_id}"
  headers = {
    "Authorization": f"Bearer {api_token}"
  }

  print(f"[*] Deleting droplet ID: {droplet_id}")
  response = requests.delete(url, headers=headers)

  if response.status_code == 204:
    print("[✓] Droplet successfully destroyed.")
    os.remove(STATE_FILE)
  elif response.status_code == 404:
    print("[!] Droplet not found. Maybe it was already deleted.")
  else:
    print(f"[!] Unexpected error: {response.status_code} {response.text}")

def main(argv):
  if len(argv) < 3:
    print("Uso: python terraform_parser.py <archivo.tf> <--apply|--destroy>")
    return

  mode = argv[2]
  input_stream = FileStream(argv[1])
  lexer = TerraformSubsetLexer(input_stream)
  stream = CommonTokenStream(lexer)
  parser = TerraformSubsetParser(stream)
  tree = parser.terraform()

  listener = TerraformApplyListener()
  walker = ParseTreeWalker()
  walker.walk(listener, tree)

  token = listener.resolve_token()

  if mode == "--apply":
    if not listener.droplet_config:
      raise Exception("Missing digitalocean_droplet resource.")
    ip, droplet_id = create_droplet(token, listener.droplet_config)
    saveState(listener.droplet_config["name"], droplet_id, ip)


  elif mode == "--destroy":
    destroyDroplet(token)

  else:
    print("Modo no reconocido. Usa --apply o --destroy.")

if __name__ == "__main__":
  main(sys.argv)
