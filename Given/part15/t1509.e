var a[1:10], b, e[84:85], c[-4:-2]

for b := 1 to 10 do
    a[b] := 100+b
end

for b := 0-2 downto 0-4 do
    c[b] := 1000-b
end

print "forward x: a"
every forward x: a do
    var a
    a := 12
    print x
    print a
end

print "forward x: c"
every forward x: c do
    var c[22:25]
    print x
    print c[24]
    every index forward x: c do
        c[x] := 20000+x
    end
    every forward x: c do
        print x
    end
    print x
end

print "bottom forward x: c"
every forward x: c do
    var c[22:25]
    print x
    print c[24]
    every index forward y: c do
        c[y] := 20000+y
    end
    every forward y: c do
        print y
        every index forward y: e do
            print y
        end
    end
    print x
end

