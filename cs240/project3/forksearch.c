#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
int main(int argc, char *argv[]){
	if(argc<2){
		return 0;
		}
	char *word=argv[1];
	FILE* in=fopen(argv[2],"r");
	char *buff=argv[4];
	int length=0;
	int buffer=0;
	int number=0;
	int a = 0;
	char c;
	int keyword = strlen(word);
	FILE* out=fopen(argv[3],"w");
		fprintf(out,"0\n");		
		fclose(out);	
	buffer = atoi (buff);
	fseek(in,0,SEEK_END);
	length= ftell(in)-1;
	fseek(in,0,SEEK_SET);
	int forknum=1;
	while(c!=EOF){
		char file[buffer];
			while(a<buffer){
				file[a]=' ';
				a++;
				}
		int d=0;
		while((c=fgetc(in))!=EOF){
		file[d]=c;
			if((c==' '||c=='\t'||c=='\n')&&(d+keyword>buffer)){
				forknum++;
				break;
				}
			d++;
			}
			a=0;	
		}
	fseek(in,0,SEEK_SET);
	int f=0;	
	int i=0;
	int p=0;
	int counter=0;
	int t=0;
	int g=0;
	int simcount=0;
	int num=0;
	
	int pid[forknum];
	while(i<forknum){
		char chunk[buffer];
		while(f<=keyword){
			chunk[f]=' ';
			f++;
			}
		int d=0;
		while((c=fgetc(in))!=EOF){
				chunk[p]=c;
				if((c==' '||c=='\t'||c=='\n')&&(d+keyword>buffer)){
				chunk[p]='\0';
					wait();
					break;
				}
				d++;
				p++;
			}
		int pipe(int fdpipe[2]);
		if((pid[i]=fork())==0){
			while(t<buffer){
				counter=0;
				simcount=0;
				while(counter<keyword){
					if(chunk[t+counter]==word[counter]){
						simcount++;
						}
						counter++;
				}
			if(((chunk[t+counter]=='\t')||(chunk[t+counter]=='\n')||(chunk[t+counter]==EOF)
					||(chunk[t+counter]==' '))&&((chunk[t-1]=='\t')||(chunk[t-1]=='\n')||
						(chunk[t-1]==' ')||(chunk[t+counter]))){
						}else{simcount=0;}
				t++;
				if(simcount==keyword){
					FILE* out=fopen(argv[3],"w");
					fprintf(out,"1\n");		
					fclose(out);
					break;
					}	
			}
			_exit(EXIT_SUCCESS);
		}
		else if((pid[i]<0)){
			exit(1);
			}
		else{}
			p=0;
			f=0;
			i++;
			}	
	return 0;
}
