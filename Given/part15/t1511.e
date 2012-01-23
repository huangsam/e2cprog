var a[1:10], b[1:10], k

every index reverse x: a do
    a[x] := 1000-x
end

every forward x: a do
    print x
    a[5] := 100  # just for fun
end

every index reverse x: b do
    b[x] := 3000-x
end

# when is x's value set?
k := 0
every forward x: b do
    k := k+1
    b[k] := 9000-k
    print x
end

every forward x: b do
    print x
end

