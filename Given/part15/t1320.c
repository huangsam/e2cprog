#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
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
int 
x_c[] = {-5, 5, 4444, 4444, 4444, 4444, 4444, 4444, };
x_a[bc(x_a, 3, 
21
, 'a')]
=
0
-
4
;
x_a[bc(x_a, 4, 
22
, 'a')]
=
0
-
1
;
x_a[bc(x_a, 5, 
23
, 'a')]
=
0
-
5
;
x_a[bc(x_a, 6, 
24
, 'a')]
=
0
-
2
;
x_a[bc(x_a, 7, 
25
, 'a')]
=
0
-
3
;
for(
x_k
=
0
-
5
;
x_k
<=
0
-
1
;
x_k
++)
{
x_c[bc(x_c, 10, 
x_k
, 'c')]
=
x_k
*
111
;
}
printf("%d\n", 
0
-
111111
);
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
x_a[bc(x_a, 15, 
x_b
, 'a')]
);
}
printf("%d\n", 
0
-
111111
);
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
x_c[bc(x_c, 20, 
x_a[bc(x_a, 20, 
x_b
, 'a')]
, 'c')]
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
