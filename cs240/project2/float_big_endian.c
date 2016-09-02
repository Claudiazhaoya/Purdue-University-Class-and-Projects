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
	float big=0;
	float final=0;
	int i=0;
	
	while(fscanf(f," %d/%d/%d/%d/%d %f\n", &month, &day, &year, &hour, &minute, &level)!=EOF){
			char *orig=(char*)&level;
			char *final=(char*)&big;
			
			while(i<4){
				
				final[i]=orig[3-i];
					i++;
		}
		i=0;
		fwrite(&big , 1, sizeof(big),o);
		
	}
	fclose(f);
	fclose(o);
return 0;
}
