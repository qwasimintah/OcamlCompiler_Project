------ AST ------
(let rec f ?v0 = 123 in (let rec g ?v1 = 456 in (let rec h ?v2 = 789 in (let x = (f ()) in (print_int ((if (x <= 0) then (g ()) else (h ())) + x))))))

------ K-Normalization ------
(let rec f ?v0 = 123 in (let rec g ?v1 = 456 in (let rec h ?v2 = 789 in (let x = (let ?v3 = () in (f ?v3)) in (let ?v4 = (let ?v5 = (let ?v7 = x in (let ?v8 = 0 in (if (?v7 <= ?v8) then (let ?v9 = () in (g ?v9)) else (let ?v10 = () in (h ?v10))))) in (let ?v6 = x in (?v5 + ?v6))) in (print_int ?v4))))))

------ AlphaConversion ------
(let rec ?v11 ?v12 = 123 in (let rec ?v13 ?v14 = 456 in (let rec ?v15 ?v16 = 789 in (let ?v17 = (let ?v18 = () in (?v11 ?v18)) in (let ?v19 = (let ?v20 = (let ?v21 = ?v17 in (let ?v22 = 0 in (if (?v21 <= ?v22) then (let ?v23 = () in (?v13 ?v23)) else (let ?v24 = () in (?v15 ?v24))))) in (let ?v25 = ?v17 in (?v20 + ?v25))) in (print_int ?v19))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v11 ?v12 = 123 in (let rec ?v13 ?v14 = 456 in (let rec ?v15 ?v16 = 789 in (let ?v18 = () in (let ?v17 = (?v11 ?v18) in (let ?v21 = ?v17 in (let ?v22 = 0 in (let ?v20 = (if (?v21 <= ?v22) then (let ?v23 = () in (?v13 ?v23)) else (let ?v24 = () in (?v15 ?v24))) in (let ?v25 = ?v17 in (let ?v19 = (?v20 + ?v25) in (print_int ?v19)))))))))))

------ ClosureConversion ------
