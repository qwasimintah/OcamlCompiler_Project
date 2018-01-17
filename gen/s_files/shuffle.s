------ AST ------
(let rec foo a b c d e f = (let ?v4 = (print_int a) in (let ?v3 = (print_int b) in (let ?v2 = (print_int c) in (let ?v1 = (print_int d) in (let ?v0 = (print_int e) in (print_int f)))))) in (let rec bar a b c d e f = (foo b a d e f c) in (bar 1 2 3 4 5 6)))

------ K-Normalization ------
(let rec foo a b c d e f = (let ?v4 = (let ?v5 = a in (print_int ?v5)) in (let ?v3 = (let ?v6 = b in (print_int ?v6)) in (let ?v2 = (let ?v7 = c in (print_int ?v7)) in (let ?v1 = (let ?v8 = d in (print_int ?v8)) in (let ?v0 = (let ?v9 = e in (print_int ?v9)) in (let ?v10 = f in (print_int ?v10))))))) in (let rec bar a b c d e f = (let ?v11 = b in (let ?v12 = a in (let ?v13 = d in (let ?v14 = e in (let ?v15 = f in (let ?v16 = c in (foo ?v11 ?v12 ?v13 ?v14 ?v15 ?v16))))))) in (let ?v17 = 1 in (let ?v18 = 2 in (let ?v19 = 3 in (let ?v20 = 4 in (let ?v21 = 5 in (let ?v22 = 6 in (bar ?v17 ?v18 ?v19 ?v20 ?v21 ?v22)))))))))

------ AlphaConversion ------
(let rec ?v23 ?v24 ?v25 ?v26 ?v27 ?v28 ?v29 = (let ?v30 = (let ?v31 = ?v24 in (print_int ?v31)) in (let ?v32 = (let ?v33 = ?v25 in (print_int ?v33)) in (let ?v34 = (let ?v35 = ?v26 in (print_int ?v35)) in (let ?v36 = (let ?v37 = ?v27 in (print_int ?v37)) in (let ?v38 = (let ?v39 = ?v28 in (print_int ?v39)) in (let ?v40 = ?v29 in (print_int ?v40))))))) in (let rec ?v41 ?v42 ?v43 ?v44 ?v45 ?v46 ?v47 = (let ?v48 = ?v43 in (let ?v49 = ?v42 in (let ?v50 = ?v45 in (let ?v51 = ?v46 in (let ?v52 = ?v47 in (let ?v53 = ?v44 in (?v23 ?v48 ?v49 ?v50 ?v51 ?v52 ?v53))))))) in (let ?v54 = 1 in (let ?v55 = 2 in (let ?v56 = 3 in (let ?v57 = 4 in (let ?v58 = 5 in (let ?v59 = 6 in (?v41 ?v54 ?v55 ?v56 ?v57 ?v58 ?v59)))))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v23 ?v24 ?v25 ?v26 ?v27 ?v28 ?v29 = (let ?v31 = ?v24 in (let ?v30 = (print_int ?v31) in (let ?v33 = ?v25 in (let ?v32 = (print_int ?v33) in (let ?v35 = ?v26 in (let ?v34 = (print_int ?v35) in (let ?v37 = ?v27 in (let ?v36 = (print_int ?v37) in (let ?v39 = ?v28 in (let ?v38 = (print_int ?v39) in (let ?v40 = ?v29 in (print_int ?v40)))))))))))) in (let rec ?v41 ?v42 ?v43 ?v44 ?v45 ?v46 ?v47 = (let ?v48 = ?v43 in (let ?v49 = ?v42 in (let ?v50 = ?v45 in (let ?v51 = ?v46 in (let ?v52 = ?v47 in (let ?v53 = ?v44 in (?v23 ?v48 ?v49 ?v50 ?v51 ?v52 ?v53))))))) in (let ?v54 = 1 in (let ?v55 = 2 in (let ?v56 = 3 in (let ?v57 = 4 in (let ?v58 = 5 in (let ?v59 = 6 in (?v41 ?v54 ?v55 ?v56 ?v57 ?v58 ?v59)))))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v41
	free_list: null
	args: [?v42, ?v43, ?v44, ?v45, ?v46, ?v47]
	code: 
(let ?v48 = ?v43 in (let ?v49 = ?v42 in (let ?v50 = ?v45 in (let ?v51 = ?v46 in (let ?v52 = ?v47 in (let ?v53 = ?v44 in (apply_direct _?v23 ?v48 ?v49 ?v50 ?v51 ?v52 ?v53)))))))
Closure list: 
closure numbers: 2
	label: _?v41
	free_list: null
	args: [?v42, ?v43, ?v44, ?v45, ?v46, ?v47]
	code: 
(let ?v48 = ?v43 in (let ?v49 = ?v42 in (let ?v50 = ?v45 in (let ?v51 = ?v46 in (let ?v52 = ?v47 in (let ?v53 = ?v44 in (apply_direct _?v23 ?v48 ?v49 ?v50 ?v51 ?v52 ?v53)))))))
	label: _?v23
	free_list: null
	args: [?v24, ?v25, ?v26, ?v27, ?v28, ?v29]
	code: 
(let ?v31 = ?v24 in (let ?v30 = (print_int ?v31) in (let ?v33 = ?v25 in (let ?v32 = (print_int ?v33) in (let ?v35 = ?v26 in (let ?v34 = (print_int ?v35) in (let ?v37 = ?v27 in (let ?v36 = (print_int ?v37) in (let ?v39 = ?v28 in (let ?v38 = (print_int ?v39) in (let ?v40 = ?v29 in (print_int ?v40))))))))))))
(let ?v54 = 1 in (let ?v55 = 2 in (let ?v56 = 3 in (let ?v57 = 4 in (let ?v58 = 5 in (let ?v59 = 6 in (apply_direct _?v41 ?v54 ?v55 ?v56 ?v57 ?v58 ?v59)))))))

------ Translation to Jerry ------
