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

<<<<<<< HEAD
	LDR r4, =1
	MOV r8, r4
	LDR r7, =5
	ADD r0, r8, r7
	MOV r6, r0
	MOV r5, r6
	LDR r9, =12
	ADD r0, r5, r9
=======
	LDR r4, =-42
	MOV r0, r4
>>>>>>> 14101a22f276ff98d2a0e3cfd3b2b6567ffed3af
	BL min_caml_print_int
	BL min_caml_print_newline

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0
