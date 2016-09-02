#include<stdio.h>

int main()
{
	char c;
	char x;
	char array[140];
	int i=0;
	
	x=88;
	while ((c=getchar())!=EOF){

	
	array[i]=c;
	i++;
				}

	int n;
	int t=0;
	for(n=0;n<i;n++)
	{
		if(array[n] == '@'){
			if(array[n+1] !='@' && (array[n+1] >='a' && array[n+1] <='z') || (array[n+1] >= 
			'A' && array[n+1]
			<='Z') || (array[n+1] >='0' && array[n+1] <='9')){
				
			 
				t=n-1;
			while( (array[t] >='a' && array[t] <='z') || (array[t] >= 
			'A' && array[t]
			<='Z') || (array[t] >='0' && array[t] <='9'))
				{
					
					
					if(array[t] !='@')
					{
						array[t]='X';
					} 
			 	        t--;
				
 						}
					}
				}
	}

	for(n=0;n<i;n++){
putchar(array[n]);
}
return 0;
}
