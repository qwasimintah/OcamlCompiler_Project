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

	LDR r4, =0
	LDR r5, =1
	LDR r6, =2
	STMFD sp!,{r4-r12}
	MOV r2, r4
	MOV r3, r5
	BL _f
	LDMFD sp!, {r4-r12}
	MOV r7, r0
	MOV r0, r7
	BL min_caml_print_int
	BL min_caml_print_newline

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0
_f:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	ADD r0, r2, r3
	MOV r5, r0
	LDR r6, =2
	SUB r0, r5, r6
	MOV r4, r0
	ADD r0, r4, r6

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

