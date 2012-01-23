# test for
var i,j

for i := 1 to 4 do print i end
for i := 4 to 1 do print 100+i end
for i := 1 downto 4 do print 200+i end
for i := 4 downto 1 do print 300+i end
for i := 1 to 4 do end

for i := 2 to 4 do
	for j := i to 5 do
		print i*1000+j
	end
end

for i := 2 to 9 do
	for j := 1 to 5 do
		if i = j then print i*10000+j end
	end
end

for j := 10 to 3 do
	print 2000+j
end

for i := 1 to 8 do
	for j := i downto 5 do print 100000*i+j end
	for j := 5 downto i do print 200000*i+j end
end
