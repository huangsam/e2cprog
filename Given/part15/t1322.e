var a[21:25], b, k

a[21] := 24
a[22] := 21
a[23] := 25
a[24] := 22
a[25] := 23

print 0-111111
for b := 21 to 25 do
    print a[b]
end

print 0-111111
for b := 21 to 25 do
    print a[a[b]]
end

print 0-111111
for b := 21 to 25 do
    print a[a[a[b]]]
end

print 0-111111
for b := 21 to 25 do
    print a[a[a[a[b]]]]
end

print 0-111111
for b := 21 to 25 do
    print a[a[a[a[a[b]]]]]
end
