let rec f x =
  let rec g a =
    let rec h b =
        a + b - x
    in  h
  in g
in print_int(((f 3) 2) 1)
