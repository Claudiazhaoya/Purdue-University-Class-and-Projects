#include <stdio.h>

main() 
{
	char buf[1];
	int n;
	unsigned char code, a, b;

	while ((n = read(0, buf, 1)) > 0) {
		code = *buf;
		a = code >> 4;
		b = code << 4;
		*buf = a | b;
		write(1, buf, 1);
	}
	return 0;
}
