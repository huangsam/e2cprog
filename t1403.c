#include <stdio.h>#include <stdlib.h>int bc(int *a, int ln, int exp, char arName);
main() {
int x_a[] = {
1, 10, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, }
;
int x_k=8888;
for(x_k=2;
x_k<=30;
x_k++){
x_a[bc(x_a, 4, x_k, 'a')]=x_k;
}
}
int bc(int *a, int ln, int exp, char arName){
int subscript = exp - a[0] + 2;
if( subscript < a[1] + 2 && subscript >= 2 )return subscript;
fprintf(stderr, "subscript (%d) out of bounds for array ", exp );
fprintf(stderr, "%c[%d:%d] on line %d\n",arName,a[0],a[0]+a[1]-1,ln );
exit(1);
return 0;
}
