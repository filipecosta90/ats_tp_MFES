lexer grammar i;
options {
  tokenVocab=iTokens;

}

@header { package gram; }

T__83 : 'char' ;
T__84 : 'int' ;
T__85 : 'boolean' ;
T__86 : 'float' ;
T__87 : 'void' ;
T__88 : ';' ;
T__89 : ',' ;
T__90 : '=' ;
T__91 : '(' ;
T__92 : ')' ;
T__93 : 'if' ;
T__94 : 'else' ;
T__95 : 'for' ;
T__96 : 'while' ;
T__97 : 'return' ;
T__98 : 'print' ;
T__99 : 'input' ;
T__100 : '{' ;
T__101 : '}' ;
T__102 : '?' ;
T__103 : ':' ;
T__104 : '||' ;
T__105 : '&&' ;
T__106 : '>' ;
T__107 : '<' ;
T__108 : '>=' ;
T__109 : '<=' ;
T__110 : '!=' ;
T__111 : '==' ;
T__112 : '*=' ;
T__113 : '/=' ;
T__114 : '+=' ;
T__115 : '-=' ;
T__116 : '+' ;
T__117 : '-' ;
T__118 : '*' ;
T__119 : '/' ;
T__120 : '%' ;
T__121 : '++' ;
T__122 : '--' ;
T__123 : '!' ;
T__124 : 'true' ;
T__125 : 'false' ;

// $ANTLR src "gram/i.g" 224
COMENTARIO_LINHA
	:	'//' ~('\r' | '\n')*
	;
	    
// $ANTLR src "gram/i.g" 228
COMENTARIO_LINHAS
	:	'/*' ( options {greedy=false;} : . )* '*/'
	;


// Tokens lex


// $ANTLR src "gram/i.g" 236
CHAR 
@after
{
    setText(getText().substring(1, getText().length()-1));
} 	
	:	'\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\''|'\\') ) '\''
	;

// $ANTLR src "gram/i.g" 244
fragment DIGITO
 	:	('0'..'9')+
	;

// $ANTLR src "gram/i.g" 248
FLOAT 	
	:	DIGITO+ '.' DIGITO* SufixoFloat?
	|	'.' DIGITO+ SufixoFloat?
	|	INT SufixoFloat
	;
	
// $ANTLR src "gram/i.g" 254
SufixoFloat 
	:	':f'|':F'
	;
	
// $ANTLR src "gram/i.g" 258
INT	
	:	('0' | '1'..'9' DIGITO*)
	;
	
// $ANTLR src "gram/i.g" 262
ID 	:	LETRA ( LETRA | '0'..'9' )*
	;
	
// $ANTLR src "gram/i.g" 265
fragment LETRA	
	:	'a'..'z' | 'A'..'Z' | '_'
	;
	
// $ANTLR src "gram/i.g" 269
WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;
