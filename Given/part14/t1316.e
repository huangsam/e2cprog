var a
var k
var a[1:10] # compile time warning (ignore 2nd declaration)

a := 0
for k := 1 to 10 do
   a := a + k
end
print a
