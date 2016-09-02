#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(int argc, char *argv[]){
	FILE* f=fopen(argv[1],"r");
	FILE* o=fopen(argv[2],"w");
	float data[1];
	int count=0;
	float dec=0;
	int i=0;
	int decimal=0;
	int bin=0;
	int exp=0;
	int array[8] = {0,0,0,0,0,0,0,0};
	while(fread(&data,4,1,f)){
	int left[23] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	int right[23] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		float num=data[0];
		int intnum=(int)num;
		dec=num-intnum;
		if(num>=0){
			fprintf(o,"0 ");
			int d=0;
			while(intnum>=1){
				i++;
				bin=intnum%2;
				intnum=intnum/2;
				left[d]=bin;
				d++;
				}
			int length=i;
			int e=0;
			printf("\n");
			while(dec>0){
				dec=dec*2;
				if(dec>=1){
					right[e]=1;
					dec=dec-1;
					}else{
					right[e]=0;
					}
				e++;
				}
		i=(i-1);
		i=(i+127);
			int b=0;
			while(i>=1){
				exp=i%2;
				i=i/2;
				array[b]=exp;
				b++;
				}
			b=0;
			while(b<8){
			fprintf(o,"%d",array[8-b-1]);
			b++;
			}
				fprintf(o," ");
				int c=1;
			while(c<=23){
				if(c<length){
			
					fprintf(o,"%d",left[length-c-1]);
					}else{
					fprintf(o,"%d",right[c-length]);
					}
			c++;
			}
			fprintf(o,"\n");
			}
		}
	fclose(f);
	fclose(o);
	return 0;
}
