grammar JAFAR;

/** JAFAR program. */
program
    : PROGRAM ID SEMI body EOF // program basic; int x; {something}
    ;

/** Body of a program. */
body
    : (sharedDecl|decl)* func* block // int x,a; share bool y; func add(int g,h):void{return;} {}
    ;

/** Variable declaration block. */
decl: (var SEMI)+                  #varDecl //int x,y; bool check;
    ;

sharedDecl: SHARED type ID (COMMA ID)* SEMI //shared int x,y; shared bool[1][2] check;
    ;
/** Function declaration. */
func: FUNC ID LPAR (params)? RPAR COLON type BEGIN (decl)* (stat)* RETURN (expr)? SEMI END #funcDecl // func add(int x,y):int {return (x+y);}
    ;                                                                                             // fun justPrint():void {print(x); return;}

params: var (SEMI var)* #paramsDecl
      ;

/** Variable declaration. */
var : type ID (COMMA ID)* //int x,y
    ;

/** Grouped sequence of statements. */
block
    : BEGIN (decl)* (stat)+ END //{int a,x,b; a:=b; x:= x+1;}
    ;

threadBlock
    : block
    ;
/** Statement. */
stat: target ASS expr SEMI                  #assStat // x:= 1;
    | IF expr COLON block (ELSE block)?     #ifStat // if (a == b): {thenpart} else {elsepart}
    | WHILE expr COLON block                #whileStat // while (a == b): {}
    | block                                 #blockStat //{@block content}
    | PARBEGIN COLON (threadBlock)+ PAREND SEMI      #threadStat // parbegin: {@thread1block} {@thread2block} parend;
    | LOCK LPAR RPAR SEMI           #lockStat // lock();
    | UNLOCK LPAR RPAR SEMI         #unlockStat // unlock();
    | PRINT LPAR expr RPAR SEMI             #outStat // print(x);
    | ID LPAR (expr (COMMA expr)*)? RPAR SEMI #funcStat // funcname(x,1,True);
    ;

/** Target of an assignment. */
target
    : ID               #idTarget // a,x
    | arrayID (LSQ expr RSQ)+ #arrayTarget // a[0][1] := x;
    ;

/** Expression. */
expr: prfOp expr        #prfExpr // (-1) (!x)
    | expr multOp expr  #multExpr // a/b, a*b, a mod b
    | expr plusOp expr  #plusExpr // a+b, a-b
    | expr compOp expr  #compExpr // a<=b, a<b, a==b, a>b, a>=b, [1,2,3] == x[i]
    | expr boolOp expr  #boolExpr // a and b, a or b
    | LPAR expr RPAR    #parExpr // (1+2)
    | ID                #idExpr
    | NUM               #numExpr
    | TRUE              #trueExpr
    | FALSE             #falseExpr
    | LSQ (expr (COMMA expr)*)? RSQ  #arrayExpr // [1,2,3] // [[1,2],[2,3]]
    | arrayID (LSQ expr RSQ)+   #indexExpr // a[1][2][3][4] given int [5][5][5][5]
    | ID LPAR (expr (COMMA expr)*)? RPAR #funcExpr // sum(12,x);
    ;

arrayID: ID;

/** Prefix operator. */
prfOp: MINUS | NOT;

/** Multiplicative operator. */
multOp: STAR | SLASH | MOD;

/** Additive operator. */
plusOp: PLUS | MINUS;

/** Boolean operator. */
boolOp: AND | OR;

/** Comparison operator. */
compOp: LE | LT | GE | GT | EQ | NE;

/** Data type. */
type: INTEGER (LSQ NUM RSQ)+    #integerArrayType // int [4][2]
    | BOOLEAN (LSQ NUM RSQ)+    #booleanArrayType // bool [2][3]
    | INTEGER  #intType
    | BOOLEAN  #boolType
    | VOID     #voidType
    ;
// Keywords

AND:     A N D;
BEGIN:   LBRACE;
BOOLEAN: B O O L;
INTEGER: I N T;
VOID: V O I D;
SHARED: S H A R E D;
ELSE:    E L S E ;
END:     RBRACE;
EXIT:    E X I T ;
FALSE:   F A L S E ;
FUNC:    F U N C;
RETURN:  R E T U R N;
IF:      I F ;
INPUT:   I N P U T;
NOT:     EXPLNMARK;
OR:      O R ;
PRINT:   P R I N T ;
PROC:    P R O C E D U R E ;
PROGRAM: P R O G R A M ;
TRUE:    T R U E ;
WHILE:   W H I L E ;
THREAD:  T H R E A D ;
PARBEGIN:P A R B E G I N;
PAREND:  P A R E N D;
LOCK: L O C K;
UNLOCK: U N L O C K;
MOD: M O D;
ASS:    ':=';
COLON:  ':';
COMMA:  ',';
DQUOTE: '"';
EQ:     '==';
GE:     '>=';
GT:     '>';
LE:     '<=';
LBRACE: '{';
LPAR:   '(';
LT:     '<';
MINUS:  '-';
NE:     '!=';
PLUS:   '+';
RBRACE: '}';
RPAR:   ')';
SEMI:   ';';
SLASH:  '/';
STAR:   '*';
LSQ:    '[';
RSQ:    ']';
EXPLNMARK: '!';

// Content-bearing token types
ID: LETTER (LETTER | DIGIT)*;
NUM: DIGIT (DIGIT)*;
STR: DQUOTE .*? DQUOTE;

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];

// Skipped token types
COMMENT: (SLASH SLASH .*? '\n') -> skip;
WS: [ \t\r\n]+ -> skip;

fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];
