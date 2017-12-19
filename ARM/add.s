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
	SUB sp, #4
	MOV r6, #5
	MOV r9, #9
	MUL r0, r9, r6
	MOV r4, #5
	MOV r0, #9
	STR r0 , [sp , #4]
	LDR r1 , [sp ,#4]
	ADD r0, r4, r1
	ADD sp, #4
	 @MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4
	MOV r7, #1
	swi 0
