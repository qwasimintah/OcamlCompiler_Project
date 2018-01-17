------ AST ------
(let rec make_adder x = (let rec adder y = (x + y) in adder) in (print_int ((make_adder 3) 7)))

------ K-Normalization ------
(let rec make_adder x = (let rec adder y = (let ?v0 = x in (let ?v1 = y in (?v0 + ?v1))) in adder) in (let ?v2 = (let ?v3 = 7 in ((make_adder 3) ?v3)) in (print_int ?v2)))

------ AlphaConversion ------
(let rec ?v4 ?v5 = (let rec ?v6 ?v7 = (let ?v8 = ?v5 in (let ?v9 = ?v7 in (?v8 + ?v9))) in ?v6) in (let ?v10 = (let ?v11 = 7 in ((?v4 3) ?v11)) in (print_int ?v10)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v4 ?v5 = (let rec ?v6 ?v7 = (let ?v8 = ?v5 in (let ?v9 = ?v7 in (?v8 + ?v9))) in ?v6) in (let ?v11 = 7 in (let ?v10 = ((?v4 3) ?v11) in (print_int ?v10))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v6
	free_list: [?v5]
	args: [?v7]
	code: 
(let ?v8 = ?v5 in (let ?v9 = ?v7 in (?v8 + ?v9)))
Closure list: 
closure numbers: 2
	label: _?v6
	free_list: [?v5]
	args: [?v7]
	code: 
(let ?v8 = ?v5 in (let ?v9 = ?v7 in (?v8 + ?v9)))
	label: _?v4
	free_list: null
	args: [?v5]
	code: 
(let ?v12 = (make_closure _?v6 ?v5) in ?v12)
(let ?v11 = 7 in (let ?v10 = ((apply_direct _?v4 3) ?v11) in (print_int ?v10)))

------ Translation to Jerry ------
