------ AST ------
(let rec compose f g = (let rec composed x = (g (f x)) in composed) in (let rec dbl x = (x + x) in (let rec inc x = (x + 1) in (let rec dec x = (x - 1) in (let h = (compose inc (compose dbl dec)) in (print_int (h 123)))))))

------ K-Normalization ------
(let rec compose f g = (let rec composed x = (let ?v0 = (let ?v1 = x in (f ?v1)) in (g ?v0)) in composed) in (let rec dbl x = (let ?v2 = x in (let ?v3 = x in (?v2 + ?v3))) in (let rec inc x = (let ?v4 = x in (let ?v5 = 1 in (?v4 + ?v5))) in (let rec dec x = (let ?v6 = x in (let ?v7 = 1 in (?v6 - ?v7))) in (let h = (let ?v8 = inc in (let ?v9 = (let ?v10 = dbl in (let ?v11 = dec in (compose ?v10 ?v11))) in (compose ?v8 ?v9))) in (let ?v12 = (let ?v13 = 123 in (h ?v13)) in (print_int ?v12)))))))

------ AlphaConversion ------
(let rec ?v14 ?v15 ?v16 = (let rec ?v17 ?v18 = (let ?v19 = (let ?v20 = ?v18 in (?v15 ?v20)) in (?v16 ?v19)) in ?v17) in (let rec ?v21 ?v22 = (let ?v23 = ?v22 in (let ?v24 = ?v22 in (?v23 + ?v24))) in (let rec ?v25 ?v26 = (let ?v27 = ?v26 in (let ?v28 = 1 in (?v27 + ?v28))) in (let rec ?v29 ?v30 = (let ?v31 = ?v30 in (let ?v32 = 1 in (?v31 - ?v32))) in (let ?v33 = (let ?v34 = ?v25 in (let ?v35 = (let ?v36 = ?v21 in (let ?v37 = ?v29 in (?v14 ?v36 ?v37))) in (?v14 ?v34 ?v35))) in (let ?v38 = (let ?v39 = 123 in (?v33 ?v39)) in (print_int ?v38)))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v14 ?v15 ?v16 = (let rec ?v17 ?v18 = (let ?v20 = ?v18 in (let ?v19 = (?v15 ?v20) in (?v16 ?v19))) in ?v17) in (let rec ?v21 ?v22 = (let ?v23 = ?v22 in (let ?v24 = ?v22 in (?v23 + ?v24))) in (let rec ?v25 ?v26 = (let ?v27 = ?v26 in (let ?v28 = 1 in (?v27 + ?v28))) in (let rec ?v29 ?v30 = (let ?v31 = ?v30 in (let ?v32 = 1 in (?v31 - ?v32))) in (let ?v34 = ?v25 in (let ?v36 = ?v21 in (let ?v37 = ?v29 in (let ?v35 = (?v14 ?v36 ?v37) in (let ?v33 = (?v14 ?v34 ?v35) in (let ?v39 = 123 in (let ?v38 = (?v33 ?v39) in (print_int ?v38))))))))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v17
	free_list: [?v16, ?v15]
	args: [?v18]
	code: 
(let ?v20 = ?v18 in (let ?v19 = (?v15 ?v20) in (?v16 ?v19)))
Closure list: 
closure numbers: 2
	label: _?v17
	free_list: [?v16, ?v15]
	args: [?v18]
	code: 
(let ?v20 = ?v18 in (let ?v19 = (?v15 ?v20) in (?v16 ?v19)))
	label: _?v29
	free_list: null
	args: [?v30]
	code: 
(let ?v31 = ?v30 in (let ?v32 = 1 in (?v31 - ?v32)))
Closure list: 
closure numbers: 3
	label: _?v17
	free_list: [?v16, ?v15]
	args: [?v18]
	code: 
(let ?v20 = ?v18 in (let ?v19 = (?v15 ?v20) in (?v16 ?v19)))
	label: _?v29
	free_list: null
	args: [?v30]
	code: 
(let ?v31 = ?v30 in (let ?v32 = 1 in (?v31 - ?v32)))
	label: _?v25
	free_list: null
	args: [?v26]
	code: 
(let ?v27 = ?v26 in (let ?v28 = 1 in (?v27 + ?v28)))
Closure list: 
closure numbers: 4
	label: _?v17
	free_list: [?v16, ?v15]
	args: [?v18]
	code: 
(let ?v20 = ?v18 in (let ?v19 = (?v15 ?v20) in (?v16 ?v19)))
	label: _?v29
	free_list: null
	args: [?v30]
	code: 
(let ?v31 = ?v30 in (let ?v32 = 1 in (?v31 - ?v32)))
	label: _?v25
	free_list: null
	args: [?v26]
	code: 
(let ?v27 = ?v26 in (let ?v28 = 1 in (?v27 + ?v28)))
	label: _?v21
	free_list: null
	args: [?v22]
	code: 
(let ?v23 = ?v22 in (let ?v24 = ?v22 in (?v23 + ?v24)))
Closure list: 
closure numbers: 5
	label: _?v17
	free_list: [?v16, ?v15]
	args: [?v18]
	code: 
(let ?v20 = ?v18 in (let ?v19 = (?v15 ?v20) in (?v16 ?v19)))
	label: _?v29
	free_list: null
	args: [?v30]
	code: 
(let ?v31 = ?v30 in (let ?v32 = 1 in (?v31 - ?v32)))
	label: _?v25
	free_list: null
	args: [?v26]
	code: 
(let ?v27 = ?v26 in (let ?v28 = 1 in (?v27 + ?v28)))
	label: _?v21
	free_list: null
	args: [?v22]
	code: 
(let ?v23 = ?v22 in (let ?v24 = ?v22 in (?v23 + ?v24)))
	label: _?v14
	free_list: null
	args: [?v15, ?v16]
	code: 
(let ?v40 = (make_closure _?v17 ?v15 ?v16) in ?v40)
(let ?v34 = ?v25 in (let ?v36 = ?v21 in (let ?v37 = ?v29 in (let ?v35 = (apply_direct _?v14 ?v36 ?v37) in (let ?v33 = (apply_direct _?v14 ?v34 ?v35) in (let ?v39 = 123 in (let ?v38 = (apply_closure ?v33 ?v39) in (print_int ?v38))))))))

------ Translation to Jerry ------
******** main *********
NOTHING	null
ASSIGN	?v34 (backend.variables.Variable@135fbaa4) null
NOTHING	null
ASSIGN	?v36 (backend.variables.Variable@45ee12a7) null
NOTHING	null
ASSIGN	?v37 (backend.variables.Variable@330bedb4) null
NOTHING	?v36 (backend.variables.Variable@45ee12a7)
NOTHING	?v37 (backend.variables.Variable@330bedb4)
ASSIGN	?v35 (backend.variables.Variable@2503dbd3) backend.instructions.InstructionCALL@29453f44
NOTHING	?v34 (backend.variables.Variable@135fbaa4)
NOTHING	?v35 (backend.variables.Variable@2503dbd3)
ASSIGN	?v33 (backend.variables.Variable@4b67cf4d) backend.instructions.InstructionCALL@5cad8086
NOTHING	tmpVar3 (backend.variables.VInteger@6e0be858)
ASSIGN	?v39 (backend.variables.VInteger@7ea987ac) 123
NOTHING	?v39 (backend.variables.VInteger@7ea987ac)
ASSIGN	?v38 (backend.variables.Variable@12a3a380) backend.instructions.InstructionCALL@61bbe9ba
NOTHING	?v38 (backend.variables.Variable@12a3a380)
CALL	print_int backend.variables.Variable@12a3a380 

******** label2 *********
NOTHING	?v18 (backend.variables.Variable@610455d6)
ASSIGN	?v20 (backend.variables.Variable@1b6d3586) backend.variables.Variable@610455d6
NOTHING	?v20 (backend.variables.Variable@1b6d3586)
ASSIGN	?v19 (backend.variables.Variable@4554617c) backend.instructions.InstructionCALL@511d50c0
NOTHING	?v19 (backend.variables.Variable@4554617c)
CALL	label4 backend.variables.Variable@4554617c 

******** label1 *********
NOTHING	null

******** label5 *********
NOTHING	?v22 (backend.variables.Variable@60e53b93)
ASSIGN	?v23 (backend.variables.Variable@74a14482) backend.variables.Variable@60e53b93
NOTHING	?v22 (backend.variables.Variable@60e53b93)
ASSIGN	?v24 (backend.variables.Variable@1540e19d) backend.variables.Variable@60e53b93
ADD	backend.variables.Variable@74a14482 backend.variables.Variable@1540e19d

******** label6 *********
NOTHING	?v26 (backend.variables.Variable@5e2de80c)
ASSIGN	?v27 (backend.variables.Variable@677327b6) backend.variables.Variable@5e2de80c
NOTHING	tmpVar1 (backend.variables.VInteger@1d44bcfa)
ASSIGN	?v28 (backend.variables.VInteger@14ae5a5) 1
ADD	backend.variables.Variable@677327b6 backend.variables.VInteger@14ae5a5

******** label7 *********
NOTHING	?v30 (backend.variables.Variable@266474c2)
ASSIGN	?v31 (backend.variables.Variable@7f31245a) backend.variables.Variable@266474c2
NOTHING	tmpVar2 (backend.variables.VInteger@6f94fa3e)
ASSIGN	?v32 (backend.variables.VInteger@6d6f6e28) 1
SUB	backend.variables.Variable@7f31245a backend.variables.VInteger@6d6f6e28


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v35 (backend.variables.Variable@2503dbd3)  : r7/r3
Var/Arg ?v38 (backend.variables.Variable@12a3a380)  : r10/r3
Var/Arg ?v39 (backend.variables.VInteger@7ea987ac)  : r9/r2
Var/Arg ?v36 (backend.variables.Variable@45ee12a7)  : r5/r2
Var/Arg ?v33 (backend.variables.Variable@4b67cf4d)  : r8/-
Var/Arg ?v34 (backend.variables.Variable@135fbaa4)  : r4/r2
Var/Arg ?v37 (backend.variables.Variable@330bedb4)  : r6/r3
***main : Arguments state***

***label2 : Variables state***
Var/Arg ?v19 (backend.variables.Variable@4554617c)  : r5/r3
Var/Arg ?v20 (backend.variables.Variable@1b6d3586)  : r4/r2
***label2 : Arguments state***
Var/Arg ?v18 (backend.variables.Variable@610455d6)  : -/r2

***label1 : Variables state***
***label1 : Arguments state***
Var/Arg ?v15 (backend.variables.Variable@5e481248)  : -/r2
Var/Arg ?v16 (backend.variables.Variable@66d3c617)  : -/r3

***label5 : Variables state***
Var/Arg ?v23 (backend.variables.Variable@74a14482)  : r4/-
Var/Arg ?v24 (backend.variables.Variable@1540e19d)  : r5/-
***label5 : Arguments state***
Var/Arg ?v22 (backend.variables.Variable@60e53b93)  : -/r2

***label6 : Variables state***
Var/Arg ?v27 (backend.variables.Variable@677327b6)  : r4/-
Var/Arg ?v28 (backend.variables.VInteger@14ae5a5)  : r5/-
***label6 : Arguments state***
Var/Arg ?v26 (backend.variables.Variable@5e2de80c)  : -/r2

***label7 : Variables state***
Var/Arg ?v32 (backend.variables.VInteger@6d6f6e28)  : r5/-
Var/Arg ?v31 (backend.variables.Variable@7f31245a)  : r4/-
***label7 : Arguments state***
Var/Arg ?v30 (backend.variables.Variable@266474c2)  : -/r2


------ ARM code generation ------
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

	LDR r9, =123
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
	STMFD sp!,{r4-r12}
	MOV r3, r5
	BL _label4
	LDMFD sp!, {r4-r12}

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
	MOV r5, r2
	ADD r0, r4, r5

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label6:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r2
	LDR r5, =1
	ADD r0, r4, r5

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label7:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r2
	LDR r5, =1
	SUB r0, r4, r5

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


