/* A Bison parser, made by GNU Bison 2.4.3.  */

/* Skeleton implementation for Bison's Yacc-like parsers in C
   
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

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.4.3"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1

/* Using locations.  */
#define YYLSP_NEEDED 0



/* Copy the first part of user declarations.  */

/* Line 189 of yacc.c  */
#line 1 "parser.y"

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
	bool errPrint=false;
	int curr_num = 0;
	int curr_type = 0;
	int line__num = 0;
	int have_print=0;
	char* curr_string;
	char* globalString;
	char* objid;
	char* arrayid;
	map<string, struct table*> symbolTable;
	//struct table* s = (struct *table)malloc(sizeof(struct table));
	// fill in type and data for variable
	// symbolTable[currentID] = s;
	

/* Line 189 of yacc.c  */
#line 121 "y.tab.c"

/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 1
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Enabling the token table.  */
#ifndef YYTOKEN_TABLE
# define YYTOKEN_TABLE 0
#endif


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

/* Line 214 of yacc.c  */
#line 51 "parser.y"

		char *string_val;
		int num_val;
	


/* Line 214 of yacc.c  */
#line 242 "y.tab.c"
} YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif


/* Copy the second part of user declarations.  */


/* Line 264 of yacc.c  */
#line 254 "y.tab.c"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(msgid) dgettext ("bison-runtime", msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(msgid) msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(e) ((void) (e))
#else
# define YYUSE(e) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(n) (n)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int yyi)
#else
static int
YYID (yyi)
    int yyi;
