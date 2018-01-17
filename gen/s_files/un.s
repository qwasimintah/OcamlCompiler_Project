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
NOTHING	tmpVar1 (backend.variables.VInteger@61bbe9ba)
ASSIGN	?v2 (backend.variables.VInteger@5cad8086) 1
NOTHING	tmpVar2 (backend.variables.VInteger@610455d6)
ASSIGN	?v3 (backend.variables.VInteger@6e0be858) 1
ADD	backend.variables.VInteger@5cad8086 backend.variables.VInteger@6e0be858


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v3 (backend.variables.VInteger@6e0be858)  : r5/-
Var/Arg ?v2 (backend.variables.VInteger@5cad8086)  : r4/-
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

