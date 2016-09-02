#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include"list.h"
struct lnode {
	int line;
	int count;
	char * word;
	struct lnode* next;
	struct lnode* prev;
};
struct lnode* newNode (char* word, int line) {
	struct lnode * newNode = NULL;
	
	newNode = malloc(sizeof(struct lnode));

	newNode->line = line;
	newNode->count = 1;
	newNode->word = strdup(word);
	newNode->next = NULL;
	newNode->prev = NULL;
	return newNode;}
void pushNode (struct lnode** head, struct lnode* node) {
	if(*head==NULL){
		*head=node;
		}
	else{
		node->next=(*head);
		*head=node;
		(node->next)->prev=(*head);
	}	
}
struct lnode* getNode (struct lnode* head, char* word) {
	struct lnode *temp;
	temp=head;

	while( temp != NULL)
	{
		if(strcmp(temp->word,word)==0){
			return temp;
		}
		temp = temp->next;
	}
	return NULL;
}
void evictNode (struct lnode** head, struct lnode* node){

	struct lnode *temp=NULL;

	if(*head==NULL){
		return;
	}

	else if(node==*head){
		if(node->next==NULL){
			node->next=NULL;
			node->prev=NULL;
			*head=NULL;
	
		}else{
			node->next->prev=temp;
			*head=node->next;
			node->next=NULL;
			node->prev=NULL;
		}
	}
	else if(node->next ==NULL){
		(node->prev)->next=NULL;
		node->next=NULL;
		node->prev=NULL;
	}
	else{
		(node->prev)->next= node->next;
		(node->next)->prev= node->prev;
		node->next=NULL;
		node->prev=NULL;
	}
}
void deleteNode (struct lnode** head, struct lnode* node) {

	
	evictNode(head,node);
	free(node->word);
	free(node);
}
struct lnode *nodeGetNext(struct lnode *node) {
	if(node==NULL){
		return NULL;
	}
	else if(node->next!=NULL){
		return node->next;
	}
	else{
	return NULL;
	}
}
char *nodeGetWord(struct lnode *node) {	
	return node->word;
}

int nodeGetLine(struct lnode *node) {	
	return node->line;
}
int nodeGetCount(struct lnode *node) {
	return node->count;
}
void nodeSetCount(struct lnode *node, int count) {
	node->count=count;
}
void nodeSetLine(struct lnode *node, int line) {
	node->line=line;
}
void deleteList(struct lnode **head) {

	struct lnode *temp=*head;
	struct lnode *temp1=NULL;

	if(*head==NULL){
		return;
	}
		
	while(temp!=NULL){
		temp1=temp;
		temp=temp->next;

		free(temp1->word);
		free(temp1);
	}
	*head=NULL;
	
	}
void insertNode (struct lnode** head, struct lnode* prevNode, struct lnode* insertingNode){
		if(prevNode==NULL){
			pushNode(head,insertingNode);
		}
		else{

			(insertingNode->next)=(prevNode->next);
			(prevNode->next)=insertingNode;
			(insertingNode->prev)=prevNode;

			if(insertingNode->next!=NULL){
			(insertingNode->next)->prev =insertingNode;
			}
		}
}
void printList (struct lnode *node){
	if(node==NULL){

			return;
	}
		while(node !=NULL){	
			printf("%s ",(node)->word);
			printf("%d ",(node)->count);
			printf("%d \n",(node)->line);
			node=(node)->next;
		}		
			}
struct lnode *nodeGetPrev(struct lnode*head, struct lnode *node){
	if(node->prev==NULL){
		return NULL;
	}
	else{
		return (node->prev);
	}
}
