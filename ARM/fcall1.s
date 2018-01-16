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
	LDR r0, =2
	STR r0 , [fp , #4]
	STMFD sp!,{r4-r12}
	MOV r2, r4
	MOV r3, r5
	BL _f
	LDMFD sp!, {r4-r12}
	MOV  r1 , r0
	STR r1 , [fp , #4]
	LDR r0 , [fp ,#4]
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
	LDR r0, =2
	STR r0 , [fp , #4]
	LDR r0 , [fp, #4]
	SUB r0, r0, r5
	MOV r4, r0
	LDR r1 , [fp, #4]
	ADD r0, r4, r1

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

