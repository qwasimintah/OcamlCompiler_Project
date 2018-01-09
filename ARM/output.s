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
<<<<<<< HEAD
=======
<<<<<<< HEAD
	LDR r6, =1
	LDR r4, =3
	ADD r0, r6, r4
	MOV r7, r0
	LDR r5, =1
	SUB r0, r7, r5
=======
>>>>>>> 3121e27a5b482752efecf90b06335fc3f4fdfc60
=======
>>>>>>> 335c53b8c22eda1ed8c219338e67d520032bb1fc
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
