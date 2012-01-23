#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() {
int x_k=8888;
int x_p=8888;
int x_q=8888;
int x_t=8888;
int x_sum=8888;
x_t=0;
x_k=0;
x_sum=0;
do{
printf("%d\n", x_k);
x_k=x_k+1;
do{
printf("%s\n", "nothing");
x_t=x_t+1;
}
while(x_k<=0);
x_p=x_k;
do{
printf("%s\n", "not much");
printf("%d\n", x_p);
x_p=x_p-1;
x_q=0;
x_t=x_t+1;
do{
printf("%s\n", "some");
printf("%d\n", x_q);
x_q=x_q+1;
x_t=x_t+1;
}
while(x_q<=x_p);
}
while(x_p!=0);
x_sum=x_sum+x_k;
}
while(x_k<10);
printf("%s\n", "sum is");
printf("%d\n", x_sum);
printf("%s\n", "t is");
printf("%d\n", x_t);
}
