------ AST ------
(let x = (truncate 1,23) in (let y = (truncate 4,56) in (let z = (truncate (-. 7,89)) in (print_int (((if (not (0 <= z)) then y else x) + (if (not (x <= 0)) then z else y)) + (if (not (0 <= y)) then x else z))))))

------ K-Normalization ------
(let x = (let ?v0 = 1,23 in (truncate ?v0)) in (let y = (let ?v1 = 4,56 in (truncate ?v1)) in (let z = (let ?v2 = (-. 7,89) in (truncate ?v2)) in (let ?v3 = (let ?v4 = (let ?v8 = (let ?v12 = 0 in (let ?v13 = z in (if (?v12 <= ?v13) then x else y))) in (let ?v9 = (let ?v10 = x in (let ?v11 = 0 in (if (?v10 <= ?v11) then y else z))) in (?v8 + ?v9))) in (let ?v5 = (let ?v6 = 0 in (let ?v7 = y in (if (?v6 <= ?v7) then z else x))) in (?v4 + ?v5))) in (print_int ?v3)))))

------ AlphaConversion ------
(let ?v14 = (let ?v15 = 1,23 in (truncate ?v15)) in (let ?v16 = (let ?v17 = 4,56 in (truncate ?v17)) in (let ?v18 = (let ?v19 = (-. 7,89) in (truncate ?v19)) in (let ?v20 = (let ?v21 = (let ?v22 = (let ?v23 = 0 in (let ?v24 = ?v18 in (if (?v23 <= ?v24) then ?v14 else ?v16))) in (let ?v25 = (let ?v26 = ?v14 in (let ?v27 = 0 in (if (?v26 <= ?v27) then ?v16 else ?v18))) in (?v22 + ?v25))) in (let ?v28 = (let ?v29 = 0 in (let ?v30 = ?v16 in (if (?v29 <= ?v30) then ?v18 else ?v14))) in (?v21 + ?v28))) in (print_int ?v20)))))

------ Reduction of Nested Let-Expressions ------
(let ?v15 = 1,23 in (let ?v14 = (truncate ?v15) in (let ?v17 = 4,56 in (let ?v16 = (truncate ?v17) in (let ?v19 = (-. 7,89) in (let ?v18 = (truncate ?v19) in (let ?v23 = 0 in (let ?v24 = ?v18 in (let ?v22 = (if (?v23 <= ?v24) then ?v14 else ?v16) in (let ?v26 = ?v14 in (let ?v27 = 0 in (let ?v25 = (if (?v26 <= ?v27) then ?v16 else ?v18) in (let ?v21 = (?v22 + ?v25) in (let ?v29 = 0 in (let ?v30 = ?v16 in (let ?v28 = (if (?v29 <= ?v30) then ?v18 else ?v14) in (let ?v20 = (?v21 + ?v28) in (print_int ?v20))))))))))))))))))

------ ClosureConversion ------
(let ?v15 = 1,23 in (let ?v14 = (truncate ?v15) in (let ?v17 = 4,56 in (let ?v16 = (truncate ?v17) in (let ?v19 = (-. 7,89) in (let ?v18 = (truncate ?v19) in (let ?v23 = 0 in (let ?v24 = ?v18 in (let ?v22 = (if (?v23 <= ?v24) then ?v14 else ?v16) in (let ?v26 = ?v14 in (let ?v27 = 0 in (let ?v25 = (if (?v26 <= ?v27) then ?v16 else ?v18) in (let ?v21 = (?v22 + ?v25) in (let ?v29 = 0 in (let ?v30 = ?v16 in (let ?v28 = (if (?v29 <= ?v30) then ?v18 else ?v14) in (let ?v20 = (?v21 + ?v28) in (print_int ?v20))))))))))))))))))

------ Translation to Jerry ------
******** main *********
NOTHING	null
ASSIGN	?v14 (backend.variables.Variable@4d7e1886) backend.instructions.InstructionCALL@4617c264
NOTHING	null
ASSIGN	?v16 (backend.variables.Variable@3cd1a2f1) backend.instructions.InstructionCALL@36baf30c
NOTHING	null
ASSIGN	?v18 (backend.variables.Variable@2f0e140b) backend.instructions.InstructionCALL@7a81197d
NOTHING	tmpVar1 (backend.variables.VInteger@5ca881b5)
ASSIGN	?v23 (backend.variables.VInteger@7440e464) 0
NOTHING	?v18 (backend.variables.Variable@2f0e140b)
ASSIGN	?v24 (backend.variables.Variable@49476842) backend.variables.Variable@2f0e140b
IF	backend.variables.VBoolean@24d46ca6 THEN label4 ELSE label5

