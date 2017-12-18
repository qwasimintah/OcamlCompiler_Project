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
	ADD r0, r6, r9
	MOV r6, r0
	ADD sp, #4
	 @MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4
	MOV r7, #1
	swi 0

