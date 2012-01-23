#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() {
int x_k=8888;
int x_sum=8888;
x_k=0;
x_sum=0;
do{
printf("%d\n", x_k);
x_k=x_k+1;
x_sum=x_sum+x_k;
}
while(x_k<10);
printf("%s\n", "sum is");
printf("%d\n", x_sum);
}
