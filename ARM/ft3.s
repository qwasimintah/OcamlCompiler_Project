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

