------ AST ------
(let x = 10 in (let rec f y = (if (y = 0) then 0 else (x + (f (y - 1)))) in (print_int (f 123))))

------ K-Normalization ------
(let x = 10 in (let rec f y = (let ?v0 = y in (let ?v1 = 0 in (if (?v0 = ?v1) then 0 else (let ?v2 = x in (let ?v3 = (let ?v4 = (let ?v5 = y in (let ?v6 = 1 in (?v5 - ?v6))) in (f ?v4)) in (?v2 + ?v3)))))) in (let ?v7 = (let ?v8 = 123 in (f ?v8)) in (print_int ?v7))))

------ AlphaConversion ------
(let ?v9 = 10 in (let rec ?v10 ?v11 = (let ?v12 = ?v11 in (let ?v13 = 0 in (if (?v12 = ?v13) then 0 else (let ?v14 = ?v9 in (let ?v15 = (let ?v16 = (let ?v17 = ?v11 in (let ?v18 = 1 in (?v17 - ?v18))) in (?v10 ?v16)) in (?v14 + ?v15)))))) in (let ?v19 = (let ?v20 = 123 in (?v10 ?v20)) in (print_int ?v19))))

------ Reduction of Nested Let-Expressions ------
(let ?v9 = 10 in (let rec ?v10 ?v11 = (let ?v12 = ?v11 in (let ?v13 = 0 in (if (?v12 = ?v13) then 0 else (let ?v14 = ?v9 in (let ?v17 = ?v11 in (let ?v18 = 1 in (let ?v16 = (?v17 - ?v18) in (let ?v15 = (?v10 ?v16) in (?v14 + ?v15))))))))) in (let ?v20 = 123 in (let ?v19 = (?v10 ?v20) in (print_int ?v19)))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v10
	free_list: [?v9]
	args: [?v11]
	code: 
(let ?v12 = ?v11 in (let ?v13 = 0 in (if (?v12 = ?v13) then 0 else (let ?v14 = ?v9 in (let ?v17 = ?v11 in (let ?v18 = 1 in (let ?v16 = (?v17 - ?v18) in (let ?v15 = (let ?v21 = (make_closure _?v10 ?v11) in (apply_closure ?v21 ?v16)) in (?v14 + ?v15)))))))))
(let ?v9 = 10 in (let ?v20 = 123 in (let ?v19 = (apply_closure _?v10 ?v20) in (print_int ?v19))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@5e481248)
ASSIGN	?v9 (backend.variables.VInteger@5cad8086) 10
NOTHING	tmpVar6 (backend.variables.VInteger@66d3c617)
ASSIGN	?v20 (backend.variables.VInteger@266474c2) 123
NOTHING	?v20 (backend.variables.VInteger@266474c2)
ASSIGN	?v19 (backend.variables.Variable@6f94fa3e) backend.instructions.InstructionCALL@63947c6b
>CALL	label1 backend.variables.VInteger@266474c2 
NOTHING	?v19 (backend.variables.Variable@6f94fa3e)
CALL	print_int backend.variables.Variable@6f94fa3e 

******** label1 *********
NOTHING	?v11 (backend.variables.Variable@2b193f2d)
ASSIGN	?v12 (backend.variables.Variable@6e0be858) backend.variables.Variable@2b193f2d
NOTHING	tmpVar2 (backend.variables.VInteger@355da254)
ASSIGN	?v13 (backend.variables.VInteger@61bbe9ba) 0
IF	backend.variables.VBoolean@4dc63996 THEN label2 ELSE label3

******** label2 *********
NOTHING	tmpVar4 (backend.variables.VInteger@d716361)

******** label3 *********
NOTHING	null
ASSIGN	?v14 (backend.variables.Variable@610455d6) null
NOTHING	?v11 (backend.variables.Variable@2b193f2d)
ASSIGN	?v17 (backend.variables.Variable@511d50c0) backend.variables.Variable@2b193f2d
NOTHING	tmpVar5 (backend.variables.VInteger@6ff3c5b5)
ASSIGN	?v18 (backend.variables.VInteger@60e53b93) 1
SUB	backend.variables.Variable@511d50c0 backend.variables.VInteger@60e53b93
ASSIGN	?v16 (backend.variables.VInteger@5e2de80c) backend.instructions.InstructionSUB@3764951d
>SUB	backend.variables.Variable@511d50c0 backend.variables.VInteger@60e53b93
NOTHING	?v16 (backend.variables.VInteger@5e2de80c)
ASSIGN	?v15 (backend.variables.Variable@1d44bcfa) backend.instructions.InstructionCALL@4b1210ee
>CALL	label1 backend.variables.VInteger@5e2de80c 
ADD	backend.variables.Variable@610455d6 backend.variables.Variable@1d44bcfa



------ Register Allocation ------
***main : Variables state***
Var/Arg ?v20 (backend.variables.VInteger@266474c2)  : r5/r2
Var/Arg ?v19 (backend.variables.Variable@6f94fa3e)  : r6/r2
Var/Arg ?v9 (backend.variables.VInteger@5cad8086)  : r4/-
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v13 (backend.variables.VInteger@61bbe9ba)  : r5/-
Var/Arg ?v16 (backend.variables.VInteger@5e2de80c)  : r9/r2
Var/Arg ?v14 (backend.variables.Variable@610455d6)  : r6/-
Var/Arg ?v12 (backend.variables.Variable@6e0be858)  : r4/-
Var/Arg ?v18 (backend.variables.VInteger@60e53b93)  : r8/-
Var/Arg ?v17 (backend.variables.Variable@511d50c0)  : r7/-
Var/Arg ?v15 (backend.variables.Variable@1d44bcfa)  : r10/-
***label1 : Arguments state***
Var/Arg ?v11 (backend.variables.Variable@2b193f2d)  : -/r2


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

	LDR r4, =10
	LDR r5, =123
	STMFD sp!,{r2-r12}
	MOV r2, r5
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	MOV r0, r6
	BL min_caml_print_int
	BL min_caml_print_newline

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0
_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r2
	LDR r5, =0
	CMP r4 , r5
	BEQ label2
	B label3
label2:
	LDR r0, =0
	b cont1
label3:
	MOV r7, r2
	LDR r8, =1
	SUB r0, r7, r8
	MOV r9, r0
	STMFD sp!,{r2-r12}
	MOV r2, r9
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r10, r0
	ADD r0, r6, r10
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


