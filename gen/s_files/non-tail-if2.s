------ AST ------
(let rec f ?v0 = 12345 in (let y = (Array.create 10 3) in (let x = 67890 in (print_int (if (y.(0) = 3) then (((f ()) + y.(1)) + x) else 7)))))

------ K-Normalization ------
(let rec f ?v0 = 12345 in (let y = (Array.create 10 3) in (let x = 67890 in (let ?v1 = (let ?v2 = y.(0) in (let ?v3 = 3 in (if (?v2 = ?v3) then (let ?v4 = (let ?v6 = (let ?v8 = () in (f ?v8)) in (let ?v7 = y.(1) in (?v6 + ?v7))) in (let ?v5 = x in (?v4 + ?v5))) else 7))) in (print_int ?v1)))))

------ AlphaConversion ------
(let rec ?v9 ?v10 = 12345 in (let ?v11 = (Array.create 10 3) in (let ?v12 = 67890 in (let ?v13 = (let ?v14 = ?v11.(0) in (let ?v15 = 3 in (if (?v14 = ?v15) then (let ?v16 = (let ?v17 = (let ?v18 = () in (?v9 ?v18)) in (let ?v19 = ?v11.(1) in (?v17 + ?v19))) in (let ?v20 = ?v12 in (?v16 + ?v20))) else 7))) in (print_int ?v13)))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v9 ?v10 = 12345 in (let ?v11 = (Array.create 10 3) in (let ?v12 = 67890 in (let ?v14 = ?v11.(0) in (let ?v15 = 3 in (let ?v13 = (if (?v14 = ?v15) then (let ?v16 = (let ?v17 = (let ?v18 = () in (?v9 ?v18)) in (let ?v19 = ?v11.(1) in (?v17 + ?v19))) in (let ?v20 = ?v12 in (?v16 + ?v20))) else 7) in (print_int ?v13)))))))

------ ClosureConversion ------
