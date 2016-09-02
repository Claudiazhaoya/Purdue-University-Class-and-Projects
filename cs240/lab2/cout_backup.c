#include<stdio.h>

int main(int argc, char *argv[])
{
	int n;
	int i=0;
	int a=0;
	char b;
	char c;
	char array[300];
	char * test=argv[1]; 
	//if there is no command line argument exit program without doing anything
	if(i==argc-1)
	{
		return 0;
	}
	else
	
	//takes in the text file and puts it into an array
	while((c=getchar())!=EOF){

	array[i]=c;
	i++;
	}

	int d=0;
	int e=0;
	for(n=0;n<i;n++)
	{

			if(!((array[e-1] >='a' && array[e-1] <='z') || (array[e-1] >='A' && array[e-1] <='Z') ||
						(array[e-1] >='0' && array[e-1] >='9')))
			{
				while(array[e]==test[d])
				{
					if(array[e+1] == ' '&& test[d+1] == '\0') 
						{
							a=a+1;
						}
					e++;
					d++;
				}
			}
			else
			{
				a=a;
			}	
		e++;	
		d=0;
	}		
	//for testing only not needed in program
	printf("%d",a);
return 0;
}
