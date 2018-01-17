------ AST ------
(let rec inprod v1 v2 acc i = (if (not (0 <= i)) then acc else (inprod v1 v2 (acc +. (v1.(i) *. v2.(i))) (i - 1))) in (let v1 = (Array.create 3 1,23) in (let v2 = (Array.create 3 4,56) in (print_int (truncate (1000000,00 *. (inprod v1 v2 0,00 2)))))))

------ K-Normalization ------
(let rec inprod v1 v2 acc i = (let ?v0 = 0 in (let ?v1 = i in (if (?v0 <= ?v1) then (let ?v2 = v1 in (let ?v3 = v2 in (let ?v4 = (acc +. (v1.(i) *. v2.(i))) in (let ?v5 = (let ?v6 = i in (let ?v7 = 1 in (?v6 - ?v7))) in (inprod ?v2 ?v3 ?v4 ?v5))))) else acc))) in (let v1 = (Array.create 3 1,23) in (let v2 = (Array.create 3 4,56) in (let ?v8 = (let ?v9 = (1000000,00 *. (inprod v1 v2 0,00 2)) in (truncate ?v9)) in (print_int ?v8)))))

------ AlphaConversion ------
(let rec ?v10 ?v11 ?v12 ?v13 ?v14 = (let ?v15 = 0 in (let ?v16 = ?v14 in (if (?v15 <= ?v16) then (let ?v17 = ?v11 in (let ?v18 = ?v12 in (let ?v19 = (?v13 +. (?v11.(?v14) *. ?v12.(?v14))) in (let ?v20 = (let ?v21 = ?v14 in (let ?v22 = 1 in (?v21 - ?v22))) in (?v10 ?v17 ?v18 ?v19 ?v20))))) else ?v13))) in (let ?v23 = (Array.create 3 1,23) in (let ?v24 = (Array.create 3 4,56) in (let ?v25 = (let ?v26 = (1000000,00 *. (?v10 ?v23 ?v24 0,00 2)) in (truncate ?v26)) in (print_int ?v25)))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v10 ?v11 ?v12 ?v13 ?v14 = (let ?v15 = 0 in (let ?v16 = ?v14 in (if (?v15 <= ?v16) then (let ?v17 = ?v11 in (let ?v18 = ?v12 in (let ?v19 = (?v13 +. (?v11.(?v14) *. ?v12.(?v14))) in (let ?v21 = ?v14 in (let ?v22 = 1 in (let ?v20 = (?v21 - ?v22) in (?v10 ?v17 ?v18 ?v19 ?v20))))))) else ?v13))) in (let ?v23 = (Array.create 3 1,23) in (let ?v24 = (Array.create 3 4,56) in (let ?v26 = (1000000,00 *. (?v10 ?v23 ?v24 0,00 2)) in (let ?v25 = (truncate ?v26) in (print_int ?v25))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v10
	free_list: null
	args: [?v11, ?v12, ?v13, ?v14]
	code: 
(let ?v15 = 0 in (let ?v16 = ?v14 in (if (?v15 <= ?v16) then (let ?v17 = ?v11 in (let ?v18 = ?v12 in (let ?v19 = (?v13 +. (?v11.(?v14) *. ?v12.(?v14))) in (let ?v21 = ?v14 in (let ?v22 = 1 in (let ?v20 = (?v21 - ?v22) in (apply_direct _?v10 ?v17 ?v18 ?v19 ?v20))))))) else ?v13)))
(let ?v23 = (Array.create 3 1,23) in (let ?v24 = (Array.create 3 4,56) in (let ?v26 = (1000000,00 *. (apply_direct _?v10 ?v23 ?v24 0,00 2)) in (let ?v25 = (truncate ?v26) in (print_int ?v25)))))

------ Translation to Jerry ------
