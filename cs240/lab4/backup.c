#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include "list.h"

struct lnode {
	int line;
	int count;
	char * word;
	struct lnode* next;
};

/**
 * Returns a new linked list node filled in with the given word and line, and
 * sets the count to be 1. Make sure to duplicate the word, as the original word
 * may be modified by the calling function.
 */
struct lnode* newNode (char* word, int line) {
	struct lnode * newNode = NULL;
	
	newNode = malloc(sizeof(struct lnode));

	newNode->line = line;
	newNode->count = 1;
	newNode->word = strdup(word);
	newNode->next = NULL;

	return newNode;
}

/**
 * In a linked list with *head as the head pointer, adds the given node to the
 * front of the list.
 */
void pushNode (struct lnode** head, struct lnode* node) {
	
	if(head==NULL){
		*head=node;
		}
	else{
		node->next=*head;
		*head=node;
	}
	
}

/**
 * Returns the pointer to the node containing the given word in the linked list
 * with head as the head pointer. If a node with the given word cannot be found,
 * the function returns NULL.
 */
struct lnode* getNode (struct lnode* head, char* word) {

	
	struct lnode *temp;
	temp=head;

	while( temp != NULL)
	{
		if(strcmp(temp->word,word)==1){
			return temp;
		}
		temp = temp->next;
	}
	return NULL;
}
/**
 * Removes the specified node from the list, and frees all memory the node is
 * using. Remember if *head is the node being deleted, it must be updated.
 */
void deleteNode (struct lnode** head, struct lnode* node) {
	
	struct	lnode *temp;
	temp=*head;

	if(node==*head){

		*head=node->next;

		free(node->word);
		free(node);	
	}
	else if(node->next==NULL){
	
	while( temp != NULL){
		if(temp->next ==node){
			temp->next=NULL;
		}
		temp = temp->next;
	}
	
	free(node->word);
	free(node);
	}
	else{
		while(temp !=NULL){
			if(temp->next== node){
				temp->next=node->next;
			}
			temp=temp->next;
		}
		free(node->word);
		free(node);
	}

}

/**
 * Simply returns the next node in the list, or NULL if there are no further nodes.
 */
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

/**
 * Simply returns the word in the given node.
 */
char *nodeGetWord(struct lnode *node) {
	
	return node->word;
}

/**
 * Simply returns the line in the given node.
 */
int nodeGetLine(struct lnode *node) {
	
	return node->line;
}

/**
 * Simply returns the count in the given node.
 */
int nodeGetCount(struct lnode *node) {

	return node->count;
}

/**
 * Set the count in the node to be the given count.
 */
void nodeSetCount(struct lnode *node, int count) {

	node->count=count;
}

/**
 * Set the line in the node to be the given line.
 */
void nodeSetLine(struct lnode *node, int line) {

	node->line=line;
}


/**
 * Deletes every node in the list with *head as the head pointer. After calling
 * this function, all memory used by the list should be freed, and *head
 * should be NULL.
 */
void deleteList(struct lnode **head) {

}
	
		

	
