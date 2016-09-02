%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/types.h>
#include<regex.h>
#include<unistd.h>
#include<dirent.h>
#include<assert.h>
#include<map>
#include<string>
#include<stack>
#include"symbol.h"
using namespace std;
extern "C" int yylex();
extern "C" FILE *yyin;
extern "C" int yylineno;
void yyerror(const char* a);

//struct symbol table[40];

struct table{ //type is either 0 or 1 or 2 or 3 for undecl, int, string , or object 
	char*data;
	int numbers;
	int type;
};
stack <char*> curr_id;
int curr_num = 0;
int curr_type = 0;
char*curr_string;
map<string, struct table*> symbolTable;
//struct table* s = (struct *table)malloc(sizeof(struct table));
// fill in type and data for variable
// symbolTable[currentID] = s;
%}
%token <string_val> STRING ID
%token <num_val> NUM
%token ASSIGN VAR PLUS MINUS MULTIPLICATION DIVISON EQUAL WRITING NEWLINE SEMI TOP END LEFTPAREN RIGHTPAREN COMMA BREAK LEFTCURLY RIGHTCURLY COLON PERIOD OTHER
%union{
	char *string_val;
	int num_val;
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
	|PERIOD ID expression linestop
	|NEWLINE
	;
declare:
	VAR ID {struct table* s=(struct table*)malloc(sizeof(struct table));
			s->type = 0;
			symbolTable[$2] =s;
				printf("num\t%d\n",s->numbers);
				printf("data\t%s\n",s->data);
				printf("type\t%d\n",s->type);
				printf("id\t%s",$2);
		}
	|VAR ID EQUAL expression { struct table* s=(struct table*)malloc(sizeof(struct table));
				s->type = curr_type;
				s->numbers = curr_num;
				s->data = curr_string;
				symbolTable[$2]=s;
				  printf("num\t%d\n",s->numbers);
				  printf("data\t%s\n",s->data);
				  printf("type\t%d\n",s->type);
				  printf("id\t%s",$2);}
	|VAR ID EQUAL LEFTCURLY object RIGHTCURLY
				{ struct table* s=(struct table*)malloc(sizeof(struct table));
				s->type=3;
				symbolTable[$2] =s;
					printf("num\t%d\n",s->numbers);
					printf("data\t%s\n",s->data);
					printf("type\t%d\n",s->type);
					printf("id\t%s",$2);
				}
	;
docwrite:
	expression
	|expression COMMA docwrite
	|
	;
expression:
	/*words*/
	math	
	|BREAK /*semanitc print a new line*/
			{
			curr_type = 2;
			char * temp = strdup("BR");
			curr_string = temp;}
	;
object:
	 blank ID COLON expression blank
	 		{struct table* s=(struct table*)malloc(sizeof(struct table));
				s->type = curr_type;
				s->numbers = curr_num;
				s->data = curr_string;
				symbolTable[$2]=s;
					printf("num\t%d\n",s->numbers);
					printf("data\t%s\n",s->data);
					printf("type\t%d\n",s->type);
					printf("id\t%s\n",$2);
			}
	|blank ID COLON expression blank COMMA object
			/*{struct table* s=(struct table*)malloc(sizeof(struct table));
				s->type = curr_type;
				s->numbers = curr_num;
				s->data = curr_string;
				symbolTable[$2]=s;
					printf("num\t%d\n",s->numbers);
					printf("data\t%s\n",s->data);
					printf("type\t%d\n",s->type);
					printf("id\t%s\n",$2);
			}*/
	|
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
	NUM {	curr_num = $1;
		curr_type = 1;}
	|ID
	|STRING{
		curr_string =$1;
		curr_type = 2;
		}
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
//FILE *yyin;
//int yylineno;
void yyerror(const char *s)
{
    fprintf(stderr, "error: %s, line: %d\n", s, yylineno);
    return ;
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

