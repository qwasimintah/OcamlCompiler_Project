------ AST ------
(let rec f a b c d = (let e = (a + b) in (let f = (a + c) in (let g = (a + d) in (let h = (b + c) in (let i = (b + d) in (let j = (c + d) in (let z = (((((((((a + b) + c) + d) + e) + f) + g) + h) + i) + j) in (- z)))))))) in (print_int (f 1 2 3 4)))

------ K-Normalization ------
(let rec f a b c d = (let e = (let ?v0 = a in (let ?v1 = b in (?v0 + ?v1))) in (let f = (let ?v2 = a in (let ?v3 = c in (?v2 + ?v3))) in (let g = (let ?v4 = a in (let ?v5 = d in (?v4 + ?v5))) in (let h = (let ?v6 = b in (let ?v7 = c in (?v6 + ?v7))) in (let i = (let ?v8 = b in (let ?v9 = d in (?v8 + ?v9))) in (let j = (let ?v10 = c in (let ?v11 = d in (?v10 + ?v11))) in (let z = (let ?v12 = (let ?v14 = (let ?v16 = (let ?v18 = (let ?v20 = (let ?v22 = (let ?v24 = (let ?v26 = (let ?v28 = a in (let ?v29 = b in (?v28 + ?v29))) in (let ?v27 = c in (?v26 + ?v27))) in (let ?v25 = d in (?v24 + ?v25))) in (let ?v23 = e in (?v22 + ?v23))) in (let ?v21 = f in (?v20 + ?v21))) in (let ?v19 = g in (?v18 + ?v19))) in (let ?v17 = h in (?v16 + ?v17))) in (let ?v15 = i in (?v14 + ?v15))) in (let ?v13 = j in (?v12 + ?v13))) in (- z)))))))) in (let ?v30 = (let ?v31 = 1 in (let ?v32 = 2 in (let ?v33 = 3 in (let ?v34 = 4 in (f ?v31 ?v32 ?v33 ?v34))))) in (print_int ?v30)))

------ AlphaConversion ------
(let rec ?v35 ?v36 ?v37 ?v38 ?v39 = (let ?v40 = (let ?v41 = ?v36 in (let ?v42 = ?v37 in (?v41 + ?v42))) in (let ?v43 = (let ?v44 = ?v36 in (let ?v45 = ?v38 in (?v44 + ?v45))) in (let ?v46 = (let ?v47 = ?v36 in (let ?v48 = ?v39 in (?v47 + ?v48))) in (let ?v49 = (let ?v50 = ?v37 in (let ?v51 = ?v38 in (?v50 + ?v51))) in (let ?v52 = (let ?v53 = ?v37 in (let ?v54 = ?v39 in (?v53 + ?v54))) in (let ?v55 = (let ?v56 = ?v38 in (let ?v57 = ?v39 in (?v56 + ?v57))) in (let ?v58 = (let ?v59 = (let ?v60 = (let ?v61 = (let ?v62 = (let ?v63 = (let ?v64 = (let ?v65 = (let ?v66 = (let ?v67 = ?v36 in (let ?v68 = ?v37 in (?v67 + ?v68))) in (let ?v69 = ?v38 in (?v66 + ?v69))) in (let ?v70 = ?v39 in (?v65 + ?v70))) in (let ?v71 = ?v40 in (?v64 + ?v71))) in (let ?v72 = ?v43 in (?v63 + ?v72))) in (let ?v73 = ?v46 in (?v62 + ?v73))) in (let ?v74 = ?v49 in (?v61 + ?v74))) in (let ?v75 = ?v52 in (?v60 + ?v75))) in (let ?v76 = ?v55 in (?v59 + ?v76))) in (- ?v58)))))))) in (let ?v77 = (let ?v78 = 1 in (let ?v79 = 2 in (let ?v80 = 3 in (let ?v81 = 4 in (?v35 ?v78 ?v79 ?v80 ?v81))))) in (print_int ?v77)))

