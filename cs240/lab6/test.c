/*******************************************************************************
 *  test.c
 *
 *  Created on: Feb 24, 2013
 *  CS240 lab6, Spring  2013
 *
 *  Main source file to parse the arguments and call the encryption function 
/********************************************************************************/
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include "cipher.h"

/* number of bytes read from the input */
int bytesRead;

extern char* encryptNames[ENC_IMPLS];
extern encryptFunc encrypts[ENC_IMPLS];

/*
 * main implementation function
 */
int main(int argc, char *argv[]) { 
	


 	int offset=atoi(argv[2]);

	char output[1025];
	char input[1025];
	char *inputer = input;
	char *outputer= output;

 	if(strcmp(argv[1],"cesar")==0){
	
		
		while((bytesRead = read(0, inputer, 1))!=0){
	
		cipher(cesar,inputer,offset,outputer);

		write(1,outputer,1);

		}
	}

	else if(strcmp(argv[1],"xor")==0){
		
		while((bytesRead = read(0, inputer, 1))!=0){

		cipher(xor,inputer,offset,outputer);

		write(1,outputer,1);
		}
	}
	else if(strcmp(argv[1],"rotate")==0){

		while((bytesRead = read(0, inputer, 1))!=0){
		
		cipher(rotate,inputer,offset,outputer);

		write(1,outputer,1);
	}

  
}
return 0;
}

