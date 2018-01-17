------ AST ------
(print_int (int_of_float ((((sin (cos (sqrt (abs_float -12,30)))) +. 4,50) -. ((6,70 *. 8,90) /. 1,23)) *. (float_of_int 1000000))))

------ K-Normalization ------
(let ?v0 = (let ?v1 = ((((sin (cos (sqrt (abs_float -12,30)))) +. 4,50) -. ((6,70 *. 8,90) /. 1,23)) *. (float_of_int 1000000)) in (int_of_float ?v1)) in (print_int ?v0))

------ AlphaConversion ------
(let ?v2 = (let ?v3 = ((((sin (cos (sqrt (abs_float -12,30)))) +. 4,50) -. ((6,70 *. 8,90) /. 1,23)) *. (float_of_int 1000000)) in (int_of_float ?v3)) in (print_int ?v2))

------ Reduction of Nested Let-Expressions ------
(let ?v3 = ((((sin (cos (sqrt (abs_float -12,30)))) +. 4,50) -. ((6,70 *. 8,90) /. 1,23)) *. (float_of_int 1000000)) in (let ?v2 = (int_of_float ?v3) in (print_int ?v2)))

------ ClosureConversion ------
(let ?v3 = ((((sin (cos (sqrt (abs_float -12,30)))) +. 4,50) -. ((6,70 *. 8,90) /. 1,23)) *. (float_of_int 1000000)) in (let ?v2 = (int_of_float ?v3) in (print_int ?v2)))

------ Translation to Jerry ------
******** main *********
NOTHING	null
ASSIGN	?v2 (backend.variables.Variable@511d50c0) backend.instructions.InstructionCALL@60e53b93
NOTHING	?v2 (backend.variables.Variable@511d50c0)
CALL	print_int backend.variables.Variable@511d50c0 


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v2 (backend.variables.Variable@511d50c0)  : r4/r2
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

