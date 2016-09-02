#include <stdio.h>

main() 
{
	char buf[1];
	int n;
	unsigned char code;

	while ((n = read(0, buf, 1)) > 0) {
		code = *buf;
		(code = code + 2) %256;
		*buf = code;
		write(1, buf, 1);
	}
	return 0;
}
