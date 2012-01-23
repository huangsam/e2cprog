#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
main() 
{
int 
x_a[] = {1, 10, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, };
int 
x_b
=8888;
int 
x_e
=8888;
int 
x_c[] = {-4, 3, 4444, 4444, 4444, 4444, };
int 
x_xx
=
9
;
printf("%d\n", 
x_a[bc(x_a, 4, 
x_xx
, 'a')]
);
printf("%d\n", 
x_c[bc(x_c, 5, 
3
, 'c')]
);
}
int bc(int *a, int ln, int exp, char arName)
{
int subscript = exp - a[0] + 2;
if( subscript < a[1] + 2 && subscript >= 2 )
return subscript;
fprintf(stderr, "subscript (%d) out of bounds for array ", exp );
fprintf(stderr, "%c[%d:%d] on line %d\n",arName,a[0],a[0]+a[1]-1,ln );
exit(1);
return 0;
}
