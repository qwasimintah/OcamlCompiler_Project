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

	SUB sp, #72
	LDR r4, =2
	LDR r5, =3
	STMFD sp!,{r2-r12}
	MOV r2, r4
	MOV r3, r5
	BL _label11
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	LDR r7, =3
	LDR r8, =2
	STMFD sp!,{r2-r12}
	MOV r2, r7
	MOV r3, r8
	BL _label11
	LDMFD sp!, {r2-r12}
	MOV r9, r0
	LDR r10, =2
	LDR r5, =2
	STMFD sp!,{r2-r12}
	MOV r2, r10
	MOV r3, r5
	BL _label11
	LDMFD sp!, {r2-r12}
	MOV r4, r0
	LDR r8, =2
	LDR r7, =3
	LDR r5, =2
	MOV r10, r6
	MOV r7, r9
	MOV r5, r4
	STMFD sp!,{r2-r12}
	MOV r2, r8
	MOV r3, r7
	SUB sp, #4
	MOV r0, r5
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r7
	STR r0, [sp]
	BL _label1
	ADD sp, #16
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label15
	LDMFD sp!, {r2-r12}
	MOV r10, r0
	MOV r0, r10
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r9, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label16
	LDMFD sp!, {r2-r12}
	MOV r7, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label17
	LDMFD sp!, {r2-r12}
	MOV r4, r0
	MOV r0, r4
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r5, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label18
	LDMFD sp!, {r2-r12}
	MOV r8, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label19
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	MOV r0, r6
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r10, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label20
	LDMFD sp!, {r2-r12}
	MOV r9, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label21
	LDMFD sp!, {r2-r12}
	MOV r7, r0
	MOV r0, r7
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r4, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label22
	LDMFD sp!, {r2-r12}
	ADD sp, #72

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0
_label6:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	LDR r4, =0
	MOV r5, r3
	CMP r4 , r5
	BLE label7
	B label8
label7:
	MOV r7, r3
	LDR r8, =1
	SUB r0, r7, r8
	MOV r9, r0
	STMFD sp!,{r2-r12}
	MOV r2, r9
	BL _label6
	LDMFD sp!, {r2-r12}
	b cont1
label8:
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label4:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	LDR r4, =0
	MOV r5, r2
	CMP r4 , r5
	BLE label5
	B label9
label5:
	LDR r8, =1
	SUB r0, r7, r8
	MOV r9, r0
	STMFD sp!,{r2-r12}
	MOV r2, r9
	BL _label6
	LDMFD sp!, {r2-r12}
	MOV r10, r0
	MOV r4, r2
	LDR r5, =1
	SUB r0, r4, r5
	MOV r6, r0
	STMFD sp!,{r2-r12}
	MOV r3, r6
	BL _label4
	LDMFD sp!, {r2-r12}
	b cont2
label9:
	b cont2
cont2:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label2:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	LDR r4, =0
	MOV r5, r2
	CMP r4 , r5
	BLE label3
	B label10
label3:
	LDR r8, =1
	SUB r0, r7, r8
	MOV r9, r0
	STMFD sp!,{r2-r12}
	MOV r2, r9
	BL _label4
	LDMFD sp!, {r2-r12}
	MOV r10, r0
	MOV r4, r2
	LDR r5, =1
	SUB r0, r4, r5
	MOV r6, r0
	STMFD sp!,{r2-r12}
	MOV r3, r6
	BL _label2
	LDMFD sp!, {r2-r12}
	b cont3
label10:
	b cont3
cont3:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r3
	LDR r6, =1
	SUB r0, r4, r6
	MOV r7, r0
	STMFD sp!,{r2-r12}
	MOV r2, r7
	BL _label2
	LDMFD sp!, {r2-r12}

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label12:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	LDR r4, =0
	MOV r5, r3
	CMP r4 , r5
	BLE label13
	B label14
label13:
	MOV r7, r3
	LDR r8, =1
	SUB r0, r7, r8
	MOV r9, r0
	STMFD sp!,{r2-r12}
	MOV r2, r9
	BL _label12
	LDMFD sp!, {r2-r12}
	b cont4
label14:
	b cont4
cont4:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label11:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r3
	LDR r6, =1
	SUB r0, r4, r6
	MOV r7, r0
	STMFD sp!,{r2-r12}
	MOV r2, r7
	BL _label12
	LDMFD sp!, {r2-r12}
	MOV r8, r0

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


