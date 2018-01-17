------ AST ------
(let rec f x = (x + 123) in (let rec g y = f in (print_int ((g 456) 789))))

------ K-Normalization ------
(let rec f x = (let ?v0 = x in (let ?v1 = 123 in (?v0 + ?v1))) in (let rec g y = f in (let ?v2 = (let ?v3 = 456 in (let ?v4 = 789 in (g ?v3 ?v4))) in (print_int ?v2))))

------ AlphaConversion ------
(let rec ?v5 ?v6 = (let ?v7 = ?v6 in (let ?v8 = 123 in (?v7 + ?v8))) in (let rec ?v9 ?v10 = ?v5 in (let ?v11 = (let ?v12 = 456 in (let ?v13 = 789 in (?v9 ?v12 ?v13))) in (print_int ?v11))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v5 ?v6 = (let ?v7 = ?v6 in (let ?v8 = 123 in (?v7 + ?v8))) in (let rec ?v9 ?v10 = ?v5 in (let ?v12 = 456 in (let ?v13 = 789 in (let ?v11 = (?v9 ?v12 ?v13) in (print_int ?v11))))))

------ ClosureConversion ------
