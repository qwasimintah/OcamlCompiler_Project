------ AST ------
(let ?v1 = (print_int 123) in (let ?v0 = (print_int (- 456)) in (print_int (789 + 0))))

------ K-Normalization ------
(let ?v1 = (let ?v2 = 123 in (print_int ?v2)) in (let ?v0 = (let ?v3 = (- 456) in (print_int ?v3)) in (let ?v4 = (let ?v5 = 789 in (let ?v6 = 0 in (?v5 + ?v6))) in (print_int ?v4))))

------ AlphaConversion ------
(let ?v7 = (let ?v8 = 123 in (print_int ?v8)) in (let ?v9 = (let ?v10 = (- 456) in (print_int ?v10)) in (let ?v11 = (let ?v12 = 789 in (let ?v13 = 0 in (?v12 + ?v13))) in (print_int ?v11))))

------ Reduction of Nested Let-Expressions ------
(let ?v8 = 123 in (let ?v7 = (print_int ?v8) in (let ?v10 = (- 456) in (let ?v9 = (print_int ?v10) in (let ?v12 = 789 in (let ?v13 = 0 in (let ?v11 = (?v12 + ?v13) in (print_int ?v11))))))))

------ ClosureConversion ------
(let ?v8 = 123 in (let ?v7 = (print_int ?v8) in (let ?v10 = (- 456) in (let ?v9 = (print_int ?v10) in (let ?v12 = 789 in (let ?v13 = 0 in (let ?v11 = (?v12 + ?v13) in (print_int ?v11))))))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@1d44bcfa)
ASSIGN	?v8 (backend.variables.VInteger@5cad8086) 123
NOTHING	?v8 (backend.variables.VInteger@5cad8086)
ASSIGN	?v7 (backend.variables.Variable@6e0be858) backend.instructions.InstructionCALL@266474c2
>CALL	print_int backend.variables.VInteger@5cad8086 
NOTHING	tmpVar2 (backend.variables.VInteger@6f94fa3e)
ASSIGN	?v10 (backend.variables.VInteger@61bbe9ba) -456
NOTHING	?v10 (backend.variables.VInteger@61bbe9ba)
ASSIGN	?v9 (backend.variables.Variable@610455d6) backend.instructions.InstructionCALL@5e481248
>CALL	print_int backend.variables.VInteger@61bbe9ba 
NOTHING	tmpVar3 (backend.variables.VInteger@66d3c617)
ASSIGN	?v12 (backend.variables.VInteger@511d50c0) 789
NOTHING	tmpVar4 (backend.variables.VInteger@63947c6b)
ASSIGN	?v13 (backend.variables.VInteger@60e53b93) 0
ADD	backend.variables.VInteger@511d50c0 backend.variables.VInteger@60e53b93
ASSIGN	?v11 (backend.variables.VInteger@5e2de80c) backend.instructions.InstructionADD@2b193f2d
>ADD	backend.variables.VInteger@511d50c0 backend.variables.VInteger@60e53b93
NOTHING	?v11 (backend.variables.VInteger@5e2de80c)
CALL	print_int backend.variables.VInteger@5e2de80c 


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v10 (backend.variables.VInteger@61bbe9ba)  : r6/r2
Var/Arg ?v11 (backend.variables.VInteger@5e2de80c)  : r10/r2
Var/Arg ?v9 (backend.variables.Variable@610455d6)  : r7/-
Var/Arg ?v7 (backend.variables.Variable@6e0be858)  : r5/-
Var/Arg ?v13 (backend.variables.VInteger@60e53b93)  : r9/-
Var/Arg ?v8 (backend.variables.VInteger@5cad8086)  : r4/r2
Var/Arg ?v12 (backend.variables.VInteger@511d50c0)  : r8/-
***main : Arguments state***


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
	MOV r0, r4
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r5, r0
	LDR r6, =-456
	MOV r0, r6
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r7, r0
	LDR r8, =789
	LDR r9, =0
	ADD r0, r8, r9
	MOV r10, r0
	MOV r0, r10
	BL min_caml_print_int
	BL min_caml_print_newline

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0

