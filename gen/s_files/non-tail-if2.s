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

	LDR r4, =67890
	LDR r5, =3
	CMP  , r5
	BEQ label2
	B label3
label2:
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	ADD r0, r6, r7
	MOV r8, r0
	MOV r9, r4
	ADD r0, r8, r9
	b cont1
label3:
	LDR r0, =7
	b cont1
cont1:
	CMP  , r5
	BEQ label2
	B label3
label2:
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	ADD r0, r6, r7
	MOV r8, r0
	MOV r9, r4
	ADD r0, r8, r9
	b cont2
label3:
	LDR r0, =7
	b cont2
cont2:
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
_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4


	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


