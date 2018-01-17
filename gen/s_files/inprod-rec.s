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
	BL _label4
	LDMFD sp!, {r2-r12}
	MOV r4, r0
	MOV r0, r4
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

	LDR r4, =0
	LDR r1 , [fp ,#4]
	MOV r5, r1
	CMP r4 , r5
	BLE label2
	B label3
label2:
	b cont1
label3:
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


