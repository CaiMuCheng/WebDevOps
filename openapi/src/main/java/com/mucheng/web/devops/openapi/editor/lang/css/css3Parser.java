// Generated from java-escape by ANTLR 4.11.1
package com.mucheng.web.devops.openapi.editor.lang.css;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class css3Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, Comment=16, 
		Space=17, Cdo=18, Cdc=19, Includes=20, DashMatch=21, Hash=22, Import=23, 
		Page=24, Media=25, Namespace=26, Charset=27, Important=28, Percentage=29, 
		Uri=30, UnicodeRange=31, MediaOnly=32, Not=33, And=34, Dimension=35, UnknownDimension=36, 
		Plus=37, Minus=38, Greater=39, Comma=40, Tilde=41, PseudoNot=42, Number=43, 
		String_=44, PrefixMatch=45, SuffixMatch=46, SubstringMatch=47, FontFace=48, 
		Supports=49, Or=50, Keyframes=51, From=52, To=53, Calc=54, Viewport=55, 
		CounterStyle=56, FontFeatureValues=57, DxImageTransform=58, Variable=59, 
		Var=60, Ident=61, Function_=62;
	public static final int
		RULE_stylesheet = 0, RULE_charset = 1, RULE_imports = 2, RULE_namespace_ = 3, 
		RULE_namespacePrefix = 4, RULE_media = 5, RULE_mediaQueryList = 6, RULE_mediaQuery = 7, 
		RULE_mediaType = 8, RULE_mediaExpression = 9, RULE_mediaFeature = 10, 
		RULE_page = 11, RULE_pseudoPage = 12, RULE_selectorGroup = 13, RULE_selector = 14, 
		RULE_combinator = 15, RULE_simpleSelectorSequence = 16, RULE_typeSelector = 17, 
		RULE_typeNamespacePrefix = 18, RULE_elementName = 19, RULE_universal = 20, 
		RULE_className = 21, RULE_attrib = 22, RULE_pseudo = 23, RULE_functionalPseudo = 24, 
		RULE_expression = 25, RULE_negation = 26, RULE_negationArg = 27, RULE_operator_ = 28, 
		RULE_property_ = 29, RULE_ruleset = 30, RULE_declarationList = 31, RULE_declaration = 32, 
		RULE_prio = 33, RULE_value = 34, RULE_expr = 35, RULE_term = 36, RULE_function_ = 37, 
		RULE_dxImageTransform = 38, RULE_hexcolor = 39, RULE_number = 40, RULE_percentage = 41, 
		RULE_dimension = 42, RULE_unknownDimension = 43, RULE_any_ = 44, RULE_atRule = 45, 
		RULE_atKeyword = 46, RULE_unused = 47, RULE_block = 48, RULE_nestedStatement = 49, 
		RULE_groupRuleBody = 50, RULE_supportsRule = 51, RULE_supportsCondition = 52, 
		RULE_supportsConditionInParens = 53, RULE_supportsNegation = 54, RULE_supportsConjunction = 55, 
		RULE_supportsDisjunction = 56, RULE_supportsDeclarationCondition = 57, 
		RULE_generalEnclosed = 58, RULE_var_ = 59, RULE_calc = 60, RULE_calcSum = 61, 
		RULE_calcProduct = 62, RULE_calcValue = 63, RULE_fontFaceRule = 64, RULE_fontFaceDeclaration = 65, 
		RULE_keyframesRule = 66, RULE_keyframesBlocks = 67, RULE_keyframeSelector = 68, 
		RULE_viewport = 69, RULE_counterStyle = 70, RULE_fontFeatureValuesRule = 71, 
		RULE_fontFamilyNameList = 72, RULE_fontFamilyName = 73, RULE_featureValueBlock = 74, 
		RULE_featureType = 75, RULE_featureValueDefinition = 76, RULE_ident = 77, 
		RULE_ws = 78;
	private static String[] makeRuleNames() {
		return new String[] {
			"stylesheet", "charset", "imports", "namespace_", "namespacePrefix", 
			"media", "mediaQueryList", "mediaQuery", "mediaType", "mediaExpression", 
			"mediaFeature", "page", "pseudoPage", "selectorGroup", "selector", "combinator", 
			"simpleSelectorSequence", "typeSelector", "typeNamespacePrefix", "elementName", 
			"universal", "className", "attrib", "pseudo", "functionalPseudo", "expression", 
			"negation", "negationArg", "operator_", "property_", "ruleset", "declarationList", 
			"declaration", "prio", "value", "expr", "term", "function_", "dxImageTransform", 
			"hexcolor", "number", "percentage", "dimension", "unknownDimension", 
			"any_", "atRule", "atKeyword", "unused", "block", "nestedStatement", 
			"groupRuleBody", "supportsRule", "supportsCondition", "supportsConditionInParens", 
			"supportsNegation", "supportsConjunction", "supportsDisjunction", "supportsDeclarationCondition", 
			"generalEnclosed", "var_", "calc", "calcSum", "calcProduct", "calcValue", 
			"fontFaceRule", "fontFaceDeclaration", "keyframesRule", "keyframesBlocks", 
			"keyframeSelector", "viewport", "counterStyle", "fontFeatureValuesRule", 
			"fontFamilyNameList", "fontFamilyName", "featureValueBlock", "featureType", 
			"featureValueDefinition", "ident", "ws"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "':'", "')'", "'{'", "'}'", "'*'", "'|'", "'.'", 
			"'['", "'='", "']'", "'/'", "'_'", "'@'", null, null, "'<!--'", "'-->'", 
			"'~='", "'|='", null, null, null, null, null, "'@charset '", null, null, 
			null, null, null, null, null, null, null, "'+'", "'-'", "'>'", "','", 
			"'~'", null, null, null, "'^='", "'$='", "'*='", null, null, null, null, 
			null, null, "'calc('", null, null, null, null, null, "'var('"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "Comment", "Space", "Cdo", "Cdc", "Includes", 
			"DashMatch", "Hash", "Import", "Page", "Media", "Namespace", "Charset", 
			"Important", "Percentage", "Uri", "UnicodeRange", "MediaOnly", "Not", 
			"And", "Dimension", "UnknownDimension", "Plus", "Minus", "Greater", "Comma", 
			"Tilde", "PseudoNot", "Number", "String_", "PrefixMatch", "SuffixMatch", 
			"SubstringMatch", "FontFace", "Supports", "Or", "Keyframes", "From", 
			"To", "Calc", "Viewport", "CounterStyle", "FontFeatureValues", "DxImageTransform", 
			"Variable", "Var", "Ident", "Function_"
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

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public css3Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StylesheetContext extends ParserRuleContext {
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(css3Parser.EOF, 0); }
		public List<CharsetContext> charset() {
			return getRuleContexts(CharsetContext.class);
		}
		public CharsetContext charset(int i) {
			return getRuleContext(CharsetContext.class,i);
		}
		public List<ImportsContext> imports() {
			return getRuleContexts(ImportsContext.class);
		}
		public ImportsContext imports(int i) {
			return getRuleContext(ImportsContext.class,i);
		}
		public List<Namespace_Context> namespace_() {
			return getRuleContexts(Namespace_Context.class);
		}
		public Namespace_Context namespace_(int i) {
			return getRuleContext(Namespace_Context.class,i);
		}
		public List<NestedStatementContext> nestedStatement() {
			return getRuleContexts(NestedStatementContext.class);
		}
		public NestedStatementContext nestedStatement(int i) {
			return getRuleContext(NestedStatementContext.class,i);
		}
		public List<TerminalNode> Comment() { return getTokens(css3Parser.Comment); }
		public TerminalNode Comment(int i) {
			return getToken(css3Parser.Comment, i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public List<TerminalNode> Cdo() { return getTokens(css3Parser.Cdo); }
		public TerminalNode Cdo(int i) {
			return getToken(css3Parser.Cdo, i);
		}
		public List<TerminalNode> Cdc() { return getTokens(css3Parser.Cdc); }
		public TerminalNode Cdc(int i) {
			return getToken(css3Parser.Cdc, i);
		}
		public StylesheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterStylesheet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitStylesheet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitStylesheet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StylesheetContext stylesheet() throws RecognitionException {
		StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			ws();
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Charset) {
				{
				{
				setState(159);
				charset();
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0) {
					{
					{
					setState(160);
					_la = _input.LA(1);
					if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Import) {
				{
				{
				setState(171);
				imports();
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0) {
					{
					{
					setState(172);
					_la = _input.LA(1);
					if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Namespace) {
				{
				{
				setState(183);
				namespace_();
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0) {
					{
					{
					setState(184);
					_la = _input.LA(1);
					if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(189);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 7187494865908828076L) != 0) {
				{
				{
				setState(195);
				nestedStatement();
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0) {
					{
					{
					setState(196);
					_la = _input.LA(1);
					if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(201);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(207);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharsetContext extends ParserRuleContext {
		public CharsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charset; }
	 
		public CharsetContext() { }
		public void copyFrom(CharsetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadCharsetContext extends CharsetContext {
		public TerminalNode Charset() { return getToken(css3Parser.Charset, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public BadCharsetContext(CharsetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterBadCharset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitBadCharset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitBadCharset(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodCharsetContext extends CharsetContext {
		public TerminalNode Charset() { return getToken(css3Parser.Charset, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public GoodCharsetContext(CharsetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterGoodCharset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitGoodCharset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitGoodCharset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharsetContext charset() throws RecognitionException {
		CharsetContext _localctx = new CharsetContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_charset);
		try {
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new GoodCharsetContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				match(Charset);
				setState(210);
				ws();
				setState(211);
				match(String_);
				setState(212);
				ws();
				setState(213);
				match(T__0);
				setState(214);
				ws();
				}
				break;
			case 2:
				_localctx = new BadCharsetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(Charset);
				setState(217);
				ws();
				setState(218);
				match(String_);
				setState(219);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportsContext extends ParserRuleContext {
		public ImportsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imports; }
	 
		public ImportsContext() { }
		public void copyFrom(ImportsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadImportContext extends ImportsContext {
		public TerminalNode Import() { return getToken(css3Parser.Import, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaQueryListContext mediaQueryList() {
			return getRuleContext(MediaQueryListContext.class,0);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public TerminalNode Uri() { return getToken(css3Parser.Uri, 0); }
		public BadImportContext(ImportsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterBadImport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitBadImport(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitBadImport(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodImportContext extends ImportsContext {
		public TerminalNode Import() { return getToken(css3Parser.Import, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaQueryListContext mediaQueryList() {
			return getRuleContext(MediaQueryListContext.class,0);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public TerminalNode Uri() { return getToken(css3Parser.Uri, 0); }
		public GoodImportContext(ImportsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterGoodImport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitGoodImport(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitGoodImport(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportsContext imports() throws RecognitionException {
		ImportsContext _localctx = new ImportsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_imports);
		int _la;
		try {
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new GoodImportContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				match(Import);
				setState(224);
				ws();
				setState(225);
				_la = _input.LA(1);
				if ( !(_la==Uri || _la==String_) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(226);
				ws();
				setState(227);
				mediaQueryList();
				setState(228);
				match(T__0);
				setState(229);
				ws();
				}
				break;
			case 2:
				_localctx = new GoodImportContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				match(Import);
				setState(232);
				ws();
				setState(233);
				_la = _input.LA(1);
				if ( !(_la==Uri || _la==String_) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(234);
				ws();
				setState(235);
				match(T__0);
				setState(236);
				ws();
				}
				break;
			case 3:
				_localctx = new BadImportContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(238);
				match(Import);
				setState(239);
				ws();
				setState(240);
				_la = _input.LA(1);
				if ( !(_la==Uri || _la==String_) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(241);
				ws();
				setState(242);
				mediaQueryList();
				}
				break;
			case 4:
				_localctx = new BadImportContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(244);
				match(Import);
				setState(245);
				ws();
				setState(246);
				_la = _input.LA(1);
				if ( !(_la==Uri || _la==String_) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(247);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Namespace_Context extends ParserRuleContext {
		public Namespace_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_; }
	 
		public Namespace_Context() { }
		public void copyFrom(Namespace_Context ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodNamespaceContext extends Namespace_Context {
		public TerminalNode Namespace() { return getToken(css3Parser.Namespace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public TerminalNode Uri() { return getToken(css3Parser.Uri, 0); }
		public NamespacePrefixContext namespacePrefix() {
			return getRuleContext(NamespacePrefixContext.class,0);
		}
		public GoodNamespaceContext(Namespace_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterGoodNamespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitGoodNamespace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitGoodNamespace(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadNamespaceContext extends Namespace_Context {
		public TerminalNode Namespace() { return getToken(css3Parser.Namespace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public TerminalNode Uri() { return getToken(css3Parser.Uri, 0); }
		public NamespacePrefixContext namespacePrefix() {
			return getRuleContext(NamespacePrefixContext.class,0);
		}
		public BadNamespaceContext(Namespace_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterBadNamespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitBadNamespace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitBadNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Namespace_Context namespace_() throws RecognitionException {
		Namespace_Context _localctx = new Namespace_Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_namespace_);
		int _la;
		try {
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new GoodNamespaceContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(251);
				match(Namespace);
				setState(252);
				ws();
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2320479738067419136L) != 0) {
					{
					setState(253);
					namespacePrefix();
					setState(254);
					ws();
					}
				}

				setState(258);
				_la = _input.LA(1);
				if ( !(_la==Uri || _la==String_) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(259);
				ws();
				setState(260);
				match(T__0);
				setState(261);
				ws();
				}
				break;
			case 2:
				_localctx = new BadNamespaceContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				match(Namespace);
				setState(264);
				ws();
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2320479738067419136L) != 0) {
					{
					setState(265);
					namespacePrefix();
					setState(266);
					ws();
					}
				}

				setState(270);
				_la = _input.LA(1);
				if ( !(_la==Uri || _la==String_) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(271);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NamespacePrefixContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public NamespacePrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespacePrefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterNamespacePrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitNamespacePrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitNamespacePrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespacePrefixContext namespacePrefix() throws RecognitionException {
		NamespacePrefixContext _localctx = new NamespacePrefixContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_namespacePrefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaContext extends ParserRuleContext {
		public TerminalNode Media() { return getToken(css3Parser.Media, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaQueryListContext mediaQueryList() {
			return getRuleContext(MediaQueryListContext.class,0);
		}
		public GroupRuleBodyContext groupRuleBody() {
			return getRuleContext(GroupRuleBodyContext.class,0);
		}
		public MediaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_media; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterMedia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitMedia(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitMedia(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MediaContext media() throws RecognitionException {
		MediaContext _localctx = new MediaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_media);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(Media);
			setState(278);
			ws();
			setState(279);
			mediaQueryList();
			setState(280);
			groupRuleBody();
			setState(281);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaQueryListContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<MediaQueryContext> mediaQuery() {
			return getRuleContexts(MediaQueryContext.class);
		}
		public MediaQueryContext mediaQuery(int i) {
			return getRuleContext(MediaQueryContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(css3Parser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(css3Parser.Comma, i);
		}
		public MediaQueryListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaQueryList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterMediaQueryList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitMediaQueryList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitMediaQueryList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MediaQueryListContext mediaQueryList() throws RecognitionException {
		MediaQueryListContext _localctx = new MediaQueryListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_mediaQueryList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(283);
				mediaQuery();
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(284);
					match(Comma);
					setState(285);
					ws();
					setState(286);
					mediaQuery();
					}
					}
					setState(292);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(295);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaQueryContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaTypeContext mediaType() {
			return getRuleContext(MediaTypeContext.class,0);
		}
		public List<TerminalNode> And() { return getTokens(css3Parser.And); }
		public TerminalNode And(int i) {
			return getToken(css3Parser.And, i);
		}
		public List<MediaExpressionContext> mediaExpression() {
			return getRuleContexts(MediaExpressionContext.class);
		}
		public MediaExpressionContext mediaExpression(int i) {
			return getRuleContext(MediaExpressionContext.class,i);
		}
		public TerminalNode MediaOnly() { return getToken(css3Parser.MediaOnly, 0); }
		public TerminalNode Not() { return getToken(css3Parser.Not, 0); }
		public MediaQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterMediaQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitMediaQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitMediaQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MediaQueryContext mediaQuery() throws RecognitionException {
		MediaQueryContext _localctx = new MediaQueryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_mediaQuery);
		int _la;
		try {
			int _alt;
			setState(322);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Comment:
			case Space:
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				enterOuterAlt(_localctx, 1);
				{
				setState(298);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(297);
					_la = _input.LA(1);
					if ( !(_la==MediaOnly || _la==Not) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				}
				setState(300);
				ws();
				setState(301);
				mediaType();
				setState(302);
				ws();
				setState(309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(303);
						match(And);
						setState(304);
						ws();
						setState(305);
						mediaExpression();
						}
						} 
					}
					setState(311);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
				mediaExpression();
				setState(319);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(313);
						match(And);
						setState(314);
						ws();
						setState(315);
						mediaExpression();
						}
						} 
					}
					setState(321);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaTypeContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public MediaTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterMediaType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitMediaType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitMediaType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MediaTypeContext mediaType() throws RecognitionException {
		MediaTypeContext _localctx = new MediaTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_mediaType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaExpressionContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaFeatureContext mediaFeature() {
			return getRuleContext(MediaFeatureContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MediaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterMediaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitMediaExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitMediaExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MediaExpressionContext mediaExpression() throws RecognitionException {
		MediaExpressionContext _localctx = new MediaExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_mediaExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(T__1);
			setState(327);
			ws();
			setState(328);
			mediaFeature();
			setState(333);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(329);
				match(T__2);
				setState(330);
				ws();
				setState(331);
				expr();
				}
			}

			setState(335);
			match(T__3);
			setState(336);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaFeatureContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public MediaFeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaFeature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterMediaFeature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitMediaFeature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitMediaFeature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MediaFeatureContext mediaFeature() throws RecognitionException {
		MediaFeatureContext _localctx = new MediaFeatureContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_mediaFeature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			ident();
			setState(339);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PageContext extends ParserRuleContext {
		public TerminalNode Page() { return getToken(css3Parser.Page, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public PseudoPageContext pseudoPage() {
			return getRuleContext(PseudoPageContext.class,0);
		}
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public PageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_page; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterPage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitPage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitPage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PageContext page() throws RecognitionException {
		PageContext _localctx = new PageContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_page);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			match(Page);
			setState(342);
			ws();
			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(343);
				pseudoPage();
				}
			}

			setState(346);
			match(T__4);
			setState(347);
			ws();
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2896940490370859136L) != 0) {
				{
				setState(348);
				declaration();
				}
			}

			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(351);
				match(T__0);
				setState(352);
				ws();
				setState(354);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2896940490370859136L) != 0) {
					{
					setState(353);
					declaration();
					}
				}

				}
				}
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(361);
			match(T__5);
			setState(362);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PseudoPageContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public PseudoPageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pseudoPage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterPseudoPage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitPseudoPage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitPseudoPage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PseudoPageContext pseudoPage() throws RecognitionException {
		PseudoPageContext _localctx = new PseudoPageContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pseudoPage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(T__2);
			setState(365);
			ident();
			setState(366);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectorGroupContext extends ParserRuleContext {
		public List<SelectorContext> selector() {
			return getRuleContexts(SelectorContext.class);
		}
		public SelectorContext selector(int i) {
			return getRuleContext(SelectorContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(css3Parser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(css3Parser.Comma, i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public SelectorGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectorGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSelectorGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSelectorGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSelectorGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectorGroupContext selectorGroup() throws RecognitionException {
		SelectorGroupContext _localctx = new SelectorGroupContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_selectorGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			selector();
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(369);
				match(Comma);
				setState(370);
				ws();
				setState(371);
				selector();
				}
				}
				setState(377);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectorContext extends ParserRuleContext {
		public List<SimpleSelectorSequenceContext> simpleSelectorSequence() {
			return getRuleContexts(SimpleSelectorSequenceContext.class);
		}
		public SimpleSelectorSequenceContext simpleSelectorSequence(int i) {
			return getRuleContext(SimpleSelectorSequenceContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<CombinatorContext> combinator() {
			return getRuleContexts(CombinatorContext.class);
		}
		public CombinatorContext combinator(int i) {
			return getRuleContext(CombinatorContext.class,i);
		}
		public SelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectorContext selector() throws RecognitionException {
		SelectorContext _localctx = new SelectorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_selector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			simpleSelectorSequence();
			setState(379);
			ws();
			setState(386);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 2886218153984L) != 0) {
				{
				{
				setState(380);
				combinator();
				setState(381);
				simpleSelectorSequence();
				setState(382);
				ws();
				}
				}
				setState(388);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CombinatorContext extends ParserRuleContext {
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode Greater() { return getToken(css3Parser.Greater, 0); }
		public TerminalNode Tilde() { return getToken(css3Parser.Tilde, 0); }
		public TerminalNode Space() { return getToken(css3Parser.Space, 0); }
		public CombinatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_combinator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterCombinator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitCombinator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitCombinator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CombinatorContext combinator() throws RecognitionException {
		CombinatorContext _localctx = new CombinatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_combinator);
		try {
			setState(397);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Plus:
				enterOuterAlt(_localctx, 1);
				{
				setState(389);
				match(Plus);
				setState(390);
				ws();
				}
				break;
			case Greater:
				enterOuterAlt(_localctx, 2);
				{
				setState(391);
				match(Greater);
				setState(392);
				ws();
				}
				break;
			case Tilde:
				enterOuterAlt(_localctx, 3);
				{
				setState(393);
				match(Tilde);
				setState(394);
				ws();
				}
				break;
			case Space:
				enterOuterAlt(_localctx, 4);
				{
				setState(395);
				match(Space);
				setState(396);
				ws();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SimpleSelectorSequenceContext extends ParserRuleContext {
		public TypeSelectorContext typeSelector() {
			return getRuleContext(TypeSelectorContext.class,0);
		}
		public UniversalContext universal() {
			return getRuleContext(UniversalContext.class,0);
		}
		public List<TerminalNode> Hash() { return getTokens(css3Parser.Hash); }
		public TerminalNode Hash(int i) {
			return getToken(css3Parser.Hash, i);
		}
		public List<ClassNameContext> className() {
			return getRuleContexts(ClassNameContext.class);
		}
		public ClassNameContext className(int i) {
			return getRuleContext(ClassNameContext.class,i);
		}
		public List<AttribContext> attrib() {
			return getRuleContexts(AttribContext.class);
		}
		public AttribContext attrib(int i) {
			return getRuleContext(AttribContext.class,i);
		}
		public List<PseudoContext> pseudo() {
			return getRuleContexts(PseudoContext.class);
		}
		public PseudoContext pseudo(int i) {
			return getRuleContext(PseudoContext.class,i);
		}
		public List<NegationContext> negation() {
			return getRuleContexts(NegationContext.class);
		}
		public NegationContext negation(int i) {
			return getRuleContext(NegationContext.class,i);
		}
		public SimpleSelectorSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleSelectorSequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSimpleSelectorSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSimpleSelectorSequence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSimpleSelectorSequence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleSelectorSequenceContext simpleSelectorSequence() throws RecognitionException {
		SimpleSelectorSequenceContext _localctx = new SimpleSelectorSequenceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_simpleSelectorSequence);
		int _la;
		try {
			setState(422);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
			case T__7:
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				enterOuterAlt(_localctx, 1);
				{
				setState(401);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(399);
					typeSelector();
					}
					break;
				case 2:
					{
					setState(400);
					universal();
					}
					break;
				}
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 4398050706952L) != 0) {
					{
					setState(408);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case Hash:
						{
						setState(403);
						match(Hash);
						}
						break;
					case T__8:
						{
						setState(404);
						className();
						}
						break;
					case T__9:
						{
						setState(405);
						attrib();
						}
						break;
					case T__2:
						{
						setState(406);
						pseudo();
						}
						break;
					case PseudoNot:
						{
						setState(407);
						negation();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(412);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__2:
			case T__8:
			case T__9:
			case Hash:
			case PseudoNot:
				enterOuterAlt(_localctx, 2);
				{
				setState(418); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(418);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case Hash:
						{
						setState(413);
						match(Hash);
						}
						break;
					case T__8:
						{
						setState(414);
						className();
						}
						break;
					case T__9:
						{
						setState(415);
						attrib();
						}
						break;
					case T__2:
						{
						setState(416);
						pseudo();
						}
						break;
					case PseudoNot:
						{
						setState(417);
						negation();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(420); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((_la) & ~0x3f) == 0 && ((1L << _la) & 4398050706952L) != 0 );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeSelectorContext extends ParserRuleContext {
		public ElementNameContext elementName() {
			return getRuleContext(ElementNameContext.class,0);
		}
		public TypeNamespacePrefixContext typeNamespacePrefix() {
			return getRuleContext(TypeNamespacePrefixContext.class,0);
		}
		public TypeSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterTypeSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitTypeSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitTypeSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeSelectorContext typeSelector() throws RecognitionException {
		TypeSelectorContext _localctx = new TypeSelectorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(424);
				typeNamespacePrefix();
				}
				break;
			}
			setState(427);
			elementName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNamespacePrefixContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TypeNamespacePrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeNamespacePrefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterTypeNamespacePrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitTypeNamespacePrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitTypeNamespacePrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNamespacePrefixContext typeNamespacePrefix() throws RecognitionException {
		TypeNamespacePrefixContext _localctx = new TypeNamespacePrefixContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeNamespacePrefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				{
				setState(429);
				ident();
				}
				break;
			case T__6:
				{
				setState(430);
				match(T__6);
				}
				break;
			case T__7:
				break;
			default:
				break;
			}
			setState(433);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementNameContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ElementNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterElementName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitElementName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitElementName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementNameContext elementName() throws RecognitionException {
		ElementNameContext _localctx = new ElementNameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_elementName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UniversalContext extends ParserRuleContext {
		public TypeNamespacePrefixContext typeNamespacePrefix() {
			return getRuleContext(TypeNamespacePrefixContext.class,0);
		}
		public UniversalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_universal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterUniversal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitUniversal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitUniversal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UniversalContext universal() throws RecognitionException {
		UniversalContext _localctx = new UniversalContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_universal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(437);
				typeNamespacePrefix();
				}
				break;
			}
			setState(440);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassNameContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ClassNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_className; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterClassName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitClassName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitClassName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassNameContext className() throws RecognitionException {
		ClassNameContext _localctx = new ClassNameContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_className);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			match(T__8);
			setState(443);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttribContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public TypeNamespacePrefixContext typeNamespacePrefix() {
			return getRuleContext(TypeNamespacePrefixContext.class,0);
		}
		public TerminalNode PrefixMatch() { return getToken(css3Parser.PrefixMatch, 0); }
		public TerminalNode SuffixMatch() { return getToken(css3Parser.SuffixMatch, 0); }
		public TerminalNode SubstringMatch() { return getToken(css3Parser.SubstringMatch, 0); }
		public TerminalNode Includes() { return getToken(css3Parser.Includes, 0); }
		public TerminalNode DashMatch() { return getToken(css3Parser.DashMatch, 0); }
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public AttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitAttrib(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitAttrib(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttribContext attrib() throws RecognitionException {
		AttribContext _localctx = new AttribContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_attrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			match(T__9);
			setState(446);
			ws();
			setState(448);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(447);
				typeNamespacePrefix();
				}
				break;
			}
			setState(450);
			ident();
			setState(451);
			ws();
			setState(460);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((_la) & ~0x3f) == 0 && ((1L << _la) & 246290607769600L) != 0) {
				{
				setState(452);
				_la = _input.LA(1);
				if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 246290607769600L) != 0) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(453);
				ws();
				setState(456);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MediaOnly:
				case Not:
				case And:
				case Or:
				case From:
				case To:
				case Ident:
					{
					setState(454);
					ident();
					}
					break;
				case String_:
					{
					setState(455);
					match(String_);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(458);
				ws();
				}
			}

			setState(462);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PseudoContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public FunctionalPseudoContext functionalPseudo() {
			return getRuleContext(FunctionalPseudoContext.class,0);
		}
		public PseudoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pseudo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterPseudo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitPseudo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitPseudo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PseudoContext pseudo() throws RecognitionException {
		PseudoContext _localctx = new PseudoContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_pseudo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464);
			match(T__2);
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(465);
				match(T__2);
				}
			}

			setState(470);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				{
				setState(468);
				ident();
				}
				break;
			case Function_:
				{
				setState(469);
				functionalPseudo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionalPseudoContext extends ParserRuleContext {
		public TerminalNode Function_() { return getToken(css3Parser.Function_, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FunctionalPseudoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionalPseudo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterFunctionalPseudo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitFunctionalPseudo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitFunctionalPseudo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionalPseudoContext functionalPseudo() throws RecognitionException {
		FunctionalPseudoContext _localctx = new FunctionalPseudoContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_functionalPseudo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			match(Function_);
			setState(473);
			ws();
			setState(474);
			expression();
			setState(475);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Plus() { return getTokens(css3Parser.Plus); }
		public TerminalNode Plus(int i) {
			return getToken(css3Parser.Plus, i);
		}
		public List<TerminalNode> Minus() { return getTokens(css3Parser.Minus); }
		public TerminalNode Minus(int i) {
			return getToken(css3Parser.Minus, i);
		}
		public List<TerminalNode> Dimension() { return getTokens(css3Parser.Dimension); }
		public TerminalNode Dimension(int i) {
			return getToken(css3Parser.Dimension, i);
		}
		public List<TerminalNode> UnknownDimension() { return getTokens(css3Parser.UnknownDimension); }
		public TerminalNode UnknownDimension(int i) {
			return getToken(css3Parser.UnknownDimension, i);
		}
		public List<TerminalNode> Number() { return getTokens(css3Parser.Number); }
		public TerminalNode Number(int i) {
			return getToken(css3Parser.Number, i);
		}
		public List<TerminalNode> String_() { return getTokens(css3Parser.String_); }
		public TerminalNode String_(int i) {
			return getToken(css3Parser.String_, i);
		}
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(484);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Plus:
					{
					setState(477);
					match(Plus);
					}
					break;
				case Minus:
					{
					setState(478);
					match(Minus);
					}
					break;
				case Dimension:
					{
					setState(479);
					match(Dimension);
					}
					break;
				case UnknownDimension:
					{
					setState(480);
					match(UnknownDimension);
					}
					break;
				case Number:
					{
					setState(481);
					match(Number);
					}
					break;
				case String_:
					{
					setState(482);
					match(String_);
					}
					break;
				case MediaOnly:
				case Not:
				case And:
				case Or:
				case From:
				case To:
				case Ident:
					{
					setState(483);
					ident();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(486);
				ws();
				}
				}
				setState(489); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((_la) & ~0x3f) == 0 && ((1L << _la) & 2320506641742561280L) != 0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NegationContext extends ParserRuleContext {
		public TerminalNode PseudoNot() { return getToken(css3Parser.PseudoNot, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public NegationArgContext negationArg() {
			return getRuleContext(NegationArgContext.class,0);
		}
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_negation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(491);
			match(PseudoNot);
			setState(492);
			ws();
			setState(493);
			negationArg();
			setState(494);
			ws();
			setState(495);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NegationArgContext extends ParserRuleContext {
		public TypeSelectorContext typeSelector() {
			return getRuleContext(TypeSelectorContext.class,0);
		}
		public UniversalContext universal() {
			return getRuleContext(UniversalContext.class,0);
		}
		public TerminalNode Hash() { return getToken(css3Parser.Hash, 0); }
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public AttribContext attrib() {
			return getRuleContext(AttribContext.class,0);
		}
		public PseudoContext pseudo() {
			return getRuleContext(PseudoContext.class,0);
		}
		public NegationArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negationArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterNegationArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitNegationArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitNegationArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NegationArgContext negationArg() throws RecognitionException {
		NegationArgContext _localctx = new NegationArgContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_negationArg);
		try {
			setState(503);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(497);
				typeSelector();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(498);
				universal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(499);
				match(Hash);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(500);
				className();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(501);
				attrib();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(502);
				pseudo();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Operator_Context extends ParserRuleContext {
		public Operator_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator_; }
	 
		public Operator_Context() { }
		public void copyFrom(Operator_Context ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadOperatorContext extends Operator_Context {
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public BadOperatorContext(Operator_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterBadOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitBadOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitBadOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodOperatorContext extends Operator_Context {
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode Comma() { return getToken(css3Parser.Comma, 0); }
		public TerminalNode Space() { return getToken(css3Parser.Space, 0); }
		public GoodOperatorContext(Operator_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterGoodOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitGoodOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitGoodOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operator_Context operator_() throws RecognitionException {
		Operator_Context _localctx = new Operator_Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_operator_);
		try {
			setState(513);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				_localctx = new GoodOperatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(505);
				match(T__12);
				setState(506);
				ws();
				}
				break;
			case Comma:
				_localctx = new GoodOperatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(507);
				match(Comma);
				setState(508);
				ws();
				}
				break;
			case Space:
				_localctx = new GoodOperatorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(509);
				match(Space);
				setState(510);
				ws();
				}
				break;
			case T__10:
				_localctx = new BadOperatorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(511);
				match(T__10);
				setState(512);
				ws();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Property_Context extends ParserRuleContext {
		public Property_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property_; }
	 
		public Property_Context() { }
		public void copyFrom(Property_Context ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadPropertyContext extends Property_Context {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public BadPropertyContext(Property_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterBadProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitBadProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitBadProperty(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodPropertyContext extends Property_Context {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode Variable() { return getToken(css3Parser.Variable, 0); }
		public GoodPropertyContext(Property_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterGoodProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitGoodProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitGoodProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Property_Context property_() throws RecognitionException {
		Property_Context _localctx = new Property_Context(_ctx, getState());
		enterRule(_localctx, 58, RULE_property_);
		try {
			setState(524);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				_localctx = new GoodPropertyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(515);
				ident();
				setState(516);
				ws();
				}
				break;
			case Variable:
				_localctx = new GoodPropertyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(518);
				match(Variable);
				setState(519);
				ws();
				}
				break;
			case T__6:
				_localctx = new BadPropertyContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(520);
				match(T__6);
				setState(521);
				ident();
				}
				break;
			case T__13:
				_localctx = new BadPropertyContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(522);
				match(T__13);
				setState(523);
				ident();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RulesetContext extends ParserRuleContext {
		public RulesetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleset; }
	 
		public RulesetContext() { }
		public void copyFrom(RulesetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownRulesetContext extends RulesetContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public UnknownRulesetContext(RulesetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterUnknownRuleset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitUnknownRuleset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitUnknownRuleset(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KnownRulesetContext extends RulesetContext {
		public SelectorGroupContext selectorGroup() {
			return getRuleContext(SelectorGroupContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public KnownRulesetContext(RulesetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterKnownRuleset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitKnownRuleset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitKnownRuleset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesetContext ruleset() throws RecognitionException {
		RulesetContext _localctx = new RulesetContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_ruleset);
		int _la;
		try {
			setState(549);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				_localctx = new KnownRulesetContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(526);
				selectorGroup();
				setState(527);
				match(T__4);
				setState(528);
				ws();
				setState(530);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2896940490370859138L) != 0) {
					{
					setState(529);
					declarationList();
					}
				}

				setState(532);
				match(T__5);
				setState(533);
				ws();
				}
				break;
			case 2:
				_localctx = new UnknownRulesetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(538);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 6932192663935386636L) != 0) {
					{
					{
					setState(535);
					any_();
					}
					}
					setState(540);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(541);
				match(T__4);
				setState(542);
				ws();
				setState(544);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2896940490370859138L) != 0) {
					{
					setState(543);
					declarationList();
					}
				}

				setState(546);
				match(T__5);
				setState(547);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationListContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public DeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterDeclarationList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitDeclarationList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitDeclarationList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationListContext declarationList() throws RecognitionException {
		DeclarationListContext _localctx = new DeclarationListContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_declarationList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(555);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(551);
				match(T__0);
				setState(552);
				ws();
				}
				}
				setState(557);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(558);
			declaration();
			setState(559);
			ws();
			setState(567);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(560);
					match(T__0);
					setState(561);
					ws();
					setState(563);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
					case 1:
						{
						setState(562);
						declaration();
						}
						break;
					}
					}
					} 
				}
				setState(569);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	 
		public DeclarationContext() { }
		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownDeclarationContext extends DeclarationContext {
		public Property_Context property_() {
			return getRuleContext(Property_Context.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public UnknownDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterUnknownDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitUnknownDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitUnknownDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KnownDeclarationContext extends DeclarationContext {
		public Property_Context property_() {
			return getRuleContext(Property_Context.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrioContext prio() {
			return getRuleContext(PrioContext.class,0);
		}
		public KnownDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterKnownDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitKnownDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitKnownDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_declaration);
		int _la;
		try {
			setState(582);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				_localctx = new KnownDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(570);
				property_();
				setState(571);
				match(T__2);
				setState(572);
				ws();
				setState(573);
				expr();
				setState(575);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Important) {
					{
					setState(574);
					prio();
					}
				}

				}
				break;
			case 2:
				_localctx = new UnknownDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(577);
				property_();
				setState(578);
				match(T__2);
				setState(579);
				ws();
				setState(580);
				value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrioContext extends ParserRuleContext {
		public TerminalNode Important() { return getToken(css3Parser.Important, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public PrioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prio; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterPrio(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitPrio(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitPrio(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrioContext prio() throws RecognitionException {
		PrioContext _localctx = new PrioContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_prio);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(584);
			match(Important);
			setState(585);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<AtKeywordContext> atKeyword() {
			return getRuleContexts(AtKeywordContext.class);
		}
		public AtKeywordContext atKeyword(int i) {
			return getRuleContext(AtKeywordContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_value);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(592); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(592);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__1:
					case T__2:
					case T__9:
					case Includes:
					case DashMatch:
					case Hash:
					case Percentage:
					case Uri:
					case UnicodeRange:
					case MediaOnly:
					case Not:
					case And:
					case Dimension:
					case UnknownDimension:
					case Plus:
					case Minus:
					case Number:
					case String_:
					case Or:
					case From:
					case To:
					case Ident:
					case Function_:
						{
						setState(587);
						any_();
						}
						break;
					case T__4:
						{
						setState(588);
						block();
						}
						break;
					case T__14:
						{
						setState(589);
						atKeyword();
						setState(590);
						ws();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(594); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<Operator_Context> operator_() {
			return getRuleContexts(Operator_Context.class);
		}
		public Operator_Context operator_(int i) {
			return getRuleContext(Operator_Context.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(596);
			term();
			setState(603);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(598);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((_la) & ~0x3f) == 0 && ((1L << _la) & 1099511769088L) != 0) {
						{
						setState(597);
						operator_();
						}
					}

					setState(600);
					term();
					}
					} 
				}
				setState(605);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	 
		public TermContext() { }
		public void copyFrom(TermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadTermContext extends TermContext {
		public DxImageTransformContext dxImageTransform() {
			return getRuleContext(DxImageTransformContext.class,0);
		}
		public BadTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterBadTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitBadTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitBadTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KnownTermContext extends TermContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public PercentageContext percentage() {
			return getRuleContext(PercentageContext.class,0);
		}
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public TerminalNode UnicodeRange() { return getToken(css3Parser.UnicodeRange, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public Var_Context var_() {
			return getRuleContext(Var_Context.class,0);
		}
		public TerminalNode Uri() { return getToken(css3Parser.Uri, 0); }
		public HexcolorContext hexcolor() {
			return getRuleContext(HexcolorContext.class,0);
		}
		public CalcContext calc() {
			return getRuleContext(CalcContext.class,0);
		}
		public Function_Context function_() {
			return getRuleContext(Function_Context.class,0);
		}
		public KnownTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterKnownTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitKnownTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitKnownTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownTermContext extends TermContext {
		public UnknownDimensionContext unknownDimension() {
			return getRuleContext(UnknownDimensionContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public UnknownTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterUnknownTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitUnknownTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitUnknownTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_term);
		try {
			setState(632);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(606);
				number();
				setState(607);
				ws();
				}
				break;
			case 2:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(609);
				percentage();
				setState(610);
				ws();
				}
				break;
			case 3:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(612);
				dimension();
				setState(613);
				ws();
				}
				break;
			case 4:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(615);
				match(String_);
				setState(616);
				ws();
				}
				break;
			case 5:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(617);
				match(UnicodeRange);
				setState(618);
				ws();
				}
				break;
			case 6:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(619);
				ident();
				setState(620);
				ws();
				}
				break;
			case 7:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(622);
				var_();
				}
				break;
			case 8:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(623);
				match(Uri);
				setState(624);
				ws();
				}
				break;
			case 9:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(625);
				hexcolor();
				}
				break;
			case 10:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(626);
				calc();
				}
				break;
			case 11:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(627);
				function_();
				}
				break;
			case 12:
				_localctx = new UnknownTermContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(628);
				unknownDimension();
				setState(629);
				ws();
				}
				break;
			case 13:
				_localctx = new BadTermContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(631);
				dxImageTransform();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_Context extends ParserRuleContext {
		public TerminalNode Function_() { return getToken(css3Parser.Function_, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Function_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterFunction_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitFunction_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitFunction_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_Context function_() throws RecognitionException {
		Function_Context _localctx = new Function_Context(_ctx, getState());
		enterRule(_localctx, 74, RULE_function_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(634);
			match(Function_);
			setState(635);
			ws();
			setState(636);
			expr();
			setState(637);
			match(T__3);
			setState(638);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DxImageTransformContext extends ParserRuleContext {
		public TerminalNode DxImageTransform() { return getToken(css3Parser.DxImageTransform, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DxImageTransformContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dxImageTransform; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterDxImageTransform(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitDxImageTransform(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitDxImageTransform(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DxImageTransformContext dxImageTransform() throws RecognitionException {
		DxImageTransformContext _localctx = new DxImageTransformContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_dxImageTransform);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(640);
			match(DxImageTransform);
			setState(641);
			ws();
			setState(642);
			expr();
			setState(643);
			match(T__3);
			setState(644);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HexcolorContext extends ParserRuleContext {
		public TerminalNode Hash() { return getToken(css3Parser.Hash, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public HexcolorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexcolor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterHexcolor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitHexcolor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitHexcolor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HexcolorContext hexcolor() throws RecognitionException {
		HexcolorContext _localctx = new HexcolorContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_hexcolor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(646);
			match(Hash);
			setState(647);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode Number() { return getToken(css3Parser.Number, 0); }
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public TerminalNode Minus() { return getToken(css3Parser.Minus, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(650);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(649);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(652);
			match(Number);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PercentageContext extends ParserRuleContext {
		public TerminalNode Percentage() { return getToken(css3Parser.Percentage, 0); }
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public TerminalNode Minus() { return getToken(css3Parser.Minus, 0); }
		public PercentageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_percentage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterPercentage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitPercentage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitPercentage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PercentageContext percentage() throws RecognitionException {
		PercentageContext _localctx = new PercentageContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_percentage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(655);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(654);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(657);
			match(Percentage);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DimensionContext extends ParserRuleContext {
		public TerminalNode Dimension() { return getToken(css3Parser.Dimension, 0); }
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public TerminalNode Minus() { return getToken(css3Parser.Minus, 0); }
		public DimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterDimension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitDimension(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitDimension(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DimensionContext dimension() throws RecognitionException {
		DimensionContext _localctx = new DimensionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_dimension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(660);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(659);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(662);
			match(Dimension);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnknownDimensionContext extends ParserRuleContext {
		public TerminalNode UnknownDimension() { return getToken(css3Parser.UnknownDimension, 0); }
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public TerminalNode Minus() { return getToken(css3Parser.Minus, 0); }
		public UnknownDimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unknownDimension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterUnknownDimension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitUnknownDimension(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitUnknownDimension(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnknownDimensionContext unknownDimension() throws RecognitionException {
		UnknownDimensionContext _localctx = new UnknownDimensionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_unknownDimension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(665);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(664);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(667);
			match(UnknownDimension);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Any_Context extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public PercentageContext percentage() {
			return getRuleContext(PercentageContext.class,0);
		}
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public UnknownDimensionContext unknownDimension() {
			return getRuleContext(UnknownDimensionContext.class,0);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public TerminalNode Uri() { return getToken(css3Parser.Uri, 0); }
		public TerminalNode Hash() { return getToken(css3Parser.Hash, 0); }
		public TerminalNode UnicodeRange() { return getToken(css3Parser.UnicodeRange, 0); }
		public TerminalNode Includes() { return getToken(css3Parser.Includes, 0); }
		public TerminalNode DashMatch() { return getToken(css3Parser.DashMatch, 0); }
		public TerminalNode Function_() { return getToken(css3Parser.Function_, 0); }
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public List<UnusedContext> unused() {
			return getRuleContexts(UnusedContext.class);
		}
		public UnusedContext unused(int i) {
			return getRuleContext(UnusedContext.class,i);
		}
		public Any_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterAny_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitAny_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitAny_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Any_Context any_() throws RecognitionException {
		Any_Context _localctx = new Any_Context(_ctx, getState());
		enterRule(_localctx, 88, RULE_any_);
		int _la;
		try {
			setState(734);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(669);
				ident();
				setState(670);
				ws();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(672);
				number();
				setState(673);
				ws();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(675);
				percentage();
				setState(676);
				ws();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(678);
				dimension();
				setState(679);
				ws();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(681);
				unknownDimension();
				setState(682);
				ws();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(684);
				match(String_);
				setState(685);
				ws();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(686);
				match(Uri);
				setState(687);
				ws();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(688);
				match(Hash);
				setState(689);
				ws();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(690);
				match(UnicodeRange);
				setState(691);
				ws();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(692);
				match(Includes);
				setState(693);
				ws();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(694);
				match(DashMatch);
				setState(695);
				ws();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(696);
				match(T__2);
				setState(697);
				ws();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(698);
				match(Function_);
				setState(699);
				ws();
				setState(704);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 6932192663936205870L) != 0) {
					{
					setState(702);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__1:
					case T__2:
					case T__9:
					case Includes:
					case DashMatch:
					case Hash:
					case Percentage:
					case Uri:
					case UnicodeRange:
					case MediaOnly:
					case Not:
					case And:
					case Dimension:
					case UnknownDimension:
					case Plus:
					case Minus:
					case Number:
					case String_:
					case Or:
					case From:
					case To:
					case Ident:
					case Function_:
						{
						setState(700);
						any_();
						}
						break;
					case T__0:
					case T__4:
					case T__14:
					case Cdo:
					case Cdc:
						{
						setState(701);
						unused();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(706);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(707);
				match(T__3);
				setState(708);
				ws();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(710);
				match(T__1);
				setState(711);
				ws();
				setState(716);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 6932192663936205870L) != 0) {
					{
					setState(714);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__1:
					case T__2:
					case T__9:
					case Includes:
					case DashMatch:
					case Hash:
					case Percentage:
					case Uri:
					case UnicodeRange:
					case MediaOnly:
					case Not:
					case And:
					case Dimension:
					case UnknownDimension:
					case Plus:
					case Minus:
					case Number:
					case String_:
					case Or:
					case From:
					case To:
					case Ident:
					case Function_:
						{
						setState(712);
						any_();
						}
						break;
					case T__0:
					case T__4:
					case T__14:
					case Cdo:
					case Cdc:
						{
						setState(713);
						unused();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(718);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(719);
				match(T__3);
				setState(720);
				ws();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(722);
				match(T__9);
				setState(723);
				ws();
				setState(728);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 6932192663936205870L) != 0) {
					{
					setState(726);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__1:
					case T__2:
					case T__9:
					case Includes:
					case DashMatch:
					case Hash:
					case Percentage:
					case Uri:
					case UnicodeRange:
					case MediaOnly:
					case Not:
					case And:
					case Dimension:
					case UnknownDimension:
					case Plus:
					case Minus:
					case Number:
					case String_:
					case Or:
					case From:
					case To:
					case Ident:
					case Function_:
						{
						setState(724);
						any_();
						}
						break;
					case T__0:
					case T__4:
					case T__14:
					case Cdo:
					case Cdc:
						{
						setState(725);
						unused();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(730);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(731);
				match(T__11);
				setState(732);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtRuleContext extends ParserRuleContext {
		public AtRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atRule; }
	 
		public AtRuleContext() { }
		public void copyFrom(AtRuleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownAtRuleContext extends AtRuleContext {
		public AtKeywordContext atKeyword() {
			return getRuleContext(AtKeywordContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public UnknownAtRuleContext(AtRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterUnknownAtRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitUnknownAtRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitUnknownAtRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtRuleContext atRule() throws RecognitionException {
		AtRuleContext _localctx = new AtRuleContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_atRule);
		int _la;
		try {
			_localctx = new UnknownAtRuleContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(736);
			atKeyword();
			setState(737);
			ws();
			setState(741);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 6932192663935386636L) != 0) {
				{
				{
				setState(738);
				any_();
				}
				}
				setState(743);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(747);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(744);
				block();
				}
				break;
			case T__0:
				{
				setState(745);
				match(T__0);
				setState(746);
				ws();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtKeywordContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public AtKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterAtKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitAtKeyword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitAtKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtKeywordContext atKeyword() throws RecognitionException {
		AtKeywordContext _localctx = new AtKeywordContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_atKeyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(749);
			match(T__14);
			setState(750);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnusedContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public AtKeywordContext atKeyword() {
			return getRuleContext(AtKeywordContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode Cdo() { return getToken(css3Parser.Cdo, 0); }
		public TerminalNode Cdc() { return getToken(css3Parser.Cdc, 0); }
		public UnusedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unused; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterUnused(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitUnused(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitUnused(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnusedContext unused() throws RecognitionException {
		UnusedContext _localctx = new UnusedContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_unused);
		try {
			setState(762);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(752);
				block();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(753);
				atKeyword();
				setState(754);
				ws();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 3);
				{
				setState(756);
				match(T__0);
				setState(757);
				ws();
				}
				break;
			case Cdo:
				enterOuterAlt(_localctx, 4);
				{
				setState(758);
				match(Cdo);
				setState(759);
				ws();
				}
				break;
			case Cdc:
				enterOuterAlt(_localctx, 5);
				{
				setState(760);
				match(Cdc);
				setState(761);
				ws();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<DeclarationListContext> declarationList() {
			return getRuleContexts(DeclarationListContext.class);
		}
		public DeclarationListContext declarationList(int i) {
			return getRuleContext(DeclarationListContext.class,i);
		}
		public List<NestedStatementContext> nestedStatement() {
			return getRuleContexts(NestedStatementContext.class);
		}
		public NestedStatementContext nestedStatement(int i) {
			return getRuleContext(NestedStatementContext.class,i);
		}
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<AtKeywordContext> atKeyword() {
			return getRuleContexts(AtKeywordContext.class);
		}
		public AtKeywordContext atKeyword(int i) {
			return getRuleContext(AtKeywordContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(764);
			match(T__4);
			setState(765);
			ws();
			setState(777);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 7763955618212267950L) != 0) {
				{
				setState(775);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					{
					setState(766);
					declarationList();
					}
					break;
				case 2:
					{
					setState(767);
					nestedStatement();
					}
					break;
				case 3:
					{
					setState(768);
					any_();
					}
					break;
				case 4:
					{
					setState(769);
					block();
					}
					break;
				case 5:
					{
					setState(770);
					atKeyword();
					setState(771);
					ws();
					}
					break;
				case 6:
					{
					setState(773);
					match(T__0);
					setState(774);
					ws();
					}
					break;
				}
				}
				setState(779);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(780);
			match(T__5);
			setState(781);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NestedStatementContext extends ParserRuleContext {
		public RulesetContext ruleset() {
			return getRuleContext(RulesetContext.class,0);
		}
		public MediaContext media() {
			return getRuleContext(MediaContext.class,0);
		}
		public PageContext page() {
			return getRuleContext(PageContext.class,0);
		}
		public FontFaceRuleContext fontFaceRule() {
			return getRuleContext(FontFaceRuleContext.class,0);
		}
		public KeyframesRuleContext keyframesRule() {
			return getRuleContext(KeyframesRuleContext.class,0);
		}
		public SupportsRuleContext supportsRule() {
			return getRuleContext(SupportsRuleContext.class,0);
		}
		public ViewportContext viewport() {
			return getRuleContext(ViewportContext.class,0);
		}
		public CounterStyleContext counterStyle() {
			return getRuleContext(CounterStyleContext.class,0);
		}
		public FontFeatureValuesRuleContext fontFeatureValuesRule() {
			return getRuleContext(FontFeatureValuesRuleContext.class,0);
		}
		public AtRuleContext atRule() {
			return getRuleContext(AtRuleContext.class,0);
		}
		public NestedStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nestedStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterNestedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitNestedStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitNestedStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NestedStatementContext nestedStatement() throws RecognitionException {
		NestedStatementContext _localctx = new NestedStatementContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_nestedStatement);
		try {
			setState(793);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__4:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case Includes:
			case DashMatch:
			case Hash:
			case Percentage:
			case Uri:
			case UnicodeRange:
			case MediaOnly:
			case Not:
			case And:
			case Dimension:
			case UnknownDimension:
			case Plus:
			case Minus:
			case PseudoNot:
			case Number:
			case String_:
			case Or:
			case From:
			case To:
			case Ident:
			case Function_:
				enterOuterAlt(_localctx, 1);
				{
				setState(783);
				ruleset();
				}
				break;
			case Media:
				enterOuterAlt(_localctx, 2);
				{
				setState(784);
				media();
				}
				break;
			case Page:
				enterOuterAlt(_localctx, 3);
				{
				setState(785);
				page();
				}
				break;
			case FontFace:
				enterOuterAlt(_localctx, 4);
				{
				setState(786);
				fontFaceRule();
				}
				break;
			case Keyframes:
				enterOuterAlt(_localctx, 5);
				{
				setState(787);
				keyframesRule();
				}
				break;
			case Supports:
				enterOuterAlt(_localctx, 6);
				{
				setState(788);
				supportsRule();
				}
				break;
			case Viewport:
				enterOuterAlt(_localctx, 7);
				{
				setState(789);
				viewport();
				}
				break;
			case CounterStyle:
				enterOuterAlt(_localctx, 8);
				{
				setState(790);
				counterStyle();
				}
				break;
			case FontFeatureValues:
				enterOuterAlt(_localctx, 9);
				{
				setState(791);
				fontFeatureValuesRule();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 10);
				{
				setState(792);
				atRule();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GroupRuleBodyContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<NestedStatementContext> nestedStatement() {
			return getRuleContexts(NestedStatementContext.class);
		}
		public NestedStatementContext nestedStatement(int i) {
			return getRuleContext(NestedStatementContext.class,i);
		}
		public GroupRuleBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupRuleBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterGroupRuleBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitGroupRuleBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitGroupRuleBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupRuleBodyContext groupRuleBody() throws RecognitionException {
		GroupRuleBodyContext _localctx = new GroupRuleBodyContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_groupRuleBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(795);
			match(T__4);
			setState(796);
			ws();
			setState(800);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 7187494865908828076L) != 0) {
				{
				{
				setState(797);
				nestedStatement();
				}
				}
				setState(802);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(803);
			match(T__5);
			setState(804);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsRuleContext extends ParserRuleContext {
		public TerminalNode Supports() { return getToken(css3Parser.Supports, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public SupportsConditionContext supportsCondition() {
			return getRuleContext(SupportsConditionContext.class,0);
		}
		public GroupRuleBodyContext groupRuleBody() {
			return getRuleContext(GroupRuleBodyContext.class,0);
		}
		public SupportsRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSupportsRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSupportsRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSupportsRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SupportsRuleContext supportsRule() throws RecognitionException {
		SupportsRuleContext _localctx = new SupportsRuleContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_supportsRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(806);
			match(Supports);
			setState(807);
			ws();
			setState(808);
			supportsCondition();
			setState(809);
			ws();
			setState(810);
			groupRuleBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsConditionContext extends ParserRuleContext {
		public SupportsNegationContext supportsNegation() {
			return getRuleContext(SupportsNegationContext.class,0);
		}
		public SupportsConjunctionContext supportsConjunction() {
			return getRuleContext(SupportsConjunctionContext.class,0);
		}
		public SupportsDisjunctionContext supportsDisjunction() {
			return getRuleContext(SupportsDisjunctionContext.class,0);
		}
		public SupportsConditionInParensContext supportsConditionInParens() {
			return getRuleContext(SupportsConditionInParensContext.class,0);
		}
		public SupportsConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSupportsCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSupportsCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSupportsCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SupportsConditionContext supportsCondition() throws RecognitionException {
		SupportsConditionContext _localctx = new SupportsConditionContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_supportsCondition);
		try {
			setState(816);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(812);
				supportsNegation();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(813);
				supportsConjunction();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(814);
				supportsDisjunction();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(815);
				supportsConditionInParens();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsConditionInParensContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public SupportsConditionContext supportsCondition() {
			return getRuleContext(SupportsConditionContext.class,0);
		}
		public SupportsDeclarationConditionContext supportsDeclarationCondition() {
			return getRuleContext(SupportsDeclarationConditionContext.class,0);
		}
		public GeneralEnclosedContext generalEnclosed() {
			return getRuleContext(GeneralEnclosedContext.class,0);
		}
		public SupportsConditionInParensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsConditionInParens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSupportsConditionInParens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSupportsConditionInParens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSupportsConditionInParens(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SupportsConditionInParensContext supportsConditionInParens() throws RecognitionException {
		SupportsConditionInParensContext _localctx = new SupportsConditionInParensContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_supportsConditionInParens);
		try {
			setState(826);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(818);
				match(T__1);
				setState(819);
				ws();
				setState(820);
				supportsCondition();
				setState(821);
				ws();
				setState(822);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(824);
				supportsDeclarationCondition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(825);
				generalEnclosed();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsNegationContext extends ParserRuleContext {
		public TerminalNode Not() { return getToken(css3Parser.Not, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode Space() { return getToken(css3Parser.Space, 0); }
		public SupportsConditionInParensContext supportsConditionInParens() {
			return getRuleContext(SupportsConditionInParensContext.class,0);
		}
		public SupportsNegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsNegation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSupportsNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSupportsNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSupportsNegation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SupportsNegationContext supportsNegation() throws RecognitionException {
		SupportsNegationContext _localctx = new SupportsNegationContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_supportsNegation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(828);
			match(Not);
			setState(829);
			ws();
			setState(830);
			match(Space);
			setState(831);
			ws();
			setState(832);
			supportsConditionInParens();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsConjunctionContext extends ParserRuleContext {
		public List<SupportsConditionInParensContext> supportsConditionInParens() {
			return getRuleContexts(SupportsConditionInParensContext.class);
		}
		public SupportsConditionInParensContext supportsConditionInParens(int i) {
			return getRuleContext(SupportsConditionInParensContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public List<TerminalNode> And() { return getTokens(css3Parser.And); }
		public TerminalNode And(int i) {
			return getToken(css3Parser.And, i);
		}
		public SupportsConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsConjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSupportsConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSupportsConjunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSupportsConjunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SupportsConjunctionContext supportsConjunction() throws RecognitionException {
		SupportsConjunctionContext _localctx = new SupportsConjunctionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_supportsConjunction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(834);
			supportsConditionInParens();
			setState(844); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(835);
					ws();
					setState(836);
					match(Space);
					setState(837);
					ws();
					setState(838);
					match(And);
					setState(839);
					ws();
					setState(840);
					match(Space);
					setState(841);
					ws();
					setState(842);
					supportsConditionInParens();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(846); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsDisjunctionContext extends ParserRuleContext {
		public List<SupportsConditionInParensContext> supportsConditionInParens() {
			return getRuleContexts(SupportsConditionInParensContext.class);
		}
		public SupportsConditionInParensContext supportsConditionInParens(int i) {
			return getRuleContext(SupportsConditionInParensContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public List<TerminalNode> Or() { return getTokens(css3Parser.Or); }
		public TerminalNode Or(int i) {
			return getToken(css3Parser.Or, i);
		}
		public SupportsDisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsDisjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSupportsDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSupportsDisjunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSupportsDisjunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SupportsDisjunctionContext supportsDisjunction() throws RecognitionException {
		SupportsDisjunctionContext _localctx = new SupportsDisjunctionContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_supportsDisjunction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(848);
			supportsConditionInParens();
			setState(858); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(849);
					ws();
					setState(850);
					match(Space);
					setState(851);
					ws();
					setState(852);
					match(Or);
					setState(853);
					ws();
					setState(854);
					match(Space);
					setState(855);
					ws();
					setState(856);
					supportsConditionInParens();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(860); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsDeclarationConditionContext extends ParserRuleContext {
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public SupportsDeclarationConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsDeclarationCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterSupportsDeclarationCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitSupportsDeclarationCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitSupportsDeclarationCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SupportsDeclarationConditionContext supportsDeclarationCondition() throws RecognitionException {
		SupportsDeclarationConditionContext _localctx = new SupportsDeclarationConditionContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_supportsDeclarationCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(862);
			match(T__1);
			setState(863);
			ws();
			setState(864);
			declaration();
			setState(865);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GeneralEnclosedContext extends ParserRuleContext {
		public TerminalNode Function_() { return getToken(css3Parser.Function_, 0); }
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public List<UnusedContext> unused() {
			return getRuleContexts(UnusedContext.class);
		}
		public UnusedContext unused(int i) {
			return getRuleContext(UnusedContext.class,i);
		}
		public GeneralEnclosedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generalEnclosed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterGeneralEnclosed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitGeneralEnclosed(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitGeneralEnclosed(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GeneralEnclosedContext generalEnclosed() throws RecognitionException {
		GeneralEnclosedContext _localctx = new GeneralEnclosedContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_generalEnclosed);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(867);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==Function_) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(872);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 6932192663936205870L) != 0) {
				{
				setState(870);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
				case T__2:
				case T__9:
				case Includes:
				case DashMatch:
				case Hash:
				case Percentage:
				case Uri:
				case UnicodeRange:
				case MediaOnly:
				case Not:
				case And:
				case Dimension:
				case UnknownDimension:
				case Plus:
				case Minus:
				case Number:
				case String_:
				case Or:
				case From:
				case To:
				case Ident:
				case Function_:
					{
					setState(868);
					any_();
					}
					break;
				case T__0:
				case T__4:
				case T__14:
				case Cdo:
				case Cdc:
					{
					setState(869);
					unused();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(874);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(875);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_Context extends ParserRuleContext {
		public TerminalNode Var() { return getToken(css3Parser.Var, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode Variable() { return getToken(css3Parser.Variable, 0); }
		public Var_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterVar_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitVar_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitVar_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_Context var_() throws RecognitionException {
		Var_Context _localctx = new Var_Context(_ctx, getState());
		enterRule(_localctx, 118, RULE_var_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(877);
			match(Var);
			setState(878);
			ws();
			setState(879);
			match(Variable);
			setState(880);
			ws();
			setState(881);
			match(T__3);
			setState(882);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalcContext extends ParserRuleContext {
		public TerminalNode Calc() { return getToken(css3Parser.Calc, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public CalcSumContext calcSum() {
			return getRuleContext(CalcSumContext.class,0);
		}
		public CalcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterCalc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitCalc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitCalc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalcContext calc() throws RecognitionException {
		CalcContext _localctx = new CalcContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_calc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(884);
			match(Calc);
			setState(885);
			ws();
			setState(886);
			calcSum();
			setState(887);
			match(T__3);
			setState(888);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalcSumContext extends ParserRuleContext {
		public List<CalcProductContext> calcProduct() {
			return getRuleContexts(CalcProductContext.class);
		}
		public CalcProductContext calcProduct(int i) {
			return getRuleContext(CalcProductContext.class,i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Plus() { return getTokens(css3Parser.Plus); }
		public TerminalNode Plus(int i) {
			return getToken(css3Parser.Plus, i);
		}
		public List<TerminalNode> Minus() { return getTokens(css3Parser.Minus); }
		public TerminalNode Minus(int i) {
			return getToken(css3Parser.Minus, i);
		}
		public CalcSumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcSum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterCalcSum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitCalcSum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitCalcSum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalcSumContext calcSum() throws RecognitionException {
		CalcSumContext _localctx = new CalcSumContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_calcSum);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(890);
			calcProduct();
			setState(901);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Space) {
				{
				{
				setState(891);
				match(Space);
				setState(892);
				ws();
				setState(893);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(894);
				ws();
				setState(895);
				match(Space);
				setState(896);
				ws();
				setState(897);
				calcProduct();
				}
				}
				setState(903);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalcProductContext extends ParserRuleContext {
		public List<CalcValueContext> calcValue() {
			return getRuleContexts(CalcValueContext.class);
		}
		public CalcValueContext calcValue(int i) {
			return getRuleContext(CalcValueContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public CalcProductContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcProduct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterCalcProduct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitCalcProduct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitCalcProduct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalcProductContext calcProduct() throws RecognitionException {
		CalcProductContext _localctx = new CalcProductContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_calcProduct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(904);
			calcValue();
			setState(916);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || _la==T__12) {
				{
				setState(914);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__6:
					{
					setState(905);
					match(T__6);
					setState(906);
					ws();
					setState(907);
					calcValue();
					}
					break;
				case T__12:
					{
					setState(909);
					match(T__12);
					setState(910);
					ws();
					setState(911);
					number();
					setState(912);
					ws();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(918);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalcValueContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public UnknownDimensionContext unknownDimension() {
			return getRuleContext(UnknownDimensionContext.class,0);
		}
		public PercentageContext percentage() {
			return getRuleContext(PercentageContext.class,0);
		}
		public CalcSumContext calcSum() {
			return getRuleContext(CalcSumContext.class,0);
		}
		public CalcValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterCalcValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitCalcValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitCalcValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalcValueContext calcValue() throws RecognitionException {
		CalcValueContext _localctx = new CalcValueContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_calcValue);
		try {
			setState(937);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(919);
				number();
				setState(920);
				ws();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(922);
				dimension();
				setState(923);
				ws();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(925);
				unknownDimension();
				setState(926);
				ws();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(928);
				percentage();
				setState(929);
				ws();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(931);
				match(T__1);
				setState(932);
				ws();
				setState(933);
				calcSum();
				setState(934);
				match(T__3);
				setState(935);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFaceRuleContext extends ParserRuleContext {
		public TerminalNode FontFace() { return getToken(css3Parser.FontFace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<FontFaceDeclarationContext> fontFaceDeclaration() {
			return getRuleContexts(FontFaceDeclarationContext.class);
		}
		public FontFaceDeclarationContext fontFaceDeclaration(int i) {
			return getRuleContext(FontFaceDeclarationContext.class,i);
		}
		public FontFaceRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFaceRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterFontFaceRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitFontFaceRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitFontFaceRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FontFaceRuleContext fontFaceRule() throws RecognitionException {
		FontFaceRuleContext _localctx = new FontFaceRuleContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_fontFaceRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(939);
			match(FontFace);
			setState(940);
			ws();
			setState(941);
			match(T__4);
			setState(942);
			ws();
			setState(944);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2896940490370859136L) != 0) {
				{
				setState(943);
				fontFaceDeclaration();
				}
			}

			setState(953);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(946);
				match(T__0);
				setState(947);
				ws();
				setState(949);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2896940490370859136L) != 0) {
					{
					setState(948);
					fontFaceDeclaration();
					}
				}

				}
				}
				setState(955);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(956);
			match(T__5);
			setState(957);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFaceDeclarationContext extends ParserRuleContext {
		public FontFaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFaceDeclaration; }
	 
		public FontFaceDeclarationContext() { }
		public void copyFrom(FontFaceDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KnownFontFaceDeclarationContext extends FontFaceDeclarationContext {
		public Property_Context property_() {
			return getRuleContext(Property_Context.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public KnownFontFaceDeclarationContext(FontFaceDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterKnownFontFaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitKnownFontFaceDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitKnownFontFaceDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownFontFaceDeclarationContext extends FontFaceDeclarationContext {
		public Property_Context property_() {
			return getRuleContext(Property_Context.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public UnknownFontFaceDeclarationContext(FontFaceDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterUnknownFontFaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitUnknownFontFaceDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitUnknownFontFaceDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FontFaceDeclarationContext fontFaceDeclaration() throws RecognitionException {
		FontFaceDeclarationContext _localctx = new FontFaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_fontFaceDeclaration);
		try {
			setState(969);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				_localctx = new KnownFontFaceDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(959);
				property_();
				setState(960);
				match(T__2);
				setState(961);
				ws();
				setState(962);
				expr();
				}
				break;
			case 2:
				_localctx = new UnknownFontFaceDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(964);
				property_();
				setState(965);
				match(T__2);
				setState(966);
				ws();
				setState(967);
				value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyframesRuleContext extends ParserRuleContext {
		public TerminalNode Keyframes() { return getToken(css3Parser.Keyframes, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode Space() { return getToken(css3Parser.Space, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public KeyframesBlocksContext keyframesBlocks() {
			return getRuleContext(KeyframesBlocksContext.class,0);
		}
		public KeyframesRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyframesRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterKeyframesRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitKeyframesRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitKeyframesRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyframesRuleContext keyframesRule() throws RecognitionException {
		KeyframesRuleContext _localctx = new KeyframesRuleContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_keyframesRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(971);
			match(Keyframes);
			setState(972);
			ws();
			setState(973);
			match(Space);
			setState(974);
			ws();
			setState(975);
			ident();
			setState(976);
			ws();
			setState(977);
			match(T__4);
			setState(978);
			ws();
			setState(979);
			keyframesBlocks();
			setState(980);
			match(T__5);
			setState(981);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyframesBlocksContext extends ParserRuleContext {
		public List<KeyframeSelectorContext> keyframeSelector() {
			return getRuleContexts(KeyframeSelectorContext.class);
		}
		public KeyframeSelectorContext keyframeSelector(int i) {
			return getRuleContext(KeyframeSelectorContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<DeclarationListContext> declarationList() {
			return getRuleContexts(DeclarationListContext.class);
		}
		public DeclarationListContext declarationList(int i) {
			return getRuleContext(DeclarationListContext.class,i);
		}
		public KeyframesBlocksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyframesBlocks; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterKeyframesBlocks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitKeyframesBlocks(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitKeyframesBlocks(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyframesBlocksContext keyframesBlocks() throws RecognitionException {
		KeyframesBlocksContext _localctx = new KeyframesBlocksContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_keyframesBlocks);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(994);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 13510799418982400L) != 0) {
				{
				{
				setState(983);
				keyframeSelector();
				setState(984);
				match(T__4);
				setState(985);
				ws();
				setState(987);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2896940490370859138L) != 0) {
					{
					setState(986);
					declarationList();
					}
				}

				setState(989);
				match(T__5);
				setState(990);
				ws();
				}
				}
				setState(996);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyframeSelectorContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> From() { return getTokens(css3Parser.From); }
		public TerminalNode From(int i) {
			return getToken(css3Parser.From, i);
		}
		public List<TerminalNode> To() { return getTokens(css3Parser.To); }
		public TerminalNode To(int i) {
			return getToken(css3Parser.To, i);
		}
		public List<TerminalNode> Percentage() { return getTokens(css3Parser.Percentage); }
		public TerminalNode Percentage(int i) {
			return getToken(css3Parser.Percentage, i);
		}
		public List<TerminalNode> Comma() { return getTokens(css3Parser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(css3Parser.Comma, i);
		}
		public KeyframeSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyframeSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterKeyframeSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitKeyframeSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitKeyframeSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyframeSelectorContext keyframeSelector() throws RecognitionException {
		KeyframeSelectorContext _localctx = new KeyframeSelectorContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_keyframeSelector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(997);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 13510799418982400L) != 0) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(998);
			ws();
			setState(1006);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(999);
				match(Comma);
				setState(1000);
				ws();
				setState(1001);
				_la = _input.LA(1);
				if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 13510799418982400L) != 0) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1002);
				ws();
				}
				}
				setState(1008);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ViewportContext extends ParserRuleContext {
		public TerminalNode Viewport() { return getToken(css3Parser.Viewport, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public ViewportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewport; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterViewport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitViewport(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitViewport(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewportContext viewport() throws RecognitionException {
		ViewportContext _localctx = new ViewportContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_viewport);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1009);
			match(Viewport);
			setState(1010);
			ws();
			setState(1011);
			match(T__4);
			setState(1012);
			ws();
			setState(1014);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2896940490370859138L) != 0) {
				{
				setState(1013);
				declarationList();
				}
			}

			setState(1016);
			match(T__5);
			setState(1017);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CounterStyleContext extends ParserRuleContext {
		public TerminalNode CounterStyle() { return getToken(css3Parser.CounterStyle, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public CounterStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_counterStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterCounterStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitCounterStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitCounterStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CounterStyleContext counterStyle() throws RecognitionException {
		CounterStyleContext _localctx = new CounterStyleContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_counterStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1019);
			match(CounterStyle);
			setState(1020);
			ws();
			setState(1021);
			ident();
			setState(1022);
			ws();
			setState(1023);
			match(T__4);
			setState(1024);
			ws();
			setState(1026);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2896940490370859138L) != 0) {
				{
				setState(1025);
				declarationList();
				}
			}

			setState(1028);
			match(T__5);
			setState(1029);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFeatureValuesRuleContext extends ParserRuleContext {
		public TerminalNode FontFeatureValues() { return getToken(css3Parser.FontFeatureValues, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public FontFamilyNameListContext fontFamilyNameList() {
			return getRuleContext(FontFamilyNameListContext.class,0);
		}
		public List<FeatureValueBlockContext> featureValueBlock() {
			return getRuleContexts(FeatureValueBlockContext.class);
		}
		public FeatureValueBlockContext featureValueBlock(int i) {
			return getRuleContext(FeatureValueBlockContext.class,i);
		}
		public FontFeatureValuesRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFeatureValuesRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterFontFeatureValuesRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitFontFeatureValuesRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitFontFeatureValuesRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FontFeatureValuesRuleContext fontFeatureValuesRule() throws RecognitionException {
		FontFeatureValuesRuleContext _localctx = new FontFeatureValuesRuleContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_fontFeatureValuesRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1031);
			match(FontFeatureValues);
			setState(1032);
			ws();
			setState(1033);
			fontFamilyNameList();
			setState(1034);
			ws();
			setState(1035);
			match(T__4);
			setState(1036);
			ws();
			setState(1040);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__14) {
				{
				{
				setState(1037);
				featureValueBlock();
				}
				}
				setState(1042);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1043);
			match(T__5);
			setState(1044);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFamilyNameListContext extends ParserRuleContext {
		public List<FontFamilyNameContext> fontFamilyName() {
			return getRuleContexts(FontFamilyNameContext.class);
		}
		public FontFamilyNameContext fontFamilyName(int i) {
			return getRuleContext(FontFamilyNameContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(css3Parser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(css3Parser.Comma, i);
		}
		public FontFamilyNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFamilyNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterFontFamilyNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitFontFamilyNameList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitFontFamilyNameList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FontFamilyNameListContext fontFamilyNameList() throws RecognitionException {
		FontFamilyNameListContext _localctx = new FontFamilyNameListContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_fontFamilyNameList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1046);
			fontFamilyName();
			setState(1054);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,98,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1047);
					ws();
					setState(1048);
					match(Comma);
					setState(1049);
					ws();
					setState(1050);
					fontFamilyName();
					}
					} 
				}
				setState(1056);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,98,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFamilyNameContext extends ParserRuleContext {
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public FontFamilyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFamilyName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterFontFamilyName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitFontFamilyName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitFontFamilyName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FontFamilyNameContext fontFamilyName() throws RecognitionException {
		FontFamilyNameContext _localctx = new FontFamilyNameContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_fontFamilyName);
		try {
			int _alt;
			setState(1067);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String_:
				enterOuterAlt(_localctx, 1);
				{
				setState(1057);
				match(String_);
				}
				break;
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(1058);
				ident();
				setState(1064);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1059);
						ws();
						setState(1060);
						ident();
						}
						} 
					}
					setState(1066);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureValueBlockContext extends ParserRuleContext {
		public FeatureTypeContext featureType() {
			return getRuleContext(FeatureTypeContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<FeatureValueDefinitionContext> featureValueDefinition() {
			return getRuleContexts(FeatureValueDefinitionContext.class);
		}
		public FeatureValueDefinitionContext featureValueDefinition(int i) {
			return getRuleContext(FeatureValueDefinitionContext.class,i);
		}
		public FeatureValueBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_featureValueBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterFeatureValueBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitFeatureValueBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitFeatureValueBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureValueBlockContext featureValueBlock() throws RecognitionException {
		FeatureValueBlockContext _localctx = new FeatureValueBlockContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_featureValueBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1069);
			featureType();
			setState(1070);
			ws();
			setState(1071);
			match(T__4);
			setState(1072);
			ws();
			setState(1074);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2320479738067419136L) != 0) {
				{
				setState(1073);
				featureValueDefinition();
				}
			}

			setState(1084);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 196610L) != 0) {
				{
				{
				setState(1076);
				ws();
				setState(1077);
				match(T__0);
				setState(1078);
				ws();
				setState(1080);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 2320479738067419136L) != 0) {
					{
					setState(1079);
					featureValueDefinition();
					}
				}

				}
				}
				setState(1086);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1087);
			match(T__5);
			setState(1088);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureTypeContext extends ParserRuleContext {
		public AtKeywordContext atKeyword() {
			return getRuleContext(AtKeywordContext.class,0);
		}
		public FeatureTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_featureType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterFeatureType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitFeatureType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitFeatureType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureTypeContext featureType() throws RecognitionException {
		FeatureTypeContext _localctx = new FeatureTypeContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_featureType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1090);
			atKeyword();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureValueDefinitionContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public FeatureValueDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_featureValueDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterFeatureValueDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitFeatureValueDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitFeatureValueDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureValueDefinitionContext featureValueDefinition() throws RecognitionException {
		FeatureValueDefinitionContext _localctx = new FeatureValueDefinitionContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_featureValueDefinition);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1092);
			ident();
			setState(1093);
			ws();
			setState(1094);
			match(T__2);
			setState(1095);
			ws();
			setState(1096);
			number();
			setState(1102);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1097);
					ws();
					setState(1098);
					number();
					}
					} 
				}
				setState(1104);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(css3Parser.Ident, 0); }
		public TerminalNode MediaOnly() { return getToken(css3Parser.MediaOnly, 0); }
		public TerminalNode Not() { return getToken(css3Parser.Not, 0); }
		public TerminalNode And() { return getToken(css3Parser.And, 0); }
		public TerminalNode Or() { return getToken(css3Parser.Or, 0); }
		public TerminalNode From() { return getToken(css3Parser.From, 0); }
		public TerminalNode To() { return getToken(css3Parser.To, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_ident);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1105);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 2320479738067419136L) != 0) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WsContext extends ParserRuleContext {
		public List<TerminalNode> Comment() { return getTokens(css3Parser.Comment); }
		public TerminalNode Comment(int i) {
			return getToken(css3Parser.Comment, i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public WsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ws; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).enterWs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3Listener ) ((css3Listener)listener).exitWs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof css3Visitor ) return ((css3Visitor<? extends T>)visitor).visitWs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WsContext ws() throws RecognitionException {
		WsContext _localctx = new WsContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_ws);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1110);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1107);
					_la = _input.LA(1);
					if ( !(_la==Comment || _la==Space) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(1112);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001>\u045a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007E\u0002"+
		"F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007J\u0002"+
		"K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000\u00a2\b\u0000\n\u0000\f\u0000\u00a5\t\u0000\u0005"+
		"\u0000\u00a7\b\u0000\n\u0000\f\u0000\u00aa\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000\u00ae\b\u0000\n\u0000\f\u0000\u00b1\t\u0000\u0005\u0000\u00b3"+
		"\b\u0000\n\u0000\f\u0000\u00b6\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		"\u00ba\b\u0000\n\u0000\f\u0000\u00bd\t\u0000\u0005\u0000\u00bf\b\u0000"+
		"\n\u0000\f\u0000\u00c2\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000\u00c6"+
		"\b\u0000\n\u0000\f\u0000\u00c9\t\u0000\u0005\u0000\u00cb\b\u0000\n\u0000"+
		"\f\u0000\u00ce\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u00de\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002\u00fa\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0101\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u010d\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003\u0112\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006"+
		"\u0121\b\u0006\n\u0006\f\u0006\u0124\t\u0006\u0003\u0006\u0126\b\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0003\u0007\u012b\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0005\u0007\u0134\b\u0007\n\u0007\f\u0007\u0137\t\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u013e\b\u0007\n"+
		"\u0007\f\u0007\u0141\t\u0007\u0003\u0007\u0143\b\u0007\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u014e"+
		"\b\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u0159\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u015e\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u0163\b\u000b\u0005\u000b\u0165\b\u000b\n\u000b\f\u000b\u0168\t"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u0176\b\r\n\r\f\r\u0179"+
		"\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u0181\b\u000e\n\u000e\f\u000e\u0184\t\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u018e\b\u000f\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u0192\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0005\u0010\u0199\b\u0010\n\u0010\f\u0010\u019c\t\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0004\u0010\u01a3\b\u0010\u000b"+
		"\u0010\f\u0010\u01a4\u0003\u0010\u01a7\b\u0010\u0001\u0011\u0003\u0011"+
		"\u01aa\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u01b0\b\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0003\u0014\u01b7\b\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u01c1\b\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u01c9\b\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u01cd\b"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0003\u0017\u01d3"+
		"\b\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01d7\b\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u01e5\b\u0019\u0001\u0019\u0004\u0019\u01e8\b\u0019\u000b\u0019\f\u0019"+
		"\u01e9\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u01f8\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0202"+
		"\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u020d\b\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0213\b\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u0219\b\u001e\n"+
		"\u001e\f\u001e\u021c\t\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003"+
		"\u001e\u0221\b\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0226"+
		"\b\u001e\u0001\u001f\u0001\u001f\u0005\u001f\u022a\b\u001f\n\u001f\f\u001f"+
		"\u022d\t\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0003\u001f\u0234\b\u001f\u0005\u001f\u0236\b\u001f\n\u001f\f\u001f\u0239"+
		"\t\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u0240\b \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0003 \u0247\b \u0001!\u0001!\u0001!\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0004\"\u0251\b\"\u000b\"\f\"\u0252\u0001#"+
		"\u0001#\u0003#\u0257\b#\u0001#\u0005#\u025a\b#\n#\f#\u025d\t#\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0003$\u0279\b$\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001"+
		"\'\u0001\'\u0001(\u0003(\u028b\b(\u0001(\u0001(\u0001)\u0003)\u0290\b"+
		")\u0001)\u0001)\u0001*\u0003*\u0295\b*\u0001*\u0001*\u0001+\u0003+\u029a"+
		"\b+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0005,\u02bf\b,\n,\f,\u02c2\t,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0005,\u02cb\b,\n,\f,\u02ce"+
		"\t,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0005,\u02d7\b,\n"+
		",\f,\u02da\t,\u0001,\u0001,\u0001,\u0003,\u02df\b,\u0001-\u0001-\u0001"+
		"-\u0005-\u02e4\b-\n-\f-\u02e7\t-\u0001-\u0001-\u0001-\u0003-\u02ec\b-"+
		"\u0001.\u0001.\u0001.\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001"+
		"/\u0001/\u0001/\u0001/\u0003/\u02fb\b/\u00010\u00010\u00010\u00010\u0001"+
		"0\u00010\u00010\u00010\u00010\u00010\u00010\u00050\u0308\b0\n0\f0\u030b"+
		"\t0\u00010\u00010\u00010\u00011\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00031\u031a\b1\u00012\u00012\u00012\u00052\u031f"+
		"\b2\n2\f2\u0322\t2\u00012\u00012\u00012\u00013\u00013\u00013\u00013\u0001"+
		"3\u00013\u00014\u00014\u00014\u00014\u00034\u0331\b4\u00015\u00015\u0001"+
		"5\u00015\u00015\u00015\u00015\u00015\u00035\u033b\b5\u00016\u00016\u0001"+
		"6\u00016\u00016\u00016\u00017\u00017\u00017\u00017\u00017\u00017\u0001"+
		"7\u00017\u00017\u00017\u00047\u034d\b7\u000b7\f7\u034e\u00018\u00018\u0001"+
		"8\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u00048\u035b\b8\u000b"+
		"8\f8\u035c\u00019\u00019\u00019\u00019\u00019\u0001:\u0001:\u0001:\u0005"+
		":\u0367\b:\n:\f:\u036a\t:\u0001:\u0001:\u0001;\u0001;\u0001;\u0001;\u0001"+
		";\u0001;\u0001;\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0005=\u0384\b=\n="+
		"\f=\u0387\t=\u0001>\u0001>\u0001>\u0001>\u0001>\u0001>\u0001>\u0001>\u0001"+
		">\u0001>\u0005>\u0393\b>\n>\f>\u0396\t>\u0001?\u0001?\u0001?\u0001?\u0001"+
		"?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001"+
		"?\u0001?\u0001?\u0001?\u0003?\u03aa\b?\u0001@\u0001@\u0001@\u0001@\u0001"+
		"@\u0003@\u03b1\b@\u0001@\u0001@\u0001@\u0003@\u03b6\b@\u0005@\u03b8\b"+
		"@\n@\f@\u03bb\t@\u0001@\u0001@\u0001@\u0001A\u0001A\u0001A\u0001A\u0001"+
		"A\u0001A\u0001A\u0001A\u0001A\u0001A\u0003A\u03ca\bA\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"C\u0001C\u0001C\u0001C\u0003C\u03dc\bC\u0001C\u0001C\u0001C\u0005C\u03e1"+
		"\bC\nC\fC\u03e4\tC\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0005"+
		"D\u03ed\bD\nD\fD\u03f0\tD\u0001E\u0001E\u0001E\u0001E\u0001E\u0003E\u03f7"+
		"\bE\u0001E\u0001E\u0001E\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001"+
		"F\u0003F\u0403\bF\u0001F\u0001F\u0001F\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0005G\u040f\bG\nG\fG\u0412\tG\u0001G\u0001G\u0001G\u0001"+
		"H\u0001H\u0001H\u0001H\u0001H\u0001H\u0005H\u041d\bH\nH\fH\u0420\tH\u0001"+
		"I\u0001I\u0001I\u0001I\u0001I\u0005I\u0427\bI\nI\fI\u042a\tI\u0003I\u042c"+
		"\bI\u0001J\u0001J\u0001J\u0001J\u0001J\u0003J\u0433\bJ\u0001J\u0001J\u0001"+
		"J\u0001J\u0003J\u0439\bJ\u0005J\u043b\bJ\nJ\fJ\u043e\tJ\u0001J\u0001J"+
		"\u0001J\u0001K\u0001K\u0001L\u0001L\u0001L\u0001L\u0001L\u0001L\u0001"+
		"L\u0001L\u0005L\u044d\bL\nL\fL\u0450\tL\u0001M\u0001M\u0001N\u0005N\u0455"+
		"\bN\nN\fN\u0458\tN\u0001N\u0000\u0000O\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c"+
		"\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u0000\t\u0001\u0000\u0010"+
		"\u0013\u0002\u0000\u001e\u001e,,\u0001\u0000 !\u0003\u0000\u000b\u000b"+
		"\u0014\u0015-/\u0001\u0000%&\u0002\u0000\u0002\u0002>>\u0002\u0000\u001d"+
		"\u001d45\u0004\u0000 \"2245==\u0001\u0000\u0010\u0011\u04ba\u0000\u009e"+
		"\u0001\u0000\u0000\u0000\u0002\u00dd\u0001\u0000\u0000\u0000\u0004\u00f9"+
		"\u0001\u0000\u0000\u0000\u0006\u0111\u0001\u0000\u0000\u0000\b\u0113\u0001"+
		"\u0000\u0000\u0000\n\u0115\u0001\u0000\u0000\u0000\f\u0125\u0001\u0000"+
		"\u0000\u0000\u000e\u0142\u0001\u0000\u0000\u0000\u0010\u0144\u0001\u0000"+
		"\u0000\u0000\u0012\u0146\u0001\u0000\u0000\u0000\u0014\u0152\u0001\u0000"+
		"\u0000\u0000\u0016\u0155\u0001\u0000\u0000\u0000\u0018\u016c\u0001\u0000"+
		"\u0000\u0000\u001a\u0170\u0001\u0000\u0000\u0000\u001c\u017a\u0001\u0000"+
		"\u0000\u0000\u001e\u018d\u0001\u0000\u0000\u0000 \u01a6\u0001\u0000\u0000"+
		"\u0000\"\u01a9\u0001\u0000\u0000\u0000$\u01af\u0001\u0000\u0000\u0000"+
		"&\u01b3\u0001\u0000\u0000\u0000(\u01b6\u0001\u0000\u0000\u0000*\u01ba"+
		"\u0001\u0000\u0000\u0000,\u01bd\u0001\u0000\u0000\u0000.\u01d0\u0001\u0000"+
		"\u0000\u00000\u01d8\u0001\u0000\u0000\u00002\u01e7\u0001\u0000\u0000\u0000"+
		"4\u01eb\u0001\u0000\u0000\u00006\u01f7\u0001\u0000\u0000\u00008\u0201"+
		"\u0001\u0000\u0000\u0000:\u020c\u0001\u0000\u0000\u0000<\u0225\u0001\u0000"+
		"\u0000\u0000>\u022b\u0001\u0000\u0000\u0000@\u0246\u0001\u0000\u0000\u0000"+
		"B\u0248\u0001\u0000\u0000\u0000D\u0250\u0001\u0000\u0000\u0000F\u0254"+
		"\u0001\u0000\u0000\u0000H\u0278\u0001\u0000\u0000\u0000J\u027a\u0001\u0000"+
		"\u0000\u0000L\u0280\u0001\u0000\u0000\u0000N\u0286\u0001\u0000\u0000\u0000"+
		"P\u028a\u0001\u0000\u0000\u0000R\u028f\u0001\u0000\u0000\u0000T\u0294"+
		"\u0001\u0000\u0000\u0000V\u0299\u0001\u0000\u0000\u0000X\u02de\u0001\u0000"+
		"\u0000\u0000Z\u02e0\u0001\u0000\u0000\u0000\\\u02ed\u0001\u0000\u0000"+
		"\u0000^\u02fa\u0001\u0000\u0000\u0000`\u02fc\u0001\u0000\u0000\u0000b"+
		"\u0319\u0001\u0000\u0000\u0000d\u031b\u0001\u0000\u0000\u0000f\u0326\u0001"+
		"\u0000\u0000\u0000h\u0330\u0001\u0000\u0000\u0000j\u033a\u0001\u0000\u0000"+
		"\u0000l\u033c\u0001\u0000\u0000\u0000n\u0342\u0001\u0000\u0000\u0000p"+
		"\u0350\u0001\u0000\u0000\u0000r\u035e\u0001\u0000\u0000\u0000t\u0363\u0001"+
		"\u0000\u0000\u0000v\u036d\u0001\u0000\u0000\u0000x\u0374\u0001\u0000\u0000"+
		"\u0000z\u037a\u0001\u0000\u0000\u0000|\u0388\u0001\u0000\u0000\u0000~"+
		"\u03a9\u0001\u0000\u0000\u0000\u0080\u03ab\u0001\u0000\u0000\u0000\u0082"+
		"\u03c9\u0001\u0000\u0000\u0000\u0084\u03cb\u0001\u0000\u0000\u0000\u0086"+
		"\u03e2\u0001\u0000\u0000\u0000\u0088\u03e5\u0001\u0000\u0000\u0000\u008a"+
		"\u03f1\u0001\u0000\u0000\u0000\u008c\u03fb\u0001\u0000\u0000\u0000\u008e"+
		"\u0407\u0001\u0000\u0000\u0000\u0090\u0416\u0001\u0000\u0000\u0000\u0092"+
		"\u042b\u0001\u0000\u0000\u0000\u0094\u042d\u0001\u0000\u0000\u0000\u0096"+
		"\u0442\u0001\u0000\u0000\u0000\u0098\u0444\u0001\u0000\u0000\u0000\u009a"+
		"\u0451\u0001\u0000\u0000\u0000\u009c\u0456\u0001\u0000\u0000\u0000\u009e"+
		"\u00a8\u0003\u009cN\u0000\u009f\u00a3\u0003\u0002\u0001\u0000\u00a0\u00a2"+
		"\u0007\u0000\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a5"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a6\u009f\u0001\u0000\u0000\u0000\u00a7\u00aa"+
		"\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a9\u00b4\u0001\u0000\u0000\u0000\u00aa\u00a8"+
		"\u0001\u0000\u0000\u0000\u00ab\u00af\u0003\u0004\u0002\u0000\u00ac\u00ae"+
		"\u0007\u0000\u0000\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ae\u00b1"+
		"\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b0\u00b3\u0001\u0000\u0000\u0000\u00b1\u00af"+
		"\u0001\u0000\u0000\u0000\u00b2\u00ab\u0001\u0000\u0000\u0000\u00b3\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5"+
		"\u0001\u0000\u0000\u0000\u00b5\u00c0\u0001\u0000\u0000\u0000\u00b6\u00b4"+
		"\u0001\u0000\u0000\u0000\u00b7\u00bb\u0003\u0006\u0003\u0000\u00b8\u00ba"+
		"\u0007\u0000\u0000\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000\u00bd\u00bb"+
		"\u0001\u0000\u0000\u0000\u00be\u00b7\u0001\u0000\u0000\u0000\u00bf\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0001\u0000\u0000\u0000\u00c1\u00cc\u0001\u0000\u0000\u0000\u00c2\u00c0"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c7\u0003b1\u0000\u00c4\u00c6\u0007\u0000"+
		"\u0000\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c9\u0001\u0000"+
		"\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000"+
		"\u0000\u0000\u00c8\u00cb\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000"+
		"\u0000\u0000\u00ca\u00c3\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd\u00cf\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d0\u0005\u0000\u0000\u0001\u00d0\u0001\u0001\u0000"+
		"\u0000\u0000\u00d1\u00d2\u0005\u001b\u0000\u0000\u00d2\u00d3\u0003\u009c"+
		"N\u0000\u00d3\u00d4\u0005,\u0000\u0000\u00d4\u00d5\u0003\u009cN\u0000"+
		"\u00d5\u00d6\u0005\u0001\u0000\u0000\u00d6\u00d7\u0003\u009cN\u0000\u00d7"+
		"\u00de\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005\u001b\u0000\u0000\u00d9"+
		"\u00da\u0003\u009cN\u0000\u00da\u00db\u0005,\u0000\u0000\u00db\u00dc\u0003"+
		"\u009cN\u0000\u00dc\u00de\u0001\u0000\u0000\u0000\u00dd\u00d1\u0001\u0000"+
		"\u0000\u0000\u00dd\u00d8\u0001\u0000\u0000\u0000\u00de\u0003\u0001\u0000"+
		"\u0000\u0000\u00df\u00e0\u0005\u0017\u0000\u0000\u00e0\u00e1\u0003\u009c"+
		"N\u0000\u00e1\u00e2\u0007\u0001\u0000\u0000\u00e2\u00e3\u0003\u009cN\u0000"+
		"\u00e3\u00e4\u0003\f\u0006\u0000\u00e4\u00e5\u0005\u0001\u0000\u0000\u00e5"+
		"\u00e6\u0003\u009cN\u0000\u00e6\u00fa\u0001\u0000\u0000\u0000\u00e7\u00e8"+
		"\u0005\u0017\u0000\u0000\u00e8\u00e9\u0003\u009cN\u0000\u00e9\u00ea\u0007"+
		"\u0001\u0000\u0000\u00ea\u00eb\u0003\u009cN\u0000\u00eb\u00ec\u0005\u0001"+
		"\u0000\u0000\u00ec\u00ed\u0003\u009cN\u0000\u00ed\u00fa\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ef\u0005\u0017\u0000\u0000\u00ef\u00f0\u0003\u009cN\u0000"+
		"\u00f0\u00f1\u0007\u0001\u0000\u0000\u00f1\u00f2\u0003\u009cN\u0000\u00f2"+
		"\u00f3\u0003\f\u0006\u0000\u00f3\u00fa\u0001\u0000\u0000\u0000\u00f4\u00f5"+
		"\u0005\u0017\u0000\u0000\u00f5\u00f6\u0003\u009cN\u0000\u00f6\u00f7\u0007"+
		"\u0001\u0000\u0000\u00f7\u00f8\u0003\u009cN\u0000\u00f8\u00fa\u0001\u0000"+
		"\u0000\u0000\u00f9\u00df\u0001\u0000\u0000\u0000\u00f9\u00e7\u0001\u0000"+
		"\u0000\u0000\u00f9\u00ee\u0001\u0000\u0000\u0000\u00f9\u00f4\u0001\u0000"+
		"\u0000\u0000\u00fa\u0005\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005\u001a"+
		"\u0000\u0000\u00fc\u0100\u0003\u009cN\u0000\u00fd\u00fe\u0003\b\u0004"+
		"\u0000\u00fe\u00ff\u0003\u009cN\u0000\u00ff\u0101\u0001\u0000\u0000\u0000"+
		"\u0100\u00fd\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000"+
		"\u0101\u0102\u0001\u0000\u0000\u0000\u0102\u0103\u0007\u0001\u0000\u0000"+
		"\u0103\u0104\u0003\u009cN\u0000\u0104\u0105\u0005\u0001\u0000\u0000\u0105"+
		"\u0106\u0003\u009cN\u0000\u0106\u0112\u0001\u0000\u0000\u0000\u0107\u0108"+
		"\u0005\u001a\u0000\u0000\u0108\u010c\u0003\u009cN\u0000\u0109\u010a\u0003"+
		"\b\u0004\u0000\u010a\u010b\u0003\u009cN\u0000\u010b\u010d\u0001\u0000"+
		"\u0000\u0000\u010c\u0109\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000"+
		"\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010f\u0007\u0001"+
		"\u0000\u0000\u010f\u0110\u0003\u009cN\u0000\u0110\u0112\u0001\u0000\u0000"+
		"\u0000\u0111\u00fb\u0001\u0000\u0000\u0000\u0111\u0107\u0001\u0000\u0000"+
		"\u0000\u0112\u0007\u0001\u0000\u0000\u0000\u0113\u0114\u0003\u009aM\u0000"+
		"\u0114\t\u0001\u0000\u0000\u0000\u0115\u0116\u0005\u0019\u0000\u0000\u0116"+
		"\u0117\u0003\u009cN\u0000\u0117\u0118\u0003\f\u0006\u0000\u0118\u0119"+
		"\u0003d2\u0000\u0119\u011a\u0003\u009cN\u0000\u011a\u000b\u0001\u0000"+
		"\u0000\u0000\u011b\u0122\u0003\u000e\u0007\u0000\u011c\u011d\u0005(\u0000"+
		"\u0000\u011d\u011e\u0003\u009cN\u0000\u011e\u011f\u0003\u000e\u0007\u0000"+
		"\u011f\u0121\u0001\u0000\u0000\u0000\u0120\u011c\u0001\u0000\u0000\u0000"+
		"\u0121\u0124\u0001\u0000\u0000\u0000\u0122\u0120\u0001\u0000\u0000\u0000"+
		"\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0126\u0001\u0000\u0000\u0000"+
		"\u0124\u0122\u0001\u0000\u0000\u0000\u0125\u011b\u0001\u0000\u0000\u0000"+
		"\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000"+
		"\u0127\u0128\u0003\u009cN\u0000\u0128\r\u0001\u0000\u0000\u0000\u0129"+
		"\u012b\u0007\u0002\u0000\u0000\u012a\u0129\u0001\u0000\u0000\u0000\u012a"+
		"\u012b\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000\u012c"+
		"\u012d\u0003\u009cN\u0000\u012d\u012e\u0003\u0010\b\u0000\u012e\u0135"+
		"\u0003\u009cN\u0000\u012f\u0130\u0005\"\u0000\u0000\u0130\u0131\u0003"+
		"\u009cN\u0000\u0131\u0132\u0003\u0012\t\u0000\u0132\u0134\u0001\u0000"+
		"\u0000\u0000\u0133\u012f\u0001\u0000\u0000\u0000\u0134\u0137\u0001\u0000"+
		"\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000"+
		"\u0000\u0000\u0136\u0143\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000"+
		"\u0000\u0000\u0138\u013f\u0003\u0012\t\u0000\u0139\u013a\u0005\"\u0000"+
		"\u0000\u013a\u013b\u0003\u009cN\u0000\u013b\u013c\u0003\u0012\t\u0000"+
		"\u013c\u013e\u0001\u0000\u0000\u0000\u013d\u0139\u0001\u0000\u0000\u0000"+
		"\u013e\u0141\u0001\u0000\u0000\u0000\u013f\u013d\u0001\u0000\u0000\u0000"+
		"\u013f\u0140\u0001\u0000\u0000\u0000\u0140\u0143\u0001\u0000\u0000\u0000"+
		"\u0141\u013f\u0001\u0000\u0000\u0000\u0142\u012a\u0001\u0000\u0000\u0000"+
		"\u0142\u0138\u0001\u0000\u0000\u0000\u0143\u000f\u0001\u0000\u0000\u0000"+
		"\u0144\u0145\u0003\u009aM\u0000\u0145\u0011\u0001\u0000\u0000\u0000\u0146"+
		"\u0147\u0005\u0002\u0000\u0000\u0147\u0148\u0003\u009cN\u0000\u0148\u014d"+
		"\u0003\u0014\n\u0000\u0149\u014a\u0005\u0003\u0000\u0000\u014a\u014b\u0003"+
		"\u009cN\u0000\u014b\u014c\u0003F#\u0000\u014c\u014e\u0001\u0000\u0000"+
		"\u0000\u014d\u0149\u0001\u0000\u0000\u0000\u014d\u014e\u0001\u0000\u0000"+
		"\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f\u0150\u0005\u0004\u0000"+
		"\u0000\u0150\u0151\u0003\u009cN\u0000\u0151\u0013\u0001\u0000\u0000\u0000"+
		"\u0152\u0153\u0003\u009aM\u0000\u0153\u0154\u0003\u009cN\u0000\u0154\u0015"+
		"\u0001\u0000\u0000\u0000\u0155\u0156\u0005\u0018\u0000\u0000\u0156\u0158"+
		"\u0003\u009cN\u0000\u0157\u0159\u0003\u0018\f\u0000\u0158\u0157\u0001"+
		"\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u015a\u0001"+
		"\u0000\u0000\u0000\u015a\u015b\u0005\u0005\u0000\u0000\u015b\u015d\u0003"+
		"\u009cN\u0000\u015c\u015e\u0003@ \u0000\u015d\u015c\u0001\u0000\u0000"+
		"\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u0166\u0001\u0000\u0000"+
		"\u0000\u015f\u0160\u0005\u0001\u0000\u0000\u0160\u0162\u0003\u009cN\u0000"+
		"\u0161\u0163\u0003@ \u0000\u0162\u0161\u0001\u0000\u0000\u0000\u0162\u0163"+
		"\u0001\u0000\u0000\u0000\u0163\u0165\u0001\u0000\u0000\u0000\u0164\u015f"+
		"\u0001\u0000\u0000\u0000\u0165\u0168\u0001\u0000\u0000\u0000\u0166\u0164"+
		"\u0001\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167\u0169"+
		"\u0001\u0000\u0000\u0000\u0168\u0166\u0001\u0000\u0000\u0000\u0169\u016a"+
		"\u0005\u0006\u0000\u0000\u016a\u016b\u0003\u009cN\u0000\u016b\u0017\u0001"+
		"\u0000\u0000\u0000\u016c\u016d\u0005\u0003\u0000\u0000\u016d\u016e\u0003"+
		"\u009aM\u0000\u016e\u016f\u0003\u009cN\u0000\u016f\u0019\u0001\u0000\u0000"+
		"\u0000\u0170\u0177\u0003\u001c\u000e\u0000\u0171\u0172\u0005(\u0000\u0000"+
		"\u0172\u0173\u0003\u009cN\u0000\u0173\u0174\u0003\u001c\u000e\u0000\u0174"+
		"\u0176\u0001\u0000\u0000\u0000\u0175\u0171\u0001\u0000\u0000\u0000\u0176"+
		"\u0179\u0001\u0000\u0000\u0000\u0177\u0175\u0001\u0000\u0000\u0000\u0177"+
		"\u0178\u0001\u0000\u0000\u0000\u0178\u001b\u0001\u0000\u0000\u0000\u0179"+
		"\u0177\u0001\u0000\u0000\u0000\u017a\u017b\u0003 \u0010\u0000\u017b\u0182"+
		"\u0003\u009cN\u0000\u017c\u017d\u0003\u001e\u000f\u0000\u017d\u017e\u0003"+
		" \u0010\u0000\u017e\u017f\u0003\u009cN\u0000\u017f\u0181\u0001\u0000\u0000"+
		"\u0000\u0180\u017c\u0001\u0000\u0000\u0000\u0181\u0184\u0001\u0000\u0000"+
		"\u0000\u0182\u0180\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000"+
		"\u0000\u0183\u001d\u0001\u0000\u0000\u0000\u0184\u0182\u0001\u0000\u0000"+
		"\u0000\u0185\u0186\u0005%\u0000\u0000\u0186\u018e\u0003\u009cN\u0000\u0187"+
		"\u0188\u0005\'\u0000\u0000\u0188\u018e\u0003\u009cN\u0000\u0189\u018a"+
		"\u0005)\u0000\u0000\u018a\u018e\u0003\u009cN\u0000\u018b\u018c\u0005\u0011"+
		"\u0000\u0000\u018c\u018e\u0003\u009cN\u0000\u018d\u0185\u0001\u0000\u0000"+
		"\u0000\u018d\u0187\u0001\u0000\u0000\u0000\u018d\u0189\u0001\u0000\u0000"+
		"\u0000\u018d\u018b\u0001\u0000\u0000\u0000\u018e\u001f\u0001\u0000\u0000"+
		"\u0000\u018f\u0192\u0003\"\u0011\u0000\u0190\u0192\u0003(\u0014\u0000"+
		"\u0191\u018f\u0001\u0000\u0000\u0000\u0191\u0190\u0001\u0000\u0000\u0000"+
		"\u0192\u019a\u0001\u0000\u0000\u0000\u0193\u0199\u0005\u0016\u0000\u0000"+
		"\u0194\u0199\u0003*\u0015\u0000\u0195\u0199\u0003,\u0016\u0000\u0196\u0199"+
		"\u0003.\u0017\u0000\u0197\u0199\u00034\u001a\u0000\u0198\u0193\u0001\u0000"+
		"\u0000\u0000\u0198\u0194\u0001\u0000\u0000\u0000\u0198\u0195\u0001\u0000"+
		"\u0000\u0000\u0198\u0196\u0001\u0000\u0000\u0000\u0198\u0197\u0001\u0000"+
		"\u0000\u0000\u0199\u019c\u0001\u0000\u0000\u0000\u019a\u0198\u0001\u0000"+
		"\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b\u01a7\u0001\u0000"+
		"\u0000\u0000\u019c\u019a\u0001\u0000\u0000\u0000\u019d\u01a3\u0005\u0016"+
		"\u0000\u0000\u019e\u01a3\u0003*\u0015\u0000\u019f\u01a3\u0003,\u0016\u0000"+
		"\u01a0\u01a3\u0003.\u0017\u0000\u01a1\u01a3\u00034\u001a\u0000\u01a2\u019d"+
		"\u0001\u0000\u0000\u0000\u01a2\u019e\u0001\u0000\u0000\u0000\u01a2\u019f"+
		"\u0001\u0000\u0000\u0000\u01a2\u01a0\u0001\u0000\u0000\u0000\u01a2\u01a1"+
		"\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4\u01a2"+
		"\u0001\u0000\u0000\u0000\u01a4\u01a5\u0001\u0000\u0000\u0000\u01a5\u01a7"+
		"\u0001\u0000\u0000\u0000\u01a6\u0191\u0001\u0000\u0000\u0000\u01a6\u01a2"+
		"\u0001\u0000\u0000\u0000\u01a7!\u0001\u0000\u0000\u0000\u01a8\u01aa\u0003"+
		"$\u0012\u0000\u01a9\u01a8\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000"+
		"\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ac\u0003&\u0013"+
		"\u0000\u01ac#\u0001\u0000\u0000\u0000\u01ad\u01b0\u0003\u009aM\u0000\u01ae"+
		"\u01b0\u0005\u0007\u0000\u0000\u01af\u01ad\u0001\u0000\u0000\u0000\u01af"+
		"\u01ae\u0001\u0000\u0000\u0000\u01af\u01b0\u0001\u0000\u0000\u0000\u01b0"+
		"\u01b1\u0001\u0000\u0000\u0000\u01b1\u01b2\u0005\b\u0000\u0000\u01b2%"+
		"\u0001\u0000\u0000\u0000\u01b3\u01b4\u0003\u009aM\u0000\u01b4\'\u0001"+
		"\u0000\u0000\u0000\u01b5\u01b7\u0003$\u0012\u0000\u01b6\u01b5\u0001\u0000"+
		"\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001\u0000"+
		"\u0000\u0000\u01b8\u01b9\u0005\u0007\u0000\u0000\u01b9)\u0001\u0000\u0000"+
		"\u0000\u01ba\u01bb\u0005\t\u0000\u0000\u01bb\u01bc\u0003\u009aM\u0000"+
		"\u01bc+\u0001\u0000\u0000\u0000\u01bd\u01be\u0005\n\u0000\u0000\u01be"+
		"\u01c0\u0003\u009cN\u0000\u01bf\u01c1\u0003$\u0012\u0000\u01c0\u01bf\u0001"+
		"\u0000\u0000\u0000\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1\u01c2\u0001"+
		"\u0000\u0000\u0000\u01c2\u01c3\u0003\u009aM\u0000\u01c3\u01cc\u0003\u009c"+
		"N\u0000\u01c4\u01c5\u0007\u0003\u0000\u0000\u01c5\u01c8\u0003\u009cN\u0000"+
		"\u01c6\u01c9\u0003\u009aM\u0000\u01c7\u01c9\u0005,\u0000\u0000\u01c8\u01c6"+
		"\u0001\u0000\u0000\u0000\u01c8\u01c7\u0001\u0000\u0000\u0000\u01c9\u01ca"+
		"\u0001\u0000\u0000\u0000\u01ca\u01cb\u0003\u009cN\u0000\u01cb\u01cd\u0001"+
		"\u0000\u0000\u0000\u01cc\u01c4\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001"+
		"\u0000\u0000\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce\u01cf\u0005"+
		"\f\u0000\u0000\u01cf-\u0001\u0000\u0000\u0000\u01d0\u01d2\u0005\u0003"+
		"\u0000\u0000\u01d1\u01d3\u0005\u0003\u0000\u0000\u01d2\u01d1\u0001\u0000"+
		"\u0000\u0000\u01d2\u01d3\u0001\u0000\u0000\u0000\u01d3\u01d6\u0001\u0000"+
		"\u0000\u0000\u01d4\u01d7\u0003\u009aM\u0000\u01d5\u01d7\u00030\u0018\u0000"+
		"\u01d6\u01d4\u0001\u0000\u0000\u0000\u01d6\u01d5\u0001\u0000\u0000\u0000"+
		"\u01d7/\u0001\u0000\u0000\u0000\u01d8\u01d9\u0005>\u0000\u0000\u01d9\u01da"+
		"\u0003\u009cN\u0000\u01da\u01db\u00032\u0019\u0000\u01db\u01dc\u0005\u0004"+
		"\u0000\u0000\u01dc1\u0001\u0000\u0000\u0000\u01dd\u01e5\u0005%\u0000\u0000"+
		"\u01de\u01e5\u0005&\u0000\u0000\u01df\u01e5\u0005#\u0000\u0000\u01e0\u01e5"+
		"\u0005$\u0000\u0000\u01e1\u01e5\u0005+\u0000\u0000\u01e2\u01e5\u0005,"+
		"\u0000\u0000\u01e3\u01e5\u0003\u009aM\u0000\u01e4\u01dd\u0001\u0000\u0000"+
		"\u0000\u01e4\u01de\u0001\u0000\u0000\u0000\u01e4\u01df\u0001\u0000\u0000"+
		"\u0000\u01e4\u01e0\u0001\u0000\u0000\u0000\u01e4\u01e1\u0001\u0000\u0000"+
		"\u0000\u01e4\u01e2\u0001\u0000\u0000\u0000\u01e4\u01e3\u0001\u0000\u0000"+
		"\u0000\u01e5\u01e6\u0001\u0000\u0000\u0000\u01e6\u01e8\u0003\u009cN\u0000"+
		"\u01e7\u01e4\u0001\u0000\u0000\u0000\u01e8\u01e9\u0001\u0000\u0000\u0000"+
		"\u01e9\u01e7\u0001\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000\u0000"+
		"\u01ea3\u0001\u0000\u0000\u0000\u01eb\u01ec\u0005*\u0000\u0000\u01ec\u01ed"+
		"\u0003\u009cN\u0000\u01ed\u01ee\u00036\u001b\u0000\u01ee\u01ef\u0003\u009c"+
		"N\u0000\u01ef\u01f0\u0005\u0004\u0000\u0000\u01f05\u0001\u0000\u0000\u0000"+
		"\u01f1\u01f8\u0003\"\u0011\u0000\u01f2\u01f8\u0003(\u0014\u0000\u01f3"+
		"\u01f8\u0005\u0016\u0000\u0000\u01f4\u01f8\u0003*\u0015\u0000\u01f5\u01f8"+
		"\u0003,\u0016\u0000\u01f6\u01f8\u0003.\u0017\u0000\u01f7\u01f1\u0001\u0000"+
		"\u0000\u0000\u01f7\u01f2\u0001\u0000\u0000\u0000\u01f7\u01f3\u0001\u0000"+
		"\u0000\u0000\u01f7\u01f4\u0001\u0000\u0000\u0000\u01f7\u01f5\u0001\u0000"+
		"\u0000\u0000\u01f7\u01f6\u0001\u0000\u0000\u0000\u01f87\u0001\u0000\u0000"+
		"\u0000\u01f9\u01fa\u0005\r\u0000\u0000\u01fa\u0202\u0003\u009cN\u0000"+
		"\u01fb\u01fc\u0005(\u0000\u0000\u01fc\u0202\u0003\u009cN\u0000\u01fd\u01fe"+
		"\u0005\u0011\u0000\u0000\u01fe\u0202\u0003\u009cN\u0000\u01ff\u0200\u0005"+
		"\u000b\u0000\u0000\u0200\u0202\u0003\u009cN\u0000\u0201\u01f9\u0001\u0000"+
		"\u0000\u0000\u0201\u01fb\u0001\u0000\u0000\u0000\u0201\u01fd\u0001\u0000"+
		"\u0000\u0000\u0201\u01ff\u0001\u0000\u0000\u0000\u02029\u0001\u0000\u0000"+
		"\u0000\u0203\u0204\u0003\u009aM\u0000\u0204\u0205\u0003\u009cN\u0000\u0205"+
		"\u020d\u0001\u0000\u0000\u0000\u0206\u0207\u0005;\u0000\u0000\u0207\u020d"+
		"\u0003\u009cN\u0000\u0208\u0209\u0005\u0007\u0000\u0000\u0209\u020d\u0003"+
		"\u009aM\u0000\u020a\u020b\u0005\u000e\u0000\u0000\u020b\u020d\u0003\u009a"+
		"M\u0000\u020c\u0203\u0001\u0000\u0000\u0000\u020c\u0206\u0001\u0000\u0000"+
		"\u0000\u020c\u0208\u0001\u0000\u0000\u0000\u020c\u020a\u0001\u0000\u0000"+
		"\u0000\u020d;\u0001\u0000\u0000\u0000\u020e\u020f\u0003\u001a\r\u0000"+
		"\u020f\u0210\u0005\u0005\u0000\u0000\u0210\u0212\u0003\u009cN\u0000\u0211"+
		"\u0213\u0003>\u001f\u0000\u0212\u0211\u0001\u0000\u0000\u0000\u0212\u0213"+
		"\u0001\u0000\u0000\u0000\u0213\u0214\u0001\u0000\u0000\u0000\u0214\u0215"+
		"\u0005\u0006\u0000\u0000\u0215\u0216\u0003\u009cN\u0000\u0216\u0226\u0001"+
		"\u0000\u0000\u0000\u0217\u0219\u0003X,\u0000\u0218\u0217\u0001\u0000\u0000"+
		"\u0000\u0219\u021c\u0001\u0000\u0000\u0000\u021a\u0218\u0001\u0000\u0000"+
		"\u0000\u021a\u021b\u0001\u0000\u0000\u0000\u021b\u021d\u0001\u0000\u0000"+
		"\u0000\u021c\u021a\u0001\u0000\u0000\u0000\u021d\u021e\u0005\u0005\u0000"+
		"\u0000\u021e\u0220\u0003\u009cN\u0000\u021f\u0221\u0003>\u001f\u0000\u0220"+
		"\u021f\u0001\u0000\u0000\u0000\u0220\u0221\u0001\u0000\u0000\u0000\u0221"+
		"\u0222\u0001\u0000\u0000\u0000\u0222\u0223\u0005\u0006\u0000\u0000\u0223"+
		"\u0224\u0003\u009cN\u0000\u0224\u0226\u0001\u0000\u0000\u0000\u0225\u020e"+
		"\u0001\u0000\u0000\u0000\u0225\u021a\u0001\u0000\u0000\u0000\u0226=\u0001"+
		"\u0000\u0000\u0000\u0227\u0228\u0005\u0001\u0000\u0000\u0228\u022a\u0003"+
		"\u009cN\u0000\u0229\u0227\u0001\u0000\u0000\u0000\u022a\u022d\u0001\u0000"+
		"\u0000\u0000\u022b\u0229\u0001\u0000\u0000\u0000\u022b\u022c\u0001\u0000"+
		"\u0000\u0000\u022c\u022e\u0001\u0000\u0000\u0000\u022d\u022b\u0001\u0000"+
		"\u0000\u0000\u022e\u022f\u0003@ \u0000\u022f\u0237\u0003\u009cN\u0000"+
		"\u0230\u0231\u0005\u0001\u0000\u0000\u0231\u0233\u0003\u009cN\u0000\u0232"+
		"\u0234\u0003@ \u0000\u0233\u0232\u0001\u0000\u0000\u0000\u0233\u0234\u0001"+
		"\u0000\u0000\u0000\u0234\u0236\u0001\u0000\u0000\u0000\u0235\u0230\u0001"+
		"\u0000\u0000\u0000\u0236\u0239\u0001\u0000\u0000\u0000\u0237\u0235\u0001"+
		"\u0000\u0000\u0000\u0237\u0238\u0001\u0000\u0000\u0000\u0238?\u0001\u0000"+
		"\u0000\u0000\u0239\u0237\u0001\u0000\u0000\u0000\u023a\u023b\u0003:\u001d"+
		"\u0000\u023b\u023c\u0005\u0003\u0000\u0000\u023c\u023d\u0003\u009cN\u0000"+
		"\u023d\u023f\u0003F#\u0000\u023e\u0240\u0003B!\u0000\u023f\u023e\u0001"+
		"\u0000\u0000\u0000\u023f\u0240\u0001\u0000\u0000\u0000\u0240\u0247\u0001"+
		"\u0000\u0000\u0000\u0241\u0242\u0003:\u001d\u0000\u0242\u0243\u0005\u0003"+
		"\u0000\u0000\u0243\u0244\u0003\u009cN\u0000\u0244\u0245\u0003D\"\u0000"+
		"\u0245\u0247\u0001\u0000\u0000\u0000\u0246\u023a\u0001\u0000\u0000\u0000"+
		"\u0246\u0241\u0001\u0000\u0000\u0000\u0247A\u0001\u0000\u0000\u0000\u0248"+
		"\u0249\u0005\u001c\u0000\u0000\u0249\u024a\u0003\u009cN\u0000\u024aC\u0001"+
		"\u0000\u0000\u0000\u024b\u0251\u0003X,\u0000\u024c\u0251\u0003`0\u0000"+
		"\u024d\u024e\u0003\\.\u0000\u024e\u024f\u0003\u009cN\u0000\u024f\u0251"+
		"\u0001\u0000\u0000\u0000\u0250\u024b\u0001\u0000\u0000\u0000\u0250\u024c"+
		"\u0001\u0000\u0000\u0000\u0250\u024d\u0001\u0000\u0000\u0000\u0251\u0252"+
		"\u0001\u0000\u0000\u0000\u0252\u0250\u0001\u0000\u0000\u0000\u0252\u0253"+
		"\u0001\u0000\u0000\u0000\u0253E\u0001\u0000\u0000\u0000\u0254\u025b\u0003"+
		"H$\u0000\u0255\u0257\u00038\u001c\u0000\u0256\u0255\u0001\u0000\u0000"+
		"\u0000\u0256\u0257\u0001\u0000\u0000\u0000\u0257\u0258\u0001\u0000\u0000"+
		"\u0000\u0258\u025a\u0003H$\u0000\u0259\u0256\u0001\u0000\u0000\u0000\u025a"+
		"\u025d\u0001\u0000\u0000\u0000\u025b\u0259\u0001\u0000\u0000\u0000\u025b"+
		"\u025c\u0001\u0000\u0000\u0000\u025cG\u0001\u0000\u0000\u0000\u025d\u025b"+
		"\u0001\u0000\u0000\u0000\u025e\u025f\u0003P(\u0000\u025f\u0260\u0003\u009c"+
		"N\u0000\u0260\u0279\u0001\u0000\u0000\u0000\u0261\u0262\u0003R)\u0000"+
		"\u0262\u0263\u0003\u009cN\u0000\u0263\u0279\u0001\u0000\u0000\u0000\u0264"+
		"\u0265\u0003T*\u0000\u0265\u0266\u0003\u009cN\u0000\u0266\u0279\u0001"+
		"\u0000\u0000\u0000\u0267\u0268\u0005,\u0000\u0000\u0268\u0279\u0003\u009c"+
		"N\u0000\u0269\u026a\u0005\u001f\u0000\u0000\u026a\u0279\u0003\u009cN\u0000"+
		"\u026b\u026c\u0003\u009aM\u0000\u026c\u026d\u0003\u009cN\u0000\u026d\u0279"+
		"\u0001\u0000\u0000\u0000\u026e\u0279\u0003v;\u0000\u026f\u0270\u0005\u001e"+
		"\u0000\u0000\u0270\u0279\u0003\u009cN\u0000\u0271\u0279\u0003N\'\u0000"+
		"\u0272\u0279\u0003x<\u0000\u0273\u0279\u0003J%\u0000\u0274\u0275\u0003"+
		"V+\u0000\u0275\u0276\u0003\u009cN\u0000\u0276\u0279\u0001\u0000\u0000"+
		"\u0000\u0277\u0279\u0003L&\u0000\u0278\u025e\u0001\u0000\u0000\u0000\u0278"+
		"\u0261\u0001\u0000\u0000\u0000\u0278\u0264\u0001\u0000\u0000\u0000\u0278"+
		"\u0267\u0001\u0000\u0000\u0000\u0278\u0269\u0001\u0000\u0000\u0000\u0278"+
		"\u026b\u0001\u0000\u0000\u0000\u0278\u026e\u0001\u0000\u0000\u0000\u0278"+
		"\u026f\u0001\u0000\u0000\u0000\u0278\u0271\u0001\u0000\u0000\u0000\u0278"+
		"\u0272\u0001\u0000\u0000\u0000\u0278\u0273\u0001\u0000\u0000\u0000\u0278"+
		"\u0274\u0001\u0000\u0000\u0000\u0278\u0277\u0001\u0000\u0000\u0000\u0279"+
		"I\u0001\u0000\u0000\u0000\u027a\u027b\u0005>\u0000\u0000\u027b\u027c\u0003"+
		"\u009cN\u0000\u027c\u027d\u0003F#\u0000\u027d\u027e\u0005\u0004\u0000"+
		"\u0000\u027e\u027f\u0003\u009cN\u0000\u027fK\u0001\u0000\u0000\u0000\u0280"+
		"\u0281\u0005:\u0000\u0000\u0281\u0282\u0003\u009cN\u0000\u0282\u0283\u0003"+
		"F#\u0000\u0283\u0284\u0005\u0004\u0000\u0000\u0284\u0285\u0003\u009cN"+
		"\u0000\u0285M\u0001\u0000\u0000\u0000\u0286\u0287\u0005\u0016\u0000\u0000"+
		"\u0287\u0288\u0003\u009cN\u0000\u0288O\u0001\u0000\u0000\u0000\u0289\u028b"+
		"\u0007\u0004\u0000\u0000\u028a\u0289\u0001\u0000\u0000\u0000\u028a\u028b"+
		"\u0001\u0000\u0000\u0000\u028b\u028c\u0001\u0000\u0000\u0000\u028c\u028d"+
		"\u0005+\u0000\u0000\u028dQ\u0001\u0000\u0000\u0000\u028e\u0290\u0007\u0004"+
		"\u0000\u0000\u028f\u028e\u0001\u0000\u0000\u0000\u028f\u0290\u0001\u0000"+
		"\u0000\u0000\u0290\u0291\u0001\u0000\u0000\u0000\u0291\u0292\u0005\u001d"+
		"\u0000\u0000\u0292S\u0001\u0000\u0000\u0000\u0293\u0295\u0007\u0004\u0000"+
		"\u0000\u0294\u0293\u0001\u0000\u0000\u0000\u0294\u0295\u0001\u0000\u0000"+
		"\u0000\u0295\u0296\u0001\u0000\u0000\u0000\u0296\u0297\u0005#\u0000\u0000"+
		"\u0297U\u0001\u0000\u0000\u0000\u0298\u029a\u0007\u0004\u0000\u0000\u0299"+
		"\u0298\u0001\u0000\u0000\u0000\u0299\u029a\u0001\u0000\u0000\u0000\u029a"+
		"\u029b\u0001\u0000\u0000\u0000\u029b\u029c\u0005$\u0000\u0000\u029cW\u0001"+
		"\u0000\u0000\u0000\u029d\u029e\u0003\u009aM\u0000\u029e\u029f\u0003\u009c"+
		"N\u0000\u029f\u02df\u0001\u0000\u0000\u0000\u02a0\u02a1\u0003P(\u0000"+
		"\u02a1\u02a2\u0003\u009cN\u0000\u02a2\u02df\u0001\u0000\u0000\u0000\u02a3"+
		"\u02a4\u0003R)\u0000\u02a4\u02a5\u0003\u009cN\u0000\u02a5\u02df\u0001"+
		"\u0000\u0000\u0000\u02a6\u02a7\u0003T*\u0000\u02a7\u02a8\u0003\u009cN"+
		"\u0000\u02a8\u02df\u0001\u0000\u0000\u0000\u02a9\u02aa\u0003V+\u0000\u02aa"+
		"\u02ab\u0003\u009cN\u0000\u02ab\u02df\u0001\u0000\u0000\u0000\u02ac\u02ad"+
		"\u0005,\u0000\u0000\u02ad\u02df\u0003\u009cN\u0000\u02ae\u02af\u0005\u001e"+
		"\u0000\u0000\u02af\u02df\u0003\u009cN\u0000\u02b0\u02b1\u0005\u0016\u0000"+
		"\u0000\u02b1\u02df\u0003\u009cN\u0000\u02b2\u02b3\u0005\u001f\u0000\u0000"+
		"\u02b3\u02df\u0003\u009cN\u0000\u02b4\u02b5\u0005\u0014\u0000\u0000\u02b5"+
		"\u02df\u0003\u009cN\u0000\u02b6\u02b7\u0005\u0015\u0000\u0000\u02b7\u02df"+
		"\u0003\u009cN\u0000\u02b8\u02b9\u0005\u0003\u0000\u0000\u02b9\u02df\u0003"+
		"\u009cN\u0000\u02ba\u02bb\u0005>\u0000\u0000\u02bb\u02c0\u0003\u009cN"+
		"\u0000\u02bc\u02bf\u0003X,\u0000\u02bd\u02bf\u0003^/\u0000\u02be\u02bc"+
		"\u0001\u0000\u0000\u0000\u02be\u02bd\u0001\u0000\u0000\u0000\u02bf\u02c2"+
		"\u0001\u0000\u0000\u0000\u02c0\u02be\u0001\u0000\u0000\u0000\u02c0\u02c1"+
		"\u0001\u0000\u0000\u0000\u02c1\u02c3\u0001\u0000\u0000\u0000\u02c2\u02c0"+
		"\u0001\u0000\u0000\u0000\u02c3\u02c4\u0005\u0004\u0000\u0000\u02c4\u02c5"+
		"\u0003\u009cN\u0000\u02c5\u02df\u0001\u0000\u0000\u0000\u02c6\u02c7\u0005"+
		"\u0002\u0000\u0000\u02c7\u02cc\u0003\u009cN\u0000\u02c8\u02cb\u0003X,"+
		"\u0000\u02c9\u02cb\u0003^/\u0000\u02ca\u02c8\u0001\u0000\u0000\u0000\u02ca"+
		"\u02c9\u0001\u0000\u0000\u0000\u02cb\u02ce\u0001\u0000\u0000\u0000\u02cc"+
		"\u02ca\u0001\u0000\u0000\u0000\u02cc\u02cd\u0001\u0000\u0000\u0000\u02cd"+
		"\u02cf\u0001\u0000\u0000\u0000\u02ce\u02cc\u0001\u0000\u0000\u0000\u02cf"+
		"\u02d0\u0005\u0004\u0000\u0000\u02d0\u02d1\u0003\u009cN\u0000\u02d1\u02df"+
		"\u0001\u0000\u0000\u0000\u02d2\u02d3\u0005\n\u0000\u0000\u02d3\u02d8\u0003"+
		"\u009cN\u0000\u02d4\u02d7\u0003X,\u0000\u02d5\u02d7\u0003^/\u0000\u02d6"+
		"\u02d4\u0001\u0000\u0000\u0000\u02d6\u02d5\u0001\u0000\u0000\u0000\u02d7"+
		"\u02da\u0001\u0000\u0000\u0000\u02d8\u02d6\u0001\u0000\u0000\u0000\u02d8"+
		"\u02d9\u0001\u0000\u0000\u0000\u02d9\u02db\u0001\u0000\u0000\u0000\u02da"+
		"\u02d8\u0001\u0000\u0000\u0000\u02db\u02dc\u0005\f\u0000\u0000\u02dc\u02dd"+
		"\u0003\u009cN\u0000\u02dd\u02df\u0001\u0000\u0000\u0000\u02de\u029d\u0001"+
		"\u0000\u0000\u0000\u02de\u02a0\u0001\u0000\u0000\u0000\u02de\u02a3\u0001"+
		"\u0000\u0000\u0000\u02de\u02a6\u0001\u0000\u0000\u0000\u02de\u02a9\u0001"+
		"\u0000\u0000\u0000\u02de\u02ac\u0001\u0000\u0000\u0000\u02de\u02ae\u0001"+
		"\u0000\u0000\u0000\u02de\u02b0\u0001\u0000\u0000\u0000\u02de\u02b2\u0001"+
		"\u0000\u0000\u0000\u02de\u02b4\u0001\u0000\u0000\u0000\u02de\u02b6\u0001"+
		"\u0000\u0000\u0000\u02de\u02b8\u0001\u0000\u0000\u0000\u02de\u02ba\u0001"+
		"\u0000\u0000\u0000\u02de\u02c6\u0001\u0000\u0000\u0000\u02de\u02d2\u0001"+
		"\u0000\u0000\u0000\u02dfY\u0001\u0000\u0000\u0000\u02e0\u02e1\u0003\\"+
		".\u0000\u02e1\u02e5\u0003\u009cN\u0000\u02e2\u02e4\u0003X,\u0000\u02e3"+
		"\u02e2\u0001\u0000\u0000\u0000\u02e4\u02e7\u0001\u0000\u0000\u0000\u02e5"+
		"\u02e3\u0001\u0000\u0000\u0000\u02e5\u02e6\u0001\u0000\u0000\u0000\u02e6"+
		"\u02eb\u0001\u0000\u0000\u0000\u02e7\u02e5\u0001\u0000\u0000\u0000\u02e8"+
		"\u02ec\u0003`0\u0000\u02e9\u02ea\u0005\u0001\u0000\u0000\u02ea\u02ec\u0003"+
		"\u009cN\u0000\u02eb\u02e8\u0001\u0000\u0000\u0000\u02eb\u02e9\u0001\u0000"+
		"\u0000\u0000\u02ec[\u0001\u0000\u0000\u0000\u02ed\u02ee\u0005\u000f\u0000"+
		"\u0000\u02ee\u02ef\u0003\u009aM\u0000\u02ef]\u0001\u0000\u0000\u0000\u02f0"+
		"\u02fb\u0003`0\u0000\u02f1\u02f2\u0003\\.\u0000\u02f2\u02f3\u0003\u009c"+
		"N\u0000\u02f3\u02fb\u0001\u0000\u0000\u0000\u02f4\u02f5\u0005\u0001\u0000"+
		"\u0000\u02f5\u02fb\u0003\u009cN\u0000\u02f6\u02f7\u0005\u0012\u0000\u0000"+
		"\u02f7\u02fb\u0003\u009cN\u0000\u02f8\u02f9\u0005\u0013\u0000\u0000\u02f9"+
		"\u02fb\u0003\u009cN\u0000\u02fa\u02f0\u0001\u0000\u0000\u0000\u02fa\u02f1"+
		"\u0001\u0000\u0000\u0000\u02fa\u02f4\u0001\u0000\u0000\u0000\u02fa\u02f6"+
		"\u0001\u0000\u0000\u0000\u02fa\u02f8\u0001\u0000\u0000\u0000\u02fb_\u0001"+
		"\u0000\u0000\u0000\u02fc\u02fd\u0005\u0005\u0000\u0000\u02fd\u0309\u0003"+
		"\u009cN\u0000\u02fe\u0308\u0003>\u001f\u0000\u02ff\u0308\u0003b1\u0000"+
		"\u0300\u0308\u0003X,\u0000\u0301\u0308\u0003`0\u0000\u0302\u0303\u0003"+
		"\\.\u0000\u0303\u0304\u0003\u009cN\u0000\u0304\u0308\u0001\u0000\u0000"+
		"\u0000\u0305\u0306\u0005\u0001\u0000\u0000\u0306\u0308\u0003\u009cN\u0000"+
		"\u0307\u02fe\u0001\u0000\u0000\u0000\u0307\u02ff\u0001\u0000\u0000\u0000"+
		"\u0307\u0300\u0001\u0000\u0000\u0000\u0307\u0301\u0001\u0000\u0000\u0000"+
		"\u0307\u0302\u0001\u0000\u0000\u0000\u0307\u0305\u0001\u0000\u0000\u0000"+
		"\u0308\u030b\u0001\u0000\u0000\u0000\u0309\u0307\u0001\u0000\u0000\u0000"+
		"\u0309\u030a\u0001\u0000\u0000\u0000\u030a\u030c\u0001\u0000\u0000\u0000"+
		"\u030b\u0309\u0001\u0000\u0000\u0000\u030c\u030d\u0005\u0006\u0000\u0000"+
		"\u030d\u030e\u0003\u009cN\u0000\u030ea\u0001\u0000\u0000\u0000\u030f\u031a"+
		"\u0003<\u001e\u0000\u0310\u031a\u0003\n\u0005\u0000\u0311\u031a\u0003"+
		"\u0016\u000b\u0000\u0312\u031a\u0003\u0080@\u0000\u0313\u031a\u0003\u0084"+
		"B\u0000\u0314\u031a\u0003f3\u0000\u0315\u031a\u0003\u008aE\u0000\u0316"+
		"\u031a\u0003\u008cF\u0000\u0317\u031a\u0003\u008eG\u0000\u0318\u031a\u0003"+
		"Z-\u0000\u0319\u030f\u0001\u0000\u0000\u0000\u0319\u0310\u0001\u0000\u0000"+
		"\u0000\u0319\u0311\u0001\u0000\u0000\u0000\u0319\u0312\u0001\u0000\u0000"+
		"\u0000\u0319\u0313\u0001\u0000\u0000\u0000\u0319\u0314\u0001\u0000\u0000"+
		"\u0000\u0319\u0315\u0001\u0000\u0000\u0000\u0319\u0316\u0001\u0000\u0000"+
		"\u0000\u0319\u0317\u0001\u0000\u0000\u0000\u0319\u0318\u0001\u0000\u0000"+
		"\u0000\u031ac\u0001\u0000\u0000\u0000\u031b\u031c\u0005\u0005\u0000\u0000"+
		"\u031c\u0320\u0003\u009cN\u0000\u031d\u031f\u0003b1\u0000\u031e\u031d"+
		"\u0001\u0000\u0000\u0000\u031f\u0322\u0001\u0000\u0000\u0000\u0320\u031e"+
		"\u0001\u0000\u0000\u0000\u0320\u0321\u0001\u0000\u0000\u0000\u0321\u0323"+
		"\u0001\u0000\u0000\u0000\u0322\u0320\u0001\u0000\u0000\u0000\u0323\u0324"+
		"\u0005\u0006\u0000\u0000\u0324\u0325\u0003\u009cN\u0000\u0325e\u0001\u0000"+
		"\u0000\u0000\u0326\u0327\u00051\u0000\u0000\u0327\u0328\u0003\u009cN\u0000"+
		"\u0328\u0329\u0003h4\u0000\u0329\u032a\u0003\u009cN\u0000\u032a\u032b"+
		"\u0003d2\u0000\u032bg\u0001\u0000\u0000\u0000\u032c\u0331\u0003l6\u0000"+
		"\u032d\u0331\u0003n7\u0000\u032e\u0331\u0003p8\u0000\u032f\u0331\u0003"+
		"j5\u0000\u0330\u032c\u0001\u0000\u0000\u0000\u0330\u032d\u0001\u0000\u0000"+
		"\u0000\u0330\u032e\u0001\u0000\u0000\u0000\u0330\u032f\u0001\u0000\u0000"+
		"\u0000\u0331i\u0001\u0000\u0000\u0000\u0332\u0333\u0005\u0002\u0000\u0000"+
		"\u0333\u0334\u0003\u009cN\u0000\u0334\u0335\u0003h4\u0000\u0335\u0336"+
		"\u0003\u009cN\u0000\u0336\u0337\u0005\u0004\u0000\u0000\u0337\u033b\u0001"+
		"\u0000\u0000\u0000\u0338\u033b\u0003r9\u0000\u0339\u033b\u0003t:\u0000"+
		"\u033a\u0332\u0001\u0000\u0000\u0000\u033a\u0338\u0001\u0000\u0000\u0000"+
		"\u033a\u0339\u0001\u0000\u0000\u0000\u033bk\u0001\u0000\u0000\u0000\u033c"+
		"\u033d\u0005!\u0000\u0000\u033d\u033e\u0003\u009cN\u0000\u033e\u033f\u0005"+
		"\u0011\u0000\u0000\u033f\u0340\u0003\u009cN\u0000\u0340\u0341\u0003j5"+
		"\u0000\u0341m\u0001\u0000\u0000\u0000\u0342\u034c\u0003j5\u0000\u0343"+
		"\u0344\u0003\u009cN\u0000\u0344\u0345\u0005\u0011\u0000\u0000\u0345\u0346"+
		"\u0003\u009cN\u0000\u0346\u0347\u0005\"\u0000\u0000\u0347\u0348\u0003"+
		"\u009cN\u0000\u0348\u0349\u0005\u0011\u0000\u0000\u0349\u034a\u0003\u009c"+
		"N\u0000\u034a\u034b\u0003j5\u0000\u034b\u034d\u0001\u0000\u0000\u0000"+
		"\u034c\u0343\u0001\u0000\u0000\u0000\u034d\u034e\u0001\u0000\u0000\u0000"+
		"\u034e\u034c\u0001\u0000\u0000\u0000\u034e\u034f\u0001\u0000\u0000\u0000"+
		"\u034fo\u0001\u0000\u0000\u0000\u0350\u035a\u0003j5\u0000\u0351\u0352"+
		"\u0003\u009cN\u0000\u0352\u0353\u0005\u0011\u0000\u0000\u0353\u0354\u0003"+
		"\u009cN\u0000\u0354\u0355\u00052\u0000\u0000\u0355\u0356\u0003\u009cN"+
		"\u0000\u0356\u0357\u0005\u0011\u0000\u0000\u0357\u0358\u0003\u009cN\u0000"+
		"\u0358\u0359\u0003j5\u0000\u0359\u035b\u0001\u0000\u0000\u0000\u035a\u0351"+
		"\u0001\u0000\u0000\u0000\u035b\u035c\u0001\u0000\u0000\u0000\u035c\u035a"+
		"\u0001\u0000\u0000\u0000\u035c\u035d\u0001\u0000\u0000\u0000\u035dq\u0001"+
		"\u0000\u0000\u0000\u035e\u035f\u0005\u0002\u0000\u0000\u035f\u0360\u0003"+
		"\u009cN\u0000\u0360\u0361\u0003@ \u0000\u0361\u0362\u0005\u0004\u0000"+
		"\u0000\u0362s\u0001\u0000\u0000\u0000\u0363\u0368\u0007\u0005\u0000\u0000"+
		"\u0364\u0367\u0003X,\u0000\u0365\u0367\u0003^/\u0000\u0366\u0364\u0001"+
		"\u0000\u0000\u0000\u0366\u0365\u0001\u0000\u0000\u0000\u0367\u036a\u0001"+
		"\u0000\u0000\u0000\u0368\u0366\u0001\u0000\u0000\u0000\u0368\u0369\u0001"+
		"\u0000\u0000\u0000\u0369\u036b\u0001\u0000\u0000\u0000\u036a\u0368\u0001"+
		"\u0000\u0000\u0000\u036b\u036c\u0005\u0004\u0000\u0000\u036cu\u0001\u0000"+
		"\u0000\u0000\u036d\u036e\u0005<\u0000\u0000\u036e\u036f\u0003\u009cN\u0000"+
		"\u036f\u0370\u0005;\u0000\u0000\u0370\u0371\u0003\u009cN\u0000\u0371\u0372"+
		"\u0005\u0004\u0000\u0000\u0372\u0373\u0003\u009cN\u0000\u0373w\u0001\u0000"+
		"\u0000\u0000\u0374\u0375\u00056\u0000\u0000\u0375\u0376\u0003\u009cN\u0000"+
		"\u0376\u0377\u0003z=\u0000\u0377\u0378\u0005\u0004\u0000\u0000\u0378\u0379"+
		"\u0003\u009cN\u0000\u0379y\u0001\u0000\u0000\u0000\u037a\u0385\u0003|"+
		">\u0000\u037b\u037c\u0005\u0011\u0000\u0000\u037c\u037d\u0003\u009cN\u0000"+
		"\u037d\u037e\u0007\u0004\u0000\u0000\u037e\u037f\u0003\u009cN\u0000\u037f"+
		"\u0380\u0005\u0011\u0000\u0000\u0380\u0381\u0003\u009cN\u0000\u0381\u0382"+
		"\u0003|>\u0000\u0382\u0384\u0001\u0000\u0000\u0000\u0383\u037b\u0001\u0000"+
		"\u0000\u0000\u0384\u0387\u0001\u0000\u0000\u0000\u0385\u0383\u0001\u0000"+
		"\u0000\u0000\u0385\u0386\u0001\u0000\u0000\u0000\u0386{\u0001\u0000\u0000"+
		"\u0000\u0387\u0385\u0001\u0000\u0000\u0000\u0388\u0394\u0003~?\u0000\u0389"+
		"\u038a\u0005\u0007\u0000\u0000\u038a\u038b\u0003\u009cN\u0000\u038b\u038c"+
		"\u0003~?\u0000\u038c\u0393\u0001\u0000\u0000\u0000\u038d\u038e\u0005\r"+
		"\u0000\u0000\u038e\u038f\u0003\u009cN\u0000\u038f\u0390\u0003P(\u0000"+
		"\u0390\u0391\u0003\u009cN\u0000\u0391\u0393\u0001\u0000\u0000\u0000\u0392"+
		"\u0389\u0001\u0000\u0000\u0000\u0392\u038d\u0001\u0000\u0000\u0000\u0393"+
		"\u0396\u0001\u0000\u0000\u0000\u0394\u0392\u0001\u0000\u0000\u0000\u0394"+
		"\u0395\u0001\u0000\u0000\u0000\u0395}\u0001\u0000\u0000\u0000\u0396\u0394"+
		"\u0001\u0000\u0000\u0000\u0397\u0398\u0003P(\u0000\u0398\u0399\u0003\u009c"+
		"N\u0000\u0399\u03aa\u0001\u0000\u0000\u0000\u039a\u039b\u0003T*\u0000"+
		"\u039b\u039c\u0003\u009cN\u0000\u039c\u03aa\u0001\u0000\u0000\u0000\u039d"+
		"\u039e\u0003V+\u0000\u039e\u039f\u0003\u009cN\u0000\u039f\u03aa\u0001"+
		"\u0000\u0000\u0000\u03a0\u03a1\u0003R)\u0000\u03a1\u03a2\u0003\u009cN"+
		"\u0000\u03a2\u03aa\u0001\u0000\u0000\u0000\u03a3\u03a4\u0005\u0002\u0000"+
		"\u0000\u03a4\u03a5\u0003\u009cN\u0000\u03a5\u03a6\u0003z=\u0000\u03a6"+
		"\u03a7\u0005\u0004\u0000\u0000\u03a7\u03a8\u0003\u009cN\u0000\u03a8\u03aa"+
		"\u0001\u0000\u0000\u0000\u03a9\u0397\u0001\u0000\u0000\u0000\u03a9\u039a"+
		"\u0001\u0000\u0000\u0000\u03a9\u039d\u0001\u0000\u0000\u0000\u03a9\u03a0"+
		"\u0001\u0000\u0000\u0000\u03a9\u03a3\u0001\u0000\u0000\u0000\u03aa\u007f"+
		"\u0001\u0000\u0000\u0000\u03ab\u03ac\u00050\u0000\u0000\u03ac\u03ad\u0003"+
		"\u009cN\u0000\u03ad\u03ae\u0005\u0005\u0000\u0000\u03ae\u03b0\u0003\u009c"+
		"N\u0000\u03af\u03b1\u0003\u0082A\u0000\u03b0\u03af\u0001\u0000\u0000\u0000"+
		"\u03b0\u03b1\u0001\u0000\u0000\u0000\u03b1\u03b9\u0001\u0000\u0000\u0000"+
		"\u03b2\u03b3\u0005\u0001\u0000\u0000\u03b3\u03b5\u0003\u009cN\u0000\u03b4"+
		"\u03b6\u0003\u0082A\u0000\u03b5\u03b4\u0001\u0000\u0000\u0000\u03b5\u03b6"+
		"\u0001\u0000\u0000\u0000\u03b6\u03b8\u0001\u0000\u0000\u0000\u03b7\u03b2"+
		"\u0001\u0000\u0000\u0000\u03b8\u03bb\u0001\u0000\u0000\u0000\u03b9\u03b7"+
		"\u0001\u0000\u0000\u0000\u03b9\u03ba\u0001\u0000\u0000\u0000\u03ba\u03bc"+
		"\u0001\u0000\u0000\u0000\u03bb\u03b9\u0001\u0000\u0000\u0000\u03bc\u03bd"+
		"\u0005\u0006\u0000\u0000\u03bd\u03be\u0003\u009cN\u0000\u03be\u0081\u0001"+
		"\u0000\u0000\u0000\u03bf\u03c0\u0003:\u001d\u0000\u03c0\u03c1\u0005\u0003"+
		"\u0000\u0000\u03c1\u03c2\u0003\u009cN\u0000\u03c2\u03c3\u0003F#\u0000"+
		"\u03c3\u03ca\u0001\u0000\u0000\u0000\u03c4\u03c5\u0003:\u001d\u0000\u03c5"+
		"\u03c6\u0005\u0003\u0000\u0000\u03c6\u03c7\u0003\u009cN\u0000\u03c7\u03c8"+
		"\u0003D\"\u0000\u03c8\u03ca\u0001\u0000\u0000\u0000\u03c9\u03bf\u0001"+
		"\u0000\u0000\u0000\u03c9\u03c4\u0001\u0000\u0000\u0000\u03ca\u0083\u0001"+
		"\u0000\u0000\u0000\u03cb\u03cc\u00053\u0000\u0000\u03cc\u03cd\u0003\u009c"+
		"N\u0000\u03cd\u03ce\u0005\u0011\u0000\u0000\u03ce\u03cf\u0003\u009cN\u0000"+
		"\u03cf\u03d0\u0003\u009aM\u0000\u03d0\u03d1\u0003\u009cN\u0000\u03d1\u03d2"+
		"\u0005\u0005\u0000\u0000\u03d2\u03d3\u0003\u009cN\u0000\u03d3\u03d4\u0003"+
		"\u0086C\u0000\u03d4\u03d5\u0005\u0006\u0000\u0000\u03d5\u03d6\u0003\u009c"+
		"N\u0000\u03d6\u0085\u0001\u0000\u0000\u0000\u03d7\u03d8\u0003\u0088D\u0000"+
		"\u03d8\u03d9\u0005\u0005\u0000\u0000\u03d9\u03db\u0003\u009cN\u0000\u03da"+
		"\u03dc\u0003>\u001f\u0000\u03db\u03da\u0001\u0000\u0000\u0000\u03db\u03dc"+
		"\u0001\u0000\u0000\u0000\u03dc\u03dd\u0001\u0000\u0000\u0000\u03dd\u03de"+
		"\u0005\u0006\u0000\u0000\u03de\u03df\u0003\u009cN\u0000\u03df\u03e1\u0001"+
		"\u0000\u0000\u0000\u03e0\u03d7\u0001\u0000\u0000\u0000\u03e1\u03e4\u0001"+
		"\u0000\u0000\u0000\u03e2\u03e0\u0001\u0000\u0000\u0000\u03e2\u03e3\u0001"+
		"\u0000\u0000\u0000\u03e3\u0087\u0001\u0000\u0000\u0000\u03e4\u03e2\u0001"+
		"\u0000\u0000\u0000\u03e5\u03e6\u0007\u0006\u0000\u0000\u03e6\u03ee\u0003"+
		"\u009cN\u0000\u03e7\u03e8\u0005(\u0000\u0000\u03e8\u03e9\u0003\u009cN"+
		"\u0000\u03e9\u03ea\u0007\u0006\u0000\u0000\u03ea\u03eb\u0003\u009cN\u0000"+
		"\u03eb\u03ed\u0001\u0000\u0000\u0000\u03ec\u03e7\u0001\u0000\u0000\u0000"+
		"\u03ed\u03f0\u0001\u0000\u0000\u0000\u03ee\u03ec\u0001\u0000\u0000\u0000"+
		"\u03ee\u03ef\u0001\u0000\u0000\u0000\u03ef\u0089\u0001\u0000\u0000\u0000"+
		"\u03f0\u03ee\u0001\u0000\u0000\u0000\u03f1\u03f2\u00057\u0000\u0000\u03f2"+
		"\u03f3\u0003\u009cN\u0000\u03f3\u03f4\u0005\u0005\u0000\u0000\u03f4\u03f6"+
		"\u0003\u009cN\u0000\u03f5\u03f7\u0003>\u001f\u0000\u03f6\u03f5\u0001\u0000"+
		"\u0000\u0000\u03f6\u03f7\u0001\u0000\u0000\u0000\u03f7\u03f8\u0001\u0000"+
		"\u0000\u0000\u03f8\u03f9\u0005\u0006\u0000\u0000\u03f9\u03fa\u0003\u009c"+
		"N\u0000\u03fa\u008b\u0001\u0000\u0000\u0000\u03fb\u03fc\u00058\u0000\u0000"+
		"\u03fc\u03fd\u0003\u009cN\u0000\u03fd\u03fe\u0003\u009aM\u0000\u03fe\u03ff"+
		"\u0003\u009cN\u0000\u03ff\u0400\u0005\u0005\u0000\u0000\u0400\u0402\u0003"+
		"\u009cN\u0000\u0401\u0403\u0003>\u001f\u0000\u0402\u0401\u0001\u0000\u0000"+
		"\u0000\u0402\u0403\u0001\u0000\u0000\u0000\u0403\u0404\u0001\u0000\u0000"+
		"\u0000\u0404\u0405\u0005\u0006\u0000\u0000\u0405\u0406\u0003\u009cN\u0000"+
		"\u0406\u008d\u0001\u0000\u0000\u0000\u0407\u0408\u00059\u0000\u0000\u0408"+
		"\u0409\u0003\u009cN\u0000\u0409\u040a\u0003\u0090H\u0000\u040a\u040b\u0003"+
		"\u009cN\u0000\u040b\u040c\u0005\u0005\u0000\u0000\u040c\u0410\u0003\u009c"+
		"N\u0000\u040d\u040f\u0003\u0094J\u0000\u040e\u040d\u0001\u0000\u0000\u0000"+
		"\u040f\u0412\u0001\u0000\u0000\u0000\u0410\u040e\u0001\u0000\u0000\u0000"+
		"\u0410\u0411\u0001\u0000\u0000\u0000\u0411\u0413\u0001\u0000\u0000\u0000"+
		"\u0412\u0410\u0001\u0000\u0000\u0000\u0413\u0414\u0005\u0006\u0000\u0000"+
		"\u0414\u0415\u0003\u009cN\u0000\u0415\u008f\u0001\u0000\u0000\u0000\u0416"+
		"\u041e\u0003\u0092I\u0000\u0417\u0418\u0003\u009cN\u0000\u0418\u0419\u0005"+
		"(\u0000\u0000\u0419\u041a\u0003\u009cN\u0000\u041a\u041b\u0003\u0092I"+
		"\u0000\u041b\u041d\u0001\u0000\u0000\u0000\u041c\u0417\u0001\u0000\u0000"+
		"\u0000\u041d\u0420\u0001\u0000\u0000\u0000\u041e\u041c\u0001\u0000\u0000"+
		"\u0000\u041e\u041f\u0001\u0000\u0000\u0000\u041f\u0091\u0001\u0000\u0000"+
		"\u0000\u0420\u041e\u0001\u0000\u0000\u0000\u0421\u042c\u0005,\u0000\u0000"+
		"\u0422\u0428\u0003\u009aM\u0000\u0423\u0424\u0003\u009cN\u0000\u0424\u0425"+
		"\u0003\u009aM\u0000\u0425\u0427\u0001\u0000\u0000\u0000\u0426\u0423\u0001"+
		"\u0000\u0000\u0000\u0427\u042a\u0001\u0000\u0000\u0000\u0428\u0426\u0001"+
		"\u0000\u0000\u0000\u0428\u0429\u0001\u0000\u0000\u0000\u0429\u042c\u0001"+
		"\u0000\u0000\u0000\u042a\u0428\u0001\u0000\u0000\u0000\u042b\u0421\u0001"+
		"\u0000\u0000\u0000\u042b\u0422\u0001\u0000\u0000\u0000\u042c\u0093\u0001"+
		"\u0000\u0000\u0000\u042d\u042e\u0003\u0096K\u0000\u042e\u042f\u0003\u009c"+
		"N\u0000\u042f\u0430\u0005\u0005\u0000\u0000\u0430\u0432\u0003\u009cN\u0000"+
		"\u0431\u0433\u0003\u0098L\u0000\u0432\u0431\u0001\u0000\u0000\u0000\u0432"+
		"\u0433\u0001\u0000\u0000\u0000\u0433\u043c\u0001\u0000\u0000\u0000\u0434"+
		"\u0435\u0003\u009cN\u0000\u0435\u0436\u0005\u0001\u0000\u0000\u0436\u0438"+
		"\u0003\u009cN\u0000\u0437\u0439\u0003\u0098L\u0000\u0438\u0437\u0001\u0000"+
		"\u0000\u0000\u0438\u0439\u0001\u0000\u0000\u0000\u0439\u043b\u0001\u0000"+
		"\u0000\u0000\u043a\u0434\u0001\u0000\u0000\u0000\u043b\u043e\u0001\u0000"+
		"\u0000\u0000\u043c\u043a\u0001\u0000\u0000\u0000\u043c\u043d\u0001\u0000"+
		"\u0000\u0000\u043d\u043f\u0001\u0000\u0000\u0000\u043e\u043c\u0001\u0000"+
		"\u0000\u0000\u043f\u0440\u0005\u0006\u0000\u0000\u0440\u0441\u0003\u009c"+
		"N\u0000\u0441\u0095\u0001\u0000\u0000\u0000\u0442\u0443\u0003\\.\u0000"+
		"\u0443\u0097\u0001\u0000\u0000\u0000\u0444\u0445\u0003\u009aM\u0000\u0445"+
		"\u0446\u0003\u009cN\u0000\u0446\u0447\u0005\u0003\u0000\u0000\u0447\u0448"+
		"\u0003\u009cN\u0000\u0448\u044e\u0003P(\u0000\u0449\u044a\u0003\u009c"+
		"N\u0000\u044a\u044b\u0003P(\u0000\u044b\u044d\u0001\u0000\u0000\u0000"+
		"\u044c\u0449\u0001\u0000\u0000\u0000\u044d\u0450\u0001\u0000\u0000\u0000"+
		"\u044e\u044c\u0001\u0000\u0000\u0000\u044e\u044f\u0001\u0000\u0000\u0000"+
		"\u044f\u0099\u0001\u0000\u0000\u0000\u0450\u044e\u0001\u0000\u0000\u0000"+
		"\u0451\u0452\u0007\u0007\u0000\u0000\u0452\u009b\u0001\u0000\u0000\u0000"+
		"\u0453\u0455\u0007\b\u0000\u0000\u0454\u0453\u0001\u0000\u0000\u0000\u0455"+
		"\u0458\u0001\u0000\u0000\u0000\u0456\u0454\u0001\u0000\u0000\u0000\u0456"+
		"\u0457\u0001\u0000\u0000\u0000\u0457\u009d\u0001\u0000\u0000\u0000\u0458"+
		"\u0456\u0001\u0000\u0000\u0000j\u00a3\u00a8\u00af\u00b4\u00bb\u00c0\u00c7"+
		"\u00cc\u00dd\u00f9\u0100\u010c\u0111\u0122\u0125\u012a\u0135\u013f\u0142"+
		"\u014d\u0158\u015d\u0162\u0166\u0177\u0182\u018d\u0191\u0198\u019a\u01a2"+
		"\u01a4\u01a6\u01a9\u01af\u01b6\u01c0\u01c8\u01cc\u01d2\u01d6\u01e4\u01e9"+
		"\u01f7\u0201\u020c\u0212\u021a\u0220\u0225\u022b\u0233\u0237\u023f\u0246"+
		"\u0250\u0252\u0256\u025b\u0278\u028a\u028f\u0294\u0299\u02be\u02c0\u02ca"+
		"\u02cc\u02d6\u02d8\u02de\u02e5\u02eb\u02fa\u0307\u0309\u0319\u0320\u0330"+
		"\u033a\u034e\u035c\u0366\u0368\u0385\u0392\u0394\u03a9\u03b0\u03b5\u03b9"+
		"\u03c9\u03db\u03e2\u03ee\u03f6\u0402\u0410\u041e\u0428\u042b\u0432\u0438"+
		"\u043c\u044e\u0456";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}