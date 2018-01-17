class exp.LetTuple
?v1 (3, 3)
Var/Arg ?v1 (backend.variables.VTuple@12a3a380)  : r4/-
?v5 (7, 9)
Var/Arg ?v5 (backend.variables.Variable@29453f44)  : r5/r2
@------ ARM------
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

