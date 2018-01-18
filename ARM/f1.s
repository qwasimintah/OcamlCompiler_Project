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

	LDR r6, =1
	LDR r4, =2
	STMFD sp!,{r2-r12}
	MOV r2, r6
	MOV r3, r4
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r5, r0
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
_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r10, r2
	MOV r7, r3
	ADD r0, r10, r7
	MOV r4, r0
	MOV r6, r2
	MOV r4, r3
	ADD r0, r6, r4
	MOV r8, r0
	MOV r5, r4
	ADD r0, r8, r5
	MOV r5, r0
	LDR r9, =-1
	MUL r0, r5, r9

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


