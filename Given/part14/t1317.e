var a[1:10], b, e, c[-4:-2]

for b := 1 to 10 do
    print b
    print a[b]
end

for b := 0-4 to 0-2 do
    print b
    print c[b]
end

for b := 1 to 10 do
    a[b] := 100-b
end

if 3 < 5 then
    var c[27:36]
    for b := 27 to 36 do
        c[b] := a[b-26]
    end
    for b := 36 downto 27 do
        print c[b]
    end
end

for b := 1 to 10 do
    print b
    print a[b]
end

# sort
for b := 1 to 9 do
    for e := b+1 to 10 do
        if a[b] > a[e] then
	     var t
	     t := a[b]
	     a[b] := a[e]
	     a[e] := t
        end
    end
end

for b := 1 to 10 do
    print b
    print a[b]
end

