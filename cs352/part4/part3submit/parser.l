%{
#include"y.tab.h"
#include"stdio.h"
extern "C" int yylex();
%}

%option yylineno

/* regular definitions */
delim   [ \t]
ws      {delim}+
letter  [A-Za-z]
digit   [0-9]
id      {letter}(_|{letter}|{digit})*
num     {digit}+


%%
"<script type=\"text/JavaScript\">"	{return(TOP);}
"</script>"				{return(END);}
"var"					{return(VAR);}
"if"					{return(IF);}
"while"					{return(WHILE);}
"do"					{return(DO);}
"for"					{return(FOR);}
"else"					{return(ELSE);}
{id}       	                        {yylval.string_val = strdup(yytext);return(ID);}
{num}                                   {yylval.num_val = atoi(yytext);return(NUM);}
","					{return(COMMA);}
"+"                                     {return(PLUS);}
"-"					{return(MINUS);}
"*"					{return(MULTIPLICATION);}
"/"					{return(DIVISON);}
"||"					{return(BOOLOR);}
"=="					{return(BOOLEQUAL);}
"&&"					{return(BOOLAND);}
"!"					{return(NOT);}
"="					{return(EQUAL);}
";"					{return(SEMI);}
"("					{return(LEFTPAREN);}
")"					{return(RIGHTPAREN);}
":"					{return(COLON);}
"document.write"			{return(WRITING);}
"<"					{return(LESSTHAN);}
">"					{return(GREATERTHAN);}
\n					{return(NEWLINE);}
"{"					{return(LEFTCURLY);}
"}"					{return(RIGHTCURLY);}
"["					{return(LEFTBRACE);}
"]"					{return(RIGHTBRACE);}
"\"<br />\""				{return(BREAK);}
\"[^\n\"]*\"				{for(int i=0;i<strlen(yytext)-1;i++){
						yytext[i] = yytext[i+1];
					}
						yytext[strlen(yytext)-2] = '\0';
					yylval.string_val = strdup(yytext);
					return(STRING);}
{ws}                                    {}
"."					{return(PERIOD);}
.					{return(OTHER);}
%%

