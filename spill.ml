let rec f x y =
  let a = x + 1 in
  let b = y + 2 in

  let res = x + y + a + b in
  -res in
print_int (f 1 2)
