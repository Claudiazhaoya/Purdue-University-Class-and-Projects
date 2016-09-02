#include <stdio.h>

int
isLittleEndian(){
	int before = 0x10;		//create data point to check against
	char * after = (char *) &before; // derefrence to see if little or big
	if(*after==0x10){
		return 1;   		//return 1 for little
	}
	return 0;			//return 0 for big
}

main()
{
	if(isLittleEndian()){
		printf("Machine is Little Endian\n");
	}
	else{
		printf("Machine is Big Endian\n");
	}
}
