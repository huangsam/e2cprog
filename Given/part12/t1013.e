# check out details of loops:
#  re-evaluation,
#  modification of loop index, and
#  availability of loop index.

## note: this program is legal until part5, where it is illegal.

var i,j,k

for i := 1 to 4 do print i end
print i

k := 4
for i := 1 to k do print i k := k-1 end
print i

for i := 1 to 4 do
    print i
    if i = 1 then i := 3 end
end
print i
