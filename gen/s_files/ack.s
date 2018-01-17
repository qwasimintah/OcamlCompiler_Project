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
NOTHING	tmpVar10 (backend.variables.VInteger@6ff3c5b5)
ASSIGN	?v43 (backend.variables.VInteger@355da254) 3
NOTHING	tmpVar11 (backend.variables.VInteger@3764951d)
ASSIGN	?v44 (backend.variables.VInteger@4dc63996) 10
NOTHING	?v43 (backend.variables.VInteger@355da254)
NOTHING	?v44 (backend.variables.VInteger@4dc63996)
ASSIGN	?v42 (backend.variables.Variable@d716361) backend.instructions.InstructionCALL@4b1210ee
NOTHING	?v42 (backend.variables.Variable@d716361)
CALL	print_int backend.variables.Variable@d716361 

******** label1 *********
NOTHING	?v22 (backend.variables.Variable@4d7e1886)
ASSIGN	?v24 (backend.variables.Variable@5cad8086) backend.variables.Variable@4d7e1886
NOTHING	tmpVar1 (backend.variables.VInteger@3cd1a2f1)
ASSIGN	?v25 (backend.variables.VInteger@6e0be858) 0
IF	backend.variables.VBoolean@2f0e140b THEN label2 ELSE label3

******** label2 *********
NOTHING	?v23 (backend.variables.Variable@7440e464)
ASSIGN	?v26 (backend.variables.Variable@61bbe9ba) backend.variables.Variable@7440e464
NOTHING	tmpVar3 (backend.variables.VInteger@49476842)
ASSIGN	?v27 (backend.variables.VInteger@610455d6) 1
ADD	backend.variables.Variable@61bbe9ba backend.variables.VInteger@610455d6

******** label3 *********
NOTHING	?v23 (backend.variables.Variable@7440e464)
ASSIGN	?v28 (backend.variables.Variable@511d50c0) backend.variables.Variable@7440e464
NOTHING	tmpVar4 (backend.variables.VInteger@78308db1)
ASSIGN	?v29 (backend.variables.VInteger@60e53b93) 0
IF	backend.variables.VBoolean@27c170f0 THEN label4 ELSE label5

******** label4 *********
NOTHING	?v22 (backend.variables.Variable@4d7e1886)
ASSIGN	?v31 (backend.variables.Variable@5e2de80c) backend.variables.Variable@4d7e1886
NOTHING	tmpVar6 (backend.variables.VInteger@5451c3a8)
ASSIGN	?v32 (backend.variables.VInteger@1d44bcfa) 1
SUB	backend.variables.Variable@5e2de80c backend.variables.VInteger@1d44bcfa
NOTHING	tmpVar7 (backend.variables.VInteger@2626b418)
ASSIGN	?v33 (backend.variables.VInteger@266474c2) 1
NOTHING	null
NOTHING	?v33 (backend.variables.VInteger@266474c2)
CALL	label1 null backend.variables.VInteger@266474c2 

******** label5 *********
NOTHING	?v22 (backend.variables.Variable@4d7e1886)
ASSIGN	?v35 (backend.variables.Variable@6f94fa3e) backend.variables.Variable@4d7e1886
NOTHING	tmpVar8 (backend.variables.VInteger@5a07e868)
ASSIGN	?v36 (backend.variables.VInteger@5e481248) 1
SUB	backend.variables.Variable@6f94fa3e backend.variables.VInteger@5e481248
NOTHING	?v22 (backend.variables.Variable@4d7e1886)
ASSIGN	?v38 (backend.variables.Variable@66d3c617) backend.variables.Variable@4d7e1886
NOTHING	?v23 (backend.variables.Variable@7440e464)
ASSIGN	?v40 (backend.variables.Variable@63947c6b) backend.variables.Variable@7440e464
NOTHING	tmpVar9 (backend.variables.VInteger@76ed5528)
ASSIGN	?v41 (backend.variables.VInteger@2b193f2d) 1
SUB	backend.variables.Variable@63947c6b backend.variables.VInteger@2b193f2d
NOTHING	?v38 (backend.variables.Variable@66d3c617)
NOTHING	null
CALL	label1 backend.variables.Variable@66d3c617 null 
NOTHING	null
NOTHING	null
CALL	label1 null null 




------ Register Allocation ------
***main : Variables state***
Var/Arg ?v44 (backend.variables.VInteger@4dc63996)  : r5/r3
Var/Arg ?v42 (backend.variables.Variable@d716361)  : r6/r2
Var/Arg ?v43 (backend.variables.VInteger@355da254)  : r4/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v36 (backend.variables.VInteger@5e481248)  : r6/-
Var/Arg ?v26 (backend.variables.Variable@61bbe9ba)  : r6/-
Var/Arg ?v31 (backend.variables.Variable@5e2de80c)  : r10/-
Var/Arg ?v38 (backend.variables.Variable@66d3c617)  : r7/r3
Var/Arg ?v33 (backend.variables.VInteger@266474c2)  : r4/r2
Var/Arg ?v35 (backend.variables.Variable@6f94fa3e)  : r5/-
Var/Arg ?v24 (backend.variables.Variable@5cad8086)  : r4/-
Var/Arg ?v27 (backend.variables.VInteger@610455d6)  : r7/-
Var/Arg ?v25 (backend.variables.VInteger@6e0be858)  : r5/-
Var/Arg ?v41 (backend.variables.VInteger@2b193f2d)  : r9/-
Var/Arg ?v29 (backend.variables.VInteger@60e53b93)  : r9/-
Var/Arg ?v28 (backend.variables.Variable@511d50c0)  : r8/-
Var/Arg ?v32 (backend.variables.VInteger@1d44bcfa)  : r11/-
Var/Arg ?v40 (backend.variables.Variable@63947c6b)  : r8/-
***label1 : Arguments state***
Var/Arg ?v22 (backend.variables.Variable@4d7e1886)  : -/r2
Var/Arg ?v23 (backend.variables.Variable@7440e464)  : -/r3


------ ARM code generation ------
