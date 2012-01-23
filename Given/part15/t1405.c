#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() {
int x_a[] = {
1, 10, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, }
;
int x_b=8888;
int x_e=8888;
int x_c[] = {
-4, 3, 4444, 4444, 4444, 4444, }
;
for(x_b=1;
x_b<=10;
x_b++){
printf("%d\n", x_b);
printf("%d\n", x_a[bc(x_a, 5, x_b, 'a')]);
}
for(x_b=0-4;
x_b<=0-2;
x_b++){
printf("%d\n", x_b);
printf("%d\n", x_c[bc(x_c, 10, x_b, 'c')]);
}
for(x_b=1;
x_b<=10;
x_b++){
x_a[bc(x_a, 14, x_b, 'a')]=100-x_b;
}
for(x_b=1;
x_b<=10;
x_b++){
printf("%d\n", x_b);
printf("%d\n", x_a[bc(x_a, 19, x_b, 'a')]);
}
for(x_b=1;
x_b<=9;
x_b++){
for(x_e=x_b-1;
x_e<=10;
x_e++){
if(x_a[bc(x_a, 25, x_b, 'a')]>
x_a[bc(x_a, 25, x_e, 'a')]){
int x_t=8888;
x_t=x_a[bc(x_a, 27, x_b, 'a')];
x_a[bc(x_a, 28, x_b, 'a')]=x_a[bc(x_a, 28, x_e, 'a')];
x_a[bc(x_a, 29, x_e, 'a')]=x_t;
}
}
}
for(x_b=1;
x_b<=10;
x_b++){
printf("%d\n", x_b);
printf("%d\n", x_a[bc(x_a, 36, x_b, 'a')]);
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
