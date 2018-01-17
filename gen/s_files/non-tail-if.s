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

	SUB sp, #20
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r4, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label2
	LDMFD sp!, {r2-r12}
	MOV r5, r0
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label3
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	LDR r7, =0
	MOV r8, r6
	CMP r7 , r8
	BLE label4
	B label5
label4:
	MOV r0, r4
	b cont1
label5:
	MOV r0, r5
	b cont1
cont1:
	CMP r7 , r8
	BLE label4
	B label5
label4:
	MOV r0, r4
	b cont2
label5:
	MOV r0, r5
	b cont2
cont2:
	MOV r9, r0
	MOV r10, r4
	LDR r7, =0
	CMP r10 , r7
	BLE label6
	B label7
label6:
	MOV r0, r5
	b cont3
label7:
	MOV r0, r6
	b cont3
cont3:
	CMP r10 , r7
	BLE label6
	B label7
label6:
	MOV r0, r5
	b cont4
label7:
	MOV r0, r6
	b cont4
cont4:
	MOV r6, r0
	ADD r0, r9, r6
	MOV r8, r0
	LDR r4, =0
	MOV r10, r5
	CMP r4 , r10
	BLE label8
	B label9
label8:
	MOV r0, r6
	b cont5
label9:
	MOV r0, r4
	b cont5
cont5:
	CMP r4 , r10
	BLE label8
	B label9
label8:
	MOV r0, r6
	b cont6
label9:
	MOV r0, r4
	b cont6
cont6:
	MOV r7, r0
	ADD r0, r8, r7
	MOV r9, r0
	MOV r0, r9
	BL min_caml_print_int
	BL min_caml_print_newline
	ADD sp, #20

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0

