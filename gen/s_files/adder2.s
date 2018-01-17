------ AST ------
(let rec make_adder x = (let rec adder y = (x + y) in adder) in (print_int (make_adder ((make_adder 3) 7) 10)))

------ K-Normalization ------
(let rec make_adder x = (let rec adder y = (let ?v0 = x in (let ?v1 = y in (?v0 + ?v1))) in adder) in (let ?v2 = (let ?v3 = (let ?v5 = 7 in ((make_adder 3) ?v5)) in (let ?v4 = 10 in (make_adder ?v3 ?v4))) in (print_int ?v2)))

------ AlphaConversion ------
(let rec ?v6 ?v7 = (let rec ?v8 ?v9 = (let ?v10 = ?v7 in (let ?v11 = ?v9 in (?v10 + ?v11))) in ?v8) in (let ?v12 = (let ?v13 = (let ?v14 = 7 in ((?v6 3) ?v14)) in (let ?v15 = 10 in (?v6 ?v13 ?v15))) in (print_int ?v12)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v6 ?v7 = (let rec ?v8 ?v9 = (let ?v10 = ?v7 in (let ?v11 = ?v9 in (?v10 + ?v11))) in ?v8) in (let ?v14 = 7 in (let ?v13 = ((?v6 3) ?v14) in (let ?v15 = 10 in (let ?v12 = (?v6 ?v13 ?v15) in (print_int ?v12))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v8
	free_list: [?v7]
	args: [?v9]
	code: 
(let ?v10 = ?v7 in (let ?v11 = ?v9 in (?v10 + ?v11)))
Closure list: 
closure numbers: 2
	label: _?v8
	free_list: [?v7]
	args: [?v9]
	code: 
(let ?v10 = ?v7 in (let ?v11 = ?v9 in (?v10 + ?v11)))
	label: _?v6
	free_list: null
	args: [?v7]
	code: 
(let ?v16 = (make_closure _?v8 ?v7) in ?v16)
(let ?v14 = 7 in (let ?v13 = ((apply_direct _?v6 3) ?v14) in (let ?v15 = 10 in (let ?v12 = (apply_direct _?v6 ?v13 ?v15) in (print_int ?v12)))))

------ Translation to Jerry ------
