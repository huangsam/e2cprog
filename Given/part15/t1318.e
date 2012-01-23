var a[21:25], b, k

for b := 21 to 24 do
    a[b] := 1001+b
end
a[25] := 1000+21

for b := 21 to 25 do
    print a[b]
end

# shift
for k := 1 to 11 do
    var t
    t := a[21]
    for b := 21 to 24 do
        a[b] := a[b+1]
    end
    a[25] := t
end

for b := 21 to 25 do
    print a[b]
end
