	.text
        .global _start
_start:
        BL _main
_main:
        @MAIN PROLOGUE
        stmfd  sp!, {fp, lr}   
	add fp, sp, #4
       
        LDR r4, =2
        LDR r5, =3
        MOV r6, r4
        MOV r7, r5
	cmp r6, r7
	beq label1
	b label2
label1:
        LDR r0, =1
        LDR r9, =1
        ADD r0, r0, r9
        b cont2
label2:
        LDR r0, =1
        mov r1, r11
        LDR r11, =1
        ADD r0, r0, r11
        mov r11, r1

        b cont2
cont2:
        MOV r4, r0
        MOV r0, r4
        BL min_caml_print_int
        BL min_caml_print_newline

        @MAIN EPILOGUE
        sub sp, fp, #4         
	ldmfd  sp!, {fp, lr}  
	bx lr
