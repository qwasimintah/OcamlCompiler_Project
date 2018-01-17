------ AST ------
(let rec inprod v1 v2 acc i = (if (not (0 <= i)) then acc else (inprod v1 v2 (acc +. (v1.(i) *. v2.(i))) (i - 1))) in (let v1 = (Array.create 3 1,23) in (let v2 = (Array.create 3 4,56) in (print_int (truncate (1000000,00 *. (inprod v1 v2 0,00 2)))))))

------ K-Normalization ------
(let rec inprod v1 v2 acc i = (let ?v0 = 0 in (let ?v1 = i in (if (?v0 <= ?v1) then (let ?v2 = v1 in (let ?v3 = v2 in (let ?v4 = (acc +. (v1.(i) *. v2.(i))) in (let ?v5 = (let ?v6 = i in (let ?v7 = 1 in (?v6 - ?v7))) in (inprod ?v2 ?v3 ?v4 ?v5))))) else acc))) in (let v1 = (Array.create 3 1,23) in (let v2 = (Array.create 3 4,56) in (let ?v8 = (let ?v9 = (1000000,00 *. (inprod v1 v2 0,00 2)) in (truncate ?v9)) in (print_int ?v8)))))

------ AlphaConversion ------
(let rec ?v10 ?v11 ?v12 ?v13 ?v14 = (let ?v15 = 0 in (let ?v16 = ?v14 in (if (?v15 <= ?v16) then (let ?v17 = ?v11 in (let ?v18 = ?v12 in (let ?v19 = (?v13 +. (?v11.(?v14) *. ?v12.(?v14))) in (let ?v20 = (let ?v21 = ?v14 in (let ?v22 = 1 in (?v21 - ?v22))) in (?v10 ?v17 ?v18 ?v19 ?v20))))) else ?v13))) in (let ?v23 = (Array.create 3 1,23) in (let ?v24 = (Array.create 3 4,56) in (let ?v25 = (let ?v26 = (1000000,00 *. (?v10 ?v23 ?v24 0,00 2)) in (truncate ?v26)) in (print_int ?v25)))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v10 ?v11 ?v12 ?v13 ?v14 = (let ?v15 = 0 in (let ?v16 = ?v14 in (if (?v15 <= ?v16) then (let ?v17 = ?v11 in (let ?v18 = ?v12 in (let ?v19 = (?v13 +. (?v11.(?v14) *. ?v12.(?v14))) in (let ?v20 = (let ?v21 = ?v14 in (let ?v22 = 1 in (?v21 - ?v22))) in (?v10 ?v17 ?v18 ?v19 ?v20))))) else ?v13))) in (let ?v23 = (Array.create 3 1,23) in (let ?v24 = (Array.create 3 4,56) in (let ?v26 = (1000000,00 *. (?v10 ?v23 ?v24 0,00 2)) in (let ?v25 = (truncate ?v26) in (print_int ?v25))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v10
	free_list: null
	args: [?v11, ?v12, ?v13, ?v14]
	code: 
(let ?v15 = 0 in (let ?v16 = ?v14 in (if (?v15 <= ?v16) then (let ?v17 = ?v11 in (let ?v18 = ?v12 in (let ?v19 = (?v13 +. (?v11.(?v14) *. ?v12.(?v14))) in (let ?v20 = (let ?v21 = ?v14 in (let ?v22 = 1 in (?v21 - ?v22))) in (apply_direct _?v10 ?v17 ?v18 ?v19 ?v20))))) else ?v13)))
(let ?v23 = (Array.create 3 1,23) in (let ?v24 = (Array.create 3 4,56) in (let ?v26 = (1000000,00 *. (apply_direct _?v10 ?v23 ?v24 0,00 2)) in (let ?v25 = (truncate ?v26) in (print_int ?v25)))))

------ Translation to Jerry ------
******** main *********
NOTHING	null
ASSIGN	?v25 (backend.variables.Variable@5e481248) backend.instructions.InstructionCALL@66d3c617
NOTHING	?v25 (backend.variables.Variable@5e481248)
CALL	print_int backend.variables.Variable@5e481248 

******** label1 *********
NOTHING	tmpVar1 (backend.variables.VInteger@63947c6b)
ASSIGN	?v15 (backend.variables.VInteger@511d50c0) 0
NOTHING	?v14 (backend.variables.Variable@2b193f2d)
ASSIGN	?v16 (backend.variables.Variable@60e53b93) backend.variables.Variable@2b193f2d
IF	backend.variables.VBoolean@355da254 THEN label2 ELSE label3

******** label2 *********
NOTHING	?v11 (backend.variables.Variable@4dc63996)
ASSIGN	?v17 (backend.variables.Variable@5e2de80c) backend.variables.Variable@4dc63996
NOTHING	?v12 (backend.variables.Variable@d716361)
ASSIGN	?v18 (backend.variables.Variable@1d44bcfa) backend.variables.Variable@d716361
NOTHING	?v14 (backend.variables.Variable@2b193f2d)
ASSIGN	?v21 (backend.variables.Variable@266474c2) backend.variables.Variable@2b193f2d
NOTHING	tmpVar3 (backend.variables.VInteger@6ff3c5b5)
ASSIGN	?v22 (backend.variables.VInteger@6f94fa3e) 1
SUB	backend.variables.Variable@266474c2 backend.variables.VInteger@6f94fa3e
NOTHING	?v17 (backend.variables.Variable@5e2de80c)
NOTHING	?v18 (backend.variables.Variable@1d44bcfa)
NOTHING	null
NOTHING	null
CALL	label1 backend.variables.Variable@5e2de80c backend.variables.Variable@1d44bcfa null null 

******** label3 *********
NOTHING	?v13 (backend.variables.Variable@3764951d)



------ Register Allocation ------
***main : Variables state***
Var/Arg ?v25 (backend.variables.Variable@5e481248)  : r4/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v17 (backend.variables.Variable@5e2de80c)  : r6/r2
Var/Arg ?v16 (backend.variables.Variable@60e53b93)  : r5/-
Var/Arg ?v21 (backend.variables.Variable@266474c2)  : r8/-
Var/Arg ?v22 (backend.variables.VInteger@6f94fa3e)  : r9/-
Var/Arg ?v15 (backend.variables.VInteger@511d50c0)  : r4/-
Var/Arg ?v18 (backend.variables.Variable@1d44bcfa)  : r7/r3
***label1 : Arguments state***
Var/Arg ?v11 (backend.variables.Variable@4dc63996)  : -/r2
Var/Arg ?v12 (backend.variables.Variable@d716361)  : -/r3
Var/Arg ?v13 (backend.variables.Variable@3764951d)  : -/[fp + 4]
Var/Arg ?v14 (backend.variables.Variable@2b193f2d)  : -/[fp + 8]


------ ARM code generation ------
