#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() {
int x_a[] = {
1, 10, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, }
;
int x_b[] = {
1, 10, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, 4444, }
;
int x_k=8888;
for({
int x_x;
for(index=sizeof(x_a);
index>
1;
index--){
x_x = index+x_a[0];
{
x_a[bc(x_a, 4, x_x, 'a')]=1000-x_x;
}
	}
}
for(