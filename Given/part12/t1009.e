# test initial value of variables
var r,s
var t
var i, k,long, n
const short = 10
var u,v,w,x
print r print s
print t
print u print v print w print x

# nesting of do inside if in inside do
i := 25
while 10/=i do
	if short=i then
		k := 20 - i
		while k do print k k:=k+1 end
	elsif 15=i then
		k := 15 - i
		n:=5
		while k do long:=10*k print long k:=k+1 end
		print n
		print long
	else
		k:=10-i
		while k do print k k:= k+1 end
	end
	i:=i-1
end
print i
print short
