#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
main() 
{
int 
x_a[] = {21, 5, 4444, 4444, 4444, 4444, 4444, 4444, };
int 
x_b
=8888;
int 
x_k
=8888;
for(
x_b
=
21
;
x_b
<=
24
;
x_b
++)
{
x_a[bc(x_a, 4, 
x_b
, 'a')]
=
1001
+
x_b
;
}
x_a[bc(x_a, 6, 
25
, 'a')]
=
1000
+
21
;
for(
x_b
=
21
;
x_b
<=
25
;
x_b
++)
{
printf("%d\n", 
x_a[bc(x_a, 9, 
x_b
, 'a')]
);
}
for(
x_k
=
1
;
x_k
<=
11
;
x_k
++)
{
int 
x_t
=8888;
x_t
=
x_a[bc(x_a, 15, 
21
, 'a')]
;
for(
x_b
=
21
;
x_b
<=
24
;
x_b
++)
{
x_a[bc(x_a, 17, 
x_b
, 'a')]
=
x_a[bc(x_a, 17, 
x_b
+
1
, 'a')]
;
}
x_a[bc(x_a, 19, 
25
, 'a')]
=
x_t
;
}
for(
x_b
=
21
;
x_b
<=
25
;
x_b
++)
{
printf("%d\n", 
x_a[bc(x_a, 23, 
x_b
, 'a')]
);
}
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
