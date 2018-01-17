let rec f x =
	let a = let y = 1 in let z = 1 in y + z in a in
print_int (f 10)
