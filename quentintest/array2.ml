let rec f _ = 
    Array.create 10 1 in
let rec g _ = 
  (f ()).(0) in
g ()

