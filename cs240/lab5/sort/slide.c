#include <stdio.h>
main(){
	struct{int a; int b;}x, *p;
	p= &x;
	x.a=0;
	printf("x.a = \t%d\n",x.a);
	printf("p->a = \t%d\n",p->a);
	printf("(*p).a=\t%d\n",(*p).a);
	}
