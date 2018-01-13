let rec f x =
  let rec g a =
    let rec h b =
        a + b - x
    in print_int(h 1)
  in print_int(g 2)
in print_int(f 3)
