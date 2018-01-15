@------ ARM------
	.text
	.global _start
_start:
	BL _main
_main:
	stmfd  sp!, {fp, lr}
	add fp, sp, #4

	LDR r5, =1
	LDR r11, =2
	ADD r0, r5, r11

	sub sp, fp, #4         
	ldmfd  sp!, {fp, lr}  
	
