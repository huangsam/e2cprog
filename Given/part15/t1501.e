var a[1:10], b, e, c[-4:-2]

for b := 1 to 10 do
    a[b] := 100+b
end

for b := 0-2 downto 0-4 do
    c[b] := 1000-b
end

for b := 1 to 10 do
    print b
    print a[b]
end

for b := 0-4 to 0-2 do
    print b
    print c[b]
end

print "index x: a"
every index x: a do
    print x
    print a[x]
end

print "index x: c"
every index x: c do
    print x
    print c[x]
end

print "index reverse x: a"
every index reverse x: a do
    print x
    print a[x]
end

print "index reverse x: c"
every index reverse x: c do
    print x
    print c[x]
end

