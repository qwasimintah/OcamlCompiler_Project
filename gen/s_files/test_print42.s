------ AST ------
(let x = 42 in (print_int x))

------ K-Normalization ------
(let x = 42 in (let ?v0 = x in (print_int ?v0)))

------ AlphaConversion ------
(let ?v1 = 42 in (let ?v2 = ?v1 in (print_int ?v2)))

------ Reduction of Nested Let-Expressions ------
(let ?v1 = 42 in (let ?v2 = ?v1 in (print_int ?v2)))

------ ClosureConversion ------
(let ?v1 = 42 in (let ?v2 = ?v1 in (print_int ?v2)))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@61bbe9ba)
ASSIGN	?v1 (backend.variables.VInteger@5cad8086) 42
NOTHING	?v1 (backend.variables.VInteger@5cad8086)
ASSIGN	?v2 (backend.variables.Variable@6e0be858) backend.variables.VInteger@5cad8086
NOTHING	?v2 (backend.variables.Variable@6e0be858)
CALL	print_int backend.variables.Variable@6e0be858 


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v2 (backend.variables.Variable@6e0be858)  : r5/r2
Var/Arg ?v1 (backend.variables.VInteger@5cad8086)  : r4/-
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

	LDR r4, =42
	MOV r5, r4
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

