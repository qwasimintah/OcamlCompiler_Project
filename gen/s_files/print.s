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
NOTHING	tmpVar1 (backend.variables.VInteger@6d6f6e28)
ASSIGN	?v8 (backend.variables.VInteger@1b6d3586) 123
NOTHING	?v8 (backend.variables.VInteger@1b6d3586)
ASSIGN	?v7 (backend.variables.Variable@4554617c) backend.instructions.InstructionCALL@135fbaa4
NOTHING	tmpVar2 (backend.variables.VInteger@45ee12a7)
ASSIGN	?v10 (backend.variables.VInteger@74a14482) -456
NOTHING	?v10 (backend.variables.VInteger@74a14482)
ASSIGN	?v9 (backend.variables.Variable@1540e19d) backend.instructions.InstructionCALL@330bedb4
NOTHING	tmpVar3 (backend.variables.VInteger@2503dbd3)
ASSIGN	?v12 (backend.variables.VInteger@677327b6) 789
NOTHING	tmpVar4 (backend.variables.VInteger@4b67cf4d)
ASSIGN	?v13 (backend.variables.VInteger@14ae5a5) 0
ADD	backend.variables.VInteger@677327b6 backend.variables.VInteger@14ae5a5
ASSIGN	?v11 (backend.variables.VInteger@7f31245a) backend.instructions.InstructionADD@7ea987ac
NOTHING	?v11 (backend.variables.VInteger@7f31245a)
CALL	print_int backend.variables.VInteger@7f31245a 


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v10 (backend.variables.VInteger@74a14482)  : r6/r3
Var/Arg ?v12 (backend.variables.VInteger@677327b6)  : r8/-
Var/Arg ?v7 (backend.variables.Variable@4554617c)  : r5/-
Var/Arg ?v8 (backend.variables.VInteger@1b6d3586)  : r4/r2
Var/Arg ?v11 (backend.variables.VInteger@7f31245a)  : r10/r2
Var/Arg ?v9 (backend.variables.Variable@1540e19d)  : r7/-
Var/Arg ?v13 (backend.variables.VInteger@14ae5a5)  : r9/-
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
	LDR r6, =-456
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

