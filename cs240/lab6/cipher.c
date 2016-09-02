/**********************************************************************
 *  cipher.c
 *
 *  Created on: Feb 24, 2013
 *  CS240 lab6: Spring,  2013
 *
 *  source file containing implementation for encryption.  
 **********************************************************************/

#include <stdio.h>
#include <string.h>
#include "cipher.h"

/* the number of bytes read from the stdin */
extern int bytesRead;

char* encryptNames[ENC_IMPLS] = {"cesar","xor","rotate"};

encryptFunc encrypts[ENC_IMPLS] = {&cesar,&xor,&rotate};


/* cesar function
 * -----------------
 *
 * cesar function is given the input and the offset. The function returns the result in the
 * output string.
 */
void cesar(unsigned char* input, char offset, unsigned char* output) {
		*output = (*input + offset) % 256;	
	}
/* xor function
 * --------------
 * The xor function takes as input the ”input” pointer and a key. The unsigned char pointed
 * by the input pointer is converted using the XOR cipher by performing the XOR between the 
 * input unsigned byte and the key before storing the result (unsigned) to output.
 */
void xor(unsigned char* input, char offset, unsigned char* output) {
  		*output = *input ^ offset;
}

/* rotate function
 * ---------------
 * The rotate function takes as ”input” as a pointer to a single unsigned char. 
 * The integer parameter, oﬀset, indicates both the direction and the number of positions to 
 * rotate the given char. A negative oﬀset means rotating to the left in a circle. E.g. an 
 * oﬀset -n means rotate the unsigned byte to the left (in a circle) by n bit positions. 
 * Similarly, a positive oﬀset means rotating to the right in a circle. A zero oﬀset will 
 * have no eﬀect. For the purpose of this assignment, you can assume the oﬀset to be between
 * -7 and 7. (Note: in general, one can deﬁne any oﬀset value, but use modulo 8 to determine
 * the eﬀective oﬀset.) The result must be stored in the output. 
 */
void rotate(unsigned char* input, char offset, unsigned char* output) {
  
	if(offset>0){
		unsigned char a,b;
		a = *input << (8-(offset));
		b = *input >> offset;
		*output = a|b;
	}
	else if(offset<0){
		unsigned char a,b;
		offset=((offset)*(-1));
		a = *input >> (8-(offset));
		b = *input << offset;	
		*output = a|b;
	}
	else if(offset==0){
		*output = *input;
	}	
}
/* cipher function will be the main interface to do the encryption */
void cipher (encryptFunc func, unsigned char* input, char offset, 
                                unsigned char* output) { 	
  	(func)(input,offset,output);
}
