	.text
	.global _start
_start:
	BL _main
_main:
	SUB sp, #4
	MOV r0, #2
	STR r0, [sp]
	MOV r4, #5
	LDR r0 , [fp , #4]
	MOV r0, #9
	LDR r1 , [fp ,#4]
	ADD r0, r4, r1
	ADD sp, #4
	MOV r7, #1
	swi 0
