@------ ARM------
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

	LDR r5, =10
	LDR r6, =123
	STMFD sp!,{r2-r12}
	MOV r2, r6
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r4, r0
	MOV r0, r4
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

	MOV r7, r3
	LDR r4, =0
	CMP r7 , r4
	BEQ label2
	B label3
label2:
	LDR r0, =0
	b cont1
label3:
	MOV r5, r3
	LDR r10, =1
	SUB r0, r5, r10
	MOV r8, r0
	STMFD sp!,{r2-r12}
	MOV r2, r8
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	ADD r0, r9, r6
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


