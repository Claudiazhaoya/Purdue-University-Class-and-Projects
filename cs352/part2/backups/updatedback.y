%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/types.h>
#include<regex.h>
#include<unistd.h>
#include<dirent.h>
#include<assert.h>
#include"symbol.h"

struct symbol table[40];
%}

%token <string_val> STRING
%token ASSIGN VAR ID NUM PLUS MINUS MULTIPLICATION DIVISON EQUAL WRITING NEWLINE SEMI TOP END LEFTPAREN RIGHTPAREN COMMA BREAK LEFTCURLY RIGHTCURLY COLON PERIOD OTHER
%union{
	char *string_val;
	int numbers;
	}
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
	|ID PERIOD ID EQUAL expression linestop
	|NEWLINE
	;
declare:
	VAR ID
	|VAR ID EQUAL expression
	|VAR ID EQUAL LEFTCURLY object RIGHTCURLY
	;
docwrite:
	expression
	|expression COMMA docwrite
	|
	;
expression:
	words
	|math	{printf("aritmatic expression\n");}
	|BREAK {printf("found br\n");} /*semanitc print a new line*/
	;
object:
	 blank ID COLON expression blank
	|blank ID COLON expression blank COMMA object
	;
words:
	STRING
	|STRING PLUS words
	;
blank:
	NEWLINE blank
	|
	;

math:
	argument operator math
	|argument
	;
argument:
	NUM	
	|ID	{printf("id\n");}
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

