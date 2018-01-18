	.text
	.global _start
_start:
	BL _main
	bl min_caml_exit
_main:
	@MAIN PROLOGUE
	SUB sp, #4
	LDR lr, [sp]
	SUB sp, #4
	STR fp, [sp]
	MOV fp, sp

	LDR r4, =1
	STMFD sp!,{r4-r12}
	MOV r3, r4
	BL _label1
	LDMFD sp!, {r4-r12}
	MOV r5, r0
	MOV r0, r5
	BL min_caml_print_int
	bl min_caml_print_newline
	
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
	LDR r5, =0
	CMP r4 ,r5
	BLE label2
	B label3
label2:
	mov r0, r4
	b cont1
label3:
	MOV r6, r3
	LDR r7, =1
	SUB r0, r6, r7
	STMFD sp!,{r4-r12}
	MOV r3, r0
	BL _label1
	mov r9, r0
	LDMFD sp!, {r4-r12}
	
	ADD r0, r9, r3
	b cont1
cont1: 

	bl min_caml_print_int
	bl min_caml_print_newline 
	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr
