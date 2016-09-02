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

	node1=newNode("1",1);
	node2=newNode("2",1);
	node3=newNode("3",1);
	node4=newNode("4",1);
	node5=newNode("5",1);
	
	pushNode(*head,node1);
	pushNode(*head,node2);
	pushNode(*head,node3);
	pushNode(*head,node4);
	pushNode(*head,node5);
	
	printList(head);

	swap(*head,node2,node4);



	printList(head);
}
