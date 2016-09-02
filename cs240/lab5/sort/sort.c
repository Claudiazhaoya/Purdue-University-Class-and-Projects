#include <stdio.h>
#include <string.h>
#include "list.h"
#include "sort.h"
/*
 * Compares n1 and n2 based on the words stored in nodes.
 * Return -1 if word in n1 less than word in n2
 * Return  0 if word in n1 is equal to word in n2
 * Return  1 if word in n1 is greater than  word in n2
 */
int wordCmp (struct lnode* n1, struct lnode* n2) {
  
 	if(strcmp(nodeGetWord(n1),nodeGetWord(n2))==0){
		return 0;
	}
	if(strcmp(nodeGetWord(n1),nodeGetWord(n2))==1){
		return 1;
	}
	if(strcmp(nodeGetWord(n1),nodeGetWord(n2))==-1){
		return -1;
	}
}
/*
 * Compares n1 and n2 based on the lines stored in nodes.
 * Return -1 if line in n1 less than line in n2
 * Return  0 if line in n1 is equal to line in n2
 * Return  1 if line in n1 is greater than  line in n2
 */
int lineCmp (struct lnode* n1, struct lnode* n2) {
  
  	if(nodeGetLine(n1)==nodeGetLine(n2)){
		return 0;
	}
	if(nodeGetLine(n1)>nodeGetLine(n2)){
		return 1;
	}
	if(nodeGetLine(n1)<nodeGetLine(n2)){
		return -1;
	}
}
/*
 * Compares n1 and n2 based on the counts stored in nodes.
 * Return -1 if count in n1 less than count in n2
 * Return  0 if count in n1 is equal to count in n2
 * Return  1 if count in n1 is greater than  count in n2
 */
int countCmp (struct lnode* n1, struct lnode* n2) {
  
  	if(nodeGetCount(n1)==nodeGetCount(n2)){
		return 0;
	}
	if(nodeGetCount(n1)>nodeGetCount(n2)){
		return 1;
	}
	if(nodeGetCount(n1)<nodeGetCount(n2)){
		return -1;
	}
}
/*
 * Swap nodes n1 and n2 in the linked list. For eg, if the
 * list looks like: -- n1 -- n2 -- , then after swapping n1
 * with n2, it should look like this: -- n2 -- n1 --.
 * head is the pointer to the head pointer of the linked list.
 */
void swap (struct lnode** head, struct lnode* n1, struct lnode* n2) {
  	if(n1==n2){
		return;
	}
	else if((n1==*head) && (nodeGetNext(n1)==n2)){
		evictNode(head,n2);
		pushNode(head,n2);
	}
	else if((n2==*head && nodeGetNext(n2)==n1)){
		evictNode(head,n1);
		pushNode(head,n1);
	}
	else if((nodeGetNext(n1)==n2)){
		evictNode(head,n1);
		insertNode(head, n2,n1);
	}
	else if((nodeGetPrev(*head,n1)==n2)){
		evictNode(head,n2);
		insertNode(head,n1,n2);
	}

	else{
		struct lnode *temp=nodeGetPrev(*head,n1);
		struct lnode *temp1=nodeGetPrev(*head,n2);

		evictNode(head,n1);
		evictNode(head,n2);
		insertNode(head, temp,n2);
		insertNode(head, temp1,n1);		
 			}
}
/*
 * Sorts the linked list whose head is (*head).
 * Based on this description, complete the function signature of sort
 */
void sortByWord (struct lnode** head) {
	struct lnode *next=NULL;
	struct lnode *node=*head;
	struct lnode *t;
	int count=0;
	int n=0;

	while(node!=NULL){
		count++;
		node=nodeGetNext(node);
	}
while(n<=count){
	t=*head;

	while(nodeGetNext(t)!=NULL){

	if(wordCmp(t,nodeGetNext(t))>0){
		 swap(head,t,nodeGetNext(t));

		 }
		 else{ 
			 t=nodeGetNext(t);
		 }
	}
	
	n++;
}
 
}
void sortByCount (struct lnode** head) {
	struct lnode *next=NULL;
	struct lnode *node=*head;
	struct lnode *t;
	int count=0;
	int n=0;

	while(node!=NULL){
		count++;
		node=nodeGetNext(node);
	}

while(n<=count){
	t=*head;

	while(nodeGetNext(t)!=NULL){

	if(countCmp(t,nodeGetNext(t))>0){
		 swap(head,t,nodeGetNext(t));


		 }
		 else{ 
			 t=nodeGetNext(t);
		 }
	}
	
	n++;
}



  
}

void sortByLine (struct lnode** head) {

	struct lnode *next=NULL;
	struct lnode *node=*head;
	struct lnode *t;
	int count=0;
	int n=0;

	while(node!=NULL){
		count++;
		node=nodeGetNext(node);
	}

while(n<=count){
	t=*head;

	while(nodeGetNext(t)!=NULL){
	
	if(lineCmp(t,nodeGetNext(t))==0){
		if(wordCmp(t,nodeGetNext(t))>0){
			swap(head,t,nodeGetNext(t));
		}
	}

	else if(lineCmp(t,nodeGetNext(t))>0){
		 swap(head,t,nodeGetNext(t));


		 }
		 else{ 
			 t=nodeGetNext(t);
		 }
	}
	
	n++;
}



  
}

