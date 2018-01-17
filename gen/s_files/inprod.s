------ AST ------
(let rec getx v = (let (x, y, z) = v in x) in (let rec gety v = (let (x, y, z) = v in y) in (let rec getz v = (let (x, y, z) = v in z) in (let rec inprod v1 v2 = ((((getx v1) *. (getx v2)) +. ((gety v1) *. (gety v2))) +. ((getz v1) *. (getz v2))) in (print_int (truncate (1000000,00 *. (inprod (1,00, 2,00, 3,00) (4,00, 5,00, 6,00)))))))))

------ K-Normalization ------
(let rec getx v = (let (x, y, z) = v in x) in (let rec gety v = (let (x, y, z) = v in y) in (let rec getz v = (let (x, y, z) = v in z) in (let rec inprod v1 v2 = ((((getx v1) *. (getx v2)) +. ((gety v1) *. (gety v2))) +. ((getz v1) *. (getz v2))) in (let ?v0 = (let ?v1 = (1000000,00 *. (inprod (1,00, 2,00, 3,00) (4,00, 5,00, 6,00))) in (truncate ?v1)) in (print_int ?v0))))))

------ AlphaConversion ------
(let rec ?v2 ?v3 = (let (?v4, ?v5, ?v6) = ?v3 in ?v4) in (let rec ?v7 ?v8 = (let (?v9, ?v10, ?v11) = ?v8 in ?v10) in (let rec ?v12 ?v13 = (let (?v14, ?v15, ?v16) = ?v13 in ?v16) in (let rec ?v17 ?v18 ?v19 = ((((?v2 ?v18) *. (?v2 ?v19)) +. ((?v7 ?v18) *. (?v7 ?v19))) +. ((?v12 ?v18) *. (?v12 ?v19))) in (let ?v20 = (let ?v21 = (1000000,00 *. (?v17 (1,00, 2,00, 3,00) (4,00, 5,00, 6,00))) in (truncate ?v21)) in (print_int ?v20))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v2 ?v3 = (let (?v4, ?v5, ?v6) = ?v3 in ?v4) in (let rec ?v7 ?v8 = (let (?v9, ?v10, ?v11) = ?v8 in ?v10) in (let rec ?v12 ?v13 = (let (?v14, ?v15, ?v16) = ?v13 in ?v16) in (let rec ?v17 ?v18 ?v19 = ((((?v2 ?v18) *. (?v2 ?v19)) +. ((?v7 ?v18) *. (?v7 ?v19))) +. ((?v12 ?v18) *. (?v12 ?v19))) in (let ?v21 = (1000000,00 *. (?v17 (1,00, 2,00, 3,00) (4,00, 5,00, 6,00))) in (let ?v20 = (truncate ?v21) in (print_int ?v20)))))))

------ ClosureConversion ------
