%{
#include"y.tab.h"
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
{id}                                    {return(ID);}
{num}                                   {return(NUM);}
","					{return(COMMA);}
"+"                                     {return(PLUS);}
"-"					{return(MINUS);}
"*"					{return(MULTIPLICATION);}
"/"					{return(DIVISON);}
"="					{return(EQUAL);}
";"					{return(SEMI);}
"("					{return(LEFTPAREN);}
")"					{return(RIGHTPAREN);}
"document.write"			{return(WRITING);}
\n					{return(NEWLINE);}
""
\"[^\n\"]*\"				{return(STRING);} /*string without new line or quote*/ 
{ws}                                    {}
.					{return(OTHER);}
%%

