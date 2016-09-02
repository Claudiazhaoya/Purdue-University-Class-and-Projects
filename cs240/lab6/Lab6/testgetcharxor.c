#include <stdio.h>

main() 
{
	char *buf;
	unsigned char msg, code, key = '\x0f';
	int input;

	while ((input = getchar()) != EOF) {
		msg = (unsigned char) input;
		code = msg ^ key;
		input = (int) code;
		putchar(input);
	}
	return 0;
}
