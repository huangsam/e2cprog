#include <stdio.h>
#include <stdlib.h>
int bc(int *a, int ln, int exp, char arName);
int everyIndex;
main() {
int x_i=8888;
int x_j=8888;
for(x_i=1;
x_i<=4;
x_i++){
printf("%d\n", x_i);
}
for(x_i=4;
x_i<=1;
x_i++){
printf("%d\n", 100+x_i);
}
for(x_i=1;
x_i>
=4;
x_i--){
printf("%d\n", 200+x_i);
}
for(x_i=4;
x_i>
=1;
x_i--){
printf("%d\n", 300+x_i);
}
for(x_i=1;
x_i<=4;
x_i++){
}
for(x_i=2;
x_i<=4;
x_i++){
for(x_j=x_i;
x_j<=5;
x_j++){
printf("%d\n", x_i*1000+x_j);
}
}
for(x_i=2;
x_i<=9;
x_i++){
for(x_j=1;
x_j<=5;
x_j++){
if(x_i==x_j){
printf("%d\n", x_i*10000+x_j);
}
}
}
for(x_j=10;
x_j<=3;
x_j++){
printf("%d\n", 2000+x_j);
}
for(x_i=1;
x_i<=8;
x_i++){
for(x_j=x_i;
x_j>
=5;
x_j--){
printf("%d\n", 100000*x_i+x_j);
}
for(x_j=5;
x_j>
=x_i;
x_j--){
printf("%d\n", 200000*x_i+x_j);
}
}
}