------ Reduction of Nested Let-Expressions ------
(let rec ?v35 ?v36 ?v37 ?v38 ?v39 = (let ?v41 = ?v36 in (let ?v42 = ?v37 in (let ?v40 = (?v41 + ?v42) in (let ?v44 = ?v36 in (let ?v45 = ?v38 in (let ?v43 = (?v44 + ?v45) in (let ?v47 = ?v36 in (let ?v48 = ?v39 in (let ?v46 = (?v47 + ?v48) in (let ?v50 = ?v37 in (let ?v51 = ?v38 in (let ?v49 = (?v50 + ?v51) in (let ?v53 = ?v37 in (let ?v54 = ?v39 in (let ?v52 = (?v53 + ?v54) in (let ?v56 = ?v38 in (let ?v57 = ?v39 in (let ?v55 = (?v56 + ?v57) in (let ?v67 = ?v36 in (let ?v68 = ?v37 in (let ?v66 = (?v67 + ?v68) in (let ?v69 = ?v38 in (let ?v65 = (?v66 + ?v69) in (let ?v70 = ?v39 in (let ?v64 = (?v65 + ?v70) in (let ?v71 = ?v40 in (let ?v63 = (?v64 + ?v71) in (let ?v72 = ?v43 in (let ?v62 = (?v63 + ?v72) in (let ?v73 = ?v46 in (let ?v61 = (?v62 + ?v73) in (let ?v74 = ?v49 in (let ?v60 = (?v61 + ?v74) in (let ?v75 = ?v52 in (let ?v59 = (?v60 + ?v75) in (let ?v76 = ?v55 in (let ?v58 = (?v59 + ?v76) in (- ?v58)))))))))))))))))))))))))))))))))))))) in (let ?v78 = 1 in (let ?v79 = 2 in (let ?v80 = 3 in (let ?v81 = 4 in (let ?v77 = (?v35 ?v78 ?v79 ?v80 ?v81) in (print_int ?v77)))))))

