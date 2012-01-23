#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() {
int x_a[] = {
1, 10, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, }
;
int x_k=8888;
for(x_k=1;
x_k<=10;
x_k++){
x_a[bc(x_a, 5, x_k, 'a')]=100-x_k;
}
for(x_k=1;
x_k<=10;
x_k++){
printf("%d\n", x_a[bc(x_a, 8, x_k, 'a')]);
}
}
int bc(int *a, int ln, int exp, char arName){
int subscript = exp - a[0] + 2;
if( subscript < a[1] + 2 && subscript >
= 2 )return subscript;
fprintf(stderr, "subscript (%d) out of bounds for array ", exp );
fprintf(stderr, "%c[%d:%d] on line %d\n",arName,a[0],a[0]+a[1]-1,ln );
exit(1);
return 0;
}
