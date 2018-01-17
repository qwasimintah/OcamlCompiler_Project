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
NOTHING	tmpVar4 (backend.variables.VInteger@7ea987ac)
ASSIGN	?v31 (backend.variables.VInteger@330bedb4) 21600
NOTHING	tmpVar5 (backend.variables.VInteger@12a3a380)
ASSIGN	?v32 (backend.variables.VInteger@2503dbd3) 337500
NOTHING	?v31 (backend.variables.VInteger@330bedb4)
NOTHING	?v32 (backend.variables.VInteger@2503dbd3)
ASSIGN	?v30 (backend.variables.Variable@4b67cf4d) backend.instructions.InstructionCALL@29453f44
NOTHING	?v30 (backend.variables.Variable@4b67cf4d)
CALL	print_int backend.variables.Variable@4b67cf4d 

******** label1 *********
NOTHING	?v16 (backend.variables.Variable@5cad8086)
ASSIGN	?v18 (backend.variables.Variable@1b6d3586) backend.variables.Variable@5cad8086
NOTHING	tmpVar1 (backend.variables.VInteger@6e0be858)
ASSIGN	?v19 (backend.variables.VInteger@4554617c) 0
IF	backend.variables.VBoolean@61bbe9ba THEN label2 ELSE label3

******** label2 *********
NOTHING	?v17 (backend.variables.Variable@610455d6)

******** label3 *********
NOTHING	?v16 (backend.variables.Variable@5cad8086)
ASSIGN	?v20 (backend.variables.Variable@74a14482) backend.variables.Variable@5cad8086
NOTHING	?v17 (backend.variables.Variable@610455d6)
ASSIGN	?v21 (backend.variables.Variable@1540e19d) backend.variables.Variable@610455d6
IF	backend.variables.VBoolean@511d50c0 THEN label4 ELSE label5

******** label4 *********
NOTHING	?v16 (backend.variables.Variable@5cad8086)
ASSIGN	?v22 (backend.variables.Variable@677327b6) backend.variables.Variable@5cad8086
NOTHING	?v17 (backend.variables.Variable@610455d6)
ASSIGN	?v24 (backend.variables.Variable@14ae5a5) backend.variables.Variable@610455d6
NOTHING	?v16 (backend.variables.Variable@5cad8086)
ASSIGN	?v25 (backend.variables.Variable@7f31245a) backend.variables.Variable@5cad8086
SUB	backend.variables.Variable@14ae5a5 backend.variables.Variable@7f31245a
NOTHING	?v22 (backend.variables.Variable@677327b6)
NOTHING	null
CALL	label1 backend.variables.Variable@677327b6 null 

******** label5 *********
NOTHING	?v17 (backend.variables.Variable@610455d6)
ASSIGN	?v26 (backend.variables.Variable@6d6f6e28) backend.variables.Variable@610455d6
NOTHING	?v16 (backend.variables.Variable@5cad8086)
ASSIGN	?v28 (backend.variables.Variable@135fbaa4) backend.variables.Variable@5cad8086
NOTHING	?v17 (backend.variables.Variable@610455d6)
ASSIGN	?v29 (backend.variables.Variable@45ee12a7) backend.variables.Variable@610455d6
SUB	backend.variables.Variable@135fbaa4 backend.variables.Variable@45ee12a7
NOTHING	?v26 (backend.variables.Variable@6d6f6e28)
NOTHING	null
CALL	label1 backend.variables.Variable@6d6f6e28 null 




------ Register Allocation ------
***main : Variables state***
Var/Arg ?v32 (backend.variables.VInteger@2503dbd3)  : r5/r3
Var/Arg ?v30 (backend.variables.Variable@4b67cf4d)  : r6/r2
Var/Arg ?v31 (backend.variables.VInteger@330bedb4)  : r4/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v20 (backend.variables.Variable@74a14482)  : r6/-
Var/Arg ?v22 (backend.variables.Variable@677327b6)  : r8/r2
Var/Arg ?v26 (backend.variables.Variable@6d6f6e28)  : r11/r3
Var/Arg ?v19 (backend.variables.VInteger@4554617c)  : r5/-
Var/Arg ?v29 (backend.variables.Variable@45ee12a7)  : r5/-
Var/Arg ?v18 (backend.variables.Variable@1b6d3586)  : r4/-
Var/Arg ?v25 (backend.variables.Variable@7f31245a)  : r10/-
Var/Arg ?v28 (backend.variables.Variable@135fbaa4)  : r4/-
Var/Arg ?v21 (backend.variables.Variable@1540e19d)  : r7/-
Var/Arg ?v24 (backend.variables.Variable@14ae5a5)  : r9/-
***label1 : Arguments state***
Var/Arg ?v16 (backend.variables.Variable@5cad8086)  : -/r2
Var/Arg ?v17 (backend.variables.Variable@610455d6)  : -/r3


------ ARM code generation ------
