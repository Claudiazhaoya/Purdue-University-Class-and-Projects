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
		//if the number is postive
		if(num>=0){
			fprintf(o,"0 ");
			int d=0;
			while(intnum>=1){
				i++;
				intnum=intnum/2;
				bin=intnum%2;
				
				left[d+1]=bin;
				d++;
				}
			int e=0;
			while(dec>0){
				dec=dec*2;
					if(dec>=1){
						right[22-e]=1;
						dec=dec-1;
						}
					else{
						right[22-e]=0;
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
			int length=b;
			b=0;
			while(b<8){
			fprintf(o,"%d",array[8-b-1]);
			b++;
			}
				fprintf(o," ");
				int c=0;
			while(c<23){
				if(c<length+1){
			
					fprintf(o,"%d",left[length-c]);
					}
				else{
					fprintf(o,"%d",right[23+length-c]);
					}
			c++;
			}
			fprintf(o,"\n");
		
			}
		//if the number is negitve
		/*if(num<0){
			fprintf(o,"1 ");
			intnum=intnum*-1;
			int d=0;
			while(intnum>=1){
				i++;
				intnum=intnum/2;
				bin=intnum%2;
				left[d+1]=bin;
				d++;
				}
			int e=0;
			while(dec>0){
				dec=dec*2;
					if(dec>=1){
						right[22-e]=1;
						dec=dec-1;
						}
					else{
						right[22-e]=0;
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
			int length=b;
			b=0;
			while(b<8){
			fprintf(o,"%d",array[8-b-1]);
			b++;
			}
				fprintf(o," ");
				int c=0;
			while(c<22){
				if(c<length+1){
			
					fprintf(o,"%d",left[length-c]);
					}
				else{
					fprintf(o,"%d",right[22+length-c]);
					}
			c++;
			}
			fprintf(o,"\n");
			
			}
*/	}
	fclose(f);
	fclose(o);
	return 0;
}
