parser:y.tab.c lex.yy.c
	gcc y.tab.c lex.yy.c -o yacc_example -lfl
y.tab.c: parser.y
	bison -y -d -g --verbose parser.y
lex.yy.c:parser.l
	lex parser.l
clean:
	rm -f lex.yy.c y.tab.c
