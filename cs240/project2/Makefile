all:
	gcc -g -c int_search.c
	gcc -o int_search int_search.c
	gcc -g -c int_little_endian.c
	gcc -o int_little_endian int_little_endian.c
	gcc -g -c int_big_endian.c
	gcc -o int_big_endian int_big_endian.c
	gcc -g -c float_search.c
	gcc -o float_search float_search.c
	gcc -g -c float_little_endian.c
	gcc -o float_little_endian float_little_endian.c
	gcc -g -c float_big_endian.c
	gcc -o float_big_endian float_big_endian.c
	gcc -g -c float_man_exp.c
	gcc -o float_man_exp float_man_exp.c

.PHONY: tar

tar:
	tar zcf turnin.tgz Makefile int_search.c int_little_endian.c int_big_endian.c float_search.c float_little_endian.c float_big_endian.c float_man_exp.c
