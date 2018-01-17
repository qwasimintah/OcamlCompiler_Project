------ AST ------
(let rec f x = (x + 123) in (let rec g y = f in (print_int ((g 456) 789))))

------ K-Normalization ------
(let rec f x = (let ?v0 = x in (let ?v1 = 123 in (?v0 + ?v1))) in (let rec g y = f in (let ?v2 = (let ?v3 = 789 in ((g 456) ?v3)) in (print_int ?v2))))

------ AlphaConversion ------
(let rec ?v4 ?v5 = (let ?v6 = ?v5 in (let ?v7 = 123 in (?v6 + ?v7))) in (let rec ?v8 ?v9 = ?v4 in (let ?v10 = (let ?v11 = 789 in ((?v8 456) ?v11)) in (print_int ?v10))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v4 ?v5 = (let ?v6 = ?v5 in (let ?v7 = 123 in (?v6 + ?v7))) in (let rec ?v8 ?v9 = ?v4 in (let ?v11 = 789 in (let ?v10 = ((?v8 456) ?v11) in (print_int ?v10)))))

------ ClosureConversion ------
