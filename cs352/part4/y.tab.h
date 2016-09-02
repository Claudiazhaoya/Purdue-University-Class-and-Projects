/* A Bison parser, made by GNU Bison 2.4.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006,
   2009, 2010 Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     STRING = 258,
     ID = 259,
     BREAK = 260,
     NUM = 261,
     ASSIGN = 262,
     VAR = 263,
     IF = 264,
     ELSE = 265,
     WHILE = 266,
     FOR = 267,
     DO = 268,
     PLUS = 269,
     MINUS = 270,
     BOOLOR = 271,
     BOOLAND = 272,
     NOT = 273,
     BOOLEQUAL = 274,
     LESSTHAN = 275,
     GREATERTHAN = 276,
     MULTIPLICATION = 277,
     DIVISON = 278,
     EQUAL = 279,
     WRITING = 280,
     NEWLINE = 281,
     SEMI = 282,
     TOP = 283,
     END = 284,
     LEFTPAREN = 285,
     RIGHTPAREN = 286,
     COMMA = 287,
     LEFTCURLY = 288,
     RIGHTCURLY = 289,
     LEFTBRACE = 290,
     RIGHTBRACE = 291,
     COLON = 292,
     PERIOD = 293,
     OTHER = 294
   };
#endif
/* Tokens.  */
#define STRING 258
#define ID 259
#define BREAK 260
#define NUM 261
#define ASSIGN 262
#define VAR 263
#define IF 264
#define ELSE 265
#define WHILE 266
#define FOR 267
#define DO 268
#define PLUS 269
#define MINUS 270
#define BOOLOR 271
#define BOOLAND 272
#define NOT 273
#define BOOLEQUAL 274
#define LESSTHAN 275
#define GREATERTHAN 276
#define MULTIPLICATION 277
#define DIVISON 278
#define EQUAL 279
#define WRITING 280
#define NEWLINE 281
#define SEMI 282
#define TOP 283
#define END 284
#define LEFTPAREN 285
#define RIGHTPAREN 286
#define COMMA 287
#define LEFTCURLY 288
#define RIGHTCURLY 289
#define LEFTBRACE 290
#define RIGHTBRACE 291
#define COLON 292
#define PERIOD 293
#define OTHER 294




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
{

/* Line 1685 of yacc.c  */
#line 51 "parser.y"

		char *string_val;
		int num_val;
	


/* Line 1685 of yacc.c  */
#line 136 "y.tab.h"
} YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


