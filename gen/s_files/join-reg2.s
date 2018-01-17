------ AST ------
(let rec f ?v0 = 123 in (let rec g ?v1 = 456 in (let rec h ?v2 = 789 in (let x = (f ()) in (print_int ((if (x <= 0) then ((g ()) + x) else ((h ()) - x)) + x))))))

------ K-Normalization ------
(let rec f ?v0 = 123 in (let rec g ?v1 = 456 in (let rec h ?v2 = 789 in (let x = (let ?v3 = () in (f ?v3)) in (let ?v4 = (let ?v5 = (let ?v7 = x in (let ?v8 = 0 in (if (?v7 <= ?v8) then (let ?v9 = (let ?v11 = () in (g ?v11)) in (let ?v10 = x in (?v9 + ?v10))) else (let ?v12 = (let ?v14 = () in (h ?v14)) in (let ?v13 = x in (?v12 - ?v13)))))) in (let ?v6 = x in (?v5 + ?v6))) in (print_int ?v4))))))

------ AlphaConversion ------
(let rec ?v15 ?v16 = 123 in (let rec ?v17 ?v18 = 456 in (let rec ?v19 ?v20 = 789 in (let ?v21 = (let ?v22 = () in (?v15 ?v22)) in (let ?v23 = (let ?v24 = (let ?v25 = ?v21 in (let ?v26 = 0 in (if (?v25 <= ?v26) then (let ?v27 = (let ?v28 = () in (?v17 ?v28)) in (let ?v29 = ?v21 in (?v27 + ?v29))) else (let ?v30 = (let ?v31 = () in (?v19 ?v31)) in (let ?v32 = ?v21 in (?v30 - ?v32)))))) in (let ?v33 = ?v21 in (?v24 + ?v33))) in (print_int ?v23))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v15 ?v16 = 123 in (let rec ?v17 ?v18 = 456 in (let rec ?v19 ?v20 = 789 in (let ?v22 = () in (let ?v21 = (?v15 ?v22) in (let ?v25 = ?v21 in (let ?v26 = 0 in (let ?v24 = (if (?v25 <= ?v26) then (let ?v27 = (let ?v28 = () in (?v17 ?v28)) in (let ?v29 = ?v21 in (?v27 + ?v29))) else (let ?v30 = (let ?v31 = () in (?v19 ?v31)) in (let ?v32 = ?v21 in (?v30 - ?v32)))) in (let ?v33 = ?v21 in (let ?v23 = (?v24 + ?v33) in (print_int ?v23)))))))))))

------ ClosureConversion ------
