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

print "forward x: a"
every forward x: a do
    print x
end

print "forward x: c"
every forward x: c do
    print x
end

print "reverse x: a"
every reverse x: a do
    print x
end

print "reverse x: c"
every reverse x: c do
    print x
end
