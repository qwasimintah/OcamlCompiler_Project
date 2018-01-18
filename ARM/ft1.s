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

	LDR r4, =6
	LDR r5, =3
	STMFD sp!,{r2-r12}
	MOV r2, r4
	MOV r3, r5
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
	SUB sp, #12

	MOV r4, r2
	LDR r5, =0
	CMP r4 , r5
	BEQ label2
	B label3
label2:
	MOV r0, r3
	b cont1
label3:
	MOV r6, r2
	MOV r7, r3
	CMP r6 , r7
	BLE label4
	B label5
label4:
	MOV r8, r2
	MOV r9, r3
	MOV r10, r2
	SUB r0, r9, r10
	MOV r4, r0
	STMFD sp!,{r2-r12}
	MOV r2, r8
	MOV r3, r4
	BL _label1
	LDMFD sp!, {r2-r12}
	b cont2
label5:
	MOV r5, r3
	MOV r6, r2
	MOV r7, r3
	SUB r0, r6, r7
	MOV r8, r0
	STMFD sp!,{r2-r12}
	MOV r2, r5
	MOV r3, r8
	BL _label1
	LDMFD sp!, {r2-r12}
	b cont2
cont2:
	b cont1
cont1:
	ADD sp, #12

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr
