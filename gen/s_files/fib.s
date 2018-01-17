------ AST ------
(let rec fib n = (if (n <= 1) then n else ((fib (n - 1)) + (fib (n - 2)))) in (print_int (fib 30)))

------ K-Normalization ------
(let rec fib n = (let ?v0 = n in (let ?v1 = 1 in (if (?v0 <= ?v1) then n else (let ?v2 = (let ?v7 = (let ?v8 = n in (let ?v9 = 1 in (?v8 - ?v9))) in (fib ?v7)) in (let ?v3 = (let ?v4 = (let ?v5 = n in (let ?v6 = 2 in (?v5 - ?v6))) in (fib ?v4)) in (?v2 + ?v3)))))) in (let ?v10 = (let ?v11 = 30 in (fib ?v11)) in (print_int ?v10)))

------ AlphaConversion ------
(let rec ?v12 ?v13 = (let ?v14 = ?v13 in (let ?v15 = 1 in (if (?v14 <= ?v15) then ?v13 else (let ?v16 = (let ?v17 = (let ?v18 = ?v13 in (let ?v19 = 1 in (?v18 - ?v19))) in (?v12 ?v17)) in (let ?v20 = (let ?v21 = (let ?v22 = ?v13 in (let ?v23 = 2 in (?v22 - ?v23))) in (?v12 ?v21)) in (?v16 + ?v20)))))) in (let ?v24 = (let ?v25 = 30 in (?v12 ?v25)) in (print_int ?v24)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v12 ?v13 = (let ?v14 = ?v13 in (let ?v15 = 1 in (if (?v14 <= ?v15) then ?v13 else (let ?v16 = (let ?v17 = (let ?v18 = ?v13 in (let ?v19 = 1 in (?v18 - ?v19))) in (?v12 ?v17)) in (let ?v20 = (let ?v21 = (let ?v22 = ?v13 in (let ?v23 = 2 in (?v22 - ?v23))) in (?v12 ?v21)) in (?v16 + ?v20)))))) in (let ?v25 = 30 in (let ?v24 = (?v12 ?v25) in (print_int ?v24))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v12
	free_list: null
	args: [?v13]
	code: 
(let ?v14 = ?v13 in (let ?v15 = 1 in (if (?v14 <= ?v15) then ?v13 else (let ?v16 = (let ?v17 = (let ?v18 = ?v13 in (let ?v19 = 1 in (?v18 - ?v19))) in (apply_direct _?v12 ?v17)) in (let ?v20 = (let ?v21 = (let ?v22 = ?v13 in (let ?v23 = 2 in (?v22 - ?v23))) in (apply_direct _?v12 ?v21)) in (?v16 + ?v20))))))
(let ?v25 = 30 in (let ?v24 = (apply_direct _?v12 ?v25) in (print_int ?v24)))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar8 (backend.variables.VInteger@5e481248)
ASSIGN	?v25 (backend.variables.VInteger@266474c2) 30
NOTHING	?v25 (backend.variables.VInteger@266474c2)
ASSIGN	?v24 (backend.variables.Variable@6f94fa3e) backend.instructions.InstructionCALL@66d3c617
NOTHING	?v24 (backend.variables.Variable@6f94fa3e)
CALL	print_int backend.variables.Variable@6f94fa3e 

******** label1 *********
NOTHING	?v13 (backend.variables.Variable@63947c6b)
ASSIGN	?v14 (backend.variables.Variable@5cad8086) backend.variables.Variable@63947c6b
NOTHING	tmpVar1 (backend.variables.VInteger@2b193f2d)
ASSIGN	?v15 (backend.variables.VInteger@6e0be858) 1
IF	backend.variables.VBoolean@355da254 THEN label2 ELSE label3

******** label2 *********
NOTHING	?v13 (backend.variables.Variable@63947c6b)

******** label3 *********
NOTHING	?v13 (backend.variables.Variable@63947c6b)
ASSIGN	?v18 (backend.variables.Variable@61bbe9ba) backend.variables.Variable@63947c6b
NOTHING	tmpVar3 (backend.variables.VInteger@4dc63996)
ASSIGN	?v19 (backend.variables.VInteger@610455d6) 1
SUB	backend.variables.Variable@61bbe9ba backend.variables.VInteger@610455d6
NOTHING	null
CALL	label1 null 
NOTHING	?v13 (backend.variables.Variable@63947c6b)
ASSIGN	?v22 (backend.variables.Variable@511d50c0) backend.variables.Variable@63947c6b
NOTHING	tmpVar4 (backend.variables.VInteger@d716361)
ASSIGN	?v23 (backend.variables.VInteger@60e53b93) 2
SUB	backend.variables.Variable@511d50c0 backend.variables.VInteger@60e53b93
NOTHING	null
CALL	label1 null 
NOTHING	null
NOTHING	null
NOTHING	null
ADD	backend.variables.VInteger@5e2de80c backend.variables.VInteger@1d44bcfa



------ Register Allocation ------
***main : Variables state***
Var/Arg ?v25 (backend.variables.VInteger@266474c2)  : r4/r2
Var/Arg ?v24 (backend.variables.Variable@6f94fa3e)  : r5/r3
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v18 (backend.variables.Variable@61bbe9ba)  : r6/-
Var/Arg tmpVar6 (backend.variables.VInteger@5e2de80c)  : r10/-
Var/Arg ?v19 (backend.variables.VInteger@610455d6)  : r7/-
Var/Arg ?v15 (backend.variables.VInteger@6e0be858)  : r5/-
Var/Arg ?v23 (backend.variables.VInteger@60e53b93)  : r9/-
Var/Arg ?v14 (backend.variables.Variable@5cad8086)  : r4/-
Var/Arg ?v22 (backend.variables.Variable@511d50c0)  : r8/-
Var/Arg tmpVar7 (backend.variables.VInteger@1d44bcfa)  : r11/-
***label1 : Arguments state***
Var/Arg ?v13 (backend.variables.Variable@63947c6b)  : -/r2


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

	LDR r4, =30
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

	MOV r4, r2
	LDR r5, =1
	CMP r4 , r5
	BLE label2
	B label3
label2:
	b cont1
label3:
	MOV r6, r2
	LDR r0, =1
	LDR r7, =1
	SUB r0, r6, r7
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r4-r12}
	MOV r8, r2
	LDR r0, =2
	LDR r9, =2
	SUB r0, r8, r9
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r4-r12}
	ADD r0, r10, r11
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


