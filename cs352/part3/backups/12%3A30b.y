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
	stack <char*> curr_strings;
	stack <int> curr_types;
	stack <int> curr_nums;
	stack <char*> ma;
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
|ID PERIOD ID EQUAL expression linestop
|NEWLINE
;
declare:
VAR ID {struct table* s=(struct table*)malloc(sizeof(struct table));
	s->type = 0;
	symbolTable[$2] =s;
	printf("kept going\n");

}
|VAR ID EQUAL expression { struct table* s=(struct table*)malloc(sizeof(struct table));
	int type = curr_types.top();
	if(type==1){//int variable
		curr_num = curr_nums.top();
		curr_nums.pop();
	}else if(type==2){//string variable
		curr_string = curr_strings.top();
		curr_strings.pop();
		printf("currString: %s\n",curr_string);
	}
	printf("curr:type%d\n",type);
	s->type = type;
	s->numbers = curr_num;
	s->data = curr_string;
	symbolTable[$2]=s;
	curr_types.pop();
}
|VAR ID EQUAL LEFTCURLY object RIGHTCURLY
{ struct table* s=(struct table*)malloc(sizeof(struct table));
	s->type=3;
	symbolTable[$2] =s;
}
;
docwrite:
expression
|expression COMMA docwrite
|
;
expression:
math/*{
	char * temp;
	char * tempid;
	char * opp;
	char * temp2;
	int type_size = curr_types.size();
	curr_type = curr_types.top();
	if(curr_type==2&&type_size>1){//string
		curr_types.pop();
		curr_type = curr_types.top();
		if(curr_type!=2){
			yyerror("TYPE VIOLATION"); //makes object report twice
		}else{
			if(curr_id.size()>0){//adding two or more string IDS
				int numID = curr_id.size();
				for(int i=0;i<numID-1;i++){
					opp = ma.top(); 
					ma.pop();
					if(strcmp("+",opp)!=0){
						yyerror("TYPE VIOLATION");
					}else{
					tempid = curr_id.top();
					curr_id.pop();
					struct table* ts = symbolTable[tempid];
					temp = ts->data;
					tempid = curr_id.top();
					ts = symbolTable[tempid];
					temp2 = ts->data;
					strcat(temp2,temp);
					}

				}
				curr_strings.push(temp2);
			}else{//adding two or more strings for 1 id
			int size = curr_strings.size();
			for(int i=0;i<size-1;i++){
				opp = ma.top();
				ma.pop();
				if(strcmp("+",opp)!=0){
					yyerror("TYPE VIOLATION");//stills goes into else
				}else{
					temp = curr_strings.top();
					curr_strings.pop();
					temp2 = curr_strings.top();
					strcat(temp2,temp);
				}
			}
			}

		}
	}else if(curr_type==1&&type_size>1){//INT
		curr_types.pop();
		curr_type = curr_types.top();
		if(curr_type!=1){
			yyerror("TYPE VIOLATION");
		}else{
			if(curr_id.size()>0){ //more than 1 id
			}
			else{//using two or more ints
			int size = curr_nums.size();
			for(int i=0;i<size-1;i++){
				opp = ma.top();
				ma.pop();
				switch (opp){
				   case "+":
				      //addition
				      break;
				   case "-":
				      //subtraction
				      break;
				   case "*":
				      //multiply
				      break;
				   case "/":
				      //divide
				      break;
				   default:
				}

			}
			}
		}
	}
}*/
|BREAK /*semanitc print a new line*/
{
	curr_type = 2;
	char * temp = strdup("BR");
	curr_string = temp;}
	;
object:
blank ID COLON expression blank
{struct table* s=(struct table*)malloc(sizeof(struct table));
	int type = curr_types.top();
	if(type==1){ //int variable
		curr_num = curr_nums.top();
		curr_nums.pop();
	}else if(type==2){ //string variable
		curr_string = curr_strings.top();
		curr_strings.pop();
	}
	s->type = type;
	s->numbers = curr_num;
	s->data = curr_string;
	symbolTable[$2]=s;
	curr_types.pop();
}
|blank ID COLON expression blank COMMA object
{struct table* s=(struct table*)malloc(sizeof(struct table));
	int type = curr_types.top();
	if(type==1){
		curr_num = curr_nums.top();
		curr_nums.pop();
	}else if(type==2){
		curr_string= curr_strings.top();
		curr_strings.pop();
	}
	s->type = type;
	s->numbers = curr_num;
	s->data = curr_string;
	symbolTable[$2]=s;
	curr_types.pop();
}
|
;
blank:
NEWLINE blank
|
;

