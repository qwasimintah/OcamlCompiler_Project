------ AST ------
(let x = 10 in (let rec f y = (if (y = 0) then 0 else (x + (f (y - 1)))) in (print_int (f 123))))

------ K-Normalization ------
(let x = 10 in (let rec f y = (let ?v0 = y in (let ?v1 = 0 in (if (?v0 = ?v1) then 0 else (let ?v2 = x in (let ?v3 = (let ?v4 = (let ?v5 = y in (let ?v6 = 1 in (?v5 - ?v6))) in (f ?v4)) in (?v2 + ?v3)))))) in (let ?v7 = (let ?v8 = 123 in (f ?v8)) in (print_int ?v7))))

------ AlphaConversion ------
(let ?v9 = 10 in (let rec ?v10 ?v11 = (let ?v12 = ?v11 in (let ?v13 = 0 in (if (?v12 = ?v13) then 0 else (let ?v14 = ?v9 in (let ?v15 = (let ?v16 = (let ?v17 = ?v11 in (let ?v18 = 1 in (?v17 - ?v18))) in (?v10 ?v16)) in (?v14 + ?v15)))))) in (let ?v19 = (let ?v20 = 123 in (?v10 ?v20)) in (print_int ?v19))))

------ Reduction of Nested Let-Expressions ------
(let ?v9 = 10 in (let rec ?v10 ?v11 = (let ?v12 = ?v11 in (let ?v13 = 0 in (if (?v12 = ?v13) then 0 else (let ?v14 = ?v9 in (let ?v15 = (let ?v16 = (let ?v17 = ?v11 in (let ?v18 = 1 in (?v17 - ?v18))) in (?v10 ?v16)) in (?v14 + ?v15)))))) in (let ?v20 = 123 in (let ?v19 = (?v10 ?v20) in (print_int ?v19)))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v10
	free_list: [?v9]
	args: [?v11]
	code: 
(let ?v12 = ?v11 in (let ?v13 = 0 in (if (?v12 = ?v13) then 0 else (let ?v14 = ?v9 in (let ?v15 = (let ?v16 = (let ?v17 = ?v11 in (let ?v18 = 1 in (?v17 - ?v18))) in (apply_closure _?v10 ?v16)) in (?v14 + ?v15))))))
(let ?v9 = 10 in (let ?v20 = 123 in (let ?v19 = (apply_closure _?v10 ?v20) in (print_int ?v19))))

------ Translation to Jerry ------
