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
{
int 
x_x
;

int indexx;
for(
indexx=0;indexx<=
x_a
[1]-1;indexx++)
{
x_x
 = 
x_a
[indexx+
x_a
[0]];
{
printf("%d\n", 
x_x
);
}
	}
}
printf("%d\n", 
x_x
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
