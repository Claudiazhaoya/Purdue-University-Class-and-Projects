#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(int argc, char *argv[]){
	FILE* f=fopen(argv[1],"r");
	FILE* o=fopen(argv[2],"w");
	int month=0;
	int day=0;
	int year=0;
	int hour=0;
	int minute=0;
	float level=0;
	
	while(fscanf(f," %d/%d/%d/%d/%d %f\n", &month, &day, &year, &hour, &minute, &level)!=EOF){
		fwrite(&level , 1, sizeof(level),o);
		}
		fclose(f);
		fclose(o);
	}
