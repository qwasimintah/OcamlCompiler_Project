	.text
	.global _start
_start: 
	  mov r0, #6
	  bl fib
	  bl min_caml_print_int
	  bl min_caml_exit
fib:
	stmfd r13!, {r4, r5, r14} @ preserve registers
	mov r4, r0 @ r4=a
	cmp r0, #1 @ compare a with 1
	movle r0, r4 @ (if a<=1) result=a
	ldmlefd r13!, {r4, r5, pc} @ (if a<=1) return
	sub r0, r4, #1
	bl fib @ fib (a-1)
	mov r5, r0 @ r5=fib(a-1)
	sub r0, r4, #2
	bl fib @ fib (a-2)
	add r0, r5, r0 @ result=fib(a-1)+fib(a-2)
	ldmfd r13!, {r4, r5, pc} 
