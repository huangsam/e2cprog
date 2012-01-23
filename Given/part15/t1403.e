var a[1:10], k, a[2:30] # compile time warning (ignore 2nd declaration)

for k := 2 to 30 do
   a[k] := k # run time error
end
