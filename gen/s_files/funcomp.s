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
	MOV r2, r5
	MOV r3, r6
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r7, r0
	STMFD sp!,{r2-r12}
	MOV r2, r4
	MOV r3, r7
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r8, r0
	LDR r9, =123
	STMFD sp!,{r2-r12}
	MOV r2, r9
	BL _label8
	LDMFD sp!, {r2-r12}
	MOV r10, r0
	MOV r0, r10
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

	MOV r4, r2
	STMFD sp!,{r2-r12}
	MOV r2, r4
	BL _label3
	LDMFD sp!, {r2-r12}
	MOV r6, r0
	STMFD sp!,{r2-r12}
	MOV r3, r6
	BL _label4
	LDMFD sp!, {r2-r12}

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4


	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label5:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r2
	MOV r6, r2
	ADD r0, r4, r6

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label6:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r2
	LDR r6, =1
	ADD r0, r4, r6

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label7:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r2
	LDR r6, =1
	SUB r0, r4, r6

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


