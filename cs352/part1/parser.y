%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/types.h>
#include<regex.h>
#include<unistd.h>
#include<dirent.h>
#include<assert.h>
%}


%token ASSIGN VAR ID NUM PLUS MINUS MULTIPLICATION DIVISON EQUAL WRITING STRING NEWLINE SEMI TOP END LEFTPAREN RIGHTPAREN COMMA OTHER
%start goal

%%
goal:
	TOP middle eof 	
	;
middle:
	state middle
	|
	;
state: 
	WRITING LEFTPAREN docwrite RIGHTPAREN linestop
	|declare linestop
	|ID EQUAL expression linestop
	|NEWLINE
	;
declare:
	VAR ID
	|VAR ID EQUAL expression
	;
docwrite:
	expression
	|expression COMMA docwrite
	|
	;
expression:
	STRING
	|math
	;
math:
	argument operator math
	|argument
	;
argument:
	NUM
	|ID
	|LEFTPAREN math RIGHTPAREN
	;
operator:
	PLUS
	|MINUS
	|MULTIPLICATION
	|DIVISON
	;
linestop:
	NEWLINE
	|SEMI
	;
eof:
	END
	|END NEWLINE
	;

%%
FILE *yyin;
int yylineno;
yyerror(char *s)
{
    fprintf(stderr, "error: %s, line: %d\n", s, yylineno);
    return 1;
}

main(int argc, char *argv[])
{
    //yydebug = 1;
    if (argc == 2) {
        FILE *file;

        file = fopen(argv[1], "r");
        if (!file) {
            fprintf(stderr, "could not open %s\n", argv[1]);
        } else{
            yyin = file;
            //yyparse() will call yylex()
            yyparse();
        }
    } else{
        fprintf(stderr, "format: ./yacc_example [filename]");
    }
    return 0;
}

