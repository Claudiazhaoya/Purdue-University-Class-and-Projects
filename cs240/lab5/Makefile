all:
	gcc -g -c list.c; ar rcu liblist.a list.o
	gcc -g -o test test.c -L. -llist
