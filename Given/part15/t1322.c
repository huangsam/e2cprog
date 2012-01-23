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
x_a[bc(x_a, 3, 
21
, 'a')]
=
24
;
x_a[bc(x_a, 4, 
22
, 'a')]
=
21
;
x_a[bc(x_a, 5, 
23
, 'a')]
=
25
;
x_a[bc(x_a, 6, 
24
, 'a')]
=
22
;
x_a[bc(x_a, 7, 
25
, 'a')]
=
23
;
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
x_a[bc(x_a, 11, 
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
x_a[bc(x_a, 16, 
x_a[bc(x_a, 16, 
x_b
, 'a')]
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
x_a[bc(x_a, 21, 
x_a[bc(x_a, 21, 
x_a[bc(x_a, 21, 
x_b
, 'a')]
, 'a')]
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
x_a[bc(x_a, 26, 
x_a[bc(x_a, 26, 
x_a[bc(x_a, 26, 
x_a[bc(x_a, 26, 
x_b
, 'a')]
, 'a')]
, 'a')]
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
x_a[bc(x_a, 31, 
x_a[bc(x_a, 31, 
x_a[bc(x_a, 31, 
x_a[bc(x_a, 31, 
x_a[bc(x_a, 31, 
x_b
, 'a')]
, 'a')]
, 'a')]
, 'a')]
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
