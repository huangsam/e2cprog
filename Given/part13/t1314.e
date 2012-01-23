var a[1:10], a # compile time warning (ignore 2nd declaration)
var k

for k := 1 to 10 do
   a[k] := 100-k
end
for k := 1 to 10 do
   print a[k]
end
