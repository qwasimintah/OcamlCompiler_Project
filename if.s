LET
LET
LET
LET
LET
LET
LET
ADD
LET
LET
ADD
APP
@------ ARM------
HERE
Variable ?v12 stored in register r9
Parameter ?v12 not saved !
Variable ?v11 stored in register r8
Parameter ?v11 not saved !
backend.variables.VBoolean@330bedb4
null
Something crazy happenned
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

	LDR r4, =2
	LDR r5, =3
	MOV r6, r4
	MOV r7, r5
label1:
	LDR r0, =1
	LDR r9, =1
	ADD r0, r8, r9
	b cont2
label2:
	LDR r0, =1
	LDR r11, =1
	ADD r0, r10, r11
	b cont2
cont2:
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

