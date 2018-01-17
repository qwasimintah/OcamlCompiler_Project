@------ ARM code generation ------
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

	LDR r4, =10
	LDR r5, =123
	STMFD sp!,{r2-r12}
	MOV r2, r5
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	MOV r0, r6
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

	MOV r4, r3
	LDR r6, =0
	CMP r4 , r6
	BEQ label2
	B label3
label2:
	LDR r0, =0
	b cont1
label3:
	MOV r8, r3
	LDR r9, =1
	SUB r0, r8, r9
	MOV r10, r0
	STMFD sp!,{r2-r12}
	MOV r2, r10
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r4, r0
	ADD r0, r7, r4
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


