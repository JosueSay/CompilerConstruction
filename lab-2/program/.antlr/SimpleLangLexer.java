// Generated from d:/repositorios/UVG/2025/CompilerConstruction/lab-2/program/SimpleLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SimpleLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, INT=7, FLOAT=8, STRING=9, 
		BOOL=10, NEWLINE=11, WS=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "INT", "FLOAT", "STRING", 
			"BOOL", "NEWLINE", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'*'", "'/'", "'+'", "'-'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "INT", "FLOAT", "STRING", "BOOL", 
			"NEWLINE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SimpleLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SimpleLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\fV\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0004\u0006\'\b\u0006\u000b\u0006\f\u0006(\u0001"+
		"\u0007\u0004\u0007,\b\u0007\u000b\u0007\f\u0007-\u0001\u0007\u0001\u0007"+
		"\u0005\u00072\b\u0007\n\u0007\f\u00075\t\u0007\u0001\b\u0001\b\u0005\b"+
		"9\b\b\n\b\f\b<\t\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tI\b\t\u0001\n\u0003\nL\b\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0004\u000bQ\b\u000b\u000b\u000b\f\u000bR\u0001"+
		"\u000b\u0001\u000b\u0001:\u0000\f\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0001\u0000\u0002\u0001\u000009\u0002\u0000\t\t  \\\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0001\u0019"+
		"\u0001\u0000\u0000\u0000\u0003\u001b\u0001\u0000\u0000\u0000\u0005\u001d"+
		"\u0001\u0000\u0000\u0000\u0007\u001f\u0001\u0000\u0000\u0000\t!\u0001"+
		"\u0000\u0000\u0000\u000b#\u0001\u0000\u0000\u0000\r&\u0001\u0000\u0000"+
		"\u0000\u000f+\u0001\u0000\u0000\u0000\u00116\u0001\u0000\u0000\u0000\u0013"+
		"H\u0001\u0000\u0000\u0000\u0015K\u0001\u0000\u0000\u0000\u0017P\u0001"+
		"\u0000\u0000\u0000\u0019\u001a\u0005*\u0000\u0000\u001a\u0002\u0001\u0000"+
		"\u0000\u0000\u001b\u001c\u0005/\u0000\u0000\u001c\u0004\u0001\u0000\u0000"+
		"\u0000\u001d\u001e\u0005+\u0000\u0000\u001e\u0006\u0001\u0000\u0000\u0000"+
		"\u001f \u0005-\u0000\u0000 \b\u0001\u0000\u0000\u0000!\"\u0005(\u0000"+
		"\u0000\"\n\u0001\u0000\u0000\u0000#$\u0005)\u0000\u0000$\f\u0001\u0000"+
		"\u0000\u0000%\'\u0007\u0000\u0000\u0000&%\u0001\u0000\u0000\u0000\'(\u0001"+
		"\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000"+
		")\u000e\u0001\u0000\u0000\u0000*,\u0007\u0000\u0000\u0000+*\u0001\u0000"+
		"\u0000\u0000,-\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-.\u0001"+
		"\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/3\u0005.\u0000\u000002\u0007"+
		"\u0000\u0000\u000010\u0001\u0000\u0000\u000025\u0001\u0000\u0000\u0000"+
		"31\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u00004\u0010\u0001\u0000"+
		"\u0000\u000053\u0001\u0000\u0000\u00006:\u0005\"\u0000\u000079\t\u0000"+
		"\u0000\u000087\u0001\u0000\u0000\u00009<\u0001\u0000\u0000\u0000:;\u0001"+
		"\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000;=\u0001\u0000\u0000\u0000"+
		"<:\u0001\u0000\u0000\u0000=>\u0005\"\u0000\u0000>\u0012\u0001\u0000\u0000"+
		"\u0000?@\u0005t\u0000\u0000@A\u0005r\u0000\u0000AB\u0005u\u0000\u0000"+
		"BI\u0005e\u0000\u0000CD\u0005f\u0000\u0000DE\u0005a\u0000\u0000EF\u0005"+
		"l\u0000\u0000FG\u0005s\u0000\u0000GI\u0005e\u0000\u0000H?\u0001\u0000"+
		"\u0000\u0000HC\u0001\u0000\u0000\u0000I\u0014\u0001\u0000\u0000\u0000"+
		"JL\u0005\r\u0000\u0000KJ\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000"+
		"LM\u0001\u0000\u0000\u0000MN\u0005\n\u0000\u0000N\u0016\u0001\u0000\u0000"+
		"\u0000OQ\u0007\u0001\u0000\u0000PO\u0001\u0000\u0000\u0000QR\u0001\u0000"+
		"\u0000\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0001"+
		"\u0000\u0000\u0000TU\u0006\u000b\u0000\u0000U\u0018\u0001\u0000\u0000"+
		"\u0000\b\u0000(-3:HKR\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}