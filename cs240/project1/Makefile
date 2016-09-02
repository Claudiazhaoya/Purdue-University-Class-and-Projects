CC=gcc

all: order_book
	
hash.o: hash.c
	$(CC) -g -c hash.c; ar rcu libhash.a hash.o

order_book: order_book.c hash.o
	$(CC) -g -o orderbook order_book.c -L. -llist -lhash -ldefault_hash
		
tar:
	tar zcf turnin.tgz Makefile order_book.c hash.c
	
clean:
	rm *.o
