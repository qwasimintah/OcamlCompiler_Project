------ AST ------
(let rec f ?v0 = 123 in (let rec g ?v1 = 456 in (let rec h ?v2 = 789 in (let x = (f ()) in (let y = (g ()) in (print_int (((if ((h ()) = 0) then (x + 1) else (y + 2)) + x) + y)))))))

------ K-Normalization ------
(let rec f ?v0 = 123 in (let rec g ?v1 = 456 in (let rec h ?v2 = 789 in (let x = (let ?v3 = () in (f ?v3)) in (let y = (let ?v4 = () in (g ?v4)) in (let ?v5 = (let ?v6 = (let ?v8 = (let ?v10 = (let ?v16 = () in (h ?v16)) in (let ?v11 = 0 in (if (?v10 = ?v11) then (let ?v12 = x in (let ?v13 = 1 in (?v12 + ?v13))) else (let ?v14 = y in (let ?v15 = 2 in (?v14 + ?v15)))))) in (let ?v9 = x in (?v8 + ?v9))) in (let ?v7 = y in (?v6 + ?v7))) in (print_int ?v5)))))))

------ AlphaConversion ------
(let rec ?v17 ?v18 = 123 in (let rec ?v19 ?v20 = 456 in (let rec ?v21 ?v22 = 789 in (let ?v23 = (let ?v24 = () in (?v17 ?v24)) in (let ?v25 = (let ?v26 = () in (?v19 ?v26)) in (let ?v27 = (let ?v28 = (let ?v29 = (let ?v30 = (let ?v31 = () in (?v21 ?v31)) in (let ?v32 = 0 in (if (?v30 = ?v32) then (let ?v33 = ?v23 in (let ?v34 = 1 in (?v33 + ?v34))) else (let ?v35 = ?v25 in (let ?v36 = 2 in (?v35 + ?v36)))))) in (let ?v37 = ?v23 in (?v29 + ?v37))) in (let ?v38 = ?v25 in (?v28 + ?v38))) in (print_int ?v27)))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v17 ?v18 = 123 in (let rec ?v19 ?v20 = 456 in (let rec ?v21 ?v22 = 789 in (let ?v24 = () in (let ?v23 = (?v17 ?v24) in (let ?v26 = () in (let ?v25 = (?v19 ?v26) in (let ?v31 = () in (let ?v30 = (?v21 ?v31) in (let ?v32 = 0 in (let ?v29 = (if (?v30 = ?v32) then (let ?v33 = ?v23 in (let ?v34 = 1 in (?v33 + ?v34))) else (let ?v35 = ?v25 in (let ?v36 = 2 in (?v35 + ?v36)))) in (let ?v37 = ?v23 in (let ?v28 = (?v29 + ?v37) in (let ?v38 = ?v25 in (let ?v27 = (?v28 + ?v38) in (print_int ?v27))))))))))))))))

------ ClosureConversion ------
