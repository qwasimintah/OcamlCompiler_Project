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

	LDR r4, =123
	LDR r5, =456
	LDR r6, =789
	STMFD sp!,{r2-r12}
	MOV r2, r6
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

	MOV r4, r2
	LDR r5, =0
	CMP r4 , r5
	BLE label3
	B label6
label3:
	LDR r6, =0
	MOV r7, r2
	CMP r6 , r7
	BLE label4
	B label5
label4:
	b cont2
label5:
	MOV r8, r2
	LDR r9, =1
	ADD r0, r8, r9
	MOV r10, r0
	STMFD sp!,{r2-r12}
	MOV r2, r10
	BL _label1
	LDMFD sp!, {r2-r12}
	b cont2
cont2:
	b cont1
label6:
	MOV r4, r2
	LDR r5, =1
	SUB r0, r4, r5
	MOV r6, r0
	STMFD sp!,{r2-r12}
	MOV r3, r6
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
	LDR r5, =0
	CMP r4 , r5
	BLE label7
	B label10
label7:
	LDR r6, =0
	MOV r7, r2
	CMP r6 , r7
	BLE label8
	B label9
label8:
	b cont4
label9:
	MOV r8, r2
	LDR r9, =1
	ADD r0, r8, r9
	MOV r10, r0
	STMFD sp!,{r2-r12}
	MOV r2, r10
	BL _label2
	LDMFD sp!, {r2-r12}
	b cont4
cont4:
	b cont3
label10:
	MOV r4, r2
	LDR r5, =1
	SUB r0, r4, r5
	MOV r6, r0
	STMFD sp!,{r2-r12}
	MOV r3, r6
	BL _label2
	LDMFD sp!, {r2-r12}
	b cont3
cont3:
	ADD sp, #4

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

