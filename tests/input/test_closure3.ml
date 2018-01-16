let rec f x =
  let rec g y = x + y in
  g 1 in
let v1 = f 3 in print_int v1
