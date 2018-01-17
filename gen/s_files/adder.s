------ AST ------
(let rec make_adder x = (let rec adder y = (x + y) in adder) in (print_int ((make_adder 3) 7)))

------ K-Normalization ------
(let rec make_adder x = (let rec adder y = (let ?v0 = x in (let ?v1 = y in (?v0 + ?v1))) in adder) in (let ?v2 = (let ?v3 = 3 in (let ?v4 = 7 in (make_adder ?v3 ?v4))) in (print_int ?v2)))

------ AlphaConversion ------
(let rec ?v5 ?v6 = (let rec ?v7 ?v8 = (let ?v9 = ?v6 in (let ?v10 = ?v8 in (?v9 + ?v10))) in ?v7) in (let ?v11 = (let ?v12 = 3 in (let ?v13 = 7 in (?v5 ?v12 ?v13))) in (print_int ?v11)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v5 ?v6 = (let rec ?v7 ?v8 = (let ?v9 = ?v6 in (let ?v10 = ?v8 in (?v9 + ?v10))) in ?v7) in (let ?v12 = 3 in (let ?v13 = 7 in (let ?v11 = (?v5 ?v12 ?v13) in (print_int ?v11)))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v7
	free_list: [?v6]
	args: [?v8]
	code: 
(let ?v9 = ?v6 in (let ?v10 = ?v8 in (?v9 + ?v10)))
Closure list: 
closure numbers: 2
	label: _?v7
	free_list: [?v6]
	args: [?v8]
	code: 
(let ?v9 = ?v6 in (let ?v10 = ?v8 in (?v9 + ?v10)))
	label: _?v5
	free_list: null
	args: [?v6]
	code: 
(let ?v14 = (make_closure _?v7 ?v6) in ?v14)
(let ?v12 = 3 in (let ?v13 = 7 in (let ?v11 = (apply_direct _?v5 ?v12 ?v13) in (print_int ?v11))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@60e53b93)
ASSIGN	?v12 (backend.variables.VInteger@61bbe9ba) 3
NOTHING	tmpVar2 (backend.variables.VInteger@5e2de80c)
ASSIGN	?v13 (backend.variables.VInteger@610455d6) 7
NOTHING	?v12 (backend.variables.VInteger@61bbe9ba)
NOTHING	?v13 (backend.variables.VInteger@610455d6)
ASSIGN	?v11 (backend.variables.Variable@511d50c0) backend.instructions.InstructionCALL@1d44bcfa
>CALL	label1 backend.variables.VInteger@61bbe9ba backend.variables.VInteger@610455d6 
NOTHING	?v11 (backend.variables.Variable@511d50c0)
CALL	print_int backend.variables.Variable@511d50c0 

******** label2 *********
NOTHING	null
ASSIGN	?v9 (backend.variables.Variable@5cad8086) null
NOTHING	?v8 (backend.variables.Variable@266474c2)
ASSIGN	?v10 (backend.variables.Variable@6e0be858) backend.variables.Variable@266474c2
ADD	backend.variables.Variable@5cad8086 backend.variables.Variable@6e0be858

******** label1 *********
NOTHING	null


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v12 (backend.variables.VInteger@61bbe9ba)  : r4/r2
Var/Arg ?v13 (backend.variables.VInteger@610455d6)  : r5/r3
Var/Arg ?v11 (backend.variables.Variable@511d50c0)  : r6/r2
***main : Arguments state***

***label2 : Variables state***
Var/Arg ?v10 (backend.variables.Variable@6e0be858)  : r5/-
Var/Arg ?v9 (backend.variables.Variable@5cad8086)  : r4/-
***label2 : Arguments state***
Var/Arg ?v8 (backend.variables.Variable@266474c2)  : -/r2

***label1 : Variables state***
***label1 : Arguments state***
Var/Arg ?v6 (backend.variables.Variable@6f94fa3e)  : -/r2


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


