------ AST ------
(let t = 123 in (let f = 456 in (let rec even x = (let rec odd x = (if (not (x <= 0)) then (even (x - 1)) else (if (not (0 <= x)) then (even (x + 1)) else f)) in (if (not (x <= 0)) then (odd (x - 1)) else (if (not (0 <= x)) then (odd (x + 1)) else t))) in (print_int (even 789)))))

------ K-Normalization ------
(let t = 123 in (let f = 456 in (let rec even x = (let rec odd x = (let ?v0 = x in (let ?v1 = 0 in (if (?v0 <= ?v1) then (let ?v2 = 0 in (let ?v3 = x in (if (?v2 <= ?v3) then f else (let ?v4 = (let ?v5 = x in (let ?v6 = 1 in (?v5 + ?v6))) in (even ?v4))))) else (let ?v7 = (let ?v8 = x in (let ?v9 = 1 in (?v8 - ?v9))) in (even ?v7))))) in (let ?v10 = x in (let ?v11 = 0 in (if (?v10 <= ?v11) then (let ?v12 = 0 in (let ?v13 = x in (if (?v12 <= ?v13) then t else (let ?v14 = (let ?v15 = x in (let ?v16 = 1 in (?v15 + ?v16))) in (odd ?v14))))) else (let ?v17 = (let ?v18 = x in (let ?v19 = 1 in (?v18 - ?v19))) in (odd ?v17)))))) in (let ?v20 = (let ?v21 = 789 in (even ?v21)) in (print_int ?v20)))))

------ AlphaConversion ------
(let ?v22 = 123 in (let ?v23 = 456 in (let rec ?v24 ?v25 = (let rec ?v26 ?v27 = (let ?v28 = ?v27 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = 0 in (let ?v31 = ?v27 in (if (?v30 <= ?v31) then ?v23 else (let ?v32 = (let ?v33 = ?v27 in (let ?v34 = 1 in (?v33 + ?v34))) in (?v24 ?v32))))) else (let ?v35 = (let ?v36 = ?v27 in (let ?v37 = 1 in (?v36 - ?v37))) in (?v24 ?v35))))) in (let ?v38 = ?v25 in (let ?v39 = 0 in (if (?v38 <= ?v39) then (let ?v40 = 0 in (let ?v41 = ?v25 in (if (?v40 <= ?v41) then ?v22 else (let ?v42 = (let ?v43 = ?v25 in (let ?v44 = 1 in (?v43 + ?v44))) in (?v26 ?v42))))) else (let ?v45 = (let ?v46 = ?v25 in (let ?v47 = 1 in (?v46 - ?v47))) in (?v26 ?v45)))))) in (let ?v48 = (let ?v49 = 789 in (?v24 ?v49)) in (print_int ?v48)))))

------ Reduction of Nested Let-Expressions ------
(let ?v22 = 123 in (let ?v23 = 456 in (let rec ?v24 ?v25 = (let rec ?v26 ?v27 = (let ?v28 = ?v27 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = 0 in (let ?v31 = ?v27 in (if (?v30 <= ?v31) then ?v23 else (let ?v32 = (let ?v33 = ?v27 in (let ?v34 = 1 in (?v33 + ?v34))) in (?v24 ?v32))))) else (let ?v35 = (let ?v36 = ?v27 in (let ?v37 = 1 in (?v36 - ?v37))) in (?v24 ?v35))))) in (let ?v38 = ?v25 in (let ?v39 = 0 in (if (?v38 <= ?v39) then (let ?v40 = 0 in (let ?v41 = ?v25 in (if (?v40 <= ?v41) then ?v22 else (let ?v42 = (let ?v43 = ?v25 in (let ?v44 = 1 in (?v43 + ?v44))) in (?v26 ?v42))))) else (let ?v45 = (let ?v46 = ?v25 in (let ?v47 = 1 in (?v46 - ?v47))) in (?v26 ?v45)))))) in (let ?v49 = 789 in (let ?v48 = (?v24 ?v49) in (print_int ?v48))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v26
	free_list: [?v23]
	args: [?v27]
	code: 
(let ?v28 = ?v27 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = 0 in (let ?v31 = ?v27 in (if (?v30 <= ?v31) then ?v23 else (let ?v32 = (let ?v33 = ?v27 in (let ?v34 = 1 in (?v33 + ?v34))) in (apply_closure _?v24 ?v32))))) else (let ?v35 = (let ?v36 = ?v27 in (let ?v37 = 1 in (?v36 - ?v37))) in (apply_closure _?v24 ?v35)))))
Closure list: 
closure numbers: 2
	label: _?v26
	free_list: [?v23]
	args: [?v27]
	code: 
