------ AST ------
(let rec ack x y = (if (x <= 0) then (y + 1) else (if (y <= 0) then (ack (x - 1) 1) else (ack (x - 1) (ack x (y - 1))))) in (print_int (ack 3 10)))

------ K-Normalization ------
(let rec ack x y = (let ?v0 = x in (let ?v1 = 0 in (if (?v0 <= ?v1) then (let ?v2 = y in (let ?v3 = 1 in (?v2 + ?v3))) else (let ?v4 = y in (let ?v5 = 0 in (if (?v4 <= ?v5) then (let ?v6 = (let ?v8 = x in (let ?v9 = 1 in (?v8 - ?v9))) in (let ?v7 = 1 in (ack ?v6 ?v7))) else (let ?v10 = (let ?v16 = x in (let ?v17 = 1 in (?v16 - ?v17))) in (let ?v11 = (let ?v12 = x in (let ?v13 = (let ?v14 = y in (let ?v15 = 1 in (?v14 - ?v15))) in (ack ?v12 ?v13))) in (ack ?v10 ?v11))))))))) in (let ?v18 = (let ?v19 = 3 in (let ?v20 = 10 in (ack ?v19 ?v20))) in (print_int ?v18)))

------ AlphaConversion ------
(let rec ?v21 ?v22 ?v23 = (let ?v24 = ?v22 in (let ?v25 = 0 in (if (?v24 <= ?v25) then (let ?v26 = ?v23 in (let ?v27 = 1 in (?v26 + ?v27))) else (let ?v28 = ?v23 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = (let ?v31 = ?v22 in (let ?v32 = 1 in (?v31 - ?v32))) in (let ?v33 = 1 in (?v21 ?v30 ?v33))) else (let ?v34 = (let ?v35 = ?v22 in (let ?v36 = 1 in (?v35 - ?v36))) in (let ?v37 = (let ?v38 = ?v22 in (let ?v39 = (let ?v40 = ?v23 in (let ?v41 = 1 in (?v40 - ?v41))) in (?v21 ?v38 ?v39))) in (?v21 ?v34 ?v37))))))))) in (let ?v42 = (let ?v43 = 3 in (let ?v44 = 10 in (?v21 ?v43 ?v44))) in (print_int ?v42)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v21 ?v22 ?v23 = (let ?v24 = ?v22 in (let ?v25 = 0 in (if (?v24 <= ?v25) then (let ?v26 = ?v23 in (let ?v27 = 1 in (?v26 + ?v27))) else (let ?v28 = ?v23 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = (let ?v31 = ?v22 in (let ?v32 = 1 in (?v31 - ?v32))) in (let ?v33 = 1 in (?v21 ?v30 ?v33))) else (let ?v34 = (let ?v35 = ?v22 in (let ?v36 = 1 in (?v35 - ?v36))) in (let ?v37 = (let ?v38 = ?v22 in (let ?v39 = (let ?v40 = ?v23 in (let ?v41 = 1 in (?v40 - ?v41))) in (?v21 ?v38 ?v39))) in (?v21 ?v34 ?v37))))))))) in (let ?v43 = 3 in (let ?v44 = 10 in (let ?v42 = (?v21 ?v43 ?v44) in (print_int ?v42)))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v21
	free_list: null
	args: [?v22, ?v23]
	code: 
(let ?v24 = ?v22 in (let ?v25 = 0 in (if (?v24 <= ?v25) then (let ?v26 = ?v23 in (let ?v27 = 1 in (?v26 + ?v27))) else (let ?v28 = ?v23 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = (let ?v31 = ?v22 in (let ?v32 = 1 in (?v31 - ?v32))) in (let ?v33 = 1 in (apply_direct _?v21 ?v30 ?v33))) else (let ?v34 = (let ?v35 = ?v22 in (let ?v36 = 1 in (?v35 - ?v36))) in (let ?v37 = (let ?v38 = ?v22 in (let ?v39 = (let ?v40 = ?v23 in (let ?v41 = 1 in (?v40 - ?v41))) in (apply_direct _?v21 ?v38 ?v39))) in (apply_direct _?v21 ?v34 ?v37)))))))))
(let ?v43 = 3 in (let ?v44 = 10 in (let ?v42 = (apply_direct _?v21 ?v43 ?v44) in (print_int ?v42))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar10 (backend.variables.VInteger@6e0be858)
ASSIGN	?v43 (backend.variables.VInteger@12a3a380) 3
NOTHING	tmpVar11 (backend.variables.VInteger@61bbe9ba)
ASSIGN	?v44 (backend.variables.VInteger@29453f44) 10
NOTHING	?v43 (backend.variables.VInteger@12a3a380)
NOTHING	?v44 (backend.variables.VInteger@29453f44)
ASSIGN	?v42 (backend.variables.Variable@5cad8086) backend.instructions.InstructionCALL@610455d6
NOTHING	?v42 (backend.variables.Variable@5cad8086)
CALL	print_int backend.variables.Variable@5cad8086 

******** label1 *********
NOTHING	?v22 (backend.variables.Variable@511d50c0)
ASSIGN	?v24 (backend.variables.Variable@1b6d3586) backend.variables.Variable@511d50c0
NOTHING	tmpVar1 (backend.variables.VInteger@60e53b93)
ASSIGN	?v25 (backend.variables.VInteger@4554617c) 0
IF	backend.variables.VBoolean@5e2de80c THEN label2 ELSE label3

******** label2 *********
NOTHING	?v23 (backend.variables.Variable@1d44bcfa)
ASSIGN	?v26 (backend.variables.Variable@74a14482) backend.variables.Variable@1d44bcfa
NOTHING	tmpVar3 (backend.variables.VInteger@266474c2)
ASSIGN	?v27 (backend.variables.VInteger@1540e19d) 1
ADD	backend.variables.Variable@74a14482 backend.variables.VInteger@1540e19d

******** label3 *********
NOTHING	?v23 (backend.variables.Variable@1d44bcfa)
ASSIGN	?v28 (backend.variables.Variable@677327b6) backend.variables.Variable@1d44bcfa
NOTHING	tmpVar4 (backend.variables.VInteger@6f94fa3e)
ASSIGN	?v29 (backend.variables.VInteger@14ae5a5) 0
IF	backend.variables.VBoolean@5e481248 THEN label4 ELSE label5

******** label4 *********
NOTHING	?v22 (backend.variables.Variable@511d50c0)
ASSIGN	?v31 (backend.variables.Variable@7f31245a) backend.variables.Variable@511d50c0
NOTHING	tmpVar6 (backend.variables.VInteger@66d3c617)
ASSIGN	?v32 (backend.variables.VInteger@6d6f6e28) 1
SUB	backend.variables.Variable@7f31245a backend.variables.VInteger@6d6f6e28
NOTHING	tmpVar7 (backend.variables.VInteger@63947c6b)
ASSIGN	?v33 (backend.variables.VInteger@135fbaa4) 1
NOTHING	null
NOTHING	?v33 (backend.variables.VInteger@135fbaa4)
CALL	label1 null backend.variables.VInteger@135fbaa4 

******** label5 *********
NOTHING	?v22 (backend.variables.Variable@511d50c0)
ASSIGN	?v35 (backend.variables.Variable@45ee12a7) backend.variables.Variable@511d50c0
NOTHING	tmpVar8 (backend.variables.VInteger@2b193f2d)
ASSIGN	?v36 (backend.variables.VInteger@330bedb4) 1
SUB	backend.variables.Variable@45ee12a7 backend.variables.VInteger@330bedb4
NOTHING	?v22 (backend.variables.Variable@511d50c0)
ASSIGN	?v38 (backend.variables.Variable@2503dbd3) backend.variables.Variable@511d50c0
NOTHING	?v23 (backend.variables.Variable@1d44bcfa)
ASSIGN	?v40 (backend.variables.Variable@4b67cf4d) backend.variables.Variable@1d44bcfa
NOTHING	tmpVar9 (backend.variables.VInteger@355da254)
ASSIGN	?v41 (backend.variables.VInteger@7ea987ac) 1
SUB	backend.variables.Variable@4b67cf4d backend.variables.VInteger@7ea987ac
NOTHING	?v38 (backend.variables.Variable@2503dbd3)
NOTHING	null
CALL	label1 backend.variables.Variable@2503dbd3 null 
NOTHING	null
NOTHING	null
CALL	label1 null null 




------ Register Allocation ------
***main : Variables state***
Var/Arg ?v44 (backend.variables.VInteger@29453f44)  : r5/r3
Var/Arg ?v43 (backend.variables.VInteger@12a3a380)  : r4/r2
Var/Arg ?v42 (backend.variables.Variable@5cad8086)  : r6/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v26 (backend.variables.Variable@74a14482)  : r6/-
Var/Arg ?v28 (backend.variables.Variable@677327b6)  : r8/-
Var/Arg ?v41 (backend.variables.VInteger@7ea987ac)  : r9/-
Var/Arg ?v32 (backend.variables.VInteger@6d6f6e28)  : r11/-
Var/Arg ?v25 (backend.variables.VInteger@4554617c)  : r5/-
Var/Arg ?v35 (backend.variables.Variable@45ee12a7)  : r5/-
Var/Arg ?v40 (backend.variables.Variable@4b67cf4d)  : r8/-
Var/Arg ?v24 (backend.variables.Variable@1b6d3586)  : r4/-
Var/Arg ?v31 (backend.variables.Variable@7f31245a)  : r10/-
Var/Arg ?v29 (backend.variables.VInteger@14ae5a5)  : r9/-
Var/Arg ?v38 (backend.variables.Variable@2503dbd3)  : r7/r3
Var/Arg ?v33 (backend.variables.VInteger@135fbaa4)  : r4/r2
Var/Arg ?v27 (backend.variables.VInteger@1540e19d)  : r7/-
Var/Arg ?v36 (backend.variables.VInteger@330bedb4)  : r6/-
***label1 : Arguments state***
Var/Arg ?v22 (backend.variables.Variable@511d50c0)  : -/r2
Var/Arg ?v23 (backend.variables.Variable@1d44bcfa)  : -/r3


------ ARM code generation ------
