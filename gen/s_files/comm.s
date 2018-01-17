------ AST ------
(let x = 1 in 2)

------ K-Normalization ------
(let x = 1 in 2)

------ AlphaConversion ------
(let ?v0 = 1 in 2)

------ Reduction of Nested Let-Expressions ------
(let ?v0 = 1 in 2)

------ ClosureConversion ------
(let ?v0 = 1 in 2)

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar1 (backend.variables.VInteger@4554617c)
ASSIGN	?v0 (backend.variables.VInteger@1b6d3586) 1
NOTHING	tmpVar2 (backend.variables.VInteger@74a14482)


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v0 (backend.variables.VInteger@1b6d3586)  : r4/-
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

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0

