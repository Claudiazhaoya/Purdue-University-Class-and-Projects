#include <stdio.h>

main() 
{
	char buf[1];
	int n;
	unsigned char code;

	while ((n = read(0, buf, 1)) > 0) {
		code = *buf;
		*buf = code ^ '\x0f';
		write(1, buf, 1);
	}
	return 0;
}
