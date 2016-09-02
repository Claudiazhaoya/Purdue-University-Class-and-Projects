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
/*{
	char * temp = curr_strings.top();
	curr_strings.pop();
	char * temp2 = curr_strings.top();
	printf("total:%s %s\n",temp,temp2);
}*/
|declare linestop
|ID EQUAL expression linestop{struct table* s=(struct table*)malloc(sizeof(struct table));
	int type = curr_types.top();
	//CHECK TO SEE IF WE CAN JUST CHANGE THE TYPE OR NOT
	if(type==1){//int
		curr_num = curr_nums.top();
		curr_nums.pop();
	}else if(type==2){
		curr_string = curr_strings.top();
		curr_strings.pop();
		printf("new string:\t%s\n",curr_string);
	}
	s->type = type;
	s->numbers = curr_num;
	s->data = curr_string;
	symbolTable[$1] = s;
	curr_types.pop();

}
|ID PERIOD ID EQUAL expression linestop{struct table* s=(struct table*)malloc(sizeof(struct table));
	if(struct table* ts = symbolTable[$3]){
		int type = curr_types.top();
		if(type==1){
			curr_num = curr_nums.top();
			curr_nums.pop();
		}else if(type==2){
			curr_string = curr_strings.top();
			curr_strings.pop();
			printf("new string:\t%s\n",curr_string);
		}
		s->type = type;
		s->numbers = curr_num;
		s->data = curr_string;
		symbolTable[$3] =s;
		curr_types.pop();
	}else{
		int type = curr_types.top();
		if(type==1){
			curr_num = curr_nums.top();
			curr_nums.pop();
		}else if(type==2){
			curr_string = curr_strings.top();
			curr_strings.pop();
			printf("new object value:\t%s\n",curr_string);
		}
		s->type = type;
		s->numbers = curr_num;
		s->data = curr_string;
		symbolTable[$3] = s;
		curr_types.pop();
	}
}
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
/*{
	char * just =strdup("after");
	curr_strings.push(just);
	printf("just epxression:\n %s\n",just);}*/
|expression COMMA docwrite
	/*{
	char * before = curr_strings.top();
	curr_strings.push(before);
	printf("all\n %s\n",before);
	}*/
|
;
expression:
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

math: 		/*based off example pemadas found on class resources*/
math PLUS T{
	int size_id = curr_id.size();
	int string_size = curr_strings.size();
	int temp;
	int temp2;
	int temp3;
	int check = curr_types.top();
	curr_types.pop();
	int check2 = curr_types.top();
	curr_types.pop();
	printf("sizeid\t%d\n",size_id);
	printf("size\t%d\n",string_size);
	if(check==1){//int
		if(check!=1||check2!=1){
			yyerror("TYPE VIOLATION");
			curr_types.push(0);
		}else{
			temp = curr_nums.top();
			curr_nums.pop();
			temp2 = curr_nums.top();
			curr_nums.pop();
			temp3 = temp2+temp;
			curr_nums.push(temp3);
			printf("temp3\t%d\n",temp3);
		}
	}else if(check==2){//string
		if(check!=2||check2!=2){
			yyerror("TYPE VIOLATION");
			curr_types.push(0);
		}else{
			char* temp = curr_strings.top();
			curr_strings.pop();
			char* temp2 = curr_strings.top();
			curr_strings.pop();
			strcat(temp2,temp);
			curr_strings.push(temp2);
			printf("new string\t%s\n",temp2);
		}
	}
}
|math MINUS T{
	int check = curr_types.top();
	curr_types.pop();
	int check2 = curr_types.top();
	curr_types.pop();
	int temp;
	int temp2;
	int temp3;
	if(check!=1||check2!=1){
		yyerror("TYPE VIOLATION");
		curr_types.push(0);//maybe
	}else{
		temp = curr_nums.top();
		curr_nums.pop();
		temp2 = curr_nums.top();
		curr_nums.pop();
		temp3 = temp2-temp;
		curr_types.push(1);
		curr_nums.push(temp3);
		printf("temp3\t%d\n",temp3);
	}
}
|T
;

T:
T MULTIPLICATION argument{
	int check = curr_types.top();
	curr_types.pop();
	int check2 = curr_types.top();
	curr_types.pop();
	int temp;
	int temp2;
	int temp3;
	if(check!=1||check2!=1){
		yyerror("TYPE VIOLATION");
		curr_types.push(0);
	}else{
		temp = curr_nums.top();
		curr_nums.pop();
		temp2 = curr_nums.top();
		curr_nums.pop();
		temp3 = temp2*temp;
		curr_types.push(1);
		curr_nums.push(temp3);
		printf("temp3\t%d\n",temp3);
	}
}
|T DIVISON argument{
	int check =curr_types.top();
	curr_types.pop();
	int check2 = curr_types.top();;
	curr_types.pop();
	int temp;
	int temp2;
	int temp3;
	if(check!=1||check2!=1){
		yyerror("TYPE VIOLATION");
		curr_types.push(0);
	}else{
		temp = curr_nums.top();
		curr_nums.pop();
		temp2 = curr_nums.top();
		curr_nums.pop();
		temp3 = temp2/temp;
		curr_nums.push(temp3);
		curr_types.push(1);
		printf("temp3\t%d\n",temp3);
	}
}
|argument
;

argument:
NUM {	curr_nums.push($1);
	curr_types.push(1);}
|ID
	{if(struct table* ts = symbolTable[$1]){
		int tes = ts->type;
		printf("tes%d\n",tes);
		if(tes==3){ //object is not allowed as variable
			yyerror("TYPE RULE VIOLATION"); 
			curr_types.push(0);
		}else if(tes==1){
			struct table* ts = symbolTable[$1];
			int temp = ts->numbers;
			curr_types.push(1);
			curr_nums.push(temp);
		}else if(tes==2){
			curr_types.push(2);
			struct table* ts = symbolTable[$1];
			char * temp = ts->data;
			curr_strings.push(temp);
		}else if(tes==0){//variable has no value and can't be used in expression
			char* err = strcat($1," has no value");
			yyerror(err);
			curr_types.push(0);

		}
	}else{
		yyerror("TYPE VIOLATION");
	}
}
|STRING{
	curr_strings.push($1);
	curr_types.push(2);
}
|LEFTPAREN math RIGHTPAREN
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
	fprintf(stderr, "Line %d,%s\n",yylineno,s);
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

