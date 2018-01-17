------ AST ------
(let rec fib n = (if (n <= 1) then n else ((fib (n - 1)) + (fib (n - 2)))) in (print_int (fib 3)))

------ K-Normalization ------
(let rec fib n = (let ?v0 = n in (let ?v1 = 1 in (if (?v0 <= ?v1) then n else (let ?v2 = (let ?v7 = (let ?v8 = n in (let ?v9 = 1 in (?v8 - ?v9))) in (fib ?v7)) in (let ?v3 = (let ?v4 = (let ?v5 = n in (let ?v6 = 2 in (?v5 - ?v6))) in (fib ?v4)) in (?v2 + ?v3)))))) in (let ?v10 = (let ?v11 = 3 in (fib ?v11)) in (print_int ?v10)))

------ AlphaConversion ------
(let rec ?v12 ?v13 = (let ?v14 = ?v13 in (let ?v15 = 1 in (if (?v14 <= ?v15) then ?v13 else (let ?v16 = (let ?v17 = (let ?v18 = ?v13 in (let ?v19 = 1 in (?v18 - ?v19))) in (?v12 ?v17)) in (let ?v20 = (let ?v21 = (let ?v22 = ?v13 in (let ?v23 = 2 in (?v22 - ?v23))) in (?v12 ?v21)) in (?v16 + ?v20)))))) in (let ?v24 = (let ?v25 = 3 in (?v12 ?v25)) in (print_int ?v24)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v12 ?v13 = (let ?v14 = ?v13 in (let ?v15 = 1 in (if (?v14 <= ?v15) then ?v13 else (let ?v18 = ?v13 in (let ?v19 = 1 in (let ?v17 = (?v18 - ?v19) in (let ?v16 = (?v12 ?v17) in (let ?v22 = ?v13 in (let ?v23 = 2 in (let ?v21 = (?v22 - ?v23) in (let ?v20 = (?v12 ?v21) in (?v16 + ?v20)))))))))))) in (let ?v25 = 3 in (let ?v24 = (?v12 ?v25) in (print_int ?v24))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v12
	free_list: null
	args: [?v13]
	code: 
(let ?v14 = ?v13 in (let ?v15 = 1 in (if (?v14 <= ?v15) then ?v13 else (let ?v18 = ?v13 in (let ?v19 = 1 in (let ?v17 = (?v18 - ?v19) in (let ?v16 = (apply_direct _?v12 ?v17) in (let ?v22 = ?v13 in (let ?v23 = 2 in (let ?v21 = (?v22 - ?v23) in (let ?v20 = (apply_direct _?v12 ?v21) in (?v16 + ?v20))))))))))))
(let ?v25 = 3 in (let ?v24 = (apply_direct _?v12 ?v25) in (print_int ?v24)))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar5 (backend.variables.VInteger@63947c6b)
ASSIGN	?v25 (backend.variables.VInteger@5e481248) 3
NOTHING	?v25 (backend.variables.VInteger@5e481248)
ASSIGN	?v24 (backend.variables.Variable@66d3c617) backend.instructions.InstructionCALL@2b193f2d
>CALL	label1 backend.variables.VInteger@5e481248 
NOTHING	?v24 (backend.variables.Variable@66d3c617)
CALL	print_int backend.variables.Variable@66d3c617 

******** label1 *********
NOTHING	?v13 (backend.variables.Variable@355da254)
ASSIGN	?v14 (backend.variables.Variable@5cad8086) backend.variables.Variable@355da254
NOTHING	tmpVar1 (backend.variables.VInteger@4dc63996)
ASSIGN	?v15 (backend.variables.VInteger@6e0be858) 1
IF	backend.variables.VBoolean@d716361 THEN label2 ELSE label3

******** label2 *********
NOTHING	?v13 (backend.variables.Variable@355da254)

******** label3 *********
NOTHING	?v13 (backend.variables.Variable@355da254)
ASSIGN	?v18 (backend.variables.Variable@61bbe9ba) backend.variables.Variable@355da254
NOTHING	tmpVar3 (backend.variables.VInteger@6ff3c5b5)
ASSIGN	?v19 (backend.variables.VInteger@610455d6) 1
SUB	backend.variables.Variable@61bbe9ba backend.variables.VInteger@610455d6
ASSIGN	?v17 (backend.variables.VInteger@511d50c0) backend.instructions.InstructionSUB@3764951d
>SUB	backend.variables.Variable@61bbe9ba backend.variables.VInteger@610455d6
NOTHING	?v17 (backend.variables.VInteger@511d50c0)
ASSIGN	?v16 (backend.variables.Variable@60e53b93) backend.instructions.InstructionCALL@4b1210ee
>CALL	label1 backend.variables.VInteger@511d50c0 
NOTHING	?v13 (backend.variables.Variable@355da254)
ASSIGN	?v22 (backend.variables.Variable@5e2de80c) backend.variables.Variable@355da254
NOTHING	tmpVar4 (backend.variables.VInteger@4d7e1886)
ASSIGN	?v23 (backend.variables.VInteger@1d44bcfa) 2
SUB	backend.variables.Variable@5e2de80c backend.variables.VInteger@1d44bcfa
ASSIGN	?v21 (backend.variables.VInteger@266474c2) backend.instructions.InstructionSUB@3cd1a2f1
>SUB	backend.variables.Variable@5e2de80c backend.variables.VInteger@1d44bcfa
NOTHING	?v21 (backend.variables.VInteger@266474c2)
ASSIGN	?v20 (backend.variables.Variable@6f94fa3e) backend.instructions.InstructionCALL@2f0e140b
>CALL	label1 backend.variables.VInteger@266474c2 
ADD	backend.variables.Variable@60e53b93 backend.variables.Variable@6f94fa3e



------ Register Allocation ------
***main : Variables state***
Var/Arg ?v25 (backend.variables.VInteger@5e481248)  : r4/r2
Var/Arg ?v24 (backend.variables.Variable@66d3c617)  : r5/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v18 (backend.variables.Variable@61bbe9ba)  : r6/-
Var/Arg ?v22 (backend.variables.Variable@5e2de80c)  : r10/-
Var/Arg ?v19 (backend.variables.VInteger@610455d6)  : r7/-
Var/Arg ?v15 (backend.variables.VInteger@6e0be858)  : r5/-
Var/Arg ?v16 (backend.variables.Variable@60e53b93)  : r9/-
Var/Arg ?v21 (backend.variables.VInteger@266474c2)  : r5/r2
Var/Arg ?v20 (backend.variables.Variable@6f94fa3e)  : r6/-
Var/Arg ?v14 (backend.variables.Variable@5cad8086)  : r4/-
Var/Arg ?v17 (backend.variables.VInteger@511d50c0)  : r8/r2
Var/Arg ?v23 (backend.variables.VInteger@1d44bcfa)  : r4/-
***label1 : Arguments state***
Var/Arg ?v13 (backend.variables.Variable@355da254)  : -/r2


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

	LDR r4, =3
	STMFD sp!,{r2-r12}
	MOV r2, r4
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r5, r0
	MOV r0, r5
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
	SUB sp, #4

	MOV r4, r2
	LDR r5, =1
	CMP r4 , r5
	BLE label2
	B label3
label2:
	MOV r0, r2
	b cont1
label3:
	MOV r6, r2
	LDR r7, =1
	SUB r0, r6, r7
	MOV r8, r0
	STMFD sp!,{r2-r12}
	MOV r2, r8
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r9, r0
	MOV r10, r2
	LDR r4, =2
	SUB r0, r10, r4
	MOV r5, r0
	STMFD sp!,{r2-r12}
	MOV r2, r5
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	ADD r0, r9, r6
	b cont1
cont1:
	ADD sp, #4

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