#endif
{
  return yyi;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#     ifndef _STDLIB_H
#      define _STDLIB_H 1
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined _STDLIB_H \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef _STDLIB_H
#    define _STDLIB_H 1
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  YYSIZE_T yyi;				\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (YYID (0))
#  endif
# endif

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)				\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack_alloc, Stack, yysize);			\
	Stack = &yyptr->Stack_alloc;					\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  14
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   146

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  40
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  20
/* YYNRULES -- Number of rules.  */
#define YYNRULES  61
/* YYNRULES -- Number of states.  */
#define YYNSTATES  138

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   294

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint8 yyprhs[] =
{
       0,     0,     3,     7,    10,    11,    17,    19,    28,    40,
      43,    48,    55,    63,    65,    72,    76,    77,    83,    86,
      87,    91,    94,    99,   106,   113,   115,   119,   120,   122,
     124,   130,   138,   139,   143,   149,   150,   153,   154,   158,
     162,   166,   170,   174,   178,   181,   183,   187,   191,   193,
     197,   201,   203,   205,   207,   209,   213,   218,   222,   224,
     226,   228
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      41,     0,    -1,    28,    42,    59,    -1,    43,    42,    -1,
      -1,    25,    30,    49,    31,    58,    -1,    44,    -1,    11,
      47,    33,    26,    43,    26,    34,    58,    -1,    13,    33,
      26,    43,    26,    34,    26,    11,    47,    27,    58,    -1,
      48,    58,    -1,     4,    24,    50,    58,    -1,     4,    38,
       4,    24,    50,    58,    -1,     4,    35,    50,    36,    24,
      50,    58,    -1,    26,    -1,     9,    47,    33,    45,    34,
      46,    -1,    26,    43,    45,    -1,    -1,    10,    33,    45,
      34,    46,    -1,    10,    44,    -1,    -1,    30,    54,    31,
      -1,     8,     4,    -1,     8,     4,    24,    50,    -1,     8,
       4,    24,    35,    52,    36,    -1,     8,     4,    24,    33,
      51,    34,    -1,    50,    -1,    50,    32,    49,    -1,    -1,
       5,    -1,    54,    -1,    53,     4,    37,    50,    53,    -1,
      53,     4,    37,    50,    53,    32,    51,    -1,    -1,    53,
      50,    53,    -1,    53,    50,    53,    32,    52,    -1,    -1,
      26,    53,    -1,    -1,    54,    17,    55,    -1,    54,    16,
      55,    -1,    54,    20,    55,    -1,    54,    21,    55,    -1,
      54,    19,    55,    -1,    54,    18,    55,    -1,    18,    55,
      -1,    55,    -1,    55,    14,    56,    -1,    55,    15,    56,
      -1,    56,    -1,    56,    22,    57,    -1,    56,    23,    57,
      -1,    57,    -1,     6,    -1,     4,    -1,     3,    -1,     4,
      38,     4,    -1,     4,    35,    50,    36,    -1,    30,    55,
      31,    -1,    26,    -1,    27,    -1,    29,    -1,    29,    26,
      -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,    59,    59,    62,    63,    66,   127,   128,   129,   130,
     131,   165,   216,   219,   222,   229,   230,   233,   234,   235,
     238,   241,   248,   266,   273,   311,   312,   313,   316,   322,
     325,   343,   361,   364,   365,   366,   369,   371,   374,   390,
     405,   406,   407,   426,   427,   428,   430,   495,   522,   525,
     553,   581,   585,   587,   631,   636,   693,   694,   698,   699,
     702,   703
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "STRING", "ID", "BREAK", "NUM", "ASSIGN",
  "VAR", "IF", "ELSE", "WHILE", "FOR", "DO", "PLUS", "MINUS", "BOOLOR",
  "BOOLAND", "NOT", "BOOLEQUAL", "LESSTHAN", "GREATERTHAN",
  "MULTIPLICATION", "DIVISON", "EQUAL", "WRITING", "NEWLINE", "SEMI",
  "TOP", "END", "LEFTPAREN", "RIGHTPAREN", "COMMA", "LEFTCURLY",
  "RIGHTCURLY", "LEFTBRACE", "RIGHTBRACE", "COLON", "PERIOD", "OTHER",
  "$accept", "goal", "middle", "state", "ifelse", "todo", "after",
  "condition", "declare", "docwrite", "expression", "object", "array",
  "blank", "bool", "math", "T", "argument", "linestop", "eof", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    40,    41,    42,    42,    43,    43,    43,    43,    43,
      43,    43,    43,    43,    44,    45,    45,    46,    46,    46,
      47,    48,    48,    48,    48,    49,    49,    49,    50,    50,
      51,    51,    51,    52,    52,    52,    53,    53,    54,    54,
      54,    54,    54,    54,    54,    54,    55,    55,    55,    56,
      56,    56,    57,    57,    57,    57,    57,    57,    58,    58,
      59,    59
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     3,     2,     0,     5,     1,     8,    11,     2,
       4,     6,     7,     1,     6,     3,     0,     5,     2,     0,
       3,     2,     4,     6,     6,     1,     3,     0,     1,     1,
       5,     7,     0,     3,     5,     0,     2,     0,     3,     3,
       3,     3,     3,     3,     2,     1,     3,     3,     1,     3,
       3,     1,     1,     1,     1,     3,     4,     3,     1,     1,
       1,     2
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     4,     0,     0,     0,     0,     0,     0,     0,    13,
       0,     4,     6,     0,     1,     0,     0,     0,    21,     0,
       0,     0,     0,    27,    60,     2,     3,    58,    59,     9,
      54,    53,    28,    52,     0,     0,     0,    29,    45,    48,
      51,     0,     0,     0,     0,    16,     0,     0,     0,    25,
      61,     0,     0,    44,     0,    10,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    32,    37,
      22,    20,     0,     0,     0,     0,     0,    27,     0,    55,
      57,    39,    38,    43,    42,    40,    41,    46,    47,    49,
      50,     0,     0,    37,     0,     0,     0,     0,    16,    19,
       0,     0,     5,    26,    56,     0,    11,    36,    24,     0,
      23,    37,    15,     0,    14,     0,     0,    12,     0,    33,
      16,    18,     0,     0,    37,    37,     0,     7,     0,    30,
      34,    19,     0,    32,    17,     0,    31,     8
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int8 yydefgoto[] =
{
      -1,     2,    10,    11,    12,    73,   114,    20,    13,    48,
      49,    94,    96,    95,    37,    38,    39,    40,    29,    25
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -71
static const yytype_int8 yypact[] =
{
      -3,    65,    32,    46,    30,    28,    28,    27,    45,   -71,
      53,    65,   -71,    25,   -71,     9,     9,    83,    64,     1,
      67,    68,    77,     9,    79,   -71,   -71,   -71,   -71,   -71,
     -71,    -2,   -71,   -71,    14,    14,    25,    92,    42,    57,
     -71,    70,    90,     5,    76,    89,    91,    65,    85,    86,
     -71,     9,   115,    42,    40,   -71,    14,    14,    14,    14,
      14,    14,    14,    14,    14,    14,    96,     9,    20,   -10,
     -71,   -71,    65,    87,    65,    97,    25,     9,    88,   -71,
     -71,    42,    42,    42,    42,    42,    42,    57,    57,   -71,
     -71,     9,    25,    99,    93,   122,    94,     9,    89,   118,
     103,    98,   -71,   -71,   -71,    25,   -71,   -71,   -71,   100,
     -71,    99,   -71,    12,   -71,   101,   105,   -71,     9,   102,
      89,   -71,    25,   125,    99,   -10,   104,   -71,    28,   107,
     -71,   118,   106,    20,   -71,    25,   -71,   -71
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
     -71,   -71,   129,   -25,    29,   -70,    10,    -6,   -71,    66,
     -14,    11,    21,   -63,   126,     7,    23,    34,   -33,   -71
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -38
static const yytype_int16 yytable[] =
{
      21,    36,    41,    55,    30,    31,    97,    33,    30,    31,
      32,    33,    30,    31,    32,    33,    93,    30,    31,    34,
      33,     5,    75,    34,   -37,     1,   -35,    34,   112,    70,
     107,    35,    14,    51,    18,    35,    52,    78,    68,    35,
      69,    53,    54,   102,    35,   120,    93,    98,   119,   100,
     126,    27,    28,    92,    62,    63,    62,    63,    19,   106,
      22,   129,    97,    81,    82,    83,    84,    85,    86,     3,
      15,    80,   117,     4,     5,    23,     6,   105,     7,    64,
      65,    16,    24,   111,    17,    87,    88,    42,    43,   127,
       8,     9,    56,    57,    58,    59,    60,    61,    89,    90,
      45,    46,   137,    47,   124,    50,    66,    71,    56,    57,
      58,    59,    60,    61,    67,    72,    76,    74,    77,    79,
      91,    99,   132,   101,   104,    93,   109,   108,   113,   115,
     110,   123,   116,   135,   125,   122,   128,   118,   131,   133,
      26,   134,   121,   103,   136,    44,   130
};

static const yytype_uint8 yycheck[] =
{
       6,    15,    16,    36,     3,     4,    69,     6,     3,     4,
       5,     6,     3,     4,     5,     6,    26,     3,     4,    18,
       6,     9,    47,    18,     4,    28,    36,    18,    98,    43,
      93,    30,     0,    35,     4,    30,    38,    51,    33,    30,
      35,    34,    35,    76,    30,    33,    26,    72,   111,    74,
     120,    26,    27,    67,    14,    15,    14,    15,    30,    92,
      33,   124,   125,    56,    57,    58,    59,    60,    61,     4,
      24,    31,   105,     8,     9,    30,    11,    91,    13,    22,
      23,    35,    29,    97,    38,    62,    63,     4,    24,   122,
      25,    26,    16,    17,    18,    19,    20,    21,    64,    65,
      33,    33,   135,    26,   118,    26,    36,    31,    16,    17,
      18,    19,    20,    21,    24,    26,    31,    26,    32,     4,
      24,    34,   128,    26,    36,    26,     4,    34,    10,    26,
      36,    26,    34,    27,    32,    34,    11,    37,    34,    32,
      11,   131,   113,    77,   133,    19,   125
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,    28,    41,     4,     8,     9,    11,    13,    25,    26,
      42,    43,    44,    48,     0,    24,    35,    38,     4,    30,
      47,    47,    33,    30,    29,    59,    42,    26,    27,    58,
       3,     4,     5,     6,    18,    30,    50,    54,    55,    56,
      57,    50,     4,    24,    54,    33,    33,    26,    49,    50,
      26,    35,    38,    55,    55,    58,    16,    17,    18,    19,
      20,    21,    14,    15,    22,    23,    36,    24,    33,    35,
      50,    31,    26,    45,    26,    43,    31,    32,    50,     4,
      31,    55,    55,    55,    55,    55,    55,    56,    56,    57,
      57,    24,    50,    26,    51,    53,    52,    53,    43,    34,
      43,    26,    58,    49,    36,    50,    58,    53,    34,     4,
      36,    50,    45,    10,    46,    26,    34,    58,    37,    53,
      33,    44,    34,    26,    50,    32,    45,    58,    11,    53,
      52,    34,    47,    32,    46,    27,    51,    58
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  However,
   YYFAIL appears to be in use.  Nevertheless, it is formally deprecated
   in Bison 2.4.2's NEWS entry, where a plan to phase it out is
   discussed.  */

#define YYFAIL		goto yyerrlab
#if defined YYFAIL
  /* This is here to suppress warnings from the GCC cpp's
     -Wunused-macros.  Normally we don't worry about that warning, but
     some users do, and we want to make it easy for users to remove
     YYFAIL uses, which will produce warnings from Bison 2.5.  */
#endif

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      yytoken = YYTRANSLATE (yychar);				\
      YYPOPSTACK (1);						\
      goto yybackup;						\
    }								\
  else								\
    {								\
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))


#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#define YYRHSLOC(Rhs, K) ((Rhs)[K])
#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)				\
    do									\
      if (YYID (N))                                                    \
	{								\
	  (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;	\
	  (Current).first_column = YYRHSLOC (Rhs, 1).first_column;	\
	  (Current).last_line    = YYRHSLOC (Rhs, N).last_line;		\
	  (Current).last_column  = YYRHSLOC (Rhs, N).last_column;	\
	}								\
      else								\
	{								\
	  (Current).first_line   = (Current).last_line   =		\
	    YYRHSLOC (Rhs, 0).last_line;				\
	  (Current).first_column = (Current).last_column =		\
	    YYRHSLOC (Rhs, 0).last_column;				\
	}								\
    while (YYID (0))
#endif


/* YY_LOCATION_PRINT -- Print the location on the stream.
   This macro was not mandated originally: define only if we know
   we won't break user code: when these are the locations we know.  */

#ifndef YY_LOCATION_PRINT
# if defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL
#  define YY_LOCATION_PRINT(File, Loc)			\
     fprintf (File, "%d.%d-%d.%d",			\
	      (Loc).first_line, (Loc).first_column,	\
	      (Loc).last_line,  (Loc).last_column)
# else
#  define YY_LOCATION_PRINT(File, Loc) ((void) 0)
# endif
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
	break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
#else
static void
yy_stack_print (yybottom, yytop)
    yytype_int16 *yybottom;
    yytype_int16 *yytop;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yyrule)
    YYSTYPE *yyvsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       		       );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif



#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into YYRESULT an error message about the unexpected token
   YYCHAR while in state YYSTATE.  Return the number of bytes copied,
   including the terminating null byte.  If YYRESULT is null, do not
   copy anything; just return the number of bytes that would be
   copied.  As a special case, return 0 if an ordinary "syntax error"
   message will do.  Return YYSIZE_MAXIMUM if overflow occurs during
   size calculation.  */
static YYSIZE_T
yysyntax_error (char *yyresult, int yystate, int yychar)
{
  int yyn = yypact[yystate];

  if (! (YYPACT_NINF < yyn && yyn <= YYLAST))
    return 0;
  else
    {
      int yytype = YYTRANSLATE (yychar);
      YYSIZE_T yysize0 = yytnamerr (0, yytname[yytype]);
      YYSIZE_T yysize = yysize0;
      YYSIZE_T yysize1;
      int yysize_overflow = 0;
      enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
      char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
      int yyx;

# if 0
      /* This is so xgettext sees the translatable formats that are
	 constructed on the fly.  */
      YY_("syntax error, unexpected %s");
      YY_("syntax error, unexpected %s, expecting %s");
      YY_("syntax error, unexpected %s, expecting %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s");
# endif
      char *yyfmt;
      char const *yyf;
      static char const yyunexpected[] = "syntax error, unexpected %s";
      static char const yyexpecting[] = ", expecting %s";
      static char const yyor[] = " or %s";
      char yyformat[sizeof yyunexpected
		    + sizeof yyexpecting - 1
		    + ((YYERROR_VERBOSE_ARGS_MAXIMUM - 2)
		       * (sizeof yyor - 1))];
      char const *yyprefix = yyexpecting;

      /* Start YYX at -YYN if negative to avoid negative indexes in
	 YYCHECK.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;

      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yycount = 1;

      yyarg[0] = yytname[yytype];
      yyfmt = yystpcpy (yyformat, yyunexpected);

      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
	if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
	  {
	    if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
	      {
		yycount = 1;
		yysize = yysize0;
		yyformat[sizeof yyunexpected - 1] = '\0';
		break;
	      }
	    yyarg[yycount++] = yytname[yyx];
	    yysize1 = yysize + yytnamerr (0, yytname[yyx]);
	    yysize_overflow |= (yysize1 < yysize);
	    yysize = yysize1;
	    yyfmt = yystpcpy (yyfmt, yyprefix);
	    yyprefix = yyor;
	  }

      yyf = YY_(yyformat);
      yysize1 = yysize + yystrlen (yyf);
      yysize_overflow |= (yysize1 < yysize);
      yysize = yysize1;

      if (yysize_overflow)
	return YYSIZE_MAXIMUM;

      if (yyresult)
	{
	  /* Avoid sprintf, as that infringes on the user's name space.
	     Don't have undefined behavior even if the translation
	     produced a string with the wrong number of "%s"s.  */
	  char *yyp = yyresult;
	  int yyi = 0;
	  while ((*yyp = *yyf) != '\0')
	    {
	      if (*yyp == '%' && yyf[1] == 's' && yyi < yycount)
		{
		  yyp += yytnamerr (yyp, yyarg[yyi++]);
		  yyf += 2;
		}
	      else
		{
		  yyp++;
		  yyf++;
		}
	    }
	}
      return yysize;
    }
}
#endif /* YYERROR_VERBOSE */


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yymsg, yytype, yyvaluep)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  YYUSE (yyvaluep);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
	break;
    }
}

