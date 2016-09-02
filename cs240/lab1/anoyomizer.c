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
//printf("%s",array);
	int n;
	for(n=0;n<140;n++)
	{
		if(array[n] == '@'){
			if(array[n-1] >='a' && array[n-1] <='z' ||
			array[n-1] >= 'A' && array[n-1] <='Z' ||
			array[n-1] >= '0' && array[n-1] <='9'){
				array[n-1]='X';
			if(array[n-2] >='a' && array[n-2] <='z' ||
			array[n-2] >= 'A' && array[n-2] <='Z' ||
			array[n-2] >= '0' && array[n-2] <='9'){
		
				array[n-2]='X';
			}
        }
}
}

printf("%s",array);
return 0;
}
