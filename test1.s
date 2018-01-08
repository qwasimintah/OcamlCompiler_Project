------ AST ------
(let x = 42 in (print_int ?v0))
------ K-Normalization ------
(let x = 42 in (print_int ?v0))
------ AlphaConversion ------
(let ?v0 = 42 in (print_int ?v0))
------ Reduction of Nested Let-Expressions ------
(let ?v0 = 42 in (print_int ?v0))
APP
------ Translation to Jerry ------
CALL print_int backend.variables.Variable@2503dbd3 

------ Register Allocation ------

------ ARM code generation ------
