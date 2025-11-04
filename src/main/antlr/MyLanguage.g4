grammar MyLanguage;

// ------ Parser ------ //
programm: statement+ ;

statement
    : assignment
    | ifStatement
    | whileStatement
    ;

assignment : ID ASSIGN expr ;

ifStatement : IF condition DO statement+ (ELSE DO statement+)? END ;
whileStatement : WHILE condition DO statement+ END ;

condition : expr (EQ | NEQ | GT | LT | GOE | LOE) expr ;

expr
    : expr MUL expr
    | expr DIV expr
    | expr ADD expr
    | expr SUB expr
    | '(' expr ')'
    | INT
    | STRING
    | ID
    ;

// ------ Lexer ------ //

// Schluesselwoerter
WHILE   : 'while';
DO      : 'do';
END     : 'end';
IF      : 'if';
ELSE    : 'else';

// Operatoren
ASSIGN  : ':=' ;
ADD     : '+' ;
SUB     : '-' ;
MUL     : '*' ;
DIV     : '/' ;
EQ      : '==' ;
NEQ     : '!=' ;
GT      : '>' ;
LT      : '<' ;
GOE     : '>=' ;
LOE     : '<=' ;

// Literale
INT     : [0-9]+ ;
STRING  : '"' (~[\n\r"])* '"' ;

// Identifier
ID  : [a-z][a-zA-Z0-9]* ;

// Whitespace / Kommentare
WS      : [ \t\r\n]+ -> skip ;
COMMENT : '#' ~[\r\n]* -> skip ;