------ ClosureConversion ------
(let ?v78 = 1 in (let ?v79 = 2 in (let ?v80 = 3 in (let ?v81 = 4 in (let ?v77 = (apply_direct _?v35 ?v78 ?v79 ?v80 ?v81) in (print_int ?v77))))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar2 (backend.variables.VInteger@2626b418)
ASSIGN	?v78 (backend.variables.VInteger@7440e464) 1
NOTHING	tmpVar3 (backend.variables.VInteger@5a07e868)
ASSIGN	?v79 (backend.variables.VInteger@49476842) 2
NOTHING	tmpVar4 (backend.variables.VInteger@76ed5528)
ASSIGN	?v80 (backend.variables.VInteger@78308db1) 3
NOTHING	tmpVar5 (backend.variables.VInteger@2c7b84de)
ASSIGN	?v81 (backend.variables.VInteger@27c170f0) 4
NOTHING	?v78 (backend.variables.VInteger@7440e464)
NOTHING	?v79 (backend.variables.VInteger@49476842)
NOTHING	?v80 (backend.variables.VInteger@78308db1)
NOTHING	?v81 (backend.variables.VInteger@27c170f0)
ASSIGN	?v77 (backend.variables.Variable@5451c3a8) backend.instructions.InstructionCALL@3fee733d
>CALL	label1 backend.variables.VInteger@7440e464 backend.variables.VInteger@49476842 backend.variables.VInteger@78308db1 backend.variables.VInteger@27c170f0 
NOTHING	?v77 (backend.variables.Variable@5451c3a8)
CALL	print_int backend.variables.Variable@5451c3a8 

******** label1 *********
NOTHING	?v36 (backend.variables.Variable@5acf9800)
ASSIGN	?v41 (backend.variables.Variable@4554617c) backend.variables.Variable@5acf9800
NOTHING	?v37 (backend.variables.Variable@4617c264)
ASSIGN	?v42 (backend.variables.Variable@74a14482) backend.variables.Variable@4617c264
ADD	backend.variables.Variable@4554617c backend.variables.Variable@74a14482
ASSIGN	?v40 (backend.variables.VInteger@1540e19d) backend.instructions.InstructionADD@36baf30c
>ADD	backend.variables.Variable@4554617c backend.variables.Variable@74a14482
NOTHING	?v36 (backend.variables.Variable@5acf9800)
ASSIGN	?v44 (backend.variables.Variable@677327b6) backend.variables.Variable@5acf9800
NOTHING	?v38 (backend.variables.Variable@7a81197d)
ASSIGN	?v45 (backend.variables.Variable@14ae5a5) backend.variables.Variable@7a81197d
ADD	backend.variables.Variable@677327b6 backend.variables.Variable@14ae5a5
ASSIGN	?v43 (backend.variables.VInteger@7f31245a) backend.instructions.InstructionADD@5ca881b5
>ADD	backend.variables.Variable@677327b6 backend.variables.Variable@14ae5a5
NOTHING	?v36 (backend.variables.Variable@5acf9800)
ASSIGN	?v47 (backend.variables.Variable@6d6f6e28) backend.variables.Variable@5acf9800
NOTHING	?v39 (backend.variables.Variable@24d46ca6)
ASSIGN	?v48 (backend.variables.Variable@135fbaa4) backend.variables.Variable@24d46ca6
ADD	backend.variables.Variable@6d6f6e28 backend.variables.Variable@135fbaa4
ASSIGN	?v46 (backend.variables.VInteger@45ee12a7) backend.instructions.InstructionADD@4517d9a3
>ADD	backend.variables.Variable@6d6f6e28 backend.variables.Variable@135fbaa4
NOTHING	?v37 (backend.variables.Variable@4617c264)
ASSIGN	?v50 (backend.variables.Variable@330bedb4) backend.variables.Variable@4617c264
NOTHING	?v38 (backend.variables.Variable@7a81197d)
ASSIGN	?v51 (backend.variables.Variable@2503dbd3) backend.variables.Variable@7a81197d
ADD	backend.variables.Variable@330bedb4 backend.variables.Variable@2503dbd3
ASSIGN	?v49 (backend.variables.VInteger@4b67cf4d) backend.instructions.InstructionADD@372f7a8d
>ADD	backend.variables.Variable@330bedb4 backend.variables.Variable@2503dbd3
NOTHING	?v37 (backend.variables.Variable@4617c264)
ASSIGN	?v53 (backend.variables.Variable@7ea987ac) backend.variables.Variable@4617c264
NOTHING	?v39 (backend.variables.Variable@24d46ca6)
ASSIGN	?v54 (backend.variables.Variable@12a3a380) backend.variables.Variable@24d46ca6
ADD	backend.variables.Variable@7ea987ac backend.variables.Variable@12a3a380
ASSIGN	?v52 (backend.variables.VInteger@29453f44) backend.instructions.InstructionADD@2f92e0f4
>ADD	backend.variables.Variable@7ea987ac backend.variables.Variable@12a3a380
NOTHING	?v38 (backend.variables.Variable@7a81197d)
ASSIGN	?v56 (backend.variables.Variable@5cad8086) backend.variables.Variable@7a81197d
NOTHING	?v39 (backend.variables.Variable@24d46ca6)
ASSIGN	?v57 (backend.variables.Variable@6e0be858) backend.variables.Variable@24d46ca6
ADD	backend.variables.Variable@5cad8086 backend.variables.Variable@6e0be858
ASSIGN	?v55 (backend.variables.VInteger@61bbe9ba) backend.instructions.InstructionADD@28a418fc
>ADD	backend.variables.Variable@5cad8086 backend.variables.Variable@6e0be858
NOTHING	?v36 (backend.variables.Variable@5acf9800)
ASSIGN	?v67 (backend.variables.Variable@610455d6) backend.variables.Variable@5acf9800
NOTHING	?v37 (backend.variables.Variable@4617c264)
ASSIGN	?v68 (backend.variables.Variable@511d50c0) backend.variables.Variable@4617c264
ADD	backend.variables.Variable@610455d6 backend.variables.Variable@511d50c0
ASSIGN	?v66 (backend.variables.VInteger@60e53b93) backend.instructions.InstructionADD@5305068a
>ADD	backend.variables.Variable@610455d6 backend.variables.Variable@511d50c0
NOTHING	?v38 (backend.variables.Variable@7a81197d)
ASSIGN	?v69 (backend.variables.Variable@5e2de80c) backend.variables.Variable@7a81197d
ADD	backend.variables.VInteger@60e53b93 backend.variables.Variable@5e2de80c
ASSIGN	?v65 (backend.variables.VInteger@1d44bcfa) backend.instructions.InstructionADD@1f32e575
>ADD	backend.variables.VInteger@60e53b93 backend.variables.Variable@5e2de80c
NOTHING	?v39 (backend.variables.Variable@24d46ca6)
ASSIGN	?v70 (backend.variables.Variable@266474c2) backend.variables.Variable@24d46ca6
ADD	backend.variables.VInteger@1d44bcfa backend.variables.Variable@266474c2
ASSIGN	?v64 (backend.variables.VInteger@6f94fa3e) backend.instructions.InstructionADD@279f2327
>ADD	backend.variables.VInteger@1d44bcfa backend.variables.Variable@266474c2
NOTHING	?v40 (backend.variables.VInteger@1540e19d)
ASSIGN	?v71 (backend.variables.Variable@5e481248) backend.variables.VInteger@1540e19d
ADD	backend.variables.VInteger@6f94fa3e backend.variables.Variable@5e481248
ASSIGN	?v63 (backend.variables.VInteger@66d3c617) backend.instructions.InstructionADD@2ff4acd0
>ADD	backend.variables.VInteger@6f94fa3e backend.variables.Variable@5e481248
NOTHING	?v43 (backend.variables.VInteger@7f31245a)
ASSIGN	?v72 (backend.variables.Variable@63947c6b) backend.variables.VInteger@7f31245a
ADD	backend.variables.VInteger@66d3c617 backend.variables.Variable@63947c6b
ASSIGN	?v62 (backend.variables.VInteger@2b193f2d) backend.instructions.InstructionADD@54bedef2
>ADD	backend.variables.VInteger@66d3c617 backend.variables.Variable@63947c6b
NOTHING	?v46 (backend.variables.VInteger@45ee12a7)
ASSIGN	?v73 (backend.variables.Variable@355da254) backend.variables.VInteger@45ee12a7
ADD	backend.variables.VInteger@2b193f2d backend.variables.Variable@355da254
ASSIGN	?v61 (backend.variables.VInteger@4dc63996) backend.instructions.InstructionADD@5caf905d
>ADD	backend.variables.VInteger@2b193f2d backend.variables.Variable@355da254
NOTHING	?v49 (backend.variables.VInteger@4b67cf4d)
ASSIGN	?v74 (backend.variables.Variable@d716361) backend.variables.VInteger@4b67cf4d
ADD	backend.variables.VInteger@4dc63996 backend.variables.Variable@d716361
ASSIGN	?v60 (backend.variables.VInteger@6ff3c5b5) backend.instructions.InstructionADD@27716f4
>ADD	backend.variables.VInteger@4dc63996 backend.variables.Variable@d716361
NOTHING	?v52 (backend.variables.VInteger@29453f44)
ASSIGN	?v75 (backend.variables.Variable@3764951d) backend.variables.VInteger@29453f44
ADD	backend.variables.VInteger@6ff3c5b5 backend.variables.Variable@3764951d
ASSIGN	?v59 (backend.variables.VInteger@4b1210ee) backend.instructions.InstructionADD@8efb846
>ADD	backend.variables.VInteger@6ff3c5b5 backend.variables.Variable@3764951d
NOTHING	?v55 (backend.variables.VInteger@61bbe9ba)
ASSIGN	?v76 (backend.variables.Variable@4d7e1886) backend.variables.VInteger@61bbe9ba
ADD	backend.variables.VInteger@4b1210ee backend.variables.Variable@4d7e1886
ASSIGN	?v58 (backend.variables.VInteger@3cd1a2f1) backend.instructions.InstructionADD@2a84aee7
>ADD	backend.variables.VInteger@4b1210ee backend.variables.Variable@4d7e1886
NOTHING	?v58 (backend.variables.VInteger@3cd1a2f1)
ASSIGN	tmpVar1 (backend.variables.VInteger@2f0e140b) -1
MULT	backend.variables.VInteger@3cd1a2f1 backend.variables.VInteger@2f0e140b


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v80 (backend.variables.VInteger@78308db1)  : r4/[fp + 4]
Var/Arg ?v81 (backend.variables.VInteger@27c170f0)  : r5/[fp + 8]
Var/Arg ?v78 (backend.variables.VInteger@7440e464)  : r6/r2
Var/Arg ?v79 (backend.variables.VInteger@49476842)  : r7/r3
Var/Arg ?v77 (backend.variables.Variable@5451c3a8)  : r8/r2
***main : Arguments state***

***label1 : Variables state***
Var/Arg ?v71 (backend.variables.Variable@5e481248)  : r4/-
Var/Arg ?v52 (backend.variables.VInteger@29453f44)  : r5/-
Var/Arg ?v55 (backend.variables.VInteger@61bbe9ba)  : r6/-
Var/Arg ?v63 (backend.variables.VInteger@66d3c617)  : r7/-
Var/Arg ?v44 (backend.variables.Variable@677327b6)  : r8/-
Var/Arg ?v53 (backend.variables.Variable@7ea987ac)  : r9/-
Var/Arg tmpVar1 (backend.variables.VInteger@2f0e140b)  : r10/-
Var/Arg ?v60 (backend.variables.VInteger@6ff3c5b5)  : r4/-
Var/Arg ?v47 (backend.variables.Variable@6d6f6e28)  : r5/-
Var/Arg ?v46 (backend.variables.VInteger@45ee12a7)  : r6/-
Var/Arg ?v73 (backend.variables.Variable@355da254)  : r7/-
Var/Arg ?v51 (backend.variables.Variable@2503dbd3)  : r8/-
Var/Arg ?v61 (backend.variables.VInteger@4dc63996)  : r9/-
Var/Arg ?v74 (backend.variables.Variable@d716361)  : r10/-
Var/Arg ?v67 (backend.variables.Variable@610455d6)  : r4/-
Var/Arg ?v57 (backend.variables.Variable@6e0be858)  : r5/-
Var/Arg ?v40 (backend.variables.VInteger@1540e19d)  : r6/-
Var/Arg ?v68 (backend.variables.Variable@511d50c0)  : r7/-
Var/Arg ?v58 (backend.variables.VInteger@3cd1a2f1)  : r8/-
Var/Arg ?v69 (backend.variables.Variable@5e2de80c)  : r9/-
Var/Arg ?v42 (backend.variables.Variable@74a14482)  : r10/-
Var/Arg ?v54 (backend.variables.Variable@12a3a380)  : r4/-
Var/Arg ?v70 (backend.variables.Variable@266474c2)  : r5/-
Var/Arg ?v41 (backend.variables.Variable@4554617c)  : r6/-
Var/Arg ?v49 (backend.variables.VInteger@4b67cf4d)  : r7/-
Var/Arg ?v64 (backend.variables.VInteger@6f94fa3e)  : r8/-
Var/Arg ?v43 (backend.variables.VInteger@7f31245a)  : r9/-
Var/Arg ?v56 (backend.variables.Variable@5cad8086)  : r10/-
Var/Arg ?v45 (backend.variables.Variable@14ae5a5)  : r4/-
Var/Arg ?v62 (backend.variables.VInteger@2b193f2d)  : r5/-
Var/Arg ?v66 (backend.variables.VInteger@60e53b93)  : r6/-
Var/Arg ?v76 (backend.variables.Variable@4d7e1886)  : r7/-
Var/Arg ?v75 (backend.variables.Variable@3764951d)  : r8/-
Var/Arg ?v48 (backend.variables.Variable@135fbaa4)  : r9/-
Var/Arg ?v59 (backend.variables.VInteger@4b1210ee)  : r10/-
Var/Arg ?v65 (backend.variables.VInteger@1d44bcfa)  : r4/-
Var/Arg ?v50 (backend.variables.Variable@330bedb4)  : r5/-
Var/Arg ?v72 (backend.variables.Variable@63947c6b)  : r6/-
***label1 : Arguments state***
Var/Arg ?v36 (backend.variables.Variable@5acf9800)  : -/r2
Var/Arg ?v37 (backend.variables.Variable@4617c264)  : -/r3
Var/Arg ?v38 (backend.variables.Variable@7a81197d)  : -/[fp + 4]
Var/Arg ?v39 (backend.variables.Variable@24d46ca6)  : -/[fp + 8]


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

	LDR r6, =1
	LDR r7, =2
	LDR r4, =3
	LDR r5, =4
	STMFD sp!,{r2-r12}
	MOV r2, r6
	MOV r3, r7
	SUB sp, #4
	MOV r0, r5
	STR r0, [sp]
	SUB sp, #4
	MOV r0, r4
	STR r0, [sp]
	BL _label1
	ADD sp, #8
	LDMFD sp!, {r2-r12}
	MOV r8, r0
	MOV r0, r8
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
	SUB sp, #116

	MOV r6, r2
	MOV r10, r3
	ADD r0, r6, r10
	MOV r6, r0
	MOV r8, r2
	LDR r1 , [fp ,#4]
	MOV r4, r1
	ADD r0, r8, r4
	MOV r9, r0
	MOV r5, r2
	LDR r1 , [fp ,#8]
	MOV r9, r1
	ADD r0, r5, r9
	MOV r6, r0
	MOV r5, r3
	LDR r1 , [fp ,#4]
	MOV r8, r1
	ADD r0, r5, r8
	MOV r7, r0
	MOV r9, r3
	LDR r1 , [fp ,#8]
	MOV r4, r1
	ADD r0, r9, r4
	MOV r5, r0
	LDR r1 , [fp ,#4]
	MOV r10, r1
	LDR r1 , [fp ,#8]
	MOV r5, r1
	ADD r0, r10, r5
	MOV r6, r0
	MOV r4, r2
	MOV r7, r3
	ADD r0, r4, r7
	MOV r6, r0
	LDR r1 , [fp ,#4]
	MOV r9, r1
	ADD r0, r6, r9
	MOV r4, r0
	LDR r1 , [fp ,#8]
	MOV r5, r1
	ADD r0, r4, r5
	MOV r8, r0
	MOV r4, r6
	ADD r0, r8, r4
	MOV r7, r0
	MOV r6, r9
	ADD r0, r7, r6
	MOV r5, r0
	MOV r7, r6
	ADD r0, r5, r7
	MOV r9, r0
	MOV r10, r7
	ADD r0, r9, r10
	MOV r4, r0
	MOV r8, r5
	ADD r0, r4, r8
	MOV r10, r0
	MOV r7, r6
	ADD r0, r10, r7
	MOV r8, r0
	LDR r10, =-1
	MUL r0, r8, r10
	ADD sp, #116

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


