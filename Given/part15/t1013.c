#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() {
int x_i=8888;
int x_j=8888;
int x_k=8888;
for(x_i=1;
x_i<=4;
x_i++){
printf("%d\n", x_i);
}
printf("%d\n", x_i);
x_k=4;
for(x_i=1;
x_i<=x_k;
x_i++){
printf("%d\n", x_i);
x_k=x_k-1;
}
printf("%d\n", x_i);
for(x_i=1;
x_i<=4;
x_i++){
printf("%d\n", x_i);
if(x_i==1){
