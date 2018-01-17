------ AST ------
(let rec make_adder x = (let rec adder y = (x + y) in adder) in (print_int (make_adder ((make_adder 3) 7) 10)))

------ K-Normalization ------
(let rec make_adder x = (let rec adder y = (let ?v0 = x in (let ?v1 = y in (?v0 + ?v1))) in adder) in (let ?v2 = (let ?v3 = (let ?v5 = 3 in (let ?v6 = 7 in (make_adder ?v5 ?v6))) in (let ?v4 = 10 in (make_adder ?v3 ?v4))) in (print_int ?v2)))

------ AlphaConversion ------
(let rec ?v7 ?v8 = (let rec ?v9 ?v10 = (let ?v11 = ?v8 in (let ?v12 = ?v10 in (?v11 + ?v12))) in ?v9) in (let ?v13 = (let ?v14 = (let ?v15 = 3 in (let ?v16 = 7 in (?v7 ?v15 ?v16))) in (let ?v17 = 10 in (?v7 ?v14 ?v17))) in (print_int ?v13)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v7 ?v8 = (let rec ?v9 ?v10 = (let ?v11 = ?v8 in (let ?v12 = ?v10 in (?v11 + ?v12))) in ?v9) in (let ?v15 = 3 in (let ?v16 = 7 in (let ?v14 = (?v7 ?v15 ?v16) in (let ?v17 = 10 in (let ?v13 = (?v7 ?v14 ?v17) in (print_int ?v13)))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v9
	free_list: [?v8]
	args: [?v10]
	code: 
(let ?v11 = ?v8 in (let ?v12 = ?v10 in (?v11 + ?v12)))
Closure list: 
closure numbers: 2
	label: _?v9
	free_list: [?v8]
	args: [?v10]
	code: 
(let ?v11 = ?v8 in (let ?v12 = ?v10 in (?v11 + ?v12)))
	label: _?v7
	free_list: null
	args: [?v8]
	code: 
(let ?v18 = (make_closure _?v9 ?v8) in ?v18)
(let ?v15 = 3 in (let ?v16 = 7 in (let ?v14 = (apply_direct _?v7 ?v15 ?v16) in (let ?v17 = 10 in (let ?v13 = (apply_direct _?v7 ?v14 ?v17) in (print_int ?v13))))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@1d44bcfa)
ASSIGN	?v15 (backend.variables.VInteger@61bbe9ba) 3
NOTHING	tmpVar2 (backend.variables.VInteger@266474c2)
ASSIGN	?v16 (backend.variables.VInteger@610455d6) 7
NOTHING	?v15 (backend.variables.VInteger@61bbe9ba)
NOTHING	?v16 (backend.variables.VInteger@610455d6)
ASSIGN	?v14 (backend.variables.Variable@511d50c0) backend.instructions.InstructionCALL@6f94fa3e
>CALL	label1 backend.variables.VInteger@61bbe9ba backend.variables.VInteger@610455d6 
NOTHING	tmpVar3 (backend.variables.VInteger@5e481248)
ASSIGN	?v17 (backend.variables.VInteger@60e53b93) 10
NOTHING	?v14 (backend.variables.Variable@511d50c0)
NOTHING	?v17 (backend.variables.VInteger@60e53b93)
ASSIGN	?v13 (backend.variables.Variable@5e2de80c) backend.instructions.InstructionCALL@66d3c617
>CALL	label1 backend.variables.Variable@511d50c0 backend.variables.VInteger@60e53b93 
NOTHING	?v13 (backend.variables.Variable@5e2de80c)
CALL	print_int backend.variables.Variable@5e2de80c 

******** label2 *********
NOTHING	null
ASSIGN	?v11 (backend.variables.Variable@5cad8086) null
NOTHING	?v10 (backend.variables.Variable@63947c6b)
ASSIGN	?v12 (backend.variables.Variable@6e0be858) backend.variables.Variable@63947c6b
ADD	backend.variables.Variable@5cad8086 backend.variables.Variable@6e0be858

******** label1 *********
NOTHING	null


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v15 (backend.variables.VInteger@61bbe9ba)  : r4/r2
Var/Arg ?v13 (backend.variables.Variable@5e2de80c)  : r8/r2
Var/Arg ?v16 (backend.variables.VInteger@610455d6)  : r5/r3
Var/Arg ?v17 (backend.variables.VInteger@60e53b93)  : r7/r3
Var/Arg ?v14 (backend.variables.Variable@511d50c0)  : r6/r2
***main : Arguments state***

***label2 : Variables state***
Var/Arg ?v12 (backend.variables.Variable@6e0be858)  : r5/-
Var/Arg ?v11 (backend.variables.Variable@5cad8086)  : r4/-
***label2 : Arguments state***
Var/Arg ?v10 (backend.variables.Variable@63947c6b)  : -/r2

***label1 : Variables state***
***label1 : Arguments state***
Var/Arg ?v8 (backend.variables.Variable@2b193f2d)  : -/r2


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
	LDR r5, =7
	STMFD sp!,{r2-r12}
	MOV r2, r4
	MOV r3, r5
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	LDR r7, =10
	STMFD sp!,{r2-r12}
	MOV r2, r6
	MOV r3, r7
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r8, r0
	MOV r0, r8
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

	MOV r5, r2
	ADD r0, r4, r5

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4


	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


