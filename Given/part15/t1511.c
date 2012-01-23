#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() 
{
int 
x_a[] = {1, 10, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, };
int 
x_b[] = {1, 10, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, };
int 
x_k
=8888;
{
int 
x_x
;

int indexx;
for(
indexx=
x_a
[1]-1;indexx>=0;indexx--)
{
x_x
 = 
indexx+
x_a
[0];
{
x_a[bc(x_a, 4, 
x_x
, 'a')]
=
1000
-
x_x
;
}
	}
}
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
[indexx];
{
printf("%d\n", 
x_x
);
x_a[bc(x_a, 9, 
5
, 'a')]
=
100
;
}
	}
}
{
int 
x_x
;

int indexx;
for(
indexx=
x_b
[1]-1;indexx>=0;indexx--)
{
x_x
 = 
indexx+
x_b
[0];
{
x_b[bc(x_b, 13, 
x_x
, 'b')]
=
3000
-
x_x
;
}
	}
}
x_k
=
0
;
{
int 
x_x
;

int indexx;
for(
indexx=0;indexx<=
x_b
[1]-1;indexx++)
{
x_x
 = 
x_b
[indexx];
{
x_k
=
x_k
+
1
;
x_b[bc(x_b, 20, 
x_k
, 'b')]
=
9000
-
x_k
;
printf("%d\n", 
x_x
);
}
	}
}
{
int 
x_x
;

int indexx;
for(
indexx=0;indexx<=
x_b
[1]-1;indexx++)
{
x_x
 = 
x_b
[indexx];
{
printf("%d\n", 
x_x
);
}
	}
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
