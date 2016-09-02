#include "mysort.h"
#include <alloca.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

//
// Sort an array of element of any type
// it uses "compFunc" to sort the elements.
// The elements are sorted such as:
//
// if ascending != 0
//   compFunc( array[ i ], array[ i+1 ] ) <= 0
// else
//   compFunc( array[ i ], array[ i+1 ] ) >= 0
//
// See test_sort to see how to use mysort.
//
void mysort( int n,                      // Number of elements
	     int elementSize,            // Size of each element
	     void * array,               // Pointer to an array
	     int ascending,              // 0 -> descending; 1 -> ascending
	     CompareFunction compFunc )  // Comparison function.
{
	void *start;
	void *second;
	void *swaper = (void*)malloc(elementSize);
	void *saver;
  for(int i = 0; i<n-1;i++){
	  start = array;
	  for(int j = 0; j<(n-i-1);j++){
		  second = start + elementSize;
		  if(ascending == 0){
			  if(compFunc(start,second)<=0){
				saver = start;
			  	memcpy(swaper, second,elementSize);
				memcpy(second,saver,elementSize);
				memcpy(start,swaper,elementSize);
			  }
		  }else{
			  if(compFunc(start,second)>=0){
				  saver = start;
				memcpy(swaper, second,elementSize);
				memcpy(second,saver,elementSize);
				memcpy(start,swaper,elementSize);
		  		}
	  		}
		start += elementSize;
	  }
  }
}

