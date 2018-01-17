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

	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r4, r0
	MOV r5, r4
	LDR r6, =0
	CMP r5 , r6
	BLE label3
	B label4
label3:
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label2
	LDMFD sp!, {r2-r12}
	MOV r7, r0
	MOV r8, r4
	ADD r0, r7, r8
	b cont1
label4:
	MOV r0, r4
	b cont1
cont1:
	CMP r5 , r6
	BLE label3
	B label4
label3:
	STMFD sp!,{r2-r12}
	MOV r2, r0
	BL _label2
	LDMFD sp!, {r2-r12}
	MOV r7, r0
	MOV r8, r4
	ADD r0, r7, r8
	b cont2
label4:
	MOV r0, r4
	b cont2
cont2:
	MOV r9, r0
	MOV r10, r4
	ADD r0, r9, r10
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


	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label2:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4


	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


