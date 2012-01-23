var a
var k
var a[1:10] # compile time warning (ignore 2nd declaration)

for k := 1 to 10 do
   a[k] := 100-k # compile time error
end
for k := 1 to 10 do
   print a[k]
end
