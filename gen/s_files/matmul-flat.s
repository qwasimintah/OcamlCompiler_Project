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

	SUB sp, #84
	LDR r4, =2
	LDR r5, =3
	STMFD sp!,{r2-r12}
	MOV r2, r4
	MOV r3, r5
	SUB sp, #4
	MOV r0, r6
	STR r0, [sp]
	MOV r3, r5
	BL _label14
	ADD sp, #4
	LDMFD sp!, {r2-r12}
	MOV r7, r0
	LDR r8, =3
	LDR r9, =2
	STMFD sp!,{r2-r12}
	MOV r2, r8
	MOV r3, r9
	SUB sp, #4
	MOV r0, r10
	STR r0, [sp]
	MOV r3, r9
	BL _label14
	ADD sp, #4
	LDMFD sp!, {r2-r12}
	MOV r5, r0
	LDR r6, =2
	LDR r4, =2
	STMFD sp!,{r2-r12}
	MOV r2, r6
	MOV r3, r4
	SUB sp, #4
	MOV r0, r9
	STR r0, [sp]
	MOV r3, r4
	BL _label14
	ADD sp, #4
	LDMFD sp!, {r2-r12}
	MOV r10, r0
	LDR r8, =2
	LDR r4, =3
	LDR r9, =2
	MOV r6, r7
	MOV r4, r5
	MOV r9, r10
	STMFD sp!,{r2-r12}
	MOV r2, r8
	MOV r3, r4
	SUB sp, #4
	MOV r0, r9
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r4
	STR r0, [sp]
	BL _label10
	ADD sp, #16
	LDMFD sp!, {r2-r12}
	MOV r7, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label15
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	MOV r0, r6
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r5, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label16
	LDMFD sp!, {r2-r12}
	MOV r4, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label17
	LDMFD sp!, {r2-r12}
	MOV r10, r0
	MOV r0, r10
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r9, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label18
	LDMFD sp!, {r2-r12}
	MOV r8, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label19
	LDMFD sp!, {r2-r12}
	MOV r7, r0
	MOV r0, r7
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r6, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label20
	LDMFD sp!, {r2-r12}
	MOV r5, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label21
	LDMFD sp!, {r2-r12}
	MOV r4, r0
	MOV r0, r4
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r10, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label22
	LDMFD sp!, {r2-r12}
	ADD sp, #84

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

	LDR r4, =0
	MOV r5, r3
	CMP r4 , r5
	BLE label2
	B label3
label2:
	MOV r7, r2
	MOV r9, r3
	LDR r10, =1
	SUB r0, r9, r10
	MOV r4, r0
	MOV r5, r6
	MOV r7, r8
	MOV r9, r10
	MOV r4, r5
	STMFD sp!,{r2-r12}
	MOV r2, r7
	MOV r3, r4
	SUB sp, #4
	MOV r0, r4
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r9
	STR r0, [sp]
	BL _label1
	ADD sp, #16
	LDMFD sp!, {r2-r12}
	b cont1
label3:
	b cont1
cont1:
	ADD sp, #4

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label4:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4
	SUB sp, #40

	LDR r4, =0
	MOV r5, r6
	CMP r4 , r5
	BLE label5
	B label6
label5:
	MOV r7, r2
	MOV r9, r3
	LDR r4, =1
	SUB r0, r9, r4
	MOV r5, r0
	MOV r6, r6
	MOV r7, r8
	MOV r9, r10
	MOV r4, r5
	STMFD sp!,{r2-r12}
	MOV r2, r7
	MOV r3, r5
	SUB sp, #4
	MOV r0, r4
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r9
	STR r0, [sp]
	BL _label1
	ADD sp, #16
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	MOV r7, r2
	MOV r8, r3
	MOV r9, r6
	LDR r10, =1
	SUB r0, r9, r10
	MOV r4, r0
	MOV r5, r8
	MOV r6, r10
	MOV r7, r5
	STMFD sp!,{r2-r12}
	MOV r2, r7
	MOV r3, r8
	SUB sp, #4
	MOV r0, r7
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r6
	STR r0, [sp]
	BL _label4
	ADD sp, #16
	LDMFD sp!, {r2-r12}
	b cont2
label6:
	b cont2
cont2:
	ADD sp, #40

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label7:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4
	SUB sp, #40

	LDR r4, =0
	MOV r5, r2
	CMP r4 , r5
	BLE label8
	B label9
label8:
	MOV r7, r2
	MOV r8, r3
	MOV r10, r4
	LDR r5, =1
	SUB r0, r10, r5
	MOV r6, r0
	MOV r7, r8
	MOV r9, r10
	MOV r4, r5
	STMFD sp!,{r2-r12}
	MOV r2, r7
	MOV r3, r8
	SUB sp, #4
	MOV r0, r4
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r9
	STR r0, [sp]
	BL _label4
	ADD sp, #16
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	MOV r7, r2
	LDR r8, =1
	SUB r0, r7, r8
	MOV r9, r0
	MOV r10, r3
	MOV r4, r4
	MOV r5, r8
	MOV r6, r10
	MOV r7, r5
	STMFD sp!,{r2-r12}
	MOV r2, r9
	MOV r3, r10
	SUB sp, #4
	MOV r0, r7
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r6
	STR r0, [sp]
	BL _label7
	ADD sp, #16
	LDMFD sp!, {r2-r12}
	b cont3
label9:
	b cont3
cont3:
	ADD sp, #40

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label10:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r2
	LDR r6, =1
	SUB r0, r4, r6
	MOV r7, r0
	MOV r8, r3
	MOV r10, r5
	MOV r4, r6
	MOV r8, r9
	MOV r10, r5
	STMFD sp!,{r2-r12}
	MOV r2, r7
	MOV r3, r8
	SUB sp, #4
	MOV r0, r10
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r8
	STR r0, [sp]
	BL _label7
	ADD sp, #16
	LDMFD sp!, {r2-r12}

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label11:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	LDR r4, =0
	MOV r5, r2
	CMP r4 , r5
	BLE label12
	B label13
label12:
	MOV r7, r2
	LDR r8, =1
	SUB r0, r7, r8
	MOV r9, r0
	MOV r10, r3
	MOV r5, r6
	STMFD sp!,{r2-r12}
	MOV r2, r9
	MOV r3, r10
	SUB sp, #4
	MOV r0, r5
	STR r0, [sp]
	MOV r3, r10
	BL _label11
	ADD sp, #4
	LDMFD sp!, {r2-r12}
	b cont4
label13:
	b cont4
cont4:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label14:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r2
	LDR r6, =1
	SUB r0, r4, r6
	MOV r7, r0
	MOV r8, r3
	STMFD sp!,{r2-r12}
	MOV r2, r7
	MOV r3, r8
	SUB sp, #4
	MOV r0, r10
	STR r0, [sp]
	MOV r3, r8
	BL _label11
	ADD sp, #4
	LDMFD sp!, {r2-r12}
	MOV r5, r0

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


