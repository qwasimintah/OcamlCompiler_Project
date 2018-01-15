------ AST ------
(let rec f x = (x + 1) in (print_int (f 3)))
------ K-Normalization ------
(let rec f x = (let ?v0 = x in (let ?v1 = 1 in (?v0 + ?v1))) in (print_int (f 3)))
------ AlphaConversion ------
(let rec ?v2 ?v3 = (let ?v4 = ?v3 in (let ?v5 = 1 in (?v4 + ?v5))) in (print_int (?v2 3)))
------ Reduction of Nested Let-Expressions ------
(let rec ?v2 ?v3 = (let ?v4 = ?v3 in (let ?v5 = 1 in (?v4 + ?v5))) in (print_int (?v2 3)))
LETREC
LET
LET
ADD
APP
APP
------ Translation to Jerry ------
NOTHING TO DO : backend.variables.VInteger@5cad8086
backend.variables.VInteger@6e0be858 := 3
CALL ?v2 backend.variables.VInteger@6e0be858 
CALL print_int null 

------ Register Allocation ------

------ ARM code generation ------
Instruction Not Supported

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
	STMFD sp!,{r4-r12}
	MOV r2, r4
	BL _?v2
	LDMFD sp!, {r4-r12}
	BL min_caml_print_int
	BL min_caml_print_newline

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0

