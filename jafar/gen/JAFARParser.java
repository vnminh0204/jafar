// Generated from C:/Users/Minh/Desktop/Maven Final Project/jafar/src/main/antlr4/finalproject/grammar\JAFAR.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JAFARParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, BEGIN=2, BOOLEAN=3, INTEGER=4, ELSE=5, END=6, EXIT=7, FALSE=8, 
		FUNC=9, IF=10, INPUT=11, NOT=12, OR=13, PRINT=14, PROC=15, PROGRAM=16, 
		TRUE=17, WHILE=18, THREAD=19, PARBEGIN=20, PAREND=21, LOCK=22, UNLOCK=23, 
		ASS=24, COLON=25, COMMA=26, DQUOTE=27, EQ=28, GE=29, GT=30, LE=31, LBRACE=32, 
		LPAR=33, LT=34, MINUS=35, NE=36, PLUS=37, RBRACE=38, RPAR=39, SEMI=40, 
		SLASH=41, STAR=42, EXPLNMARK=43, ID=44, NUM=45, STR=46, COMMENT=47, WS=48;
	public static final int
		RULE_program = 0, RULE_body = 1, RULE_decl = 2, RULE_var = 3, RULE_block = 4, 
		RULE_stat = 5, RULE_target = 6, RULE_expr = 7, RULE_prfOp = 8, RULE_multOp = 9, 
		RULE_plusOp = 10, RULE_boolOp = 11, RULE_compOp = 12, RULE_type = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "body", "decl", "var", "block", "stat", "target", "expr", 
			"prfOp", "multOp", "plusOp", "boolOp", "compOp", "type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"':='", "':'", "','", "'\"'", "'=='", "'>='", "'>'", "'<='", "'{'", "'('", 
			"'<'", "'-'", "'!='", "'+'", "'}'", "')'", "';'", "'/'", "'*'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "BEGIN", "BOOLEAN", "INTEGER", "ELSE", "END", "EXIT", "FALSE", 
			"FUNC", "IF", "INPUT", "NOT", "OR", "PRINT", "PROC", "PROGRAM", "TRUE", 
			"WHILE", "THREAD", "PARBEGIN", "PAREND", "LOCK", "UNLOCK", "ASS", "COLON", 
			"COMMA", "DQUOTE", "EQ", "GE", "GT", "LE", "LBRACE", "LPAR", "LT", "MINUS", 
			"NE", "PLUS", "RBRACE", "RPAR", "SEMI", "SLASH", "STAR", "EXPLNMARK", 
			"ID", "NUM", "STR", "COMMENT", "WS"
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
	public String getGrammarFileName() { return "JAFAR.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JAFARParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode PROGRAM() { return getToken(JAFARParser.PROGRAM, 0); }
		public TerminalNode ID() { return getToken(JAFARParser.ID, 0); }
		public TerminalNode SEMI() { return getToken(JAFARParser.SEMI, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode EOF() { return getToken(JAFARParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(PROGRAM);
			setState(29);
			match(ID);
			setState(30);
			match(SEMI);
			setState(31);
			body();
			setState(32);
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

	public static class BodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BOOLEAN || _la==INTEGER) {
				{
				{
				setState(34);
				decl();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(40);
			block();
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

	public static class DeclContext extends ParserRuleContext {
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	 
		public DeclContext() { }
		public void copyFrom(DeclContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDeclContext extends DeclContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(JAFARParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(JAFARParser.SEMI, i);
		}
		public VarDeclContext(DeclContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl);
		try {
			int _alt;
			_localctx = new VarDeclContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(45); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(42);
					var();
					setState(43);
					match(SEMI);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(47); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class VarContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(JAFARParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(JAFARParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JAFARParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JAFARParser.COMMA, i);
		}
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			type();
			setState(50);
			match(ID);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(51);
				match(COMMA);
				setState(52);
				match(ID);
				}
				}
				setState(57);
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(JAFARParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(JAFARParser.END, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(BEGIN);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BOOLEAN || _la==INTEGER) {
				{
				{
				setState(59);
				decl();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				stat();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BEGIN) | (1L << IF) | (1L << WHILE) | (1L << PARBEGIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << ID))) != 0) );
			setState(70);
			match(END);
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

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssStatContext extends StatContext {
		public TargetContext target() {
			return getRuleContext(TargetContext.class,0);
		}
		public TerminalNode ASS() { return getToken(JAFARParser.ASS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(JAFARParser.SEMI, 0); }
		public AssStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterAssStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitAssStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitAssStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStatContext extends StatContext {
		public TerminalNode IF() { return getToken(JAFARParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(JAFARParser.COLON, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(JAFARParser.ELSE, 0); }
		public IfStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterIfStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitIfStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitIfStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThreadStatContext extends StatContext {
		public TerminalNode PARBEGIN() { return getToken(JAFARParser.PARBEGIN, 0); }
		public TerminalNode COLON() { return getToken(JAFARParser.COLON, 0); }
		public TerminalNode PAREND() { return getToken(JAFARParser.PAREND, 0); }
		public TerminalNode SEMI() { return getToken(JAFARParser.SEMI, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public ThreadStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterThreadStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitThreadStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitThreadStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStatContext extends StatContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterBlockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitBlockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitBlockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LockStatContext extends StatContext {
		public TerminalNode LOCK() { return getToken(JAFARParser.LOCK, 0); }
		public TerminalNode LPAR() { return getToken(JAFARParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(JAFARParser.RPAR, 0); }
		public TerminalNode SEMI() { return getToken(JAFARParser.SEMI, 0); }
		public LockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterLockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitLockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitLockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnlockStatContext extends StatContext {
		public TerminalNode UNLOCK() { return getToken(JAFARParser.UNLOCK, 0); }
		public TerminalNode LPAR() { return getToken(JAFARParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(JAFARParser.RPAR, 0); }
		public TerminalNode SEMI() { return getToken(JAFARParser.SEMI, 0); }
		public UnlockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterUnlockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitUnlockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitUnlockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStatContext extends StatContext {
		public TerminalNode WHILE() { return getToken(JAFARParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(JAFARParser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterWhileStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitWhileStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stat);
		int _la;
		try {
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new AssStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				target();
				setState(73);
				match(ASS);
				setState(74);
				expr(0);
				setState(75);
				match(SEMI);
				}
				break;
			case IF:
				_localctx = new IfStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				match(IF);
				setState(78);
				expr(0);
				setState(79);
				match(COLON);
				setState(80);
				block();
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(81);
					match(ELSE);
					setState(82);
					block();
					}
				}

				}
				break;
			case WHILE:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				match(WHILE);
				setState(86);
				expr(0);
				setState(87);
				match(COLON);
				setState(88);
				block();
				}
				break;
			case BEGIN:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				block();
				}
				break;
			case PARBEGIN:
				_localctx = new ThreadStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(91);
				match(PARBEGIN);
				setState(92);
				match(COLON);
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(93);
					block();
					}
					}
					setState(96); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BEGIN );
				setState(98);
				match(PAREND);
				setState(99);
				match(SEMI);
				}
				break;
			case LOCK:
				_localctx = new LockStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(101);
				match(LOCK);
				setState(102);
				match(LPAR);
				setState(103);
				match(RPAR);
				setState(104);
				match(SEMI);
				}
				break;
			case UNLOCK:
				_localctx = new UnlockStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(105);
				match(UNLOCK);
				setState(106);
				match(LPAR);
				setState(107);
				match(RPAR);
				setState(108);
				match(SEMI);
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

	public static class TargetContext extends ParserRuleContext {
		public TargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_target; }
	 
		public TargetContext() { }
		public void copyFrom(TargetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdTargetContext extends TargetContext {
		public TerminalNode ID() { return getToken(JAFARParser.ID, 0); }
		public IdTargetContext(TargetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterIdTarget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitIdTarget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitIdTarget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TargetContext target() throws RecognitionException {
		TargetContext _localctx = new TargetContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_target);
		try {
			_localctx = new IdTargetContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(ID);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParExprContext extends ExprContext {
		public TerminalNode LPAR() { return getToken(JAFARParser.LPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(JAFARParser.RPAR, 0); }
		public ParExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterParExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitParExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitParExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TrueExprContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(JAFARParser.TRUE, 0); }
		public TrueExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterTrueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitTrueExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitTrueExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CompOpContext compOp() {
			return getRuleContext(CompOpContext.class,0);
		}
		public CompExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterCompExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitCompExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitCompExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrfExprContext extends ExprContext {
		public PrfOpContext prfOp() {
			return getRuleContext(PrfOpContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrfExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterPrfExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitPrfExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitPrfExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FalseExprContext extends ExprContext {
		public TerminalNode FALSE() { return getToken(JAFARParser.FALSE, 0); }
		public FalseExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterFalseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitFalseExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitFalseExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BoolOpContext boolOp() {
			return getRuleContext(BoolOpContext.class,0);
		}
		public BoolExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterBoolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitBoolExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitBoolExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MultOpContext multOp() {
			return getRuleContext(MultOpContext.class,0);
		}
		public MultExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitMultExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitMultExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumExprContext extends ExprContext {
		public TerminalNode NUM() { return getToken(JAFARParser.NUM, 0); }
		public NumExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterNumExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitNumExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitNumExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PlusOpContext plusOp() {
			return getRuleContext(PlusOpContext.class,0);
		}
		public PlusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterPlusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitPlusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitPlusExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(JAFARParser.ID, 0); }
		public IdExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterIdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitIdExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitIdExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case MINUS:
				{
				_localctx = new PrfExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(114);
				prfOp();
				setState(115);
				expr(10);
				}
				break;
			case LPAR:
				{
				_localctx = new ParExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(117);
				match(LPAR);
				setState(118);
				expr(0);
				setState(119);
				match(RPAR);
				}
				break;
			case ID:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(ID);
				}
				break;
			case NUM:
				{
				_localctx = new NumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				match(NUM);
				}
				break;
			case TRUE:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(123);
				match(TRUE);
				}
				break;
			case FALSE:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124);
				match(FALSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(145);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(143);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new MultExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(127);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(128);
						multOp();
						setState(129);
						expr(10);
						}
						break;
					case 2:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(131);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(132);
						plusOp();
						setState(133);
						expr(9);
						}
						break;
					case 3:
						{
						_localctx = new CompExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(135);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(136);
						compOp();
						setState(137);
						expr(8);
						}
						break;
					case 4:
						{
						_localctx = new BoolExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(139);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(140);
						boolOp();
						setState(141);
						expr(7);
						}
						break;
					}
					} 
				}
				setState(147);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrfOpContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(JAFARParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(JAFARParser.NOT, 0); }
		public PrfOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prfOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterPrfOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitPrfOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitPrfOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrfOpContext prfOp() throws RecognitionException {
		PrfOpContext _localctx = new PrfOpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_prfOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_la = _input.LA(1);
			if ( !(_la==NOT || _la==MINUS) ) {
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

	public static class MultOpContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(JAFARParser.STAR, 0); }
		public MultOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterMultOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitMultOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitMultOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultOpContext multOp() throws RecognitionException {
		MultOpContext _localctx = new MultOpContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_multOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(STAR);
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

	public static class PlusOpContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(JAFARParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(JAFARParser.MINUS, 0); }
		public PlusOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plusOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterPlusOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitPlusOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitPlusOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusOpContext plusOp() throws RecognitionException {
		PlusOpContext _localctx = new PlusOpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_plusOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_la = _input.LA(1);
			if ( !(_la==MINUS || _la==PLUS) ) {
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

	public static class BoolOpContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(JAFARParser.AND, 0); }
		public TerminalNode OR() { return getToken(JAFARParser.OR, 0); }
		public BoolOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterBoolOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitBoolOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitBoolOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolOpContext boolOp() throws RecognitionException {
		BoolOpContext _localctx = new BoolOpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
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

	public static class CompOpContext extends ParserRuleContext {
		public TerminalNode LE() { return getToken(JAFARParser.LE, 0); }
		public TerminalNode LT() { return getToken(JAFARParser.LT, 0); }
		public TerminalNode GE() { return getToken(JAFARParser.GE, 0); }
		public TerminalNode GT() { return getToken(JAFARParser.GT, 0); }
		public TerminalNode EQ() { return getToken(JAFARParser.EQ, 0); }
		public TerminalNode NE() { return getToken(JAFARParser.NE, 0); }
		public CompOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterCompOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitCompOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitCompOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompOpContext compOp() throws RecognitionException {
		CompOpContext _localctx = new CompOpContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_compOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << GE) | (1L << GT) | (1L << LE) | (1L << LT) | (1L << NE))) != 0)) ) {
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntTypeContext extends TypeContext {
		public TerminalNode INTEGER() { return getToken(JAFARParser.INTEGER, 0); }
		public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterIntType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitIntType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitIntType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolTypeContext extends TypeContext {
		public TerminalNode BOOLEAN() { return getToken(JAFARParser.BOOLEAN, 0); }
		public BoolTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).enterBoolType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JAFARListener ) ((JAFARListener)listener).exitBoolType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JAFARVisitor ) return ((JAFARVisitor<? extends T>)visitor).visitBoolType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_type);
		try {
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				_localctx = new IntTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(INTEGER);
				}
				break;
			case BOOLEAN:
				_localctx = new BoolTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(BOOLEAN);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00010\u00a3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0005\u0001$\b\u0001\n\u0001\f\u0001"+
		"\'\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0004\u0002.\b\u0002\u000b\u0002\f\u0002/\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0005\u00036\b\u0003\n\u0003\f\u00039\t\u0003\u0001"+
		"\u0004\u0001\u0004\u0005\u0004=\b\u0004\n\u0004\f\u0004@\t\u0004\u0001"+
		"\u0004\u0004\u0004C\b\u0004\u000b\u0004\f\u0004D\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"T\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005_\b\u0005"+
		"\u000b\u0005\f\u0005`\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005n\b\u0005\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"~\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007"+
		"\u0090\b\u0007\n\u0007\f\u0007\u0093\t\u0007\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0003\r\u00a1\b\r\u0001\r\u0000\u0001\u000e\u000e\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0004\u0002"+
		"\u0000\f\f##\u0002\u0000##%%\u0002\u0000\u0001\u0001\r\r\u0003\u0000\u001c"+
		"\u001f\"\"$$\u00ab\u0000\u001c\u0001\u0000\u0000\u0000\u0002%\u0001\u0000"+
		"\u0000\u0000\u0004-\u0001\u0000\u0000\u0000\u00061\u0001\u0000\u0000\u0000"+
		"\b:\u0001\u0000\u0000\u0000\nm\u0001\u0000\u0000\u0000\fo\u0001\u0000"+
		"\u0000\u0000\u000e}\u0001\u0000\u0000\u0000\u0010\u0094\u0001\u0000\u0000"+
		"\u0000\u0012\u0096\u0001\u0000\u0000\u0000\u0014\u0098\u0001\u0000\u0000"+
		"\u0000\u0016\u009a\u0001\u0000\u0000\u0000\u0018\u009c\u0001\u0000\u0000"+
		"\u0000\u001a\u00a0\u0001\u0000\u0000\u0000\u001c\u001d\u0005\u0010\u0000"+
		"\u0000\u001d\u001e\u0005,\u0000\u0000\u001e\u001f\u0005(\u0000\u0000\u001f"+
		" \u0003\u0002\u0001\u0000 !\u0005\u0000\u0000\u0001!\u0001\u0001\u0000"+
		"\u0000\u0000\"$\u0003\u0004\u0002\u0000#\"\u0001\u0000\u0000\u0000$\'"+
		"\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000"+
		"\u0000&(\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000()\u0003\b"+
		"\u0004\u0000)\u0003\u0001\u0000\u0000\u0000*+\u0003\u0006\u0003\u0000"+
		"+,\u0005(\u0000\u0000,.\u0001\u0000\u0000\u0000-*\u0001\u0000\u0000\u0000"+
		"./\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000"+
		"\u00000\u0005\u0001\u0000\u0000\u000012\u0003\u001a\r\u000027\u0005,\u0000"+
		"\u000034\u0005\u001a\u0000\u000046\u0005,\u0000\u000053\u0001\u0000\u0000"+
		"\u000069\u0001\u0000\u0000\u000075\u0001\u0000\u0000\u000078\u0001\u0000"+
		"\u0000\u00008\u0007\u0001\u0000\u0000\u000097\u0001\u0000\u0000\u0000"+
		":>\u0005\u0002\u0000\u0000;=\u0003\u0004\u0002\u0000<;\u0001\u0000\u0000"+
		"\u0000=@\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000"+
		"\u0000\u0000?B\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000AC\u0003"+
		"\n\u0005\u0000BA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DB\u0001"+
		"\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000"+
		"FG\u0005\u0006\u0000\u0000G\t\u0001\u0000\u0000\u0000HI\u0003\f\u0006"+
		"\u0000IJ\u0005\u0018\u0000\u0000JK\u0003\u000e\u0007\u0000KL\u0005(\u0000"+
		"\u0000Ln\u0001\u0000\u0000\u0000MN\u0005\n\u0000\u0000NO\u0003\u000e\u0007"+
		"\u0000OP\u0005\u0019\u0000\u0000PS\u0003\b\u0004\u0000QR\u0005\u0005\u0000"+
		"\u0000RT\u0003\b\u0004\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000"+
		"\u0000Tn\u0001\u0000\u0000\u0000UV\u0005\u0012\u0000\u0000VW\u0003\u000e"+
		"\u0007\u0000WX\u0005\u0019\u0000\u0000XY\u0003\b\u0004\u0000Yn\u0001\u0000"+
		"\u0000\u0000Zn\u0003\b\u0004\u0000[\\\u0005\u0014\u0000\u0000\\^\u0005"+
		"\u0019\u0000\u0000]_\u0003\b\u0004\u0000^]\u0001\u0000\u0000\u0000_`\u0001"+
		"\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000"+
		"ab\u0001\u0000\u0000\u0000bc\u0005\u0015\u0000\u0000cd\u0005(\u0000\u0000"+
		"dn\u0001\u0000\u0000\u0000ef\u0005\u0016\u0000\u0000fg\u0005!\u0000\u0000"+
		"gh\u0005\'\u0000\u0000hn\u0005(\u0000\u0000ij\u0005\u0017\u0000\u0000"+
		"jk\u0005!\u0000\u0000kl\u0005\'\u0000\u0000ln\u0005(\u0000\u0000mH\u0001"+
		"\u0000\u0000\u0000mM\u0001\u0000\u0000\u0000mU\u0001\u0000\u0000\u0000"+
		"mZ\u0001\u0000\u0000\u0000m[\u0001\u0000\u0000\u0000me\u0001\u0000\u0000"+
		"\u0000mi\u0001\u0000\u0000\u0000n\u000b\u0001\u0000\u0000\u0000op\u0005"+
		",\u0000\u0000p\r\u0001\u0000\u0000\u0000qr\u0006\u0007\uffff\uffff\u0000"+
		"rs\u0003\u0010\b\u0000st\u0003\u000e\u0007\nt~\u0001\u0000\u0000\u0000"+
		"uv\u0005!\u0000\u0000vw\u0003\u000e\u0007\u0000wx\u0005\'\u0000\u0000"+
		"x~\u0001\u0000\u0000\u0000y~\u0005,\u0000\u0000z~\u0005-\u0000\u0000{"+
		"~\u0005\u0011\u0000\u0000|~\u0005\b\u0000\u0000}q\u0001\u0000\u0000\u0000"+
		"}u\u0001\u0000\u0000\u0000}y\u0001\u0000\u0000\u0000}z\u0001\u0000\u0000"+
		"\u0000}{\u0001\u0000\u0000\u0000}|\u0001\u0000\u0000\u0000~\u0091\u0001"+
		"\u0000\u0000\u0000\u007f\u0080\n\t\u0000\u0000\u0080\u0081\u0003\u0012"+
		"\t\u0000\u0081\u0082\u0003\u000e\u0007\n\u0082\u0090\u0001\u0000\u0000"+
		"\u0000\u0083\u0084\n\b\u0000\u0000\u0084\u0085\u0003\u0014\n\u0000\u0085"+
		"\u0086\u0003\u000e\u0007\t\u0086\u0090\u0001\u0000\u0000\u0000\u0087\u0088"+
		"\n\u0007\u0000\u0000\u0088\u0089\u0003\u0018\f\u0000\u0089\u008a\u0003"+
		"\u000e\u0007\b\u008a\u0090\u0001\u0000\u0000\u0000\u008b\u008c\n\u0006"+
		"\u0000\u0000\u008c\u008d\u0003\u0016\u000b\u0000\u008d\u008e\u0003\u000e"+
		"\u0007\u0007\u008e\u0090\u0001\u0000\u0000\u0000\u008f\u007f\u0001\u0000"+
		"\u0000\u0000\u008f\u0083\u0001\u0000\u0000\u0000\u008f\u0087\u0001\u0000"+
		"\u0000\u0000\u008f\u008b\u0001\u0000\u0000\u0000\u0090\u0093\u0001\u0000"+
		"\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000"+
		"\u0000\u0000\u0092\u000f\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0007\u0000\u0000\u0000\u0095\u0011\u0001\u0000"+
		"\u0000\u0000\u0096\u0097\u0005*\u0000\u0000\u0097\u0013\u0001\u0000\u0000"+
		"\u0000\u0098\u0099\u0007\u0001\u0000\u0000\u0099\u0015\u0001\u0000\u0000"+
		"\u0000\u009a\u009b\u0007\u0002\u0000\u0000\u009b\u0017\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0007\u0003\u0000\u0000\u009d\u0019\u0001\u0000\u0000"+
		"\u0000\u009e\u00a1\u0005\u0004\u0000\u0000\u009f\u00a1\u0005\u0003\u0000"+
		"\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u009f\u0001\u0000\u0000"+
		"\u0000\u00a1\u001b\u0001\u0000\u0000\u0000\f%/7>DS`m}\u008f\u0091\u00a0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}