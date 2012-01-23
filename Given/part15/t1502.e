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

print "element x: a"
every element x: a do
    print x
end

print "element x: c"
every element x: c do
    print x
end

print "element reverse x: a"
every element reverse x: a do
    print x
end

print "element reverse x: c"
every element reverse x: c do
    print x
end
