#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(int argc, char *argv[]){
	FILE* f=fopen(argv[1],"r");
	FILE* o=fopen(argv[2],"w");
	int pop=0;
	int zip=0;
	int max=0;
	int min=0;
	double mean=0;
	double total=0;
	int count=0;
	while(fscanf(f," %d %d\n", &zip, &pop)!=EOF){
		count++;
		if(count==1){
			min=pop;
			max=pop;
			}
		else if(pop<min){
			min=pop;
			}
		else if(pop>max){
			max=pop;
			}
		total=total+pop;
		} 
		mean=total/count;
		fprintf(o, "%d\n",min);
		fprintf(o, "%d\n",max);
		fprintf(o, "%.3f\n",mean); 
		fclose(f);
		fclose(o);
	return 0;
}
