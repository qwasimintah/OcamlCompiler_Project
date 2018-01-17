------ AST ------
(let rec gcd m n = (if (m = 0) then n else (if (m <= n) then (gcd m (n - m)) else (gcd n (m - n)))) in (print_int (gcd 21600 337500)))

------ K-Normalization ------
(let rec gcd m n = (let ?v0 = m in (let ?v1 = 0 in (if (?v0 = ?v1) then n else (let ?v2 = m in (let ?v3 = n in (if (?v2 <= ?v3) then (let ?v4 = m in (let ?v5 = (let ?v6 = n in (let ?v7 = m in (?v6 - ?v7))) in (gcd ?v4 ?v5))) else (let ?v8 = n in (let ?v9 = (let ?v10 = m in (let ?v11 = n in (?v10 - ?v11))) in (gcd ?v8 ?v9))))))))) in (let ?v12 = (let ?v13 = 21600 in (let ?v14 = 337500 in (gcd ?v13 ?v14))) in (print_int ?v12)))

------ AlphaConversion ------
(let rec ?v15 ?v16 ?v17 = (let ?v18 = ?v16 in (let ?v19 = 0 in (if (?v18 = ?v19) then ?v17 else (let ?v20 = ?v16 in (let ?v21 = ?v17 in (if (?v20 <= ?v21) then (let ?v22 = ?v16 in (let ?v23 = (let ?v24 = ?v17 in (let ?v25 = ?v16 in (?v24 - ?v25))) in (?v15 ?v22 ?v23))) else (let ?v26 = ?v17 in (let ?v27 = (let ?v28 = ?v16 in (let ?v29 = ?v17 in (?v28 - ?v29))) in (?v15 ?v26 ?v27))))))))) in (let ?v30 = (let ?v31 = 21600 in (let ?v32 = 337500 in (?v15 ?v31 ?v32))) in (print_int ?v30)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v15 ?v16 ?v17 = (let ?v18 = ?v16 in (let ?v19 = 0 in (if (?v18 = ?v19) then ?v17 else (let ?v20 = ?v16 in (let ?v21 = ?v17 in (if (?v20 <= ?v21) then (let ?v22 = ?v16 in (let ?v23 = (let ?v24 = ?v17 in (let ?v25 = ?v16 in (?v24 - ?v25))) in (?v15 ?v22 ?v23))) else (let ?v26 = ?v17 in (let ?v27 = (let ?v28 = ?v16 in (let ?v29 = ?v17 in (?v28 - ?v29))) in (?v15 ?v26 ?v27))))))))) in (let ?v31 = 21600 in (let ?v32 = 337500 in (let ?v30 = (?v15 ?v31 ?v32) in (print_int ?v30)))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v15
	free_list: null
	args: [?v16, ?v17]
	code: 
(let ?v18 = ?v16 in (let ?v19 = 0 in (if (?v18 = ?v19) then ?v17 else (let ?v20 = ?v16 in (let ?v21 = ?v17 in (if (?v20 <= ?v21) then (let ?v22 = ?v16 in (let ?v23 = (let ?v24 = ?v17 in (let ?v25 = ?v16 in (?v24 - ?v25))) in (apply_direct _?v15 ?v22 ?v23))) else (let ?v26 = ?v17 in (let ?v27 = (let ?v28 = ?v16 in (let ?v29 = ?v17 in (?v28 - ?v29))) in (apply_direct _?v15 ?v26 ?v27)))))))))
(let ?v31 = 21600 in (let ?v32 = 337500 in (let ?v30 = (apply_direct _?v15 ?v31 ?v32) in (print_int ?v30))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar4 (backend.variables.VInteger@2b193f2d)
ASSIGN	?v31 (backend.variables.VInteger@5e481248) 21600
NOTHING	tmpVar5 (backend.variables.VInteger@355da254)
ASSIGN	?v32 (backend.variables.VInteger@66d3c617) 337500
NOTHING	?v31 (backend.variables.VInteger@5e481248)
NOTHING	?v32 (backend.variables.VInteger@66d3c617)
ASSIGN	?v30 (backend.variables.Variable@63947c6b) backend.instructions.InstructionCALL@4dc63996
NOTHING	?v30 (backend.variables.Variable@63947c6b)
CALL	print_int backend.variables.Variable@63947c6b 

******** label1 *********
NOTHING	?v16 (backend.variables.Variable@d716361)
ASSIGN	?v18 (backend.variables.Variable@5cad8086) backend.variables.Variable@d716361
NOTHING	tmpVar1 (backend.variables.VInteger@6ff3c5b5)
ASSIGN	?v19 (backend.variables.VInteger@6e0be858) 0
IF	backend.variables.VBoolean@3764951d THEN label2 ELSE label3

******** label2 *********
NOTHING	?v17 (backend.variables.Variable@4b1210ee)

******** label3 *********
NOTHING	?v16 (backend.variables.Variable@d716361)
ASSIGN	?v20 (backend.variables.Variable@61bbe9ba) backend.variables.Variable@d716361
NOTHING	?v17 (backend.variables.Variable@4b1210ee)
ASSIGN	?v21 (backend.variables.Variable@610455d6) backend.variables.Variable@4b1210ee
IF	backend.variables.VBoolean@4d7e1886 THEN label4 ELSE label5

******** label4 *********
NOTHING	?v16 (backend.variables.Variable@d716361)
ASSIGN	?v22 (backend.variables.Variable@511d50c0) backend.variables.Variable@d716361
NOTHING	?v17 (backend.variables.Variable@4b1210ee)
ASSIGN	?v24 (backend.variables.Variable@60e53b93) backend.variables.Variable@4b1210ee
NOTHING	?v16 (backend.variables.Variable@d716361)
ASSIGN	?v25 (backend.variables.Variable@5e2de80c) backend.variables.Variable@d716361
SUB	backend.variables.Variable@60e53b93 backend.variables.Variable@5e2de80c
NOTHING	?v22 (backend.variables.Variable@511d50c0)
NOTHING	null
CALL	label1 backend.variables.Variable@511d50c0 null 

******** label5 *********
NOTHING	?v17 (backend.variables.Variable@4b1210ee)
ASSIGN	?v26 (backend.variables.Variable@1d44bcfa) backend.variables.Variable@4b1210ee
NOTHING	?v16 (backend.variables.Variable@d716361)
ASSIGN	?v28 (backend.variables.Variable@266474c2) backend.variables.Variable@d716361
NOTHING	?v17 (backend.variables.Variable@4b1210ee)
ASSIGN	?v29 (backend.variables.Variable@6f94fa3e) backend.variables.Variable@4b1210ee
SUB	backend.variables.Variable@266474c2 backend.variables.Variable@6f94fa3e
NOTHING	?v26 (backend.variables.Variable@1d44bcfa)
NOTHING	null
CALL	label1 backend.variables.Variable@1d44bcfa null 




------ Register Allocation ------
***main : Variables state***
Var/Arg ?v31 (backend.variables.VInteger@5e481248)  : r4/r2
Var/Arg ?v32 (backend.variables.VInteger@66d3c617)  : r5/r3
Var/Arg ?v30 (backend.variables.Variable@63947c6b)  : r6/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v20 (backend.variables.Variable@61bbe9ba)  : r6/-
Var/Arg ?v25 (backend.variables.Variable@5e2de80c)  : r10/-
Var/Arg ?v21 (backend.variables.Variable@610455d6)  : r7/-
Var/Arg ?v19 (backend.variables.VInteger@6e0be858)  : r5/-
Var/Arg ?v24 (backend.variables.Variable@60e53b93)  : r9/-
Var/Arg ?v28 (backend.variables.Variable@266474c2)  : r4/-
Var/Arg ?v29 (backend.variables.Variable@6f94fa3e)  : r5/-
Var/Arg ?v18 (backend.variables.Variable@5cad8086)  : r4/-
Var/Arg ?v22 (backend.variables.Variable@511d50c0)  : r8/r2
Var/Arg ?v26 (backend.variables.Variable@1d44bcfa)  : r11/r3
***label1 : Arguments state***
Var/Arg ?v16 (backend.variables.Variable@d716361)  : -/r2
Var/Arg ?v17 (backend.variables.Variable@4b1210ee)  : -/r3


------ ARM code generation ------
