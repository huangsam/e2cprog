var k, p, q, t, sum

t := 0
k := 0
sum := 0
repeat
    print k
    k := k+1
    repeat
        print "nothing"
	t := t+1
    until k > 0
    p := k
    repeat
        print "not much"
	print p
	p := p-1
        q := 0
	t := t+1
        repeat
            print "some"
	    print q
	    q := q+1
	    t := t+1
        until q > p
    until p = 0
    sum := sum + k
until k >= 10
print "sum is"
print sum
print "t is"
print t
