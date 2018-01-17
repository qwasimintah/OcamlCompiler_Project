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

	LDR r6, =123
	LDR r5, =456
	LDR r4, =789
	STMFD sp!,{r2-r12}
	MOV r2, r4
	BL _label1
	LDMFD sp!, {r2-r12}
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
_label2:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4
	SUB sp, #4

	MOV r5, r2
	LDR r4, =0
	CMP r5 , r4
	BLE label3
	B label6
label3:
	LDR r6, =0
	MOV r5, r2
	CMP r6 , r5
	BLE label4
	B label5
label4:
	b cont2
label5:
	MOV r9, r2
	LDR r7, =1
	ADD r0, r9, r7
	MOV r10, r0
	STMFD sp!,{r2-r12}
	MOV r2, r10
	BL _label1
	LDMFD sp!, {r2-r12}
	b cont2
cont2:
	b cont1
label6:
	MOV r8, r2
	LDR r6, =1
	SUB r0, r8, r6
	MOV r4, r0
	STMFD sp!,{r2-r12}
	MOV r3, r4
	BL _label1
	LDMFD sp!, {r2-r12}
	b cont1
cont1:
	ADD sp, #4

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4
	SUB sp, #4

	MOV r4, r2
	LDR r9, =0
	CMP r4 , r9
	BLE label7
	B label10
label7:
	LDR r7, =0
	MOV r4, r2
	CMP r7 , r4
	BLE label8
	B label9
label8:
	b cont4
label9:
	MOV r5, r2
	LDR r8, =1
	ADD r0, r5, r8
	MOV r5, r0
	STMFD sp!,{r2-r12}
	MOV r2, r5
	BL _label2
	LDMFD sp!, {r2-r12}
	b cont4
cont4:
	b cont3
label10:
	MOV r6, r2
	LDR r6, =1
	SUB r0, r6, r6
	MOV r10, r0
	STMFD sp!,{r2-r12}
	MOV r3, r10
	BL _label2
	LDMFD sp!, {r2-r12}
	b cont3
cont3:
	ADD sp, #4

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


