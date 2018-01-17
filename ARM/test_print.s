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

	LDR r6, =123
	MOV r0, r6
	BL min_caml_print_int
	BL min_caml_print_newline
	LDR r4, =-456
	MOV r0, r4
	BL min_caml_print_int
	BL min_caml_print_newline
	LDR r7, =789
	LDR r5, =0
	ADD r0, r7, r5
	BL min_caml_print_int
	BL min_caml_print_newline

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0
