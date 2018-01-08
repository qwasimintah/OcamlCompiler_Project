------ AST ------
(let x = (1 + 2) in (print_int ?v2))
------ K-Normalization ------
(let x = (let ?v3 = 1 in (let ?v4 = 2 in (?v3 + ?v4))) in (print_int ?v2))
------ AlphaConversion ------
(let ?v2 = (let ?v3 = 1 in (let ?v4 = 2 in (?v3 + ?v4))) in (print_int ?v2))
------ Reduction of Nested Let-Expressions ------
(let ?v3 = 1 in (let ?v4 = 2 in (let ?v2 = (?v3 + ?v4) in (print_int ?v2))))
LET
INT
LET
INT
LET
APP
------ Translation to Jerry ------
backend.variables.VInteger@266474c2 := 1
backend.variables.VInteger@6f94fa3e := 2
CALL print_int 

------ Register Allocation ------
Variable ?v3 stored in register r5
Variable ?v4 stored in register r8

------ ARM code generation ------
