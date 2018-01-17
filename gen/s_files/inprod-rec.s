------ AST ------
(let rec inprod v1 v2 i = (if (not (0 <= i)) then 0,00 else ((v1.(i) *. v2.(i)) +. (inprod v1 v2 (i - 1)))) in (let v1 = (Array.create 3 1,23) in (let v2 = (Array.create 3 4,56) in (print_int (truncate (1000000,00 *. (inprod v1 v2 2)))))))

------ K-Normalization ------
(let rec inprod v1 v2 i = (let ?v0 = 0 in (let ?v1 = i in (if (?v0 <= ?v1) then ((v1.(i) *. v2.(i)) +. (inprod v1 v2 (i - 1))) else 0,00))) in (let v1 = (Array.create 3 1,23) in (let v2 = (Array.create 3 4,56) in (let ?v2 = (let ?v3 = (1000000,00 *. (inprod v1 v2 2)) in (truncate ?v3)) in (print_int ?v2)))))

------ AlphaConversion ------
(let rec ?v4 ?v5 ?v6 ?v7 = (let ?v8 = 0 in (let ?v9 = ?v7 in (if (?v8 <= ?v9) then ((?v5.(?v7) *. ?v6.(?v7)) +. (?v4 ?v5 ?v6 (?v7 - 1))) else 0,00))) in (let ?v10 = (Array.create 3 1,23) in (let ?v11 = (Array.create 3 4,56) in (let ?v12 = (let ?v13 = (1000000,00 *. (?v4 ?v10 ?v11 2)) in (truncate ?v13)) in (print_int ?v12)))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v4 ?v5 ?v6 ?v7 = (let ?v8 = 0 in (let ?v9 = ?v7 in (if (?v8 <= ?v9) then ((?v5.(?v7) *. ?v6.(?v7)) +. (?v4 ?v5 ?v6 (?v7 - 1))) else 0,00))) in (let ?v10 = (Array.create 3 1,23) in (let ?v11 = (Array.create 3 4,56) in (let ?v13 = (1000000,00 *. (?v4 ?v10 ?v11 2)) in (let ?v12 = (truncate ?v13) in (print_int ?v12))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v4
	free_list: null
	args: [?v5, ?v6, ?v7]
	code: 
(let ?v8 = 0 in (let ?v9 = ?v7 in (if (?v8 <= ?v9) then ((?v5.(?v7) *. ?v6.(?v7)) +. (apply_direct _?v4 ?v5 ?v6 (?v7 - 1))) else 0,00)))
(let ?v10 = (Array.create 3 1,23) in (let ?v11 = (Array.create 3 4,56) in (let ?v13 = (1000000,00 *. (apply_direct _?v4 ?v10 ?v11 2)) in (let ?v12 = (truncate ?v13) in (print_int ?v12)))))

------ Translation to Jerry ------
******** main *********
NOTHING	null
ASSIGN	?v12 (backend.variables.Variable@5e2de80c) backend.instructions.InstructionCALL@1d44bcfa
NOTHING	?v12 (backend.variables.Variable@5e2de80c)
CALL	print_int backend.variables.Variable@5e2de80c 

******** label1 *********
NOTHING	tmpVar1 (backend.variables.VInteger@266474c2)
ASSIGN	?v8 (backend.variables.VInteger@511d50c0) 0
NOTHING	?v7 (backend.variables.Variable@6f94fa3e)
ASSIGN	?v9 (backend.variables.Variable@60e53b93) backend.variables.Variable@6f94fa3e
IF	backend.variables.VBoolean@5e481248 THEN label2 ELSE label3

******** label2 *********

******** label3 *********



------ Register Allocation ------
***main : Variables state***
Var/Arg ?v12 (backend.variables.Variable@5e2de80c)  : r4/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v9 (backend.variables.Variable@60e53b93)  : r5/-
Var/Arg ?v8 (backend.variables.VInteger@511d50c0)  : r4/-
***label1 : Arguments state***
Var/Arg ?v5 (backend.variables.Variable@66d3c617)  : -/r2
Var/Arg ?v6 (backend.variables.Variable@63947c6b)  : -/r3
Var/Arg ?v7 (backend.variables.Variable@6f94fa3e)  : -/[fp + 4]


------ ARM code generation ------
