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
#include<queue>
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
	stack <string> output;
	stack <char*> ma;
	int curr_num = 0;
	int curr_type = 0;
	int line__num = 0;
	int have_print=0;
	char* curr_string;
	char* globalString;
	char* objid;
	map<string, struct table*> symbolTable;
	//struct table* s = (struct *table)malloc(sizeof(struct table));
	// fill in type and data for variable
	// symbolTable[currentID] = s;
	%}
	%token <string_val> STRING ID BREAK
	%token <num_val> NUM
	%token ASSIGN VAR PLUS MINUS MULTIPLICATION DIVISON EQUAL WRITING NEWLINE SEMI TOP END LEFTPAREN RIGHTPAREN COMMA LEFTCURLY RIGHTCURLY COLON PERIOD OTHER
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
{
	int size = curr_types.size();
	int ids = curr_id.size();
	//printf("types on stack %d\n",size);
	//printf("id on stack %d\n",ids);
	string docs[size];
	//globalString = (char*)malloc(50)

	for(int i=0;i<size;i++){
		int type = curr_types.top();
		curr_types.pop();
		if(type==1){//int 
			int temp = curr_nums.top();
			curr_nums.pop();
			std::string temp2 = std::to_string(temp);
			//output.push(temp);
			//printf("%d",temp);
			output.push(temp2);
			//docs[i]=temp;

		}else if(type==2){//string
			char * temp = curr_strings.top();
			curr_strings.pop();
			if(strcmp("<br />",temp)==0){
				//printf("\n");
				char * temp2 = strdup("\n");
				//docs[i]=temp2;
				output.push(temp2);
			}else{
				char * temp2 = strdup(temp);
				output.push(temp2);
				//docs[i]=temp2;
				//printf("%s",temp);
			}
		}else if(type==0){
			char * temp = strdup("undefined");
			output.push(temp);
		}
	}for(int i=0;i<size;i++){
		string temp = output.top();///////right here
		char * printer = strdup(temp.c_str());
		//char* temp1 = output.top();
		output.pop();
		printf("%s",printer);
		have_print++;
	}
	line__num ++;

}
|declare linestop{line__num ++;}
|ID EQUAL expression linestop{struct table* s=(struct table*)malloc(sizeof(struct table));
	int type = curr_types.top();
	if(struct table* ts = symbolTable[$1]){
		int id_type = ts->type;
		//printf("id_type %d\n",id_type);
		//CHECK TO SEE IF WE CAN JUST CHANGE THE TYPE OR NOT
		//printf("got here\n");
		if(type==1){//int
			curr_num = curr_nums.top();
			curr_nums.pop();
		}else if(type==2){
			curr_string = curr_strings.top();
			curr_strings.pop();
			//printf("new string:\t%s\n",curr_string);
		}else if(type==0){
			//aprintf("IT WAS 0\n");
		}
		s->type = type;
		s->numbers = curr_num;
		s->data = curr_string;
		symbolTable[$1] = s;
		curr_types.pop();
	}else{
		sprintf(globalString, "Line %d, type violation", line__num+1);
		yyerror(globalString);
	}
	line__num ++;

}
|ID PERIOD ID EQUAL expression linestop{struct table* s=(struct table*)malloc(sizeof(struct table));
char * per = strdup(".");
char * tid = strdup($1);
	if(struct table* op = symbolTable[$1]){
		int checker = op->type;
		if(checker!=3){
			sprintf(globalString, "Line %d, type violation", line__num+1);
			yyerror(globalString);

		}
		strcat(tid,per);
		strcat(tid,$3);
		if(struct table* ts = symbolTable[tid]){
			int type = curr_types.top();
			if(type==1){
				curr_num = curr_nums.top();
				curr_nums.pop();
			}else if(type==2){
				curr_string = curr_strings.top();
				curr_strings.pop();
				//printf("neuw string:\t%s\n",curr_string);
			}
			s->type = type;
			s->numbers = curr_num;
			s->data = curr_string;
			symbolTable[tid] =s;
			curr_types.pop();
		}else{
			int type = curr_types.top();
				//printf("in here: %d\n",type);
			if(type==1){
				//printf("TYPE was 1\n");
				curr_num = curr_nums.top();
				curr_nums.pop();
			}else if(type==2){
				curr_string = curr_strings.top();
				curr_strings.pop();
				//printf("new object value:\t%s\n",curr_string);
			}
			s->type = type;
			s->numbers = curr_num;
			s->data = curr_string;
			symbolTable[tid] = s;
				//printf("tid %s\n",tid);
			curr_types.pop();
		}
	}else{
		//needs to be handled
	}
	line__num ++;
}
|NEWLINE{line__num ++;}
;
declare:
VAR ID {struct table* s=(struct table*)malloc(sizeof(struct table));
	s->type = 0;
	symbolTable[$2] =s;
	//printf("kept going\n");

}
|VAR ID EQUAL expression { struct table* s=(struct table*)malloc(sizeof(struct table));
	int type = curr_types.top();
	if(type==1){//int variable
		curr_num = curr_nums.top();
		curr_nums.pop();
	}else if(type==2){//string variable
		curr_string = curr_strings.top();
		curr_strings.pop();
		//printf("currString: %s\n",curr_string);
	}
	//printf("curr:type%d\n",type);
	s->type = type;
	s->numbers = curr_num;
	s->data = curr_string;
	symbolTable[$2]=s;
	curr_types.pop();
}
|VAR ID EQUAL LEFTCURLY object RIGHTCURLY
{ objid = strdup($2);
struct table* s=(struct table*)malloc(sizeof(struct table));
struct table* in=(struct table*)malloc(sizeof(struct table));
char* per = strdup(".");
char* temp;
char* temp1 = strdup($2);
char* oid;
	s->type=3;
	symbolTable[$2] =s;
	int size= curr_types.size();
	//printf("uppder type %d\n",size);
	for(int i=0;i<size;i++){
		int curr_type = curr_types.top();
		curr_types.pop();
		if(curr_type==1){
			curr_num = curr_nums.top();
			curr_nums.pop();
		}else if(curr_type==2){
			curr_string = curr_strings.top();
			curr_strings.pop();
		}
		in->type = curr_type;
		in->numbers = curr_num;
		in->data = curr_string;
		temp = curr_id.top();
		curr_id.pop();
		temp1 = strcat(temp1,per);
		oid = strcat(temp1,temp);
		//printf("oid %s\n",oid);
		symbolTable[oid]=in;
		temp1 = strdup($2);

	}
}
;
docwrite:
expression
|expression COMMA docwrite
|
;
expression:
math
|BREAK /*semanitc print a new line*/
{
	curr_type = 2;
	curr_types.push(2);
	char * br = strdup("<br />");
	curr_strings.push(br);}
	;
