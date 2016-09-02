#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
int main(int argc, char *argv[]){
FILE* in=fopen(argv[1],"r");
char chunk[30];
char c;
int p=0;
int d=0;
int keyword=7;
int buffer=30;
while((c=fgetc(in))!=EOF){
	chunk[p]=c;
	if((c==' '||c=='\t'||c=='\n')&&(d+keyword>buffer)){
	chunk[p]='\0';
		break;
			}
			printf("%s",chunk);
			d++;
			p++;
		}
	}
	
