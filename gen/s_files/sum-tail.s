------ AST ------
(let rec sum acc x = (if (x <= 0) then acc else (sum (acc + x) (x - 1))) in (print_int (sum 0 10000)))

------ K-Normalization ------
(let rec sum acc x = (let ?v0 = x in (let ?v1 = 0 in (if (?v0 <= ?v1) then acc else (let ?v2 = (let ?v6 = acc in (let ?v7 = x in (?v6 + ?v7))) in (let ?v3 = (let ?v4 = x in (let ?v5 = 1 in (?v4 - ?v5))) in (sum ?v2 ?v3)))))) in (let ?v8 = (let ?v9 = 0 in (let ?v10 = 10000 in (sum ?v9 ?v10))) in (print_int ?v8)))

------ AlphaConversion ------
(let rec ?v11 ?v12 ?v13 = (let ?v14 = ?v13 in (let ?v15 = 0 in (if (?v14 <= ?v15) then ?v12 else (let ?v16 = (let ?v17 = ?v12 in (let ?v18 = ?v13 in (?v17 + ?v18))) in (let ?v19 = (let ?v20 = ?v13 in (let ?v21 = 1 in (?v20 - ?v21))) in (?v11 ?v16 ?v19)))))) in (let ?v22 = (let ?v23 = 0 in (let ?v24 = 10000 in (?v11 ?v23 ?v24))) in (print_int ?v22)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v11 ?v12 ?v13 = (let ?v14 = ?v13 in (let ?v15 = 0 in (if (?v14 <= ?v15) then ?v12 else (let ?v16 = (let ?v17 = ?v12 in (let ?v18 = ?v13 in (?v17 + ?v18))) in (let ?v19 = (let ?v20 = ?v13 in (let ?v21 = 1 in (?v20 - ?v21))) in (?v11 ?v16 ?v19)))))) in (let ?v23 = 0 in (let ?v24 = 10000 in (let ?v22 = (?v11 ?v23 ?v24) in (print_int ?v22)))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v11
	free_list: null
	args: [?v12, ?v13]
	code: 
(let ?v14 = ?v13 in (let ?v15 = 0 in (if (?v14 <= ?v15) then ?v12 else (let ?v16 = (let ?v17 = ?v12 in (let ?v18 = ?v13 in (?v17 + ?v18))) in (let ?v19 = (let ?v20 = ?v13 in (let ?v21 = 1 in (?v20 - ?v21))) in (apply_direct _?v11 ?v16 ?v19))))))
(let ?v23 = 0 in (let ?v24 = 10000 in (let ?v22 = (apply_direct _?v11 ?v23 ?v24) in (print_int ?v22))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar4 (backend.variables.VInteger@45ee12a7)
ASSIGN	?v23 (backend.variables.VInteger@7f31245a) 0
NOTHING	tmpVar5 (backend.variables.VInteger@330bedb4)
ASSIGN	?v24 (backend.variables.VInteger@6d6f6e28) 10000
NOTHING	?v23 (backend.variables.VInteger@7f31245a)
NOTHING	?v24 (backend.variables.VInteger@6d6f6e28)
ASSIGN	?v22 (backend.variables.Variable@135fbaa4) backend.instructions.InstructionCALL@2503dbd3
NOTHING	?v22 (backend.variables.Variable@135fbaa4)
CALL	print_int backend.variables.Variable@135fbaa4 

******** label1 *********
NOTHING	?v13 (backend.variables.Variable@4b67cf4d)
ASSIGN	?v14 (backend.variables.Variable@1b6d3586) backend.variables.Variable@4b67cf4d
NOTHING	tmpVar1 (backend.variables.VInteger@7ea987ac)
ASSIGN	?v15 (backend.variables.VInteger@4554617c) 0
IF	backend.variables.VBoolean@12a3a380 THEN label2 ELSE label3

******** label2 *********
NOTHING	?v12 (backend.variables.Variable@29453f44)

******** label3 *********
NOTHING	?v12 (backend.variables.Variable@29453f44)
ASSIGN	?v17 (backend.variables.Variable@74a14482) backend.variables.Variable@29453f44
NOTHING	?v13 (backend.variables.Variable@4b67cf4d)
ASSIGN	?v18 (backend.variables.Variable@1540e19d) backend.variables.Variable@4b67cf4d
ADD	backend.variables.Variable@74a14482 backend.variables.Variable@1540e19d
NOTHING	?v13 (backend.variables.Variable@4b67cf4d)
ASSIGN	?v20 (backend.variables.Variable@677327b6) backend.variables.Variable@4b67cf4d
NOTHING	tmpVar3 (backend.variables.VInteger@5cad8086)
ASSIGN	?v21 (backend.variables.VInteger@14ae5a5) 1
SUB	backend.variables.Variable@677327b6 backend.variables.VInteger@14ae5a5
NOTHING	null
NOTHING	null
CALL	label1 null null 



------ Register Allocation ------
***main : Variables state***
Var/Arg ?v24 (backend.variables.VInteger@6d6f6e28)  : r5/r3
Var/Arg ?v23 (backend.variables.VInteger@7f31245a)  : r4/r2
Var/Arg ?v22 (backend.variables.Variable@135fbaa4)  : r6/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v17 (backend.variables.Variable@74a14482)  : r6/-
Var/Arg ?v20 (backend.variables.Variable@677327b6)  : r8/-
Var/Arg ?v15 (backend.variables.VInteger@4554617c)  : r5/-
Var/Arg ?v14 (backend.variables.Variable@1b6d3586)  : r4/-
Var/Arg ?v18 (backend.variables.Variable@1540e19d)  : r7/-
Var/Arg ?v21 (backend.variables.VInteger@14ae5a5)  : r9/-
***label1 : Arguments state***
Var/Arg ?v12 (backend.variables.Variable@29453f44)  : -/r2
Var/Arg ?v13 (backend.variables.Variable@4b67cf4d)  : -/r3


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

	LDR r4, =0
	LDR r5, =10000
	MOV r0, r6
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

	MOV r4, r3
	LDR r5, =0
	CMP r4 , r5
	BLE label2
	B label3
label2:
	b cont1
label3:
	MOV r6, r2
	MOV r7, r3
	ADD r0, r6, r7
	MOV r8, r3
	LDR r0, =1
	LDR r9, =1
	SUB r0, r8, r9
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label1
	LDMFD sp!, {r4-r12}
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


