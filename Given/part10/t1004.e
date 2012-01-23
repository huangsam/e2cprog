# test parser -- this is another valid program

const N = 98765
var
	a, b, another, i, k, s
a:= 100 b := 200
print a print b
a := 44
b := 0-a a := 888-999 another := a - 999
print a print b print another
# how's precedence, associativity?
print  3+2*4	print (8 - 7 - 6)	print (8-(7-6))	print ((8-7)-6)

i := 1
while i<= 10 do print i i:=i+1 end

i := 20
if i=10 then i:=15 elsif i=20 then i:=5 end
print i

i := 10
if i=10 then i:=15 elsif i=20 then i:=5 end
print i

i := 40
if i=10 then i:=15 elsif i=20 then i:=5 end
print i

s:=0k:=10
while k>0 do s:=s+k k:= k-1 end
print k print s
print N
