	.text
	.global _start
_start:
	BL _main
_main:
	@MAIN PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4
	SUB sp, #4
	MOV r6, #5
	MOV r9, #9
	SUB r0, r9, r6
	ADD sp, #4

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

	MOV r7, #1
	swi 0

