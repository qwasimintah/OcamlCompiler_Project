------ AST ------
(let x = 42 in (print_int ?v0))
------ K-Normalization ------
(let x = 42 in (print_int ?v0))
------ AlphaConversion ------
(let ?v0 = 42 in (print_int ?v0))
------ Reduction of Nested Let-Expressions ------
(let ?v0 = 42 in (print_int ?v0))
------ Translation to Jerry ------
backend.variables.VInteger@5e481248 := 42
CALL print_int backend.variables.VInteger@5e481248 backend.variables.VInteger@5e481248 

------ Register Allocation ------
Variable ?v0 stored in register r4

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
	MOV r0, r4
	BL min_caml_print_int
	BL min_caml_print_newline

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0

