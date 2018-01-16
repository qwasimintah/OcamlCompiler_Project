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

	LDR r4, =2
	LDR r5, =2
	MOV r6, r4
	MOV r7, r5
	CMP r6 , r7
	BEQ label1
	B label2
label1:
	MOV r8, r4
	LDR r9, =1
	ADD r0, r8, r9
	b cont2

label2:
	LDR r6, =1
	ADD r0, r10, r6
	BL min_caml_print_int
	BL min_caml_print_newline
	b cont2
cont2:
	MOV r4, r0
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