object:
blank ID COLON expression blank
{struct table* s=(struct table*)malloc(sizeof(struct table));
//printf("no comma %s\n",objid);
	curr_id.push($2);
	//int type = curr_types.top();
	//if(type==1){ //int variable
	//	curr_num = curr_nums.top();
	//	curr_nums.pop();
	//}else if(type==2){ //string variable
	//	curr_string = curr_strings.top();
	//	curr_strings.pop();
	//}
	//s->type = type;
	//s->numbers = curr_num;
	//s->data = curr_string;
	//symbolTable[$2]=s;
	//curr_types.pop();
}
|blank ID COLON expression blank COMMA object
{struct table* s=(struct table*)malloc(sizeof(struct table));
	curr_id.push($2);
//printf("here %s\n",$2);
	//int type = curr_types.top();
	//if(type==1){
	//	curr_num = curr_nums.top();
	//	curr_nums.pop();
	//}else if(type==2){
	//	curr_string= curr_strings.top();
	//	curr_strings.pop();
	//}
	//s->type = type;
	//s->numbers = curr_num;
	//s->data = curr_string;
	//symbolTable[$2]=s;
	//curr_types.pop();
}
|
;
blank:
NEWLINE blank
	{line__num++;}
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
		//printf("check %d\n",check);
	curr_types.pop();
	int check2 = curr_types.top();
	curr_types.pop();
	//printf("sizeid\t%d\n",size_id);
	//printf("size\t%d\n",string_size);
	if(check!=0&&check2!=0){
		if(check==1){//int
			if(check!=1||check2!=1){
				sprintf(globalString, "Line %d, type violation", line__num+1);
				yyerror(globalString);
				curr_types.push(0);
			}else{
				temp = curr_nums.top();
				curr_nums.pop();
				temp2 = curr_nums.top();
				curr_nums.pop();
				temp3 = temp2+temp;
				curr_nums.push(temp3);
				curr_types.push(1);
				//printf("temp3\t%d\n",temp3);
			}
		}else if(check==2){//string
			if(check!=2||check2!=2){
				sprintf(globalString, "Line %d, type violation", line__num+1);
				yyerror(globalString);
				curr_types.push(0);
			}else{
				char* temp = curr_strings.top();
				curr_strings.pop();
				char* temp2 = curr_strings.top();
				curr_strings.pop();
				if(strcmp(temp,"<br />")==0||strcmp(temp2,"<br />")==0){
				sprintf(globalString, "Line %d, type violation", line__num+1);
				yyerror(globalString);
				curr_types.push(0);

				}else{
					strcat(temp2,temp);
					curr_strings.push(temp2);
					curr_types.push(2);
				}
				//printf("new string\t%s\n",temp2);
			}
		}
	}else{
		curr_types.push(0);
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
	if(check!=0&&check2!=0){
		if(check!=1||check2!=1){
			sprintf(globalString, "Line %d, type violation", line__num+1);
			yyerror(globalString);
			curr_types.push(0);//maybe
		}else{
			temp = curr_nums.top();
			curr_nums.pop();
			temp2 = curr_nums.top();
			curr_nums.pop();
			temp3 = temp2-temp;
			curr_types.push(1);
			curr_nums.push(temp3);
			//printf("temp3\t%d\n",temp3);
		}
	}else{
		curr_types.push(0);
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
	if(check!=0&&check2!=0){
		if(check!=1||check2!=1){
			sprintf(globalString, "Line %d, type violation",line__num+1);
			yyerror(globalString);
			curr_types.push(0);
		}else{
			temp = curr_nums.top();
			curr_nums.pop();
			temp2 = curr_nums.top();
			curr_nums.pop();
			temp3 = temp2*temp;
			curr_types.push(1);
			curr_nums.push(temp3);
			//printf("temp3\t%d\n",temp3);
		}
	}else{
		curr_types.push(0);

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
	if(check!=0&&check2!=0){
		if(check!=1||check2!=1){
			sprintf(globalString, "Line %d, type violation",line__num+1);
			yyerror(globalString);
			curr_types.push(0);
		}else{
			temp = curr_nums.top();
			curr_nums.pop();
			temp2 = curr_nums.top();
			curr_nums.pop();
			temp3 = temp2/temp;
			curr_nums.push(temp3);
			curr_types.push(1);
			//printf("temp3\t%d\n",temp3);
		}
	}else{
		curr_types.push(0);

	}
}
|argument
;

argument:
NUM {	curr_nums.push($1);
	curr_types.push(1);}
	|ID
{char * idtemp = strdup($1);
//printf("idtemp %s\n",idtemp);
if(struct table* ts = symbolTable[$1]){
	//printf("why are we here\n");
	int tes = ts->type;
	//printf("tes%d\n",tes);
	if(tes==3){ //object is not allowed as variable
		sprintf(globalString, "Line %d, type violation", line__num+1);
		yyerror(globalString); 
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
		//printf("temp %s\n",temp);
		curr_strings.push(temp);
		curr_id.push($1);//added while working with doc.write check here for other id errors
	}else if(tes==0){//variable has no value and can't be used in expression
		sprintf(globalString, "Line %d, %s has no value", line__num+1,$1);
		yyerror(globalString); 
		//char* err = strcat($1," has no value");
		//yyerror(err);
		curr_types.push(0);
	}
}else{
					       sprintf(globalString, "Line %d, type violation",line__num+1);
					       yyerror(globalString);
					       curr_types.push(0);

				       }
}
|STRING{
	//printf("only string\n");
	curr_strings.push($1);
	curr_types.push(2);
}
|ID PERIOD ID{
	char * temp = strdup($1);
	char * per = strdup(".");
	strcat(temp,per);
	strcat(temp,$3);
		//printf("id.id %s\n",temp);
	if(struct table* ot = symbolTable[$1]){
		int op = ot->type;
		//printf("type %d\n",op);
		//printf("up above %d\n",op);
		if(op==3){
			if(struct table* ts = symbolTable[temp]){
				int tes1 = ts->type;
				//printf("down below: %d\n",tes1);
				if(tes1==3){
					sprintf(globalString, "Line %d, type violation",line__num+1);
					yyerror(globalString);
					curr_types.push(0);
				}
				else if(tes1==1){
					int temp = ts->numbers;
					curr_types.push(1);
					curr_nums.push(temp);
				}else if(tes1==2){
					char* temp = ts->data;
					curr_types.push(2);
					curr_strings.push(temp);
				}else if(tes1==0){
					char* err = strcat($1," has no value");
					yyerror(err);
					curr_types.push(0);
				}
			}else{
				sprintf(globalString, "Line %d, type violation", line__num+1);
				yyerror(globalString);
				curr_types.push(0);
			}
		}else{
			sprintf(globalString, "Line %d, type violation", line__num+1);
			yyerror(globalString);
			curr_types.push(0);
		}
	}else{
		sprintf(globalString, "Line %d, type violation", line__num+1);
		yyerror(globalString);
		curr_types.push(0);
	}
}
|LEFTPAREN math RIGHTPAREN
;

linestop:
NEWLINE
|SEMI{line__num--;}
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
	if(have_print>0){
		fprintf(stdout,"\n");
	}
	//fprintf(stderr, "Line %d,%s\n",yylineno,s);
	fprintf(stdout,"%s\n",s);
	return ;
}

main(int argc, char *argv[])
{
	//yydebug = 1;
	if (argc == 2) {
		globalString = (char*)malloc(50);
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

