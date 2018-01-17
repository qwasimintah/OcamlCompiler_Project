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

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0