/* Prevent warnings from -Wmissing-prototypes.  */
#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */


/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;

/* Number of syntax errors so far.  */
int yynerrs;



/*-------------------------.
| yyparse or yypush_parse.  |
`-------------------------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{


    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       `yyss': related to states.
       `yyvs': related to semantic values.

       Refer to the stacks thru separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yytoken = 0;
  yyss = yyssa;
  yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */
  yyssp = yyss;
  yyvsp = yyvs;

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;

	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),
		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss_alloc, yyss);
	YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yyn == YYPACT_NINF)
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yyn == 0 || yyn == YYTABLE_NINF)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  *++yyvsp = yylval;

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 5:

/* Line 1464 of yacc.c  */
#line 67 "parser.y"
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
		}else if(type==4){
			int temp = curr_nums.top();
			curr_nums.pop();
			if(temp==1){//true
				char * outer = strdup("True");
				output.push(outer);
			}
		}else if(type==0){
			char * temp = strdup("undefined");
			output.push(temp);
		}
		//printf("%d",errPrint);
	}for(int i=0;i<size;i++){
		string temp = output.top();///////right here
		char * printer = strdup(temp.c_str());
		//char* temp1 = output.top();
		output.pop();
		if(!errPrint){
			printf("%s",printer);
			have_print++;
		}
		//have_print++;
	}
	line__num ++;

}
    break;

  case 9:

