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

	LDR r8, =0
	LDR r4, =1
	STMFD sp!,{r4-r12}
	MOV r3, r8
	MOV r2, r4
	BL _f
	LDMFD sp!, {r4-r12}
	MOV r10, r0
	MOV r0, r10
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
	ADD r0, r3, r2
	MOV r6, r0
	LDR r9, =2
	SUB r0, r9, r6

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

