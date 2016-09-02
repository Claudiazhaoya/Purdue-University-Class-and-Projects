#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(int argc, char *argv[]){
	int first = atoi (argv[1]);
	int second = atoi (argv[2]);
	if(first!=second){
		if(first>second){
			printf("%d\n",first);
		}
		else if(second>first){
			printf("%d\n",second);
		}
	}
}
