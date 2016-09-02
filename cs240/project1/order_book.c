#include <stdio.h>
#include "list.h"
#include "hash.h"
#include "default_hash.h"

/*
 * Default print function. Custom printers should adhere
 * to this format.
 */
#define SIZE 113
void printOrderData(struct order *order_data, FILE *out){
	fprintf(out, "%d %c %d %f\n", 
					 getOrderId(order_data), 
					 getOrderSide(order_data),
					 getOrderQty(order_data), 
					 getOrderPrice(order_data));
}

	int funcHash(int x){
	
		return x%SIZE;

}

int main(int argc, char *argv[]) {
	FILE* f=stdin;
	FILE* o=stdout;
	int db=0;	
	int hb=0;
	int i;
	for(i=0; i<argc;i++){
	if(strcmp(argv[i],"-i")==0){	
		f=fopen(argv[i+1],"r");	
	}
	if(strcmp(argv[i],"-o")==0){
		o=fopen(argv[i+1],"w");
	}
	if(strcmp(argv[i],"-h")==0){	
		hb=1;
	}
	if(strcmp(argv[i],"-dh")==0){
		db=1;
	}
}
struct hashStorage* hash;
if(db){
hash= createHash(SIZE, default_hash,printOrderData);
}else if(hb){
hash= createHash(SIZE, funcHash,printOrderData);
}else if(hb==0 && db==0){
hash= createHash(SIZE, NULL, printOrderData);
}
char c;
char sym[4];
struct order temp;
while((c=fgetc(f))!=EOF){
	
	if(c=='A'){
		fscanf(f, " %d %c %s %d %lf\n", &(temp.id), &(temp.side), sym, &(temp.quantity), &(temp.price));
		addOrder(hash,&temp);
		}
	if(c=='X'){
		fscanf(f, " %d %s\n", &(temp.id), sym);
		cancelOrder(hash,&temp);
		}
	if(c=='T'){
		fscanf(f, " %d %s %d\n", &(temp.id), sym, &(temp.quantity));
		reduceOrderQty(hash,&temp);
		}
	if(c=='C'){
		fscanf(f, " %d %s %d\n", &(temp.id), sym, &(temp.quantity));
		reduceOrderQty(hash,&temp);
		}
	if(c=='R'){
		fscanf(f, " %d %s %d %lf\n", &(temp.id), sym, &(temp.quantity), &(temp.price));
		changeOrder(hash,&temp);
		}
		
		}
	printOrderBook(hash,o);
	fclose(f);
	fclose(o);
	freeOrderBook(&hash);	
  return 0;
}
