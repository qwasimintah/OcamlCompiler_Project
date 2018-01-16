.data
	.global heap_ptr
	heap: .skip 4096
	heap_ptr: .word heap

	.text
	.global _start
_start:
	MOV r0, #0
	bl create_array
create_array: 
	CMP r0, #5
	BEQ end_create
	LDR r1, =heap_ptr
	LSL r2, r0, #2
	ADD r2, r1, r2
	MOV r3, #0
	STR r3, [r2]
	ADD r0, r0, #1
	B create_array
end_create: 
	MOV r0, #0

put_array_element: 
	MOV r0, #2
	LDR r1, =heap_ptr
	LSL r2, r0, #2
	ADD r2, r1, r2
	MOV r3, #7
	STR r3, [r2]
get_array_element: 
	MOV r0, #2
	LDR r1, =heap_ptr
	LSL r2, r0, #2
	ADD r2, r1, r2
	LDR r1, [r2]
	MOV r0, r1
	BL min_caml_print_int
	BL min_caml_exit