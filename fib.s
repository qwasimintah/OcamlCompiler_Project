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

	LDR r4, =3
	STMFD sp!,{r4-r12}
	MOV r2, r4
	BL _label1
	LDMFD sp!, {r4-r12}
	STMFD sp!,{r4-r12}
	MOV r2, r4
	BL _label1
	LDMFD sp!, {r4-r12}
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
	SUB sp, #4

	MOV r4, r2
	LDR r5, =1
	CMP r4 , r5
	BLE label2
	B label3
label2:
	b cont1
label3:
	MOV r6, r2
	LDR r7, =1
	SUB r0, r6, r7
	MOV r8, r0
	STMFD sp!,{r4-r12}
	MOV r2, r8
	BL _label1
	LDMFD sp!, {r4-r12}
	STMFD sp!,{r4-r12}
	MOV r2, r8
	BL _label1
	LDMFD sp!, {r4-r12}
	MOV r9, r0
	MOV r10, r2
	LDR r4, =2
	SUB r0, r10, r4
	MOV r5, r0
	STMFD sp!,{r4-r12}
	MOV r3, r5
	BL _label1
	LDMFD sp!, {r4-r12}
	STMFD sp!,{r4-r12}
	MOV r3, r5
	BL _label1
	LDMFD sp!, {r4-r12}
	MOV r6, r0
	ADD r0, r9, r6
	b cont1
cont1:
	ADD sp, #4

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


