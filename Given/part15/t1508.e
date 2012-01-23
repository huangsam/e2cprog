var a[1:10], b, e, c[-4:-2]

for b := 1 to 10 do
    a[b] := 100+b
end

for b := 0-2 downto 0-4 do
    c[b] := 1000-b
end

print "element forward x: c"
every element forward x: c do
    print "inloop"
    print x
    every element forward x: c do
        print x
    end
end
