let rec make_adder x =
  let rec adder y = x + y in
  adder in
print_int (make_adder((make_adder 3) 7) 10)