math:
argument PLUS math
{	char* temp;
	char* temp2;
	char* opp;
	char* tempid;
	int curr_type = curr_types.top();
	int type_size =curr_types.size();
	printf("type_size%d\n",type_size);
	if(curr_type==2&&type_size>1)
	{printf("in begin");
		int size = curr_strings.size();
		for(int i=0;i<size-1;i++)
		{	curr_type = curr_types.top();
			printf("curr_type%d",curr_type);
			if(curr_type!=2)
			{
				yyerror("TYPE VIOLATION");
			}else{

				curr_types.pop();
				temp = curr_strings.top();
				curr_strings.pop();
				temp2 = curr_strings.top();
				strcat(temp2,temp);
				printf("arg%s\n",temp2);
			}
		}
	}else if(curr_id.size()>0){
		printf("got here");
		int numID = curr_id.size();
		for(int i=0;i<numID-1;i++){
			curr_type = curr_types.top();
			if(curr_type!=2)
			{
				yyerror("TYPE VIOLATION");
			}
			tempid = curr_id.top();
			curr_id.pop();
			struct table* ts = symbolTable[tempid];
			temp = ts->data;
			tempid =  curr_id.top();
			ts = symbolTable[tempid];
			temp2 = ts->data;
			strcat(temp2,temp);
		}
		char* send = strdup(temp2);
		curr_strings.push(send);
	}/*else if(curr_type==1&&type_size>1){//add two numbers
			int size = curr_nums.size();
			for(int i=0;i<size-1;i++){
				curr_type = curr_types.top();
				if(curr_type!=1){
					yyerror("TYPE VIOLATION");
				}else{
					curr_types.pop();
					intemp = curr_nums.top();
					curr_nums.pop();
					intemp2 = curr_nums.top();
					curr_nums.pop();
					new_num = intemp2+intemp;
					printf("new_num%d\n",new_num);
				}
			}curr_nums.push(new_num);
	}*/else{
		yyerror("TYPE ERROR");
	}
}
|argument MINUS math
	{	int type_size = curr_types.size();
		curr_type = curr_types.top();
		int temp;
		int temp2;
		int new_num;
		printf("type now%d\n",curr_type);
		printf("size now%d\n",type_size);
		if(curr_type==1&&type_size>1){//subtractin two numbers
			int size = curr_nums.size();
			for(int i=0;i<size-1;i++){
				curr_type = curr_types.top();
				if(curr_type!=1){
					yyerror("TYPE VIOLATION");
				}else{
					curr_types.pop();
					temp = curr_nums.top();
					curr_nums.pop();
					temp2 = curr_nums.top();
					curr_nums.pop();
					new_num = temp2-temp;
					printf("new_num%d\n",new_num);
					curr_nums.push(new_num);
				}
			}//curr_nums.push(new_num);
	}else{
		yyerror("TYPE VIOLATION");
	}
	}
|argument DIVISON math
|argument MULTIPLICATION math
|argument
;
argument:
NUM {	curr_nums.push($1);
	curr_types.push(1);
	printf("NUM FOUND\n");}
|ID
	{if(struct table* ts = symbolTable[$1]){
		int tes = ts->type;
		printf("tes%d\n",tes);
		if(tes==3){ //object is not allowed as variable
			yyerror("TYPE RULE VIOLATION"); 
			curr_types.push(0);
		}else if(tes==1){
			curr_types.push(1);
			curr_id.push($1);
		}else if(tes==2){
			curr_types.push(2);
			printf("pushed\n");
			curr_id.push($1);
		}
	}else{
		yyerror("TYPE VIOLATION");
	}
}
|STRING{
	curr_strings.push($1);
	curr_types.push(2);
	printf("string found\n");
}
|LEFTPAREN math RIGHTPAREN
;
/*operator:
PLUS{printf("plus found\n");
	char* plus = strdup("+");
	ma.push(plus);}
	|MINUS{
		char* minus = strdup("-");
		ma.push(minus);}
		|MULTIPLICATION{
			char* mult = strdup("*");
			ma.push(mult);}
			|DIVISON{
				char* div = strdup("/");
				ma.push(div);}
				;*/
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
	//fprintf(stderr, "Line: %d, %s\n",yylineno,s); 
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

