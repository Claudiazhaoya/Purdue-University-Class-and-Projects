#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(int argc, char *argv[]){
	FILE* f=fopen(argv[1],"r");
	FILE* o=fopen(argv[2],"w");
	int zip=0;
	int pop=0;
	while(fscanf(f," %d %d\n", &zip, &pop)!=EOF){
		fwrite(&pop , 1, sizeof(pop),o);
	}
	fclose(f);
	fclose(o);
}
