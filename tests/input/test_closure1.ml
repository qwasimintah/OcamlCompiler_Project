let rec f x =
  let rec g y = x + y in
  g in
let v1 = f 3 in
let v2 = v1 7 in print_int v2
