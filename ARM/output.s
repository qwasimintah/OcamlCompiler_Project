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

	SUB sp, #20
	LDR r4, =4
	LDR r5, =1
	ADD r0, r4, r5
	MOV r6, r0
	MOV r7, r6
	LDR r8, =2
	ADD r0, r7, r8
	MOV r9, r0
	MOV r10, r9
	LDR r11, =3
	ADD r0, r10, r11
	MOV r4, r0
	LDR r5, =2
	LDR r6, =1
	SUB r0, r5, r6
	MOV r7, r0
	MOV r8, r4
	MOV r9, r7
	SUB r0, , 
	ADD sp, #20

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0
