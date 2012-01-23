   # no var at global level
   if 1 = 1 then
      var a
      print 1111
      if 1 < 2 then
         # no var at local level
         print a # but should find a up in next level
      end
      print 3333
   end
   print 4444
