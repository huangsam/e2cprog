#include <stdio.h>

int bc(int *arrayName, int index) {
	int curSize = sizeof(arrayName);
	if(index < 0 || index >= curSize) {
	fprintf(stderr, "index out of bounds\n") ;
	exit(1);
	}
	else {
		return 1;
	}
}

main() 
{
int 
x_a
=8888;
int 
x_k[4];

if (bc(x_k, 0)) {
	fprintf(stdout, "yes\n");
}
}

