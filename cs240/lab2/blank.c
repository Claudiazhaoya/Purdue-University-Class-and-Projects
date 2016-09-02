#include<stdio.h>

int main(int argc, char *argv[])
{

	int n;
	int i=0;
	int t=0;
	char array[300];
	char * test=argv[1];
	char c;

	if(i==argc-1)
	{
		return 0;
	}
	else

	while((c=getchar())!=EOF){

	array[i]=c;
	i++;
	}

	int d=0;
	int e=0;
	if(array[0]==test[0])
	{
		while(array[e]==test[d])
		{
			if((array[e+1] == ' ' || array[e+1] =='\t' || array[e+1] == '\n' || e+1==i) &&(test[d+1] == '\0'))
				{
					
					for(t=d;t>=0;t--)
					{
					array[e] = ' ';
					e--;
					}
					
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
				if((array[e+1] == ' '|| array[e+1] == '\t' || array[e+1] == '\n' || e+1==i) && (test[d+1]=='\0')) 

				{
					for(t=d;t>=0;t--)
					{
					array[e] = ' ';
					e--;
					}
				}
			e++;	
			d++;
		}
	}
	e++;
	d=0;
}
	for(n=0;n<i;n++){

	putchar(array[n]);
	}
	

return 0;
}
