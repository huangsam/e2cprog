# test some scoping  - this program is legal
const N = 10
var i
for i := 1 to N/2 do
    var N
    N := i*i+8*i
    print N
end
print N
for i := 1 to N/2 do
    const N = 16
    print N
    if N = i*8 then
        var N
	N := i*i+8*i
	print N
    end
end
print N
