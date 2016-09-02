//
// Implement the List class
//

#include <stdio.h>
#include "List.h"
#include <stdlib.h>
#include <stdio.h>

//
// Inserts a new element with value "val" in
// ascending order.
//
void
List::insertSorted( int val )
{
	struct ListNode* node = (struct ListNode*)malloc(sizeof(struct ListNode));
	node -> _value = val;
	node -> _next = NULL;

	struct ListNode *saver = _head;
	struct ListNode *tracker = _head;
	struct ListNode *backer = _head;

	if(_head == NULL){
		_head = node;
	}
	else{
		while(tracker != NULL){
			if((tracker->_value >= val) && (backer->_value <= val)){
				node->_next = (tracker->_next);
				tracker->_next = node;
			}
			backer = tracker;
			tracker = tracker->_next;
		}
	}

		

 
}

//
// Inserts a new element with value "val" at
// the end of the list.
//
void
List::append( int val )
{
	struct ListNode* node = (struct ListNode*)malloc(sizeof(struct ListNode));
	node -> _value = val;
	node -> _next = NULL;

	struct ListNode *tracker = _head;

	if(_head == NULL){
		_head = node;
	}
	else{
		while(tracker->_next !=NULL){
			tracker = tracker->_next;
		}
		tracker->_next = node;
	}


}

//
// Inserts a new element with value "val" at
// the beginning of the list.
//
void
List::prepend( int val )
{
	  struct ListNode* node = (struct ListNode*)malloc(sizeof(struct ListNode));
	  node -> _value = val;
	  node -> _next = NULL;
	  if(_head == NULL){
		  _head = node;
	  }
	  else{
		  node -> _next = _head;
		  _head = node;
	  }
}

// Removes an element with value "val" from List
// Returns 0 if succeeds or -1 if it fails
int 
List:: remove( int val )
{
	struct ListNode *tracker = _head;
	struct ListNode *forDelete =_head;
  	if(_head == NULL){
		return -1;
	}
	else if(_head->_value == val){
		forDelete = _head;
		_head = _head->_next;
		free(forDelete);
		return 0;
	}
		while(tracker->_next != NULL){
			if((tracker->_next)->_value == val){
				forDelete = tracker->_next;
				tracker->_next = (tracker->_next)->_next;
				free(forDelete);
				return 0;
			}
			tracker = tracker->_next;
		}
  return -1;
}

// Prints The elements in the list. 
void
List::print()
{
	struct ListNode *tracker = _head;

  while(tracker->_next != NULL){
	  fprintf(stderr,"%d ",tracker->_value);
	  tracker = tracker->_next;
  }
}

//
// Returns 0 if "value" is in the list or -1 otherwise.
//
int
List::lookup(int val)
{
	struct ListNode *tracker = _head;
  if(_head == NULL){
	  return -1;
  }
  else{
	while(tracker -> _next != NULL){
		if(tracker->_value==val){
			return 0;
		}
		else{
			tracker = tracker->_next;
		}
  	}
  }

  return -1;
}

//
// List constructor
//
List::List()
{
  _head = NULL;
}

//
// List destructor: delete all list elements, if any.
// needed
List::~List()
{
  struct ListNode *tracker = _head;
  while(tracker->_next !=NULL){

	  tracker = _head;
	  _head = _head->_next;
	  free(tracker);
  }
  free(_head);
}

