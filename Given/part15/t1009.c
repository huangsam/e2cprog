#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() {
int x_r=8888;
int x_s=8888;
int x_t=8888;
int x_i=8888;
int x_k=8888;
int x_long=8888;
int x_n=8888;
int x_short=10;
int x_u=8888;
int x_v=8888;
int x_w=8888;
int x_x=8888;
printf("%d\n", x_r);
printf("%d\n", x_s);
printf("%d\n", x_t);
printf("%d\n", x_u);
printf("%d\n", x_v);
printf("%d\n", x_w);
printf("%d\n", x_x);
x_i=25;
while(10!=x_i){
if(x_short==x_i){
x_k=20-x_i;
while(x_k){
printf("%d\n", x_k);
x_k=x_k+1;
}
}
else if(15==x_i){
x_k=15-x_i;
x_n=5;
while(x_k){
x_long=10*x_k;
printf("%d\n", x_long);
x_k=x_k+1;
}
printf("%d\n", x_n);
printf("%d\n", x_long);
}
else{
x_k=10-x_i;
while(x_k){
printf("%d\n", x_k);
x_k=x_k+1;
}
}
x_i=x_i-1;
}
printf("%d\n", x_i);
printf("%d\n", x_short);
}
