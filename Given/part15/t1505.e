var a[1:10], b, e, c[-4:-2]

for b := 1 to 10 do
    a[b] := 100+b
end

for b := 0-2 downto 0-4 do
    c[b] := 1000-b
end

# check that every's iv really goes out of scope

print "element forward x: a"
every element forward x: a do
    print x
end

print x # no such variable

