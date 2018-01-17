------ AST ------
(let rec foo a b c d e f = (let ?v4 = (print_int a) in (let ?v3 = (print_int b) in (let ?v2 = (print_int c) in (let ?v1 = (print_int d) in (let ?v0 = (print_int e) in (print_int f)))))) in (let rec bar a b c d e f = (foo b a d e f c) in (bar 1 2 3 4 5 6)))

------ K-Normalization ------
(let rec foo a b c d e f = (let ?v4 = (let ?v5 = a in (print_int ?v5)) in (let ?v3 = (let ?v6 = b in (print_int ?v6)) in (let ?v2 = (let ?v7 = c in (print_int ?v7)) in (let ?v1 = (let ?v8 = d in (print_int ?v8)) in (let ?v0 = (let ?v9 = e in (print_int ?v9)) in (let ?v10 = f in (print_int ?v10))))))) in (let rec bar a b c d e f = (let ?v11 = b in (let ?v12 = a in (let ?v13 = d in (let ?v14 = e in (let ?v15 = f in (let ?v16 = c in (foo ?v11 ?v12 ?v13 ?v14 ?v15 ?v16))))))) in (let ?v17 = 1 in (let ?v18 = 2 in (let ?v19 = 3 in (let ?v20 = 4 in (let ?v21 = 5 in (let ?v22 = 6 in (bar ?v17 ?v18 ?v19 ?v20 ?v21 ?v22)))))))))

