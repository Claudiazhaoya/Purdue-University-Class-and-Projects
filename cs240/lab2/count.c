#include<stdio.h>

int main(int argc, char *argv[])
{
	int n;
	int i=0;
	int a=0;
	int f=0;
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
	if(array[0] == test[0])
	{	
		while(array[e]==test[d])
		{
			if((array[e+1] == ' ' || array[e+1] == '\t' || array[e+1] == '\n' || e+1==i ) && (test[d+1] == '\0'))
				{
					f=f+1;
				}
			
			e++;
			d++;
		}
		
	}
	for(n=0;n<i;n++)
	{		
			if((array[e-1] == ' ' || array[e-1] == '\t' || array[e-1] == '\n'))
			{

				
				while(array[e]==test[d])
				{
					if((array[e+1] == ' ' || array[e+1] == '\t' || array[e+1] == '\n' ||  e+1==i) && (test[d+1]== '\0')) 
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
	a=a+f;		
	printf("%d",a);
return 0;
}
