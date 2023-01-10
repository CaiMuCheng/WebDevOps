// Generated from java-escape by ANTLR 4.11.1
package com.mucheng.web.devops.openapi.editor.lang.css;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link css3Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface css3Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link css3Parser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(css3Parser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodCharset}
	 * labeled alternative in {@link css3Parser#charset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodCharset(css3Parser.GoodCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badCharset}
	 * labeled alternative in {@link css3Parser#charset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadCharset(css3Parser.BadCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodImport}
	 * labeled alternative in {@link css3Parser#imports}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodImport(css3Parser.GoodImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badImport}
	 * labeled alternative in {@link css3Parser#imports}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadImport(css3Parser.BadImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodNamespace}
	 * labeled alternative in {@link css3Parser#namespace_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodNamespace(css3Parser.GoodNamespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badNamespace}
	 * labeled alternative in {@link css3Parser#namespace_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadNamespace(css3Parser.BadNamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#namespacePrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespacePrefix(css3Parser.NamespacePrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#media}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMedia(css3Parser.MediaContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#mediaQueryList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaQueryList(css3Parser.MediaQueryListContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#mediaQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaQuery(css3Parser.MediaQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#mediaType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaType(css3Parser.MediaTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#mediaExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaExpression(css3Parser.MediaExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#mediaFeature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaFeature(css3Parser.MediaFeatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#page}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPage(css3Parser.PageContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#pseudoPage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPseudoPage(css3Parser.PseudoPageContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#selectorGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectorGroup(css3Parser.SelectorGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(css3Parser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#combinator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCombinator(css3Parser.CombinatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#simpleSelectorSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleSelectorSequence(css3Parser.SimpleSelectorSequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#typeSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSelector(css3Parser.TypeSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#typeNamespacePrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeNamespacePrefix(css3Parser.TypeNamespacePrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#elementName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementName(css3Parser.ElementNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#universal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniversal(css3Parser.UniversalContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#className}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassName(css3Parser.ClassNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#attrib}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrib(css3Parser.AttribContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#pseudo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPseudo(css3Parser.PseudoContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#functionalPseudo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionalPseudo(css3Parser.FunctionalPseudoContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(css3Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#negation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(css3Parser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#negationArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegationArg(css3Parser.NegationArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodOperator}
	 * labeled alternative in {@link css3Parser#operator_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodOperator(css3Parser.GoodOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badOperator}
	 * labeled alternative in {@link css3Parser#operator_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadOperator(css3Parser.BadOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodProperty}
	 * labeled alternative in {@link css3Parser#property_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodProperty(css3Parser.GoodPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badProperty}
	 * labeled alternative in {@link css3Parser#property_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadProperty(css3Parser.BadPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code knownRuleset}
	 * labeled alternative in {@link css3Parser#ruleset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownRuleset(css3Parser.KnownRulesetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownRuleset}
	 * labeled alternative in {@link css3Parser#ruleset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownRuleset(css3Parser.UnknownRulesetContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#declarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationList(css3Parser.DeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code knownDeclaration}
	 * labeled alternative in {@link css3Parser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownDeclaration(css3Parser.KnownDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownDeclaration}
	 * labeled alternative in {@link css3Parser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownDeclaration(css3Parser.UnknownDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#prio}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrio(css3Parser.PrioContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(css3Parser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(css3Parser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code knownTerm}
	 * labeled alternative in {@link css3Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownTerm(css3Parser.KnownTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownTerm}
	 * labeled alternative in {@link css3Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownTerm(css3Parser.UnknownTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badTerm}
	 * labeled alternative in {@link css3Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadTerm(css3Parser.BadTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#function_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_(css3Parser.Function_Context ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#dxImageTransform}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDxImageTransform(css3Parser.DxImageTransformContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#hexcolor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexcolor(css3Parser.HexcolorContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(css3Parser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#percentage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentage(css3Parser.PercentageContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimension(css3Parser.DimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#unknownDimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownDimension(css3Parser.UnknownDimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#any_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny_(css3Parser.Any_Context ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownAtRule}
	 * labeled alternative in {@link css3Parser#atRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownAtRule(css3Parser.UnknownAtRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#atKeyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtKeyword(css3Parser.AtKeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#unused}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnused(css3Parser.UnusedContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(css3Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#nestedStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedStatement(css3Parser.NestedStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#groupRuleBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupRuleBody(css3Parser.GroupRuleBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#supportsRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsRule(css3Parser.SupportsRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#supportsCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsCondition(css3Parser.SupportsConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#supportsConditionInParens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsConditionInParens(css3Parser.SupportsConditionInParensContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#supportsNegation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsNegation(css3Parser.SupportsNegationContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#supportsConjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsConjunction(css3Parser.SupportsConjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#supportsDisjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsDisjunction(css3Parser.SupportsDisjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#supportsDeclarationCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsDeclarationCondition(css3Parser.SupportsDeclarationConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#generalEnclosed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneralEnclosed(css3Parser.GeneralEnclosedContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#var_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_(css3Parser.Var_Context ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#calc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalc(css3Parser.CalcContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#calcSum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcSum(css3Parser.CalcSumContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#calcProduct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcProduct(css3Parser.CalcProductContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#calcValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcValue(css3Parser.CalcValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#fontFaceRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontFaceRule(css3Parser.FontFaceRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code knownFontFaceDeclaration}
	 * labeled alternative in {@link css3Parser#fontFaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownFontFaceDeclaration(css3Parser.KnownFontFaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownFontFaceDeclaration}
	 * labeled alternative in {@link css3Parser#fontFaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownFontFaceDeclaration(css3Parser.UnknownFontFaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#keyframesRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyframesRule(css3Parser.KeyframesRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#keyframesBlocks}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyframesBlocks(css3Parser.KeyframesBlocksContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#keyframeSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyframeSelector(css3Parser.KeyframeSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#viewport}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewport(css3Parser.ViewportContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#counterStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCounterStyle(css3Parser.CounterStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#fontFeatureValuesRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontFeatureValuesRule(css3Parser.FontFeatureValuesRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#fontFamilyNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontFamilyNameList(css3Parser.FontFamilyNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#fontFamilyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontFamilyName(css3Parser.FontFamilyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#featureValueBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeatureValueBlock(css3Parser.FeatureValueBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#featureType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeatureType(css3Parser.FeatureTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#featureValueDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeatureValueDefinition(css3Parser.FeatureValueDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(css3Parser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link css3Parser#ws}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWs(css3Parser.WsContext ctx);
}