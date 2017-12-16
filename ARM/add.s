	.text
	.global _start
_start:
	BL _main
_main:
	SUB sp, #4
	MOV r0, #2
	STR r0, [sp]
	MOV r4, #5
	MOV [fp , #4], #9
	MUL r0, [fp , #4], r4
	ADD sp, #2
	MOV r7, #1
	swi 0
