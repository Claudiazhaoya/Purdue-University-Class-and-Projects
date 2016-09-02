#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(int argc, char *argv[]){
	FILE* f=fopen(argv[1],"r");
	FILE* o=fopen(argv[2],"w");
	int zip=0;
	int pop=0;
	int first=0;
	int final=0;
	while(fscanf(f,"%d %d\n", &zip, &pop)!=EOF){
		first= pop<<8 &0xFF00FF00 |
		pop>>8 &0x00FF00FF;
		
  		final= first<<16 &0xFFFF0000 |
		first>>16 &0x0000FFFF;
		fwrite(&final, 1, sizeof(final),o);
	}
	fclose(f);
	fclose(o);
}
