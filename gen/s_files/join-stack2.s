------ AST ------
(let rec f ?v0 = 123 in (let rec g ?v1 = 456 in (let x = (f ()) in (print_int ((if (x <= 0) then ((g ()) + x) else x) + x)))))

------ K-Normalization ------
(let rec f ?v0 = 123 in (let rec g ?v1 = 456 in (let x = (let ?v2 = () in (f ?v2)) in (let ?v3 = (let ?v4 = (let ?v6 = x in (let ?v7 = 0 in (if (?v6 <= ?v7) then (let ?v8 = (let ?v10 = () in (g ?v10)) in (let ?v9 = x in (?v8 + ?v9))) else x))) in (let ?v5 = x in (?v4 + ?v5))) in (print_int ?v3)))))

------ AlphaConversion ------
(let rec ?v11 ?v12 = 123 in (let rec ?v13 ?v14 = 456 in (let ?v15 = (let ?v16 = () in (?v11 ?v16)) in (let ?v17 = (let ?v18 = (let ?v19 = ?v15 in (let ?v20 = 0 in (if (?v19 <= ?v20) then (let ?v21 = (let ?v22 = () in (?v13 ?v22)) in (let ?v23 = ?v15 in (?v21 + ?v23))) else ?v15))) in (let ?v24 = ?v15 in (?v18 + ?v24))) in (print_int ?v17)))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v11 ?v12 = 123 in (let rec ?v13 ?v14 = 456 in (let ?v16 = () in (let ?v15 = (?v11 ?v16) in (let ?v19 = ?v15 in (let ?v20 = 0 in (let ?v18 = (if (?v19 <= ?v20) then (let ?v22 = () in (let ?v21 = (?v13 ?v22) in (let ?v23 = ?v15 in (?v21 + ?v23)))) else ?v15) in (let ?v24 = ?v15 in (let ?v17 = (?v18 + ?v24) in (print_int ?v17))))))))))

------ ClosureConversion ------
