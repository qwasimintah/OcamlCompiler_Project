	.text
	.global _start
_start:
	bl _main

_main: 
     mov r1, #2
     mov r2, #3
     cmp r1, r2
     beq true
     b false
     mov r0, #11
     bl min_caml_print_int
     bl min_caml_print_newline

true:
    mov r0, #10
    bl min_caml_print_int
    bl min_caml_print_newline
	
false: 
    mov r0, #22
    bl min_caml_print_int
    bl min_caml_print_newline


 
