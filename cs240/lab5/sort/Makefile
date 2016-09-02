all:
	gcc -g -c sort.c; ar rcu libsort.a sort.o
	gcc -g -c list.c; ar rcu liblist.a list.o
	gcc -g -o test test.c -L. -llist -lsort
