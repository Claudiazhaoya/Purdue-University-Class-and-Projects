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
	float total=0;
	float min=0;
	float max=0;
	float mean=0;
	int count=0;
	while(fscanf(f," %d/%d/%d/%d/%d %f\n", &month, &day, &year, &hour, &minute, &level)!=EOF){
		count++;
		
		if(count==1){
			min=level;
			max=level;
			}
		else if(level<min){
			min=level;
			}
		else if(level>max){
			max=level;
			}
		total=total+level;
		
		}
		mean=total/count;
		fprintf(o, "%f\n",min);
		fprintf(o, "%f\n",max);
		fprintf(o, "%.3f\n",mean);
		fclose(f);
		fclose(o);
	return 0;
	
}