------ AlphaConversion ------
(let rec ?v23 ?v24 ?v25 ?v26 ?v27 ?v28 ?v29 = (let ?v30 = (let ?v31 = ?v24 in (print_int ?v31)) in (let ?v32 = (let ?v33 = ?v25 in (print_int ?v33)) in (let ?v34 = (let ?v35 = ?v26 in (print_int ?v35)) in (let ?v36 = (let ?v37 = ?v27 in (print_int ?v37)) in (let ?v38 = (let ?v39 = ?v28 in (print_int ?v39)) in (let ?v40 = ?v29 in (print_int ?v40))))))) in (let rec ?v41 ?v42 ?v43 ?v44 ?v45 ?v46 ?v47 = (let ?v48 = ?v43 in (let ?v49 = ?v42 in (let ?v50 = ?v45 in (let ?v51 = ?v46 in (let ?v52 = ?v47 in (let ?v53 = ?v44 in (?v23 ?v48 ?v49 ?v50 ?v51 ?v52 ?v53))))))) in (let ?v54 = 1 in (let ?v55 = 2 in (let ?v56 = 3 in (let ?v57 = 4 in (let ?v58 = 5 in (let ?v59 = 6 in (?v41 ?v54 ?v55 ?v56 ?v57 ?v58 ?v59)))))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v23 ?v24 ?v25 ?v26 ?v27 ?v28 ?v29 = (let ?v31 = ?v24 in (let ?v30 = (print_int ?v31) in (let ?v33 = ?v25 in (let ?v32 = (print_int ?v33) in (let ?v35 = ?v26 in (let ?v34 = (print_int ?v35) in (let ?v37 = ?v27 in (let ?v36 = (print_int ?v37) in (let ?v39 = ?v28 in (let ?v38 = (print_int ?v39) in (let ?v40 = ?v29 in (print_int ?v40)))))))))))) in (let rec ?v41 ?v42 ?v43 ?v44 ?v45 ?v46 ?v47 = (let ?v48 = ?v43 in (let ?v49 = ?v42 in (let ?v50 = ?v45 in (let ?v51 = ?v46 in (let ?v52 = ?v47 in (let ?v53 = ?v44 in (?v23 ?v48 ?v49 ?v50 ?v51 ?v52 ?v53))))))) in (let ?v54 = 1 in (let ?v55 = 2 in (let ?v56 = 3 in (let ?v57 = 4 in (let ?v58 = 5 in (let ?v59 = 6 in (?v41 ?v54 ?v55 ?v56 ?v57 ?v58 ?v59)))))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v41
	free_list: null
	args: [?v42, ?v43, ?v44, ?v45, ?v46, ?v47]
	code: 
(let ?v48 = ?v43 in (let ?v49 = ?v42 in (let ?v50 = ?v45 in (let ?v51 = ?v46 in (let ?v52 = ?v47 in (let ?v53 = ?v44 in (apply_direct _?v23 ?v48 ?v49 ?v50 ?v51 ?v52 ?v53)))))))
Closure list: 
closure numbers: 2
	label: _?v41
	free_list: null
	args: [?v42, ?v43, ?v44, ?v45, ?v46, ?v47]
	code: 
(let ?v48 = ?v43 in (let ?v49 = ?v42 in (let ?v50 = ?v45 in (let ?v51 = ?v46 in (let ?v52 = ?v47 in (let ?v53 = ?v44 in (apply_direct _?v23 ?v48 ?v49 ?v50 ?v51 ?v52 ?v53)))))))
	label: _?v23
	free_list: null
	args: [?v24, ?v25, ?v26, ?v27, ?v28, ?v29]
	code: 
(let ?v31 = ?v24 in (let ?v30 = (print_int ?v31) in (let ?v33 = ?v25 in (let ?v32 = (print_int ?v33) in (let ?v35 = ?v26 in (let ?v34 = (print_int ?v35) in (let ?v37 = ?v27 in (let ?v36 = (print_int ?v37) in (let ?v39 = ?v28 in (let ?v38 = (print_int ?v39) in (let ?v40 = ?v29 in (print_int ?v40))))))))))))
(let ?v54 = 1 in (let ?v55 = 2 in (let ?v56 = 3 in (let ?v57 = 4 in (let ?v58 = 5 in (let ?v59 = 6 in (apply_direct _?v41 ?v54 ?v55 ?v56 ?v57 ?v58 ?v59)))))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@7440e464)
ASSIGN	?v54 (backend.variables.VInteger@6ff3c5b5) 1
NOTHING	tmpVar2 (backend.variables.VInteger@49476842)
ASSIGN	?v55 (backend.variables.VInteger@3764951d) 2
NOTHING	tmpVar3 (backend.variables.VInteger@78308db1)
ASSIGN	?v56 (backend.variables.VInteger@4b1210ee) 3
NOTHING	tmpVar4 (backend.variables.VInteger@27c170f0)
ASSIGN	?v57 (backend.variables.VInteger@4d7e1886) 4
NOTHING	tmpVar5 (backend.variables.VInteger@5451c3a8)
ASSIGN	?v58 (backend.variables.VInteger@3cd1a2f1) 5
NOTHING	tmpVar6 (backend.variables.VInteger@2626b418)
ASSIGN	?v59 (backend.variables.VInteger@2f0e140b) 6
NOTHING	?v54 (backend.variables.VInteger@6ff3c5b5)
NOTHING	?v55 (backend.variables.VInteger@3764951d)
NOTHING	?v56 (backend.variables.VInteger@4b1210ee)
NOTHING	?v57 (backend.variables.VInteger@4d7e1886)
NOTHING	?v58 (backend.variables.VInteger@3cd1a2f1)
NOTHING	?v59 (backend.variables.VInteger@2f0e140b)
CALL	label2 backend.variables.VInteger@6ff3c5b5 backend.variables.VInteger@3764951d backend.variables.VInteger@4b1210ee backend.variables.VInteger@4d7e1886 backend.variables.VInteger@3cd1a2f1 backend.variables.VInteger@2f0e140b 

******** label1 *********
NOTHING	?v24 (backend.variables.Variable@5a07e868)
ASSIGN	?v31 (backend.variables.Variable@5cad8086) backend.variables.Variable@5a07e868
NOTHING	?v31 (backend.variables.Variable@5cad8086)
ASSIGN	?v30 (backend.variables.Variable@6e0be858) backend.instructions.InstructionCALL@76ed5528
NOTHING	?v25 (backend.variables.Variable@2c7b84de)
ASSIGN	?v33 (backend.variables.Variable@61bbe9ba) backend.variables.Variable@2c7b84de
NOTHING	?v33 (backend.variables.Variable@61bbe9ba)
ASSIGN	?v32 (backend.variables.Variable@610455d6) backend.instructions.InstructionCALL@3fee733d
NOTHING	?v26 (backend.variables.Variable@5acf9800)
ASSIGN	?v35 (backend.variables.Variable@511d50c0) backend.variables.Variable@5acf9800
NOTHING	?v35 (backend.variables.Variable@511d50c0)
ASSIGN	?v34 (backend.variables.Variable@60e53b93) backend.instructions.InstructionCALL@4617c264
NOTHING	?v27 (backend.variables.Variable@36baf30c)
ASSIGN	?v37 (backend.variables.Variable@5e2de80c) backend.variables.Variable@36baf30c
NOTHING	?v37 (backend.variables.Variable@5e2de80c)
ASSIGN	?v36 (backend.variables.Variable@1d44bcfa) backend.instructions.InstructionCALL@7a81197d
NOTHING	?v28 (backend.variables.Variable@5ca881b5)
ASSIGN	?v39 (backend.variables.Variable@266474c2) backend.variables.Variable@5ca881b5
NOTHING	?v39 (backend.variables.Variable@266474c2)
ASSIGN	?v38 (backend.variables.Variable@6f94fa3e) backend.instructions.InstructionCALL@24d46ca6
NOTHING	?v29 (backend.variables.Variable@4517d9a3)
ASSIGN	?v40 (backend.variables.Variable@5e481248) backend.variables.Variable@4517d9a3
NOTHING	?v40 (backend.variables.Variable@5e481248)
CALL	print_int backend.variables.Variable@5e481248 

******** label2 *********
NOTHING	?v43 (backend.variables.Variable@372f7a8d)
ASSIGN	?v48 (backend.variables.Variable@66d3c617) backend.variables.Variable@372f7a8d
NOTHING	?v42 (backend.variables.Variable@2f92e0f4)
ASSIGN	?v49 (backend.variables.Variable@63947c6b) backend.variables.Variable@2f92e0f4
NOTHING	?v45 (backend.variables.Variable@28a418fc)
ASSIGN	?v50 (backend.variables.Variable@2b193f2d) backend.variables.Variable@28a418fc
NOTHING	?v46 (backend.variables.Variable@5305068a)
ASSIGN	?v51 (backend.variables.Variable@355da254) backend.variables.Variable@5305068a
NOTHING	?v47 (backend.variables.Variable@1f32e575)
ASSIGN	?v52 (backend.variables.Variable@4dc63996) backend.variables.Variable@1f32e575
NOTHING	?v44 (backend.variables.Variable@279f2327)
ASSIGN	?v53 (backend.variables.Variable@d716361) backend.variables.Variable@279f2327
NOTHING	?v48 (backend.variables.Variable@66d3c617)
NOTHING	?v49 (backend.variables.Variable@63947c6b)
NOTHING	?v50 (backend.variables.Variable@2b193f2d)
NOTHING	?v51 (backend.variables.Variable@355da254)
NOTHING	?v52 (backend.variables.Variable@4dc63996)
NOTHING	?v53 (backend.variables.Variable@d716361)
CALL	label1 backend.variables.Variable@66d3c617 backend.variables.Variable@63947c6b backend.variables.Variable@2b193f2d backend.variables.Variable@355da254 backend.variables.Variable@4dc63996 backend.variables.Variable@d716361 


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v58 (backend.variables.VInteger@3cd1a2f1)  : r8/[fp + 12]
Var/Arg ?v59 (backend.variables.VInteger@2f0e140b)  : r9/[fp + 16]
Var/Arg ?v54 (backend.variables.VInteger@6ff3c5b5)  : r4/r2
Var/Arg ?v57 (backend.variables.VInteger@4d7e1886)  : r7/[fp + 8]
Var/Arg ?v55 (backend.variables.VInteger@3764951d)  : r5/r3
Var/Arg ?v56 (backend.variables.VInteger@4b1210ee)  : r6/[fp + 4]
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v40 (backend.variables.Variable@5e481248)  : r6/r3
Var/Arg ?v33 (backend.variables.Variable@61bbe9ba)  : r6/r3
Var/Arg ?v37 (backend.variables.Variable@5e2de80c)  : r10/r3
Var/Arg ?v32 (backend.variables.Variable@610455d6)  : r7/-
Var/Arg ?v30 (backend.variables.Variable@6e0be858)  : r5/-
Var/Arg ?v34 (backend.variables.Variable@60e53b93)  : r9/-
Var/Arg ?v39 (backend.variables.Variable@266474c2)  : r4/r2
Var/Arg ?v38 (backend.variables.Variable@6f94fa3e)  : r5/-
Var/Arg ?v31 (backend.variables.Variable@5cad8086)  : r4/r2
Var/Arg ?v35 (backend.variables.Variable@511d50c0)  : r8/r2
Var/Arg ?v36 (backend.variables.Variable@1d44bcfa)  : r11/-
***label1 : Arguments state***
Var/Arg ?v24 (backend.variables.Variable@5a07e868)  : -/r2
Var/Arg ?v25 (backend.variables.Variable@2c7b84de)  : -/r3
Var/Arg ?v26 (backend.variables.Variable@5acf9800)  : -/[fp + 4]
Var/Arg ?v27 (backend.variables.Variable@36baf30c)  : -/[fp + 8]
Var/Arg ?v28 (backend.variables.Variable@5ca881b5)  : -/[fp + 12]
Var/Arg ?v29 (backend.variables.Variable@4517d9a3)  : -/[fp + 16]

***label2 : Variables state***
Var/Arg ?v52 (backend.variables.Variable@4dc63996)  : r8/[fp + 12]
Var/Arg ?v53 (backend.variables.Variable@d716361)  : r9/[fp + 16]
Var/Arg ?v48 (backend.variables.Variable@66d3c617)  : r4/r2
Var/Arg ?v50 (backend.variables.Variable@2b193f2d)  : r6/[fp + 4]
Var/Arg ?v51 (backend.variables.Variable@355da254)  : r7/[fp + 8]
Var/Arg ?v49 (backend.variables.Variable@63947c6b)  : r5/r3
***label2 : Arguments state***
Var/Arg ?v42 (backend.variables.Variable@2f92e0f4)  : -/r2
Var/Arg ?v43 (backend.variables.Variable@372f7a8d)  : -/r3
Var/Arg ?v44 (backend.variables.Variable@279f2327)  : -/-
Var/Arg ?v45 (backend.variables.Variable@28a418fc)  : -/-
Var/Arg ?v46 (backend.variables.Variable@5305068a)  : -/-
Var/Arg ?v47 (backend.variables.Variable@1f32e575)  : -/-


------ ARM code generation ------