/* Line 1464 of yacc.c  */
#line 130 "parser.y"
    {line__num ++;}
    break;

  case 10:

/* Line 1464 of yacc.c  */
#line 131 "parser.y"
    {struct table* s=(struct table*)malloc(sizeof(struct table));
	errPrint = false;// same statement
	//printf("%d",line__num);
	int type = curr_types.top();
	if(struct table* ts = symbolTable[(yyvsp[(1) - (4)].string_val)]){
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
		symbolTable[(yyvsp[(1) - (4)].string_val)] = s;
		curr_types.pop();
	}else{
		if(!errPrint){
			errPrint=true;
		sprintf(globalString, "Line %d, %s undeclared", line__num+1,(yyvsp[(1) - (4)].string_val));
		yyerror(globalString);
		}
	}
	line__num ++;

}
    break;

  case 11:

/* Line 1464 of yacc.c  */
#line 165 "parser.y"
    {struct table* s=(struct table*)malloc(sizeof(struct table));
	char * per = strdup(".");
	char * tid = strdup((yyvsp[(1) - (6)].string_val));
	if(struct table* op = symbolTable[(yyvsp[(1) - (6)].string_val)]){
		int checker = op->type;
		if(checker!=3){
			sprintf(globalString, "Line %d, type violation", line__num+1);
			yyerror(globalString);

		}
		strcat(tid,per);
		strcat(tid,(yyvsp[(3) - (6)].string_val));
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
    break;

  case 12:

/* Line 1464 of yacc.c  */
#line 216 "parser.y"
    {
	
}
    break;

  case 13:

/* Line 1464 of yacc.c  */
#line 219 "parser.y"
    {line__num ++;}
    break;

  case 14:

/* Line 1464 of yacc.c  */
#line 223 "parser.y"
    {curr_nums.pop();
	curr_types.pop();
	}
    break;

  case 21:

/* Line 1464 of yacc.c  */
#line 241 "parser.y"
    {struct table* s=(struct table*)malloc(sizeof(struct table));
	s->type = 0;
	symbolTable[(yyvsp[(2) - (2)].string_val)] =s;
	//printf("kept going\n");
	//errPrint=false;

}
    break;

  case 22:

/* Line 1464 of yacc.c  */
#line 248 "parser.y"
    { struct table* s=(struct table*)malloc(sizeof(struct table));
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
	symbolTable[(yyvsp[(2) - (4)].string_val)]=s;
	curr_types.pop();
	//errPrint=false;
}
    break;

  case 23:

/* Line 1464 of yacc.c  */
#line 267 "parser.y"
    {arrayid = strdup((yyvsp[(2) - (6)].string_val));
	struct table* s=(struct table*)malloc(sizeof(struct table));
	s->type=4;
	symbolTable[(yyvsp[(2) - (6)].string_val)]=s;
}
    break;

  case 24:

/* Line 1464 of yacc.c  */
#line 274 "parser.y"
    { objid = strdup((yyvsp[(2) - (6)].string_val));
	struct table* s=(struct table*)malloc(sizeof(struct table));
	struct table* in=(struct table*)malloc(sizeof(struct table));
	char* per = strdup(".");
	char* temp;
	char* temp1 = strdup((yyvsp[(2) - (6)].string_val));
	char* oid;
	s->type=3;
	symbolTable[(yyvsp[(2) - (6)].string_val)] =s;
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
		temp1 = strdup((yyvsp[(2) - (6)].string_val));

	}
	//	errPrint=false;
}
    break;

  case 28:

/* Line 1464 of yacc.c  */
#line 317 "parser.y"
    {
	curr_type = 2;
	curr_types.push(2);
	char * br = strdup("<br />");
	curr_strings.push(br);}
    break;

  case 30:

/* Line 1464 of yacc.c  */
#line 326 "parser.y"
    {struct table* s=(struct table*)malloc(sizeof(struct table));
	//printf("no comma %s\n",objid);
	curr_id.push((yyvsp[(2) - (5)].string_val));
	//int type = curr_types.top();
	//if(type==1){ //int variable
	//	curr_num = curr_nums.top();
	//	curr_nums.pop();
	//}else if(type==2){ //string variable
	//	curr_string = curr_strings.top();
	//	curr_strings.pop(;
	//}
	//s->type = type;
	//s->numbers = curr_num;
	//s->data = curr_string;
	//symbolTable[$2]=s;
	//curr_types.pop();
}
    break;

  case 31:

/* Line 1464 of yacc.c  */
#line 344 "parser.y"
    {struct table* s=(struct table*)malloc(sizeof(struct table));
	curr_id.push((yyvsp[(2) - (7)].string_val));
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
    break;

  case 36:

/* Line 1464 of yacc.c  */
#line 370 "parser.y"
    {line__num++;}
    break;

  case 38:

/* Line 1464 of yacc.c  */
#line 374 "parser.y"
    {
		int check = curr_types.top();
	curr_types.pop();
	int check2 = curr_types.top();
	curr_types.pop();
	if(check==1&&check2==1){
		int temp = curr_nums.top();
		curr_nums.pop();
		int temp2 = curr_nums.top();
		curr_nums.pop();
	}else if(check==2&&check2==2){
		curr_strings.top();
		curr_strings.pop();
		}
	}
    break;

  case 39:

/* Line 1464 of yacc.c  */
#line 390 "parser.y"
    {
	int check = curr_types.top();
	curr_types.pop();
	int check2 = curr_types.top();
	curr_types.pop();
	if(check==1&&check2==1){
		int temp = curr_nums.top();
		curr_nums.pop();
		int temp2 = curr_nums.top();
		curr_nums.pop();
	}else if(check==2&&check2==2){
		curr_strings.top();
		curr_strings.pop();
		}
	}
    break;

  case 42:

/* Line 1464 of yacc.c  */
#line 407 "parser.y"
    {
	int check = curr_types.top();
	curr_types.pop();
	int check2 = curr_types.top();
	curr_types.pop();
	if(check==1&&check2==1){//both int
		int temp = curr_nums.top();
		curr_nums.pop();
		int temp2 = curr_nums.top();
		curr_nums.pop();
		/*if(temp2-temp==0){//true
			curr_types.push(4);
			curr_nums.push(1);
		}*/
	}else if(check==2&&check2==2){
		curr_strings.pop();
		curr_strings.pop();
	}
}
    break;

  case 46:

/* Line 1464 of yacc.c  */
#line 430 "parser.y"
    {
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
				if(!errPrint){
					errPrint=true;
					sprintf(globalString, "Line %d, type violation", line__num+1);
					yyerror(globalString);
				}
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
				if(!errPrint){
					errPrint=true;
					sprintf(globalString, "Line %d, type violation", line__num+1);
					yyerror(globalString);
				}
				curr_types.push(0);
			}else{
				char* temp = curr_strings.top();
				curr_strings.pop();
				char* temp2 = curr_strings.top();
				curr_strings.pop();
				if(strcmp(temp,"<br />")==0||strcmp(temp2,"<br />")==0){
					if(!errPrint){
						errPrint=true;
						sprintf(globalString, "Line %d, type violation", line__num+1);
						yyerror(globalString);
					}
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
    break;

  case 47:

/* Line 1464 of yacc.c  */
#line 495 "parser.y"
    {
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
    break;

  case 49:

/* Line 1464 of yacc.c  */
#line 525 "parser.y"
    {
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
    break;

  case 50:

/* Line 1464 of yacc.c  */
#line 553 "parser.y"
    {
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
    break;

  case 52:

/* Line 1464 of yacc.c  */
#line 585 "parser.y"
    {	curr_nums.push((yyvsp[(1) - (1)].num_val));
	curr_types.push(1);}
    break;

  case 53:

/* Line 1464 of yacc.c  */
#line 588 "parser.y"
    {char * idtemp = strdup((yyvsp[(1) - (1)].string_val));
	//printf("idtemp %s\n",idtemp);
	if(struct table* ts = symbolTable[(yyvsp[(1) - (1)].string_val)]){
		//printf("thise");
		//printf("why are we here\n");
		int tes = ts->type;
		//printf("tes%d\n",tes);
		if(tes==3){ //object is not allowed as variable
			sprintf(globalString, "Line %d, type violation", line__num+1);
			yyerror(globalString); 
			curr_types.push(0);
		}else if(tes==1){
			//printf("just checking");
			struct table* ts = symbolTable[(yyvsp[(1) - (1)].string_val)];
			int temp = ts->numbers;
			curr_types.push(1);
			curr_nums.push(temp);
		}else if(tes==2){
			curr_types.push(2);
			struct table* ts = symbolTable[(yyvsp[(1) - (1)].string_val)];
			char * temp = ts->data;
			//printf("temp %s\n",temp);
			curr_strings.push(temp);
			curr_id.push((yyvsp[(1) - (1)].string_val));//added while working with doc.write check here for other id errors
		}else if(tes==0){//variable has no value and can't be used in expression
			if(!errPrint){
				errPrint=true;
				fprintf(stderr, "Line %d, %s has no value", line__num+1,(yyvsp[(1) - (1)].string_val));
				yyerror(globalString); 
				//char* err = strcat($1," has no value");
				//yyerror(err);
			}
			curr_types.push(0);
		}
	}else{						if(!errPrint){
		errPrint=true;
		sprintf(globalString, "Line %d, type violation",line__num+1);
		yyerror(globalString);
	}
	curr_types.push(0);

	}
}
    break;

  case 54:

/* Line 1464 of yacc.c  */
#line 631 "parser.y"
    {
	//printf("only string\n");
	curr_strings.push((yyvsp[(1) - (1)].string_val));
	curr_types.push(2);
}
    break;

  case 55:

/* Line 1464 of yacc.c  */
#line 636 "parser.y"
    {
	char * temp = strdup((yyvsp[(1) - (3)].string_val));
	char * per = strdup(".");
	strcat(temp,per);
	strcat(temp,(yyvsp[(3) - (3)].string_val));
	//printf("id.id %s\n",temp);
	if(struct table* ot = symbolTable[(yyvsp[(1) - (3)].string_val)]){
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
					//printf("%d",errPrint);
					int temp = ts->numbers;
					curr_types.push(1);
					curr_nums.push(temp);
				}else if(tes1==2){
					char* temp = ts->data;
					curr_types.push(2);
					curr_strings.push(temp);
				}else if(tes1==0){
					//printf("nah here");
					char* err = strcat((yyvsp[(1) - (3)].string_val)," has no value");
					yyerror(err);
					curr_types.push(0);
				}
			}else{
				//printf("%d",errPrint);
				if(!errPrint){
					errPrint=true;
					fprintf(stderr, "Line %d, %s has no value", line__num+1,temp);
					yyerror(globalString);
				}
				curr_types.push(0);
			}
		}else{
			sprintf(globalString, "Line %d, typ violation", line__num+1);
			yyerror(globalString);
			curr_types.push(0);
		}
	}else{
		if(!errPrint){
			errPrint=true;
			sprintf(globalString, "Line %d, %s has no value", line__num+1,temp);
			yyerror(globalString);
		}
		curr_types.push(0);
	}
}
    break;

  case 59:

/* Line 1464 of yacc.c  */
#line 699 "parser.y"
    {line__num--;}
    break;



/* Line 1464 of yacc.c  */
#line 2295 "y.tab.c"
      default: break;
    }
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
      {
	YYSIZE_T yysize = yysyntax_error (0, yystate, yychar);
	if (yymsg_alloc < yysize && yymsg_alloc < YYSTACK_ALLOC_MAXIMUM)
	  {
	    YYSIZE_T yyalloc = 2 * yysize;
	    if (! (yysize <= yyalloc && yyalloc <= YYSTACK_ALLOC_MAXIMUM))
	      yyalloc = YYSTACK_ALLOC_MAXIMUM;
	    if (yymsg != yymsgbuf)
	      YYSTACK_FREE (yymsg);
	    yymsg = (char *) YYSTACK_ALLOC (yyalloc);
	    if (yymsg)
	      yymsg_alloc = yyalloc;
	    else
	      {
		yymsg = yymsgbuf;
		yymsg_alloc = sizeof yymsgbuf;
	      }
	  }

	if (0 < yysize && yysize <= yymsg_alloc)
	  {
	    (void) yysyntax_error (yymsg, yystate, yychar);
	    yyerror (yymsg);
	  }
	else
	  {
	    yyerror (YY_("syntax error"));
	    if (yysize != 0)
	      goto yyexhaustedlab;
	  }
      }
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (yyn != YYPACT_NINF)
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;


      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  *++yyvsp = yylval;


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined(yyoverflow) || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
     yydestruct ("Cleanup: discarding lookahead",
		 yytoken, &yylval);
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}



/* Line 1684 of yacc.c  */
#line 706 "parser.y"

//FILE *yyin;
//int yylineno;
void yyerror(const char *s)
{
	if(have_print>0){
		fprintf(stderr,"\n");
	}
	//fprintf(stderr, "Line %d,%s\n",yylineno,s);
	fprintf(stderr,"%s\n",s);
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


