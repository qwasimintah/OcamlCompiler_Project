------ AST ------
(let rec f n = (if (not (0 <= n)) then () else (let ?v0 = (print_int n) in (let a = (Array.create 1 f) in (a.(0) (n - 1))))) in (f 9))

------ K-Normalization ------
(let rec f n = (let ?v1 = 0 in (let ?v2 = n in (if (?v1 <= ?v2) then (let ?v0 = (let ?v3 = n in (print_int ?v3)) in (let a = (Array.create 1 f) in (let ?v4 = (let ?v5 = n in (let ?v6 = 1 in (?v5 - ?v6))) in (a.(0) ?v4)))) else ()))) in (let ?v7 = 9 in (f ?v7)))

------ AlphaConversion ------
(let rec ?v8 ?v9 = (let ?v10 = 0 in (let ?v11 = ?v9 in (if (?v10 <= ?v11) then (let ?v12 = (let ?v13 = ?v9 in (print_int ?v13)) in (let ?v14 = (Array.create 1 ?v8) in (let ?v15 = (let ?v16 = ?v9 in (let ?v17 = 1 in (?v16 - ?v17))) in (?v14.(0) ?v15)))) else ()))) in (let ?v18 = 9 in (?v8 ?v18)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v8 ?v9 = (let ?v10 = 0 in (let ?v11 = ?v9 in (if (?v10 <= ?v11) then (let ?v12 = (let ?v13 = ?v9 in (print_int ?v13)) in (let ?v14 = (Array.create 1 ?v8) in (let ?v15 = (let ?v16 = ?v9 in (let ?v17 = 1 in (?v16 - ?v17))) in (?v14.(0) ?v15)))) else ()))) in (let ?v18 = 9 in (?v8 ?v18)))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v8
	free_list: null
	args: [?v9]
	code: 
(let ?v10 = 0 in (let ?v11 = ?v9 in (if (?v10 <= ?v11) then (let ?v12 = (let ?v13 = ?v9 in (print_int ?v13)) in (let ?v14 = (Array.create 1 ?v8) in (let ?v15 = (let ?v16 = ?v9 in (let ?v17 = 1 in (?v16 - ?v17))) in (apply_closure.(0) ?v14 ?v15)))) else ())))
(let ?v18 = 9 in (apply_direct _?v8 ?v18))

------ Translation to Jerry ------