(let ?v28 = ?v27 in (let ?v29 = 0 in (if (?v28 <= ?v29) then (let ?v30 = 0 in (let ?v31 = ?v27 in (if (?v30 <= ?v31) then ?v23 else (let ?v32 = (let ?v33 = ?v27 in (let ?v34 = 1 in (?v33 + ?v34))) in (apply_closure _?v24 ?v32))))) else (let ?v35 = (let ?v36 = ?v27 in (let ?v37 = 1 in (?v36 - ?v37))) in (apply_closure _?v24 ?v35)))))
	label: _?v24
	free_list: [?v23, ?v22]
	args: [?v25]
	code: 
(let ?v38 = ?v25 in (let ?v39 = 0 in (if (?v38 <= ?v39) then (let ?v40 = 0 in (let ?v41 = ?v25 in (if (?v40 <= ?v41) then ?v22 else (let ?v42 = (let ?v43 = ?v25 in (let ?v44 = 1 in (?v43 + ?v44))) in (apply_closure _?v26 ?v42))))) else (let ?v45 = (let ?v46 = ?v25 in (let ?v47 = 1 in (?v46 - ?v47))) in (apply_closure _?v26 ?v45)))))
(let ?v22 = 123 in (let ?v23 = 456 in (let ?v49 = 789 in (let ?v48 = (apply_closure _?v24 ?v49) in (print_int ?v48)))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@511d50c0)
ASSIGN	?v22 (backend.variables.VInteger@1b6d3586) 123
NOTHING	tmpVar2 (backend.variables.VInteger@60e53b93)
ASSIGN	?v23 (backend.variables.VInteger@4554617c) 456
NOTHING	tmpVar15 (backend.variables.VInteger@5e2de80c)
ASSIGN	?v49 (backend.variables.VInteger@61bbe9ba) 789
NOTHING	?v49 (backend.variables.VInteger@61bbe9ba)
ASSIGN	?v48 (backend.variables.Variable@610455d6) backend.instructions.InstructionCALL@1d44bcfa
NOTHING	?v48 (backend.variables.Variable@610455d6)
CALL	print_int backend.variables.Variable@610455d6 

******** label2 *********
NOTHING	?v27 (backend.variables.Variable@266474c2)
ASSIGN	?v28 (backend.variables.Variable@74a14482) backend.variables.Variable@266474c2
NOTHING	tmpVar3 (backend.variables.VInteger@6f94fa3e)
ASSIGN	?v29 (backend.variables.VInteger@1540e19d) 0
IF	backend.variables.VBoolean@5e481248 THEN label3 ELSE label6

******** label3 *********
NOTHING	tmpVar5 (backend.variables.VInteger@66d3c617)
ASSIGN	?v30 (backend.variables.VInteger@677327b6) 0
NOTHING	?v27 (backend.variables.Variable@266474c2)
ASSIGN	?v31 (backend.variables.Variable@14ae5a5) backend.variables.Variable@266474c2
IF	backend.variables.VBoolean@63947c6b THEN label4 ELSE label5

******** label4 *********
NOTHING	null

******** label5 *********
NOTHING	?v27 (backend.variables.Variable@266474c2)
ASSIGN	?v33 (backend.variables.Variable@7f31245a) backend.variables.Variable@266474c2
NOTHING	tmpVar7 (backend.variables.VInteger@2b193f2d)
ASSIGN	?v34 (backend.variables.VInteger@6d6f6e28) 1
ADD	backend.variables.Variable@7f31245a backend.variables.VInteger@6d6f6e28
NOTHING	null
CALL	label1 null 


******** label6 *********
NOTHING	?v27 (backend.variables.Variable@266474c2)
ASSIGN	?v36 (backend.variables.Variable@135fbaa4) backend.variables.Variable@266474c2
NOTHING	tmpVar8 (backend.variables.VInteger@355da254)
ASSIGN	?v37 (backend.variables.VInteger@45ee12a7) 1
SUB	backend.variables.Variable@135fbaa4 backend.variables.VInteger@45ee12a7
NOTHING	null
CALL	label1 null 


******** label1 *********
NOTHING	?v25 (backend.variables.Variable@4dc63996)
ASSIGN	?v38 (backend.variables.Variable@330bedb4) backend.variables.Variable@4dc63996
NOTHING	tmpVar9 (backend.variables.VInteger@d716361)
ASSIGN	?v39 (backend.variables.VInteger@2503dbd3) 0
IF	backend.variables.VBoolean@6ff3c5b5 THEN label7 ELSE label10

******** label7 *********
NOTHING	tmpVar11 (backend.variables.VInteger@3764951d)
ASSIGN	?v40 (backend.variables.VInteger@4b67cf4d) 0
NOTHING	?v25 (backend.variables.Variable@4dc63996)
ASSIGN	?v41 (backend.variables.Variable@7ea987ac) backend.variables.Variable@4dc63996
IF	backend.variables.VBoolean@4b1210ee THEN label8 ELSE label9

******** label8 *********
NOTHING	null

******** label9 *********
NOTHING	?v25 (backend.variables.Variable@4dc63996)
ASSIGN	?v43 (backend.variables.Variable@12a3a380) backend.variables.Variable@4dc63996
NOTHING	tmpVar13 (backend.variables.VInteger@4d7e1886)
ASSIGN	?v44 (backend.variables.VInteger@29453f44) 1
ADD	backend.variables.Variable@12a3a380 backend.variables.VInteger@29453f44
NOTHING	null
CALL	label2 null 


******** label10 *********
NOTHING	?v25 (backend.variables.Variable@4dc63996)
ASSIGN	?v46 (backend.variables.Variable@5cad8086) backend.variables.Variable@4dc63996
NOTHING	tmpVar14 (backend.variables.VInteger@3cd1a2f1)
ASSIGN	?v47 (backend.variables.VInteger@6e0be858) 1
SUB	backend.variables.Variable@5cad8086 backend.variables.VInteger@6e0be858
NOTHING	null
CALL	label2 null 



------ Register Allocation ------
***main : Variables state***
Var/Arg ?v49 (backend.variables.VInteger@61bbe9ba)  : r6/r2
Var/Arg ?v48 (backend.variables.Variable@610455d6)  : r7/r3
Var/Arg ?v23 (backend.variables.VInteger@4554617c)  : r5/-
Var/Arg ?v22 (backend.variables.VInteger@1b6d3586)  : r4/-
***main : Arguments state***

***label2 : Variables state***
Var/Arg ?v28 (backend.variables.Variable@74a14482)  : r4/-
Var/Arg ?v30 (backend.variables.VInteger@677327b6)  : r6/-
Var/Arg ?v34 (backend.variables.VInteger@6d6f6e28)  : r9/-
Var/Arg ?v37 (backend.variables.VInteger@45ee12a7)  : r11/-
Var/Arg ?v33 (backend.variables.Variable@7f31245a)  : r8/-
Var/Arg ?v36 (backend.variables.Variable@135fbaa4)  : r10/-
Var/Arg ?v29 (backend.variables.VInteger@1540e19d)  : r5/-
Var/Arg ?v31 (backend.variables.Variable@14ae5a5)  : r7/-
***label2 : Arguments state***
Var/Arg ?v27 (backend.variables.Variable@266474c2)  : -/r2

***label1 : Variables state***
Var/Arg ?v39 (backend.variables.VInteger@2503dbd3)  : r5/-
Var/Arg ?v44 (backend.variables.VInteger@29453f44)  : r9/-
Var/Arg ?v43 (backend.variables.Variable@12a3a380)  : r8/-
Var/Arg ?v47 (backend.variables.VInteger@6e0be858)  : r11/-
Var/Arg ?v41 (backend.variables.Variable@7ea987ac)  : r7/-
Var/Arg ?v40 (backend.variables.VInteger@4b67cf4d)  : r6/-
Var/Arg ?v46 (backend.variables.Variable@5cad8086)  : r10/-
Var/Arg ?v38 (backend.variables.Variable@330bedb4)  : r4/-
***label1 : Arguments state***
Var/Arg ?v25 (backend.variables.Variable@4dc63996)  : -/r2


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

	MOV r4, r2
	LDR r5, =0
	CMP r4 , r5
	BLE label3
	B label6
label3:
	LDR r0, =0
	LDR r6, =0
	MOV r7, r2
	CMP r6 , r7
	BLE label4
	B label5
label4:
	b cont2
label5:
	MOV r8, r2
	LDR r0, =1
	LDR r9, =1
	ADD r0, r8, r9
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r4-r12}
	b cont2
	b cont1
label6:
	MOV r10, r2
	LDR r0, =1
	LDR r11, =1
	SUB r0, r10, r11
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r4-r12}
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r2
	LDR r5, =0
	CMP r4 , r5
	BLE label7
	B label10
label7:
	LDR r0, =0
	LDR r6, =0
	MOV r7, r2
	CMP r6 , r7
	BLE label8
	B label9
label8:
	b cont4
label9:
	MOV r8, r2
	LDR r0, =1
	LDR r9, =1
	ADD r0, r8, r9
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label2
	LDMFD sp!, {r4-r12}
	b cont4
	b cont3
label10:
	MOV r10, r2
	LDR r0, =1
	LDR r11, =1
	SUB r0, r10, r11
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label2
	LDMFD sp!, {r4-r12}
	b cont3
cont3:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


