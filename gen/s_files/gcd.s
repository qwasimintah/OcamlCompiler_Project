------ AST ------
(let rec gcd m n = (if (m = 0) then n else (if (m <= n) then (gcd m (n - m)) else (gcd n (m - n)))) in (print_int (gcd 21600 337500)))

------ K-Normalization ------
(let rec gcd m n = (let ?v0 = m in (let ?v1 = 0 in (if (?v0 = ?v1) then n else (let ?v2 = m in (let ?v3 = n in (if (?v2 <= ?v3) then (let ?v4 = m in (let ?v5 = (let ?v6 = n in (let ?v7 = m in (?v6 - ?v7))) in (gcd ?v4 ?v5))) else (let ?v8 = n in (let ?v9 = (let ?v10 = m in (let ?v11 = n in (?v10 - ?v11))) in (gcd ?v8 ?v9))))))))) in (let ?v12 = (let ?v13 = 21600 in (let ?v14 = 337500 in (gcd ?v13 ?v14))) in (print_int ?v12)))

------ AlphaConversion ------
(let rec ?v15 ?v16 ?v17 = (let ?v18 = ?v16 in (let ?v19 = 0 in (if (?v18 = ?v19) then ?v17 else (let ?v20 = ?v16 in (let ?v21 = ?v17 in (if (?v20 <= ?v21) then (let ?v22 = ?v16 in (let ?v23 = (let ?v24 = ?v17 in (let ?v25 = ?v16 in (?v24 - ?v25))) in (?v15 ?v22 ?v23))) else (let ?v26 = ?v17 in (let ?v27 = (let ?v28 = ?v16 in (let ?v29 = ?v17 in (?v28 - ?v29))) in (?v15 ?v26 ?v27))))))))) in (let ?v30 = (let ?v31 = 21600 in (let ?v32 = 337500 in (?v15 ?v31 ?v32))) in (print_int ?v30)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v15 ?v16 ?v17 = (let ?v18 = ?v16 in (let ?v19 = 0 in (if (?v18 = ?v19) then ?v17 else (let ?v20 = ?v16 in (let ?v21 = ?v17 in (if (?v20 <= ?v21) then (let ?v22 = ?v16 in (let ?v24 = ?v17 in (let ?v25 = ?v16 in (let ?v23 = (?v24 - ?v25) in (?v15 ?v22 ?v23))))) else (let ?v26 = ?v17 in (let ?v28 = ?v16 in (let ?v29 = ?v17 in (let ?v27 = (?v28 - ?v29) in (?v15 ?v26 ?v27))))))))))) in (let ?v31 = 21600 in (let ?v32 = 337500 in (let ?v30 = (?v15 ?v31 ?v32) in (print_int ?v30)))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v15
	free_list: null
	args: [?v16, ?v17]
	code: 
(let ?v18 = ?v16 in (let ?v19 = 0 in (if (?v18 = ?v19) then ?v17 else (let ?v20 = ?v16 in (let ?v21 = ?v17 in (if (?v20 <= ?v21) then (let ?v22 = ?v16 in (let ?v24 = ?v17 in (let ?v25 = ?v16 in (let ?v23 = (?v24 - ?v25) in (apply_direct _?v15 ?v22 ?v23))))) else (let ?v26 = ?v17 in (let ?v28 = ?v16 in (let ?v29 = ?v17 in (let ?v27 = (?v28 - ?v29) in (apply_direct _?v15 ?v26 ?v27)))))))))))
(let ?v31 = 21600 in (let ?v32 = 337500 in (let ?v30 = (apply_direct _?v15 ?v31 ?v32) in (print_int ?v30))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar4 (backend.variables.VInteger@4dc63996)
ASSIGN	?v31 (backend.variables.VInteger@63947c6b) 21600
NOTHING	tmpVar5 (backend.variables.VInteger@d716361)
ASSIGN	?v32 (backend.variables.VInteger@2b193f2d) 337500
NOTHING	?v31 (backend.variables.VInteger@63947c6b)
NOTHING	?v32 (backend.variables.VInteger@2b193f2d)
ASSIGN	?v30 (backend.variables.Variable@355da254) backend.instructions.InstructionCALL@6ff3c5b5
>CALL	label1 backend.variables.VInteger@63947c6b backend.variables.VInteger@2b193f2d 
NOTHING	?v30 (backend.variables.Variable@355da254)
CALL	print_int backend.variables.Variable@355da254 

******** label1 *********
NOTHING	?v16 (backend.variables.Variable@3764951d)
ASSIGN	?v18 (backend.variables.Variable@5cad8086) backend.variables.Variable@3764951d
NOTHING	tmpVar1 (backend.variables.VInteger@4b1210ee)
ASSIGN	?v19 (backend.variables.VInteger@6e0be858) 0
IF	backend.variables.VBoolean@4d7e1886 THEN label2 ELSE label3

******** label2 *********
NOTHING	?v17 (backend.variables.Variable@3cd1a2f1)

******** label3 *********
NOTHING	?v16 (backend.variables.Variable@3764951d)
ASSIGN	?v20 (backend.variables.Variable@61bbe9ba) backend.variables.Variable@3764951d
NOTHING	?v17 (backend.variables.Variable@3cd1a2f1)
ASSIGN	?v21 (backend.variables.Variable@610455d6) backend.variables.Variable@3cd1a2f1
IF	backend.variables.VBoolean@2f0e140b THEN label4 ELSE label5

******** label4 *********
NOTHING	?v16 (backend.variables.Variable@3764951d)
ASSIGN	?v22 (backend.variables.Variable@511d50c0) backend.variables.Variable@3764951d
NOTHING	?v17 (backend.variables.Variable@3cd1a2f1)
ASSIGN	?v24 (backend.variables.Variable@60e53b93) backend.variables.Variable@3cd1a2f1
NOTHING	?v16 (backend.variables.Variable@3764951d)
ASSIGN	?v25 (backend.variables.Variable@5e2de80c) backend.variables.Variable@3764951d
SUB	backend.variables.Variable@60e53b93 backend.variables.Variable@5e2de80c
ASSIGN	?v23 (backend.variables.VInteger@1d44bcfa) backend.instructions.InstructionSUB@7440e464
>SUB	backend.variables.Variable@60e53b93 backend.variables.Variable@5e2de80c
NOTHING	?v22 (backend.variables.Variable@511d50c0)
NOTHING	?v23 (backend.variables.VInteger@1d44bcfa)
CALL	label1 backend.variables.Variable@511d50c0 backend.variables.VInteger@1d44bcfa 

******** label5 *********
NOTHING	?v17 (backend.variables.Variable@3cd1a2f1)
ASSIGN	?v26 (backend.variables.Variable@266474c2) backend.variables.Variable@3cd1a2f1
NOTHING	?v16 (backend.variables.Variable@3764951d)
ASSIGN	?v28 (backend.variables.Variable@6f94fa3e) backend.variables.Variable@3764951d
NOTHING	?v17 (backend.variables.Variable@3cd1a2f1)
ASSIGN	?v29 (backend.variables.Variable@5e481248) backend.variables.Variable@3cd1a2f1
SUB	backend.variables.Variable@6f94fa3e backend.variables.Variable@5e481248
ASSIGN	?v27 (backend.variables.VInteger@66d3c617) backend.instructions.InstructionSUB@49476842
>SUB	backend.variables.Variable@6f94fa3e backend.variables.Variable@5e481248
NOTHING	?v26 (backend.variables.Variable@266474c2)
NOTHING	?v27 (backend.variables.VInteger@66d3c617)
CALL	label1 backend.variables.Variable@266474c2 backend.variables.VInteger@66d3c617 




------ Register Allocation ------
***main : Variables state***
Var/Arg ?v32 (backend.variables.VInteger@2b193f2d)  : r5/r3
Var/Arg ?v30 (backend.variables.Variable@355da254)  : r6/r2
Var/Arg ?v31 (backend.variables.VInteger@63947c6b)  : r4/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v29 (backend.variables.Variable@5e481248)  : r7/-
Var/Arg ?v20 (backend.variables.Variable@61bbe9ba)  : r6/-
Var/Arg ?v25 (backend.variables.Variable@5e2de80c)  : r10/-
Var/Arg ?v21 (backend.variables.Variable@610455d6)  : r7/-
Var/Arg ?v19 (backend.variables.VInteger@6e0be858)  : r5/-
Var/Arg ?v27 (backend.variables.VInteger@66d3c617)  : r8/r3
Var/Arg ?v24 (backend.variables.Variable@60e53b93)  : r9/-
Var/Arg ?v26 (backend.variables.Variable@266474c2)  : r5/r2
Var/Arg ?v28 (backend.variables.Variable@6f94fa3e)  : r6/-
Var/Arg ?v18 (backend.variables.Variable@5cad8086)  : r4/-
Var/Arg ?v22 (backend.variables.Variable@511d50c0)  : r8/r2
Var/Arg ?v23 (backend.variables.VInteger@1d44bcfa)  : r4/r3
***label1 : Arguments state***
Var/Arg ?v16 (backend.variables.Variable@3764951d)  : -/r2
Var/Arg ?v17 (backend.variables.Variable@3cd1a2f1)  : -/r3


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

	LDR r4, =21600
	LDR r5, =337500
	STMFD sp!,{r2-r12}
	MOV r2, r4
	MOV r3, r5
	BL _label1
	LDMFD sp!, {r2-r12}
	MOV r6, r0
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
	SUB sp, #12

	MOV r4, r2
	LDR r5, =0
	CMP r4 , r5
	BEQ label2
	B label3
label2:
	MOV r0, r3
	b cont1
label3:
	MOV r6, r2
	MOV r7, r3
	CMP r6 , r7
	BLE label4
	B label5
label4:
	MOV r8, r2
	MOV r9, r3
	MOV r10, r2
	SUB r0, r9, r10
	MOV r4, r0
	STMFD sp!,{r2-r12}
	MOV r2, r8
	MOV r3, r4
	BL _label1
	LDMFD sp!, {r2-r12}
	b cont2
label5:
	MOV r5, r3
	MOV r6, r2
	MOV r7, r3
	SUB r0, r6, r7
	MOV r8, r0
	STMFD sp!,{r2-r12}
	MOV r2, r5
	MOV r3, r8
	BL _label1
	LDMFD sp!, {r2-r12}
	b cont2
cont2:
	b cont1
cont1:
	ADD sp, #12

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


