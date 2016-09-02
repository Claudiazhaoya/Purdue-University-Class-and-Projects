#include<stdio.h>

int main(int argc, char *argv[])
{
	int n;
	int i=1;
	while(i<argc)
	{
	printf("%s",argv[i]);
		if(i==argc-1)
			{
			break;
			}
		else
			{
			printf(" ");
			}
	n++;
	i++;
	}	
return 0;
}
	

