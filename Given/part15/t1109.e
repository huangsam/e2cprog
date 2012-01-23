# more string tests

const N = 98765
var
	a, b, another, i, k, s

print "early stuff"
a:= 100 b := 200
print a print b
a := 44
b := 0-a a := 888-999 another := a - 999
print a print b print another

print "how's precedence, associativity?"
print  3+2*4	print (8 - 7 - 6)	print (8-(7-6))	print ((8-7)-6)

print "loop 1"
i := 1
while i<= 10 do print i i:=i+1 end

print "loop 2"
i := 20
if i=10 then i:=15 elsif i=20 then i:=5 end
print i

print "loop 3"
i := 10
if i=10 then i:=15 elsif i=20 then i:=5 end
print i

print "loop 4"
i := 40
if i=10 then i:=15 elsif i=20 then i:=5 end
print i

print "loop 5"
s:=0k:=10
while k>0 do s:=s+k k:= k-1 end
print k print s

print N
print "all done"
