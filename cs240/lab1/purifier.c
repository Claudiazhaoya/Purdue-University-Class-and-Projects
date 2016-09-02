#include<stdio.h>

int main()
{
	char c;
	char x;
	char array[200];
	int i=0;

	x=88;
	while ((c=getchar())!=EOF){

	array[i]=c;
	i++;
}
	int n;
	for(n=0;n<200;n++)
	{
		if(array[n] == 'h'||array[n] == 'H'){
			if(array[n+1] == 'e'||array[n+1] == 'E'){
					if(array[n+2]=='c'||array[n+2]=='C'){
						if(array[n+3]=='k'||array[n+3]=='K'){
							if(array[n+4] >='a' && array[n+4] <='z' ||
							array[n+4] >= 'A' && array[n+4] <='Z' ||
							array[n-1] >= 'a' && array[n-1] <='z' ||
							array[n-1] >= 'A' && array[n-1] <='Z'){
							array[n]=array[n];
							array[n+1]=array[n+1];
							array[n+2]=array[n+2];
							array[n+3]=array[n+3];
							
							}
							else{
				
	
								array[n]='X';
								array[n+1]='X';
								array[n+2]='X';
								array[n+3]='X';
								}
					}
				}
			}
		}
		if(array[n] == 'c'||array[n]== 'C'){
			if(array[n+1] == 'r'||array[n+1]=='R'){
					if(array[n+2]=='a'||array[n+2]=='A'){
						if(array[n+3]=='p'||array[n+3]=='P'){
							if(array[n+4] >='a' && array[n+4] <='z' ||
							array[n+4] >= 'A' && array[n+4] <='Z' ||
							array[n-1] >= 'a' && array[n-1] <='z' ||
							array[n-1] >= 'A' && array[n-1] <='Z'){
							array[n]=array[n];
							array[n+1]=array[n+1];
							array[n+2]=array[n+2];
							array[n+3]=array[n+3];
							
							}
							else{

								array[n]='X';
								array[n+1]='X';
								array[n+2]='X';
								array[n+3]='X';
								}
					}
				}
			}
		}
		if(array[n] == 's'||array[n]=='S'){
			if(array[n+1] == 'u'||array[n+1]=='U'){
					if(array[n+2]=='c'||array[n+2]=='C'){
						if(array[n+3]=='k'||array[n+3]=='K'){
							if(array[n+4] >='a' && array[n+4] <='z' ||
							array[n+4] >= 'A' && array[n+4] <='Z' ||
							array[n-1] >= 'a' && array[n-1] <='z' ||
							array[n-1] >= 'A' && array[n-1] <='Z'){
							array[n]=array[n];
							array[n+1]=array[n+1];
							array[n+2]=array[n+2];
							array[n+3]=array[n+3];
							
							}							
							else{
								array[n]='X';
								array[n+1]='X';
								array[n+2]='X';
								array[n+3]='X';
								}
					}
				}
			}
		}
		if(array[n] == 'b'||array[n]=='B'){
			if(array[n+1] == 'u'||array[n+1]=='U'){
					if(array[n+2]=='l'||array[n+2]=='L'){
						if(array[n+3]=='l'||array[n+3]=='L'){
							if(array[n+4] >= 'a' && array[n+4] <='z' || 							        array[n+4] >= 'A' && array[n+4] <='Z' ||
							array[n-1] >= 'a' && array[n-1] <='z' ||
							array[n-1] >= 'A' && array[n-1] <='Z'){    
							array[n]=array[n];
							array[n+1]=array[n+1];
							array[n+2]=array[n+2];
							array[n+3]=array[n+3];
			
							}
							else{
								array[n]='X';
								array[n+1]='X';
								array[n+2]='X';
								array[n+3]='X';
								}
					}
				}
			}
		}
		if(array[n] == 'd'||array[n]=='D'){
			if(array[n+1] == 'r'||array[n+1]=='R'){
					if(array[n+2]=='a'||array[n+2]=='A'){
						if(array[n+3]=='t'||array[n+3]=='T'){
							if(array[n+4] >= 'a' && array[n+4] <='z' || 							        array[n+4] >= 'A' && array[n+4] <='Z' ||
							array[n-1] >= 'a' && array[n-1] <='z' ||
							array[n-1] >= 'A' && array[n-1] <='Z'){    
							array[n]=array[n];
							array[n+1]=array[n+1];
							array[n+2]=array[n+2];
							array[n+3]=array[n+3];
							
							}
							else{
								array[n]='X';
								array[n+1]='X';
								array[n+2]='X';
								array[n+3]='X';
								}
					}
				}
			}
		}

	}
	while(array[n]!=EOF){ 
    printf("%s",array);
}
   return 0;
}
