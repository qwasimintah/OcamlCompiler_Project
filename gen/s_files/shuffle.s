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

	LDR r4, =1
	LDR r5, =2
	LDR r6, =3
	LDR r7, =4
	LDR r8, =5
	LDR r9, =6
	STMFD sp!,{r2-r12}
	MOV r2, r4
	MOV r3, r5
	SUB sp, #4
	MOV r0, r9
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r8
	STR r0, [sp]
	BL _label2
	ADD sp, #16
	LDMFD sp!, {r2-r12}

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
	SUB sp, #8

	MOV r4, r2
	MOV r0, r4
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r6, r0
	MOV r7, r3
	MOV r0, r7
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r9, r0
	LDR r1 , [fp ,#4]
	MOV r10, r1
	MOV r0, r10
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r4, r0
	LDR r1 , [fp ,#8]
	MOV r6, r1
	MOV r0, r6
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r7, r0
	LDR r1 , [fp ,#12]
	MOV r9, r1
	MOV r0, r9
	BL min_caml_print_int
	BL min_caml_print_newline
	MOV r10, r0
	LDR r1 , [fp ,#16]
	MOV r4, r1
	MOV r0, r4
	BL min_caml_print_int
	BL min_caml_print_newline
	ADD sp, #8

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label2:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r3
	MOV r6, r2
	MOV r8, r9
	MOV r10, r5
	MOV r6, r7
	MOV r8, r9
	STMFD sp!,{r2-r12}
	MOV r2, r4
	MOV r3, r6
	SUB sp, #4
	MOV r0, r8
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r6
	STR r0, [sp]
	BL _label1
	ADD sp, #16
	LDMFD sp!, {r2-r12}

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