******** label4 *********
NOTHING	?v14 (backend.variables.Variable@4d7e1886)

******** label5 *********
NOTHING	?v16 (backend.variables.Variable@3cd1a2f1)

ASSIGN	?v22 (backend.variables.VInteger@78308db1) backend.instructions.InstructionIF@4517d9a3
NOTHING	?v14 (backend.variables.Variable@4d7e1886)
ASSIGN	?v26 (backend.variables.Variable@27c170f0) backend.variables.Variable@4d7e1886
NOTHING	tmpVar3 (backend.variables.VInteger@372f7a8d)
ASSIGN	?v27 (backend.variables.VInteger@5451c3a8) 0
IF	backend.variables.VBoolean@2f92e0f4 THEN label6 ELSE label7

******** label6 *********
NOTHING	?v16 (backend.variables.Variable@3cd1a2f1)

******** label7 *********
NOTHING	?v18 (backend.variables.Variable@2f0e140b)

ASSIGN	?v25 (backend.variables.VInteger@2626b418) backend.instructions.InstructionIF@28a418fc
ADD	backend.variables.VInteger@78308db1 backend.variables.VInteger@2626b418
ASSIGN	?v21 (backend.variables.VInteger@5a07e868) backend.instructions.InstructionADD@5305068a
NOTHING	tmpVar5 (backend.variables.VInteger@1f32e575)
ASSIGN	?v29 (backend.variables.VInteger@76ed5528) 0
NOTHING	?v16 (backend.variables.Variable@3cd1a2f1)
ASSIGN	?v30 (backend.variables.Variable@2c7b84de) backend.variables.Variable@3cd1a2f1
IF	backend.variables.VBoolean@279f2327 THEN label8 ELSE label9

******** label8 *********
NOTHING	?v18 (backend.variables.Variable@2f0e140b)

******** label9 *********
NOTHING	?v14 (backend.variables.Variable@4d7e1886)

ASSIGN	?v28 (backend.variables.VInteger@3fee733d) backend.instructions.InstructionIF@2ff4acd0
ADD	backend.variables.VInteger@5a07e868 backend.variables.VInteger@3fee733d
ASSIGN	?v20 (backend.variables.VInteger@5acf9800) backend.instructions.InstructionADD@54bedef2
NOTHING	?v20 (backend.variables.VInteger@5acf9800)
CALL	print_int backend.variables.VInteger@5acf9800 


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v16 (backend.variables.Variable@3cd1a2f1)  : r5/-
Var/Arg ?v22 (backend.variables.VInteger@78308db1)  : r9/-
Var/Arg ?v23 (backend.variables.VInteger@7440e464)  : r7/-
Var/Arg ?v18 (backend.variables.Variable@2f0e140b)  : r6/-
Var/Arg ?v24 (backend.variables.Variable@49476842)  : r8/-
Var/Arg ?v29 (backend.variables.VInteger@76ed5528)  : r6/-
Var/Arg ?v30 (backend.variables.Variable@2c7b84de)  : r7/-
Var/Arg ?v21 (backend.variables.VInteger@5a07e868)  : r5/-
Var/Arg ?v20 (backend.variables.VInteger@5acf9800)  : r10/r2
Var/Arg ?v26 (backend.variables.Variable@27c170f0)  : r10/-
Var/Arg ?v28 (backend.variables.VInteger@3fee733d)  : r8/-
Var/Arg ?v14 (backend.variables.Variable@4d7e1886)  : r4/-
Var/Arg ?v27 (backend.variables.VInteger@5451c3a8)  : r11/-
Var/Arg ?v25 (backend.variables.VInteger@2626b418)  : r4/-
***main : Arguments state***


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

	SUB sp, #20
	LDR r7, =0
	MOV r8, r6
	CMP r7 , r8
	BLE label4
	B label5
label4:
	b cont1
label5:
	b cont1
cont1:
	MOV r9, r0
	MOV r10, r4
	LDR r11, =0
	CMP r10 , r11
	BLE label6
	B label7
label6:
	b cont2
label7:
	b cont2
cont2:
	MOV r4, r0
	ADD r0, r9, r4
	MOV r5, r0
	LDR r6, =0
	MOV r7, r5
	CMP r6 , r7
	BLE label8
	B label9
label8:
	b cont3
label9:
	b cont3
cont3:
	MOV r8, r0
	ADD r0, r5, r8
	MOV r10, r0
	MOV r0, r10
	BL min_caml_print_int
	BL min_caml_print_newline
	ADD sp, #20

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0

