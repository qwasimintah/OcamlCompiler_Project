------ AST ------
(1 + 1)

------ K-Normalization ------
(let ?v0 = 1 in (let ?v1 = 1 in (?v0 + ?v1)))

------ AlphaConversion ------
(let ?v2 = 1 in (let ?v3 = 1 in (?v2 + ?v3)))

------ Reduction of Nested Let-Expressions ------
(let ?v2 = 1 in (let ?v3 = 1 in (?v2 + ?v3)))

------ ClosureConversion ------
(let ?v2 = 1 in (let ?v3 = 1 in (?v2 + ?v3)))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@74a14482)
ASSIGN	?v2 (backend.variables.VInteger@1b6d3586) 1
NOTHING	tmpVar2 (backend.variables.VInteger@1540e19d)
ASSIGN	?v3 (backend.variables.VInteger@4554617c) 1
ADD	backend.variables.VInteger@1b6d3586 backend.variables.VInteger@4554617c


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v3 (backend.variables.VInteger@4554617c)  : r5/-
Var/Arg ?v2 (backend.variables.VInteger@1b6d3586)  : r4/-
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

	LDR r4, =1
	LDR r5, =1
	ADD r0, r4, r5

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0

