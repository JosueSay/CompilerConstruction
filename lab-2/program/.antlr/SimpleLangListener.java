// Generated from d:/repositorios/UVG/2025/CompilerConstruction/lab-2/program/SimpleLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleLangParser}.
 */
public interface SimpleLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(SimpleLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(SimpleLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(SimpleLangParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(SimpleLangParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Float}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloat(SimpleLangParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Float}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloat(SimpleLangParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBool(SimpleLangParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBool(SimpleLangParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(SimpleLangParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(SimpleLangParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(SimpleLangParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(SimpleLangParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(SimpleLangParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(SimpleLangParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterString(SimpleLangParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitString(SimpleLangParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(SimpleLangParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(SimpleLangParser.IntContext ctx);
}