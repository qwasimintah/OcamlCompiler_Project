------ AST ------
(let t = 123 in (let f = 456 in (let rec even x = (let rec odd x = (if (not (x <= 0)) then (even (x - 1)) else (if (not (0 <= x)) then (even (x + 1)) else f)) in (if (not (x <= 0)) then (odd (x - 1)) else (if (not (0 <= x)) then (odd (x + 1)) else t))) in (print_int (even 789)))))

------ K-Normalization ------
(let t = 123 in (let f = 456 in (let rec even x = (let rec odd x = (let ?v0 = x in (let ?v1 = 0 in (if (?v0 <= ?v1) then (let ?v2 = 0 in (let ?v3 = x in (if (?v2 <= ?v3) then f else (let ?v4 = (let ?v5 = x in (let ?v6 = 1 in (?v5 + ?v6))) in (even ?v4))))) else (let ?v7 = (let ?v8 = x in (let ?v9 = 1 in (?v8 - ?v9))) in (even ?v7))))) in (let ?v10 = x in (let ?v11 = 0 in (if (?v10 <= ?v11) then (let ?v12 = 0 in (let ?v13 = x in (if (?v12 <= ?v13) then t else (let ?v14 = (let ?v15 = x in (let ?v16 = 1 in (?v15 + ?v16))) in (odd ?v14))))) else (let ?v17 = (let ?v18 = x in (let ?v19 = 1 in (?v18 - ?v19))) in (odd ?v17)))))) in (let ?v20 = (let ?v21 = 789 in (even ?v21)) in (print_int ?v20)))))

------ AlphaConversion ------
(let ?v22 = 123 in (let ?v23 = 456 in (let rec ?v24 ?v25 = (let rec ?v26 ?v27 = (let ?v28 = ?v27 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = 0 in (let ?v31 = ?v27 in (if (?v30 <= ?v31) then ?v23 else (let ?v32 = (let ?v33 = ?v27 in (let ?v34 = 1 in (?v33 + ?v34))) in (?v24 ?v32))))) else (let ?v35 = (let ?v36 = ?v27 in (let ?v37 = 1 in (?v36 - ?v37))) in (?v24 ?v35))))) in (let ?v38 = ?v25 in (let ?v39 = 0 in (if (?v38 <= ?v39) then (let ?v40 = 0 in (let ?v41 = ?v25 in (if (?v40 <= ?v41) then ?v22 else (let ?v42 = (let ?v43 = ?v25 in (let ?v44 = 1 in (?v43 + ?v44))) in (?v26 ?v42))))) else (let ?v45 = (let ?v46 = ?v25 in (let ?v47 = 1 in (?v46 - ?v47))) in (?v26 ?v45)))))) in (let ?v48 = (let ?v49 = 789 in (?v24 ?v49)) in (print_int ?v48)))))

------ Reduction of Nested Let-Expressions ------
(let ?v22 = 123 in (let ?v23 = 456 in (let rec ?v24 ?v25 = (let rec ?v26 ?v27 = (let ?v28 = ?v27 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = 0 in (let ?v31 = ?v27 in (if (?v30 <= ?v31) then ?v23 else (let ?v33 = ?v27 in (let ?v34 = 1 in (let ?v32 = (?v33 + ?v34) in (?v24 ?v32))))))) else (let ?v36 = ?v27 in (let ?v37 = 1 in (let ?v35 = (?v36 - ?v37) in (?v24 ?v35))))))) in (let ?v38 = ?v25 in (let ?v39 = 0 in (if (?v38 <= ?v39) then (let ?v40 = 0 in (let ?v41 = ?v25 in (if (?v40 <= ?v41) then ?v22 else (let ?v43 = ?v25 in (let ?v44 = 1 in (let ?v42 = (?v43 + ?v44) in (?v26 ?v42))))))) else (let ?v46 = ?v25 in (let ?v47 = 1 in (let ?v45 = (?v46 - ?v47) in (?v26 ?v45)))))))) in (let ?v49 = 789 in (let ?v48 = (?v24 ?v49) in (print_int ?v48))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v26
	free_list: [?v23]
	args: [?v27]
	code: 
(let ?v28 = ?v27 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = 0 in (let ?v31 = ?v27 in (if (?v30 <= ?v31) then ?v23 else (let ?v33 = ?v27 in (let ?v34 = 1 in (let ?v32 = (?v33 + ?v34) in (let ?v50 = (make_closure _?v24 ?v27) in (apply_closure ?v50 ?v32)))))))) else (let ?v36 = ?v27 in (let ?v37 = 1 in (let ?v35 = (?v36 - ?v37) in (apply_closure _?v24 ?v35)))))))
Closure list: 
closure numbers: 2
	label: _?v26
	free_list: [?v23]
	args: [?v27]
	code: 
