#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include "list.h"
#include "hash.h"
#include <assert.h>

int giveZero(int x){
	return 0;
	} 
struct hashStorage* createHash(int size, 
                int (*myHash)(int),
                void(*printOrder)(struct order *, FILE *)){
		struct hashStorage* htable= (struct hashStorage*) malloc(sizeof(struct hashStorage)); 
		if(myHash==NULL){
			htable->table = (struct onode**) (calloc(1,sizeof(struct onode*))); 
				htable->size=1;
				htable->funcHash = NULL;
		    }
		else{
			htable->table = (struct onode**)(calloc(size,sizeof(struct onode*)));
			htable->funcHash = myHash;
			htable->size = size;
			}
	htable->printItem = printOrder;		
	return htable;
}
struct onode* addOrder(struct hashStorage* hash, struct order* data){
	struct onode* datainfo = newNode(data);
	int d = (data->id);
	if(hash->funcHash==NULL){	
		pushNode(&(hash->table[0]),datainfo);
		} 
	else {			
	pushNode(&(hash->table[hash->funcHash(data->id)]),datainfo);
	return datainfo; 
}
void cancelOrder(struct hashStorage *hash, struct order* data){	
	if(hash->size==1){
		deleteNode(&(hash->table[0]),getOrderNode(hash->table[0],data->id));
		}
	else{
	deleteNode(&(hash->table[hash->funcHash(data->id)]),
	getOrderNode((hash->table[hash->funcHash(data->id)]),
	getOrderId(data)));
	}
}
void reduceOrderQty(struct hashStorage* hash, struct order* data){
	int n;
	int quant;
	int newquant;
	struct onode* t;
		if(hash->size==1){
			quant = getOrderQty(data);
			t=getOrderNode((hash->table[0]),data->id);
			newquant = getOrderQty(getOrderData(getOrderNode(hash->table[0],data->id)))-quant;
			setOrderQty(t->data,newquant);
			}
	else if(getOrderQty(getOrderData(hash->table[hash->funcHash(data->id)]))== getOrderQty(data)){
		cancelOrder(hash,data);
	}
	else{
		n = getOrderQty(getOrderData(hash->table[hash->funcHash(data->id)]))-(getOrderQty(data));
	setOrderQty(getOrderData(hash->table[hash->funcHash(data->id)]),n);			
}
}
void changeOrder(struct hashStorage* hash, struct order* data){
	double price;
	int quant;
	int newquant;

	quant = getOrderQty(data);
	price = getOrderPrice(data);
	if(hash->size==1){	
		struct onode* nodish= getOrderNode(hash->table[0],getOrderId(data));
		setOrderQty(nodish->data,quant);
		setOrderPrice(nodish->data,price);
	}
	else{
	struct onode* nodish= getOrderNode(hash->table[hash->funcHash(data->id)],getOrderId(data));
	nodish->data->quantity = quant;
	//setOrderQty(nodish->data,quant);
	nodish->data->price = price;
	//setOrderPrice(nodish->data,price);
	}
}
void printOrderBook(struct hashStorage* hash, FILE *out){	
	int n;
	for(n=0;n<getHashSize(hash); n++){
			printList((hash->table[n]),(hash->printItem),out);
}
}
void freeOrderBook(struct hashStorage** hash){	
	int n;
	for(n=0; n<getHashSize(*hash); n++){
		deleteList(&((*hash)->table[n]));
		
	}
}
int getHashSize(struct hashStorage* hash){
	return hash->size;
}
struct onode** getHashTable(struct hashStorage* hash){
	return hash->table;
}
