/* Purpose: showing result of bit rotation */
#include <stdio.h>

main() {

	char a = '\x0f';
	char b, c;

	unsigned char ua = '\x0f';
	unsigned char ub, uc;

	printf("a is \t %#X\n", a);
	
        b = a >> 2;
	printf("b is \t %#X\n", b);
        c = a << 6;
	printf("c is \t %#X\n", c);
	a = b | c;
        
	printf("a is \t %#X\n", a);
	
/* unsigned numbers */

	printf("ua is \t %#X\n", ua);
	
        ub = ua >> 2;
	printf("ub is \t %#X\n", ub);
        uc = ua << 6;
	printf("uc is \t %#X\n", uc);
	ua = ub | uc;
        
	printf("ua is \t %#X\n", ua);

        ua = '\x0f';
	ua = ua >> 2 | ua << 6; 
/*	printf("rotation of ua is \t%#X\n",  (unsigned char) ua >> 2 | ua << 6);
	printf("rotation of ua is \t%#X\n",  ua);
*/
	printf("rotation of ua is \t%#X\n",  (unsigned char) ua >> 2 | ua << 6);
	
}

