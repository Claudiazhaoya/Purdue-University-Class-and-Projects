#include <stdio.h>
int
isLittleEndian(){
	int a = 0x10;
	char * p = (char *) &a;
	if(*p==0x10) {
		return 1;
	}
	return 0;
}
main()
{
	if(isLittleEndian()){
		printf("Machine is little\n");
	}else{
		printf("Big\n");
	}
}
