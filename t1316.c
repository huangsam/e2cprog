#include <stdio.h>
#include <stdlib.h>

int bc(char *arrayName, int *array, int index, int lno) {
int curSize = array[1];
int ub = array[1] + array[0] - 1;
if(index < 0 || index > ub) {
fprintf(stderr,"subscript (%d) out of bounds for array %s[%d:%d] on line %d",
index,
arrayName,
array[0],
ub,
lno
);
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
x_k
=8888;
x_a
=
0
;
for(
x_k
=
1
;
x_k
<=
10
;
x_k
++)
{
x_a
=
x_a
+
x_k
;
}
printf("%d\n", 
x_a
);
}
