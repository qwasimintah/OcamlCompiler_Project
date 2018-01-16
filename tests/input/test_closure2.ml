let rec f x =
  let rec g a =
    let rec h b =
        a + b - x
    in  h
  in g
in let v1 = f 3 in
let v2 = v1 2 in
let v3 = v2 1 in print_int v3
