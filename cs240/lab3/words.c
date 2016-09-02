#include <stdio.h>
#include "utils.h"
#include "words.h"

int readMessage(char* buf) {
int c;
int i=0;
	while((c=getchar())!=EOF)
	{
		if(c=='\n'){
				*(buf+i)=c;
				i++;		
				break;
		}
		else{
		*(buf+i)=c;
		 }
	
		i++;	
	}
	if(c==EOF)
	{
		return EOF;	
	}
	return i;
}

int getNextWordIndex(char* str, int len, int prevEnd){
	int index=0;
	int n;
		for(n=prevEnd;n<len;n++)
		{
			if((getCharType(*(str+n))== 0)|| (getCharType(*(str+n))==1))
				{
				prevEnd=n;
				return n;
				}
		}
			prevEnd=n;
			return -1;
				
} 

void matchAndBlankWord(char* str, int start, int wLen, char* keyword) {

	if(strLen(keyword)==wLen){
		if(strNCmp(str,keyword,wLen)==1){
			replaceChar(str, ' ',wLen);
			}
		}
	}



void lowerCaseString(char* str, int len) {
	
	int n;
	for(n=0;n<len; n++)
	{
		*( str+n) = lowerCaseOf( *( str+n));
	}
		
}


int wordLength(char* str, int strLen, int start) {
	int n=0;
	int i=0;
	for(n+start; n<strLen; n++)
		{
		if((getCharType(*(str+n))!=0)&& (getCharType(*(str+n))!=1))
			{
				break;
			}
		i++;
		}
	return i;	
}

