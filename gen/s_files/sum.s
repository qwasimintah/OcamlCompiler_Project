------ AST ------
(let rec sum x = (if (x <= 0) then 0 else ((sum (x - 1)) + x)) in (print_int (sum 10000)))

------ K-Normalization ------
(let rec sum x = (let ?v0 = x in (let ?v1 = 0 in (if (?v0 <= ?v1) then 0 else (let ?v2 = (let ?v4 = (let ?v5 = x in (let ?v6 = 1 in (?v5 - ?v6))) in (sum ?v4)) in (let ?v3 = x in (?v2 + ?v3)))))) in (let ?v7 = (let ?v8 = 10000 in (sum ?v8)) in (print_int ?v7)))

------ AlphaConversion ------
(let rec ?v9 ?v10 = (let ?v11 = ?v10 in (let ?v12 = 0 in (if (?v11 <= ?v12) then 0 else (let ?v13 = (let ?v14 = (let ?v15 = ?v10 in (let ?v16 = 1 in (?v15 - ?v16))) in (?v9 ?v14)) in (let ?v17 = ?v10 in (?v13 + ?v17)))))) in (let ?v18 = (let ?v19 = 10000 in (?v9 ?v19)) in (print_int ?v18)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v9 ?v10 = (let ?v11 = ?v10 in (let ?v12 = 0 in (if (?v11 <= ?v12) then 0 else (let ?v13 = (let ?v14 = (let ?v15 = ?v10 in (let ?v16 = 1 in (?v15 - ?v16))) in (?v9 ?v14)) in (let ?v17 = ?v10 in (?v13 + ?v17)))))) in (let ?v19 = 10000 in (let ?v18 = (?v9 ?v19) in (print_int ?v18))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v9
	free_list: null
	args: [?v10]
	code: 
(let ?v11 = ?v10 in (let ?v12 = 0 in (if (?v11 <= ?v12) then 0 else (let ?v13 = (let ?v14 = (let ?v15 = ?v10 in (let ?v16 = 1 in (?v15 - ?v16))) in (apply_direct _?v9 ?v14)) in (let ?v17 = ?v10 in (?v13 + ?v17))))))
(let ?v19 = 10000 in (let ?v18 = (apply_direct _?v9 ?v19) in (print_int ?v18)))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar6 (backend.variables.VInteger@6d6f6e28)
ASSIGN	?v19 (backend.variables.VInteger@14ae5a5) 10000
NOTHING	?v19 (backend.variables.VInteger@14ae5a5)
ASSIGN	?v18 (backend.variables.Variable@7f31245a) backend.instructions.InstructionCALL@135fbaa4
NOTHING	?v18 (backend.variables.Variable@7f31245a)
CALL	print_int backend.variables.Variable@7f31245a 

******** label1 *********
NOTHING	?v10 (backend.variables.Variable@45ee12a7)
ASSIGN	?v11 (backend.variables.Variable@1b6d3586) backend.variables.Variable@45ee12a7
NOTHING	tmpVar1 (backend.variables.VInteger@330bedb4)
ASSIGN	?v12 (backend.variables.VInteger@4554617c) 0
IF	backend.variables.VBoolean@2503dbd3 THEN label2 ELSE label3

******** label2 *********
NOTHING	tmpVar3 (backend.variables.VInteger@4b67cf4d)

******** label3 *********
NOTHING	?v10 (backend.variables.Variable@45ee12a7)
ASSIGN	?v15 (backend.variables.Variable@74a14482) backend.variables.Variable@45ee12a7
NOTHING	tmpVar4 (backend.variables.VInteger@7ea987ac)
ASSIGN	?v16 (backend.variables.VInteger@1540e19d) 1
SUB	backend.variables.Variable@74a14482 backend.variables.VInteger@1540e19d
NOTHING	null
CALL	label1 null 
NOTHING	?v10 (backend.variables.Variable@45ee12a7)
ASSIGN	?v17 (backend.variables.Variable@677327b6) backend.variables.Variable@45ee12a7
NOTHING	null
ADD	backend.variables.VInteger@12a3a380 backend.variables.Variable@677327b6



------ Register Allocation ------
***main : Variables state***
Var/Arg ?v18 (backend.variables.Variable@7f31245a)  : r5/r3
Var/Arg ?v19 (backend.variables.VInteger@14ae5a5)  : r4/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v15 (backend.variables.Variable@74a14482)  : r6/-
Var/Arg ?v17 (backend.variables.Variable@677327b6)  : r8/-
Var/Arg ?v12 (backend.variables.VInteger@4554617c)  : r5/-
Var/Arg ?v11 (backend.variables.Variable@1b6d3586)  : r4/-
Var/Arg ?v16 (backend.variables.VInteger@1540e19d)  : r7/-
***label1 : Arguments state***
Var/Arg ?v10 (backend.variables.Variable@45ee12a7)  : -/r2


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

	LDR r4, =10000
	MOV r0, r5
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

	MOV r4, r2
	LDR r5, =0
	CMP r4 , r5
	BLE label2
	B label3
label2:
	LDR r0, =0
	b cont1
label3:
	MOV r6, r2
	LDR r0, =1
	LDR r7, =1
	SUB r0, r6, r7
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r4-r12}
	MOV r8, r2
	ADD r0, r9, r8
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


