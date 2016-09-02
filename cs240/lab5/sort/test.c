#include <stdio.h>
#include <string.h>
#include "list.h"
#include "sort.h"


void main(){

	struct lnode *head;
		head=NULL;

	struct lnode *node1;
	struct lnode *node2;
	struct lnode *node3;
	struct lnode *node4;
	struct lnode *node5;

	node1=newNode("aplha",5);
	node2=newNode("bravo",2);
	node3=newNode("charle",3);
	node4=newNode("delta",4);
	node5=newNode("echo",5);
	
	pushNode(&head,node1);
	pushNode(&head,node2);
	pushNode(&head,node3);
	pushNode(&head,node4);
	pushNode(&head,node5);
	
	printList(head);
	printf("\n");

	sortByWord(&head);



	printList(head);
}
