#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() {
int x_N=98765;
int x_a=8888;
int x_b=8888;
int x_another=8888;
int x_i=8888;
int x_k=8888;
int x_s=8888;
printf("%s\n", "early stuff");
x_a=100;
x_b=200;
printf("%d\n", x_a);
printf("%d\n", x_b);
x_a=44;
x_b=0-x_a;
x_a=888-999;
x_another=x_a-999;
printf("%d\n", x_a);
printf("%d\n", x_b);
printf("%d\n", x_another);
printf("%s\n", "how's precedence, associativity?");
printf("%d\n", 3+2*4);
printf("%d\n", (8-7-6));
printf("%d\n", (8-(7-6)));
printf("%d\n", ((8-7)-6));
printf("%s\n", "loop 1");
x_i=1;
while(x_i<=10){
printf("%d\n", x_i);
x_i=x_i+1;
}
printf("%s\n", "loop 2");
x_i=20;
if(x_i==10){
x_i=15;
}
else if(x_i==20){
x_i=5;
}
printf("%d\n", x_i);
printf("%s\n", "loop 3");
x_i=10;
if(x_i==10){
x_i=15;
}
else if(x_i==20){
x_i=5;
}
printf("%d\n", x_i);
printf("%s\n", "loop 4");
x_i=40;
if(x_i==10){
x_i=15;
}
else if(x_i==20){
x_i=5;
}
printf("%d\n", x_i);
printf("%s\n", "loop 5");
x_s=0;
x_k=10;
while(x_k>
0){
x_s=x_s+x_k;
x_k=x_k-1;
}
printf("%d\n", x_k);
printf("%d\n", x_s);
printf("%d\n", x_N);
printf("%s\n", "all done");
}
