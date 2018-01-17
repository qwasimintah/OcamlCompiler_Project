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

	LDR r0, =1
	STR r0 , [fp , #-4]
	LDR r0, =2
	STR r0 , [fp , #-8]
	LDR r0, =3
	STR r0 , [fp , #-12]
	LDR r1 , [fp ,#-4]
	MOV r4, r1
	LDR r1 , [fp ,#-8]
	MOV r8, r1
	LDR r1 , [fp ,#-12]
	MOV r7, r1
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

