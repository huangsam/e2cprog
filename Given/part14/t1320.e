var a[21:25], b, k, c[-5:-1]

a[21] := 0-4
a[22] := 0-1
a[23] := 0-5
a[24] := 0-2
a[25] := 0-3

for k := 0-5 to 0-1 do
    c[k] := k*111
end

print 0-111111
for b := 21 to 25 do
    print a[b]
end

print 0-111111
for b := 21 to 25 do
    print c[a[b]]
end