(let ?v28 = ?v27 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = 0 in (let ?v31 = ?v27 in (if (?v30 <= ?v31) then ?v23 else (let ?v33 = ?v27 in (let ?v34 = 1 in (let ?v32 = (?v33 + ?v34) in (let ?v50 = (make_closure _?v24 ?v27) in (apply_closure ?v50 ?v32)))))))) else (let ?v36 = ?v27 in (let ?v37 = 1 in (let ?v35 = (?v36 - ?v37) in (apply_closure _?v24 ?v35)))))))
	label: _?v24
	free_list: [?v23, ?v22]
	args: [?v25]
	code: 
(let ?v38 = ?v25 in (let ?v39 = 0 in (if (?v38 <= ?v39) then (let ?v40 = 0 in (let ?v41 = ?v25 in (if (?v40 <= ?v41) then ?v22 else (let ?v43 = ?v25 in (let ?v44 = 1 in (let ?v42 = (?v43 + ?v44) in (let ?v51 = (make_closure _?v26 ?v27) in (apply_closure ?v51 ?v42)))))))) else (let ?v46 = ?v25 in (let ?v47 = 1 in (let ?v45 = (?v46 - ?v47) in (apply_closure _?v26 ?v45)))))))
(let ?v22 = 123 in (let ?v23 = 456 in (let ?v49 = 789 in (let ?v48 = (apply_closure _?v24 ?v49) in (print_int ?v48)))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@49476842)
ASSIGN	?v22 (backend.variables.VInteger@5cad8086) 123
NOTHING	tmpVar2 (backend.variables.VInteger@78308db1)
ASSIGN	?v23 (backend.variables.VInteger@6e0be858) 456
NOTHING	tmpVar15 (backend.variables.VInteger@27c170f0)
ASSIGN	?v49 (backend.variables.VInteger@2f0e140b) 789
NOTHING	?v49 (backend.variables.VInteger@2f0e140b)
ASSIGN	?v48 (backend.variables.Variable@7440e464) backend.instructions.InstructionCALL@5451c3a8
>CALL	label1 backend.variables.VInteger@2f0e140b 
NOTHING	?v48 (backend.variables.Variable@7440e464)
CALL	print_int backend.variables.Variable@7440e464 

******** label2 *********
NOTHING	?v27 (backend.variables.Variable@2626b418)
ASSIGN	?v28 (backend.variables.Variable@61bbe9ba) backend.variables.Variable@2626b418
NOTHING	tmpVar3 (backend.variables.VInteger@5a07e868)
ASSIGN	?v29 (backend.variables.VInteger@610455d6) 0
IF	backend.variables.VBoolean@76ed5528 THEN label3 ELSE label6

******** label3 *********
NOTHING	tmpVar5 (backend.variables.VInteger@2c7b84de)
ASSIGN	?v30 (backend.variables.VInteger@511d50c0) 0
NOTHING	?v27 (backend.variables.Variable@2626b418)
ASSIGN	?v31 (backend.variables.Variable@60e53b93) backend.variables.Variable@2626b418
IF	backend.variables.VBoolean@3fee733d THEN label4 ELSE label5

******** label4 *********
NOTHING	null

******** label5 *********
NOTHING	?v27 (backend.variables.Variable@2626b418)
ASSIGN	?v33 (backend.variables.Variable@5e2de80c) backend.variables.Variable@2626b418
NOTHING	tmpVar7 (backend.variables.VInteger@5acf9800)
ASSIGN	?v34 (backend.variables.VInteger@1d44bcfa) 1
ADD	backend.variables.Variable@5e2de80c backend.variables.VInteger@1d44bcfa
ASSIGN	?v32 (backend.variables.VInteger@266474c2) backend.instructions.InstructionADD@4617c264
>ADD	backend.variables.Variable@5e2de80c backend.variables.VInteger@1d44bcfa
NOTHING	?v32 (backend.variables.VInteger@266474c2)
CALL	label1 backend.variables.VInteger@266474c2 


******** label6 *********
NOTHING	?v27 (backend.variables.Variable@2626b418)
ASSIGN	?v36 (backend.variables.Variable@6f94fa3e) backend.variables.Variable@2626b418
NOTHING	tmpVar8 (backend.variables.VInteger@36baf30c)
ASSIGN	?v37 (backend.variables.VInteger@5e481248) 1
SUB	backend.variables.Variable@6f94fa3e backend.variables.VInteger@5e481248
ASSIGN	?v35 (backend.variables.VInteger@66d3c617) backend.instructions.InstructionSUB@7a81197d
>SUB	backend.variables.Variable@6f94fa3e backend.variables.VInteger@5e481248
NOTHING	?v35 (backend.variables.VInteger@66d3c617)
CALL	label1 backend.variables.VInteger@66d3c617 


******** label1 *********
NOTHING	?v25 (backend.variables.Variable@5ca881b5)
ASSIGN	?v38 (backend.variables.Variable@63947c6b) backend.variables.Variable@5ca881b5
NOTHING	tmpVar9 (backend.variables.VInteger@24d46ca6)
ASSIGN	?v39 (backend.variables.VInteger@2b193f2d) 0
IF	backend.variables.VBoolean@4517d9a3 THEN label7 ELSE label10

******** label7 *********
NOTHING	tmpVar11 (backend.variables.VInteger@372f7a8d)
ASSIGN	?v40 (backend.variables.VInteger@355da254) 0
NOTHING	?v25 (backend.variables.Variable@5ca881b5)
ASSIGN	?v41 (backend.variables.Variable@4dc63996) backend.variables.Variable@5ca881b5
IF	backend.variables.VBoolean@2f92e0f4 THEN label8 ELSE label9

******** label8 *********
NOTHING	null

******** label9 *********
NOTHING	?v25 (backend.variables.Variable@5ca881b5)
ASSIGN	?v43 (backend.variables.Variable@d716361) backend.variables.Variable@5ca881b5
NOTHING	tmpVar13 (backend.variables.VInteger@28a418fc)
ASSIGN	?v44 (backend.variables.VInteger@6ff3c5b5) 1
ADD	backend.variables.Variable@d716361 backend.variables.VInteger@6ff3c5b5
ASSIGN	?v42 (backend.variables.VInteger@3764951d) backend.instructions.InstructionADD@5305068a
>ADD	backend.variables.Variable@d716361 backend.variables.VInteger@6ff3c5b5
NOTHING	?v42 (backend.variables.VInteger@3764951d)
CALL	label2 backend.variables.VInteger@3764951d 


******** label10 *********
NOTHING	?v25 (backend.variables.Variable@5ca881b5)
ASSIGN	?v46 (backend.variables.Variable@4b1210ee) backend.variables.Variable@5ca881b5
NOTHING	tmpVar14 (backend.variables.VInteger@1f32e575)
ASSIGN	?v47 (backend.variables.VInteger@4d7e1886) 1
SUB	backend.variables.Variable@4b1210ee backend.variables.VInteger@4d7e1886
ASSIGN	?v45 (backend.variables.VInteger@3cd1a2f1) backend.instructions.InstructionSUB@279f2327
>SUB	backend.variables.Variable@4b1210ee backend.variables.VInteger@4d7e1886
NOTHING	?v45 (backend.variables.VInteger@3cd1a2f1)
CALL	label2 backend.variables.VInteger@3cd1a2f1 



------ Register Allocation ------
***main : Variables state***
Var/Arg ?v23 (backend.variables.VInteger@6e0be858)  : r5/-
Var/Arg ?v48 (backend.variables.Variable@7440e464)  : r7/r2
Var/Arg ?v49 (backend.variables.VInteger@2f0e140b)  : r6/r2
Var/Arg ?v22 (backend.variables.VInteger@5cad8086)  : r4/-
***main : Arguments state***

***label2 : Variables state***
Var/Arg ?v37 (backend.variables.VInteger@5e481248)  : r5/-
Var/Arg ?v28 (backend.variables.Variable@61bbe9ba)  : r4/-
Var/Arg ?v33 (backend.variables.Variable@5e2de80c)  : r8/-
Var/Arg ?v29 (backend.variables.VInteger@610455d6)  : r5/-
Var/Arg ?v35 (backend.variables.VInteger@66d3c617)  : r6/r2
Var/Arg ?v31 (backend.variables.Variable@60e53b93)  : r7/-
Var/Arg ?v32 (backend.variables.VInteger@266474c2)  : r10/r2
Var/Arg ?v36 (backend.variables.Variable@6f94fa3e)  : r4/-
Var/Arg ?v30 (backend.variables.VInteger@511d50c0)  : r6/-
Var/Arg ?v34 (backend.variables.VInteger@1d44bcfa)  : r9/-
***label2 : Arguments state***
Var/Arg ?v27 (backend.variables.Variable@2626b418)  : -/r2

***label1 : Variables state***
Var/Arg ?v41 (backend.variables.Variable@4dc63996)  : r7/-
Var/Arg ?v43 (backend.variables.Variable@d716361)  : r8/-
Var/Arg ?v45 (backend.variables.VInteger@3cd1a2f1)  : r6/r2
Var/Arg ?v39 (backend.variables.VInteger@2b193f2d)  : r5/-
Var/Arg ?v44 (backend.variables.VInteger@6ff3c5b5)  : r9/-
Var/Arg ?v47 (backend.variables.VInteger@4d7e1886)  : r5/-
Var/Arg ?v40 (backend.variables.VInteger@355da254)  : r6/-
Var/Arg ?v42 (backend.variables.VInteger@3764951d)  : r10/r2
Var/Arg ?v46 (backend.variables.Variable@4b1210ee)  : r4/-
Var/Arg ?v38 (backend.variables.Variable@63947c6b)  : r4/-
***label1 : Arguments state***
Var/Arg ?v25 (backend.variables.Variable@5ca881b5)  : -/r2


------ ARM code generation ------
	.text
	.global _start
_start:
	BL _main
_main:
	@MAIN PROLOGUE
	SUB sp, #4
	LDR lr, [sp]
	SUB sp, #4
	STR fp, [sp]
	MOV fp, sp

	LDR r4, =123
	LDR r5, =456
	LDR r6, =789
	STMFD sp!,{r2-r12}
	MOV r2, r6
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r7, r0
	MOV r0, r7
	BL min_caml_print_int
	BL min_caml_print_newline

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0
_label2:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4
	SUB sp, #4

	MOV r4, r2
	LDR r5, =0
	CMP r4 , r5
	BLE label3
	B label6
label3:
	LDR r6, =0
	MOV r7, r2
	CMP r6 , r7
	BLE label4
	B label5
label4:
	b cont2
label5:
	MOV r8, r2
	LDR r9, =1
	ADD r0, r8, r9
	MOV r10, r0
	STMFD sp!,{r2-r12}
	MOV r2, r10
	BL _label1
	LDMFD sp!, {r2-r12}
	b cont2
cont2:
	b cont1
label6:
	MOV r4, r2
	LDR r5, =1
	SUB r0, r4, r5
	MOV r6, r0
	STMFD sp!,{r2-r12}
	MOV r2, r6
	BL _label1
	LDMFD sp!, {r2-r12}
	b cont1
cont1:
	ADD sp, #4

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4
	SUB sp, #4

	MOV r4, r2
	LDR r5, =0
	CMP r4 , r5
	BLE label7
	B label10
label7:
	LDR r6, =0
	MOV r7, r2
	CMP r6 , r7
	BLE label8
	B label9
label8:
	b cont4
label9:
	MOV r8, r2
	LDR r9, =1
	ADD r0, r8, r9
	MOV r10, r0
	STMFD sp!,{r2-r12}
	MOV r2, r10
	BL _label2
	LDMFD sp!, {r2-r12}
	b cont4
cont4:
	b cont3
label10:
	MOV r4, r2
	LDR r5, =1
	SUB r0, r4, r5
	MOV r6, r0
	STMFD sp!,{r2-r12}
	MOV r2, r6
	BL _label2
	LDMFD sp!, {r2-r12}
	b cont3
cont3:
	ADD sp, #4

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


