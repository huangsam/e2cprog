#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
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
for(
x_b
=
1
;
x_b
<=
10
;
x_b
++)
{
x_a[bc(x_a, 4, 
x_b
, 'a')]
=
100
+
x_b
;
}
for(
x_b
=
0
-
2
;
x_b
>=
0
-
4
;
x_b
--)
{
x_c[bc(x_c, 8, 
x_b
, 'c')]
=
1000
-
x_b
;
}
printf(
"%s\n", "element forward x: a"
);
