------ AST ------
(let rec mul l m n a b c = (let rec loop1 i = (if (not (0 <= i)) then () else (let rec loop2 j = (if (not (0 <= j)) then () else (let rec loop3 k = (if (not (0 <= k)) then () else (let ?v0 = (c.(i).(j) <- (c.(i).(j) +. (a.(i).(k) *. b.(k).(j)))) in (loop3 (k - 1)))) in (let ?v1 = (loop3 (m - 1)) in (loop2 (j - 1))))) in (let ?v2 = (loop2 (n - 1)) in (loop1 (i - 1))))) in (loop1 (l - 1))) in (let dummy = (Array.create 0 0,00) in (let rec make m n = (let mat = (Array.create m dummy) in (let rec init i = (if (not (0 <= i)) then () else (let ?v3 = (mat.(i) <- (Array.create n 0,00)) in (init (i - 1)))) in (let ?v4 = (init (m - 1)) in mat))) in (let a = (make 2 3) in (let b = (make 3 2) in (let c = (make 2 2) in (let ?v24 = (a.(0).(0) <- 1,00) in (let ?v23 = (a.(0).(1) <- 2,00) in (let ?v22 = (a.(0).(2) <- 3,00) in (let ?v21 = (a.(1).(0) <- 4,00) in (let ?v20 = (a.(1).(1) <- 5,00) in (let ?v19 = (a.(1).(2) <- 6,00) in (let ?v18 = (b.(0).(0) <- 7,00) in (let ?v17 = (b.(0).(1) <- 8,00) in (let ?v16 = (b.(1).(0) <- 9,00) in (let ?v15 = (b.(1).(1) <- 10,00) in (let ?v14 = (b.(2).(0) <- 11,00) in (let ?v13 = (b.(2).(1) <- 12,00) in (let ?v12 = (mul 2 3 2 a b c) in (let ?v11 = (print_int (truncate c.(0).(0))) in (let ?v10 = (print_newline ()) in (let ?v9 = (print_int (truncate c.(0).(1))) in (let ?v8 = (print_newline ()) in (let ?v7 = (print_int (truncate c.(1).(0))) in (let ?v6 = (print_newline ()) in (let ?v5 = (print_int (truncate c.(1).(1))) in (print_newline ())))))))))))))))))))))))))))

------ K-Normalization ------
(let rec mul l m n a b c = (let rec loop1 i = (let ?v25 = 0 in (let ?v26 = i in (if (?v25 <= ?v26) then (let rec loop2 j = (let ?v27 = 0 in (let ?v28 = j in (if (?v27 <= ?v28) then (let rec loop3 k = (let ?v29 = 0 in (let ?v30 = k in (if (?v29 <= ?v30) then (let ?v0 = (c.(i).(j) <- (c.(i).(j) +. (a.(i).(k) *. b.(k).(j)))) in (let ?v31 = (let ?v32 = k in (let ?v33 = 1 in (?v32 - ?v33))) in (loop3 ?v31))) else ()))) in (let ?v1 = (let ?v34 = (let ?v35 = m in (let ?v36 = 1 in (?v35 - ?v36))) in (loop3 ?v34)) in (let ?v37 = (let ?v38 = j in (let ?v39 = 1 in (?v38 - ?v39))) in (loop2 ?v37)))) else ()))) in (let ?v2 = (let ?v40 = (let ?v41 = n in (let ?v42 = 1 in (?v41 - ?v42))) in (loop2 ?v40)) in (let ?v43 = (let ?v44 = i in (let ?v45 = 1 in (?v44 - ?v45))) in (loop1 ?v43)))) else ()))) in (let ?v46 = (let ?v47 = l in (let ?v48 = 1 in (?v47 - ?v48))) in (loop1 ?v46))) in (let dummy = (Array.create 0 0,00) in (let rec make m n = (let mat = (Array.create m dummy) in (let rec init i = (let ?v49 = 0 in (let ?v50 = i in (if (?v49 <= ?v50) then (let ?v3 = (mat.(i) <- (Array.create n 0,00)) in (let ?v51 = (let ?v52 = i in (let ?v53 = 1 in (?v52 - ?v53))) in (init ?v51))) else ()))) in (let ?v4 = (let ?v54 = (let ?v55 = m in (let ?v56 = 1 in (?v55 - ?v56))) in (init ?v54)) in mat))) in (let a = (let ?v57 = 2 in (let ?v58 = 3 in (make ?v57 ?v58))) in (let b = (let ?v59 = 3 in (let ?v60 = 2 in (make ?v59 ?v60))) in (let c = (let ?v61 = 2 in (let ?v62 = 2 in (make ?v61 ?v62))) in (let ?v24 = (a.(0).(0) <- 1,00) in (let ?v23 = (a.(0).(1) <- 2,00) in (let ?v22 = (a.(0).(2) <- 3,00) in (let ?v21 = (a.(1).(0) <- 4,00) in (let ?v20 = (a.(1).(1) <- 5,00) in (let ?v19 = (a.(1).(2) <- 6,00) in (let ?v18 = (b.(0).(0) <- 7,00) in (let ?v17 = (b.(0).(1) <- 8,00) in (let ?v16 = (b.(1).(0) <- 9,00) in (let ?v15 = (b.(1).(1) <- 10,00) in (let ?v14 = (b.(2).(0) <- 11,00) in (let ?v13 = (b.(2).(1) <- 12,00) in (let ?v12 = (let ?v63 = 2 in (let ?v64 = 3 in (let ?v65 = 2 in (let ?v66 = a in (let ?v67 = b in (let ?v68 = c in (mul ?v63 ?v64 ?v65 ?v66 ?v67 ?v68))))))) in (let ?v11 = (let ?v69 = (let ?v70 = c.(0).(0) in (truncate ?v70)) in (print_int ?v69)) in (let ?v10 = (let ?v71 = () in (print_newline ?v71)) in (let ?v9 = (let ?v72 = (let ?v73 = c.(0).(1) in (truncate ?v73)) in (print_int ?v72)) in (let ?v8 = (let ?v74 = () in (print_newline ?v74)) in (let ?v7 = (let ?v75 = (let ?v76 = c.(1).(0) in (truncate ?v76)) in (print_int ?v75)) in (let ?v6 = (let ?v77 = () in (print_newline ?v77)) in (let ?v5 = (let ?v78 = (let ?v79 = c.(1).(1) in (truncate ?v79)) in (print_int ?v78)) in (let ?v80 = () in (print_newline ?v80))))))))))))))))))))))))))))

------ AlphaConversion ------
(let rec ?v81 ?v82 ?v83 ?v84 ?v85 ?v86 ?v87 = (let rec ?v88 ?v89 = (let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let rec ?v92 ?v93 = (let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let rec ?v96 ?v97 = (let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v101 = (let ?v102 = ?v97 in (let ?v103 = 1 in (?v102 - ?v103))) in (?v96 ?v101))) else ()))) in (let ?v104 = (let ?v105 = (let ?v106 = ?v83 in (let ?v107 = 1 in (?v106 - ?v107))) in (?v96 ?v105)) in (let ?v108 = (let ?v109 = ?v93 in (let ?v110 = 1 in (?v109 - ?v110))) in (?v92 ?v108)))) else ()))) in (let ?v111 = (let ?v112 = (let ?v113 = ?v84 in (let ?v114 = 1 in (?v113 - ?v114))) in (?v92 ?v112)) in (let ?v115 = (let ?v116 = ?v89 in (let ?v117 = 1 in (?v116 - ?v117))) in (?v88 ?v115)))) else ()))) in (let ?v118 = (let ?v119 = ?v82 in (let ?v120 = 1 in (?v119 - ?v120))) in (?v88 ?v118))) in (let ?v121 = (Array.create 0 0,00) in (let rec ?v122 ?v123 ?v124 = (let ?v125 = (Array.create ?v123 ?v121) in (let rec ?v126 ?v127 = (let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v131 = (let ?v132 = ?v127 in (let ?v133 = 1 in (?v132 - ?v133))) in (?v126 ?v131))) else ()))) in (let ?v134 = (let ?v135 = (let ?v136 = ?v123 in (let ?v137 = 1 in (?v136 - ?v137))) in (?v126 ?v135)) in ?v125))) in (let ?v138 = (let ?v139 = 2 in (let ?v140 = 3 in (?v122 ?v139 ?v140))) in (let ?v141 = (let ?v142 = 3 in (let ?v143 = 2 in (?v122 ?v142 ?v143))) in (let ?v144 = (let ?v145 = 2 in (let ?v146 = 2 in (?v122 ?v145 ?v146))) in (let ?v147 = (?v138.(0).(0) <- 1,00) in (let ?v148 = (?v138.(0).(1) <- 2,00) in (let ?v149 = (?v138.(0).(2) <- 3,00) in (let ?v150 = (?v138.(1).(0) <- 4,00) in (let ?v151 = (?v138.(1).(1) <- 5,00) in (let ?v152 = (?v138.(1).(2) <- 6,00) in (let ?v153 = (?v141.(0).(0) <- 7,00) in (let ?v154 = (?v141.(0).(1) <- 8,00) in (let ?v155 = (?v141.(1).(0) <- 9,00) in (let ?v156 = (?v141.(1).(1) <- 10,00) in (let ?v157 = (?v141.(2).(0) <- 11,00) in (let ?v158 = (?v141.(2).(1) <- 12,00) in (let ?v159 = (let ?v160 = 2 in (let ?v161 = 3 in (let ?v162 = 2 in (let ?v163 = ?v138 in (let ?v164 = ?v141 in (let ?v165 = ?v144 in (?v81 ?v160 ?v161 ?v162 ?v163 ?v164 ?v165))))))) in (let ?v166 = (let ?v167 = (let ?v168 = ?v144.(0).(0) in (truncate ?v168)) in (print_int ?v167)) in (let ?v169 = (let ?v170 = () in (print_newline ?v170)) in (let ?v171 = (let ?v172 = (let ?v173 = ?v144.(0).(1) in (truncate ?v173)) in (print_int ?v172)) in (let ?v174 = (let ?v175 = () in (print_newline ?v175)) in (let ?v176 = (let ?v177 = (let ?v178 = ?v144.(1).(0) in (truncate ?v178)) in (print_int ?v177)) in (let ?v179 = (let ?v180 = () in (print_newline ?v180)) in (let ?v181 = (let ?v182 = (let ?v183 = ?v144.(1).(1) in (truncate ?v183)) in (print_int ?v182)) in (let ?v184 = () in (print_newline ?v184))))))))))))))))))))))))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v81 ?v82 ?v83 ?v84 ?v85 ?v86 ?v87 = (let rec ?v88 ?v89 = (let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let rec ?v92 ?v93 = (let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let rec ?v96 ?v97 = (let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v102 = ?v97 in (let ?v103 = 1 in (let ?v101 = (?v102 - ?v103) in (?v96 ?v101))))) else ()))) in (let ?v106 = ?v83 in (let ?v107 = 1 in (let ?v105 = (?v106 - ?v107) in (let ?v104 = (?v96 ?v105) in (let ?v109 = ?v93 in (let ?v110 = 1 in (let ?v108 = (?v109 - ?v110) in (?v92 ?v108))))))))) else ()))) in (let ?v113 = ?v84 in (let ?v114 = 1 in (let ?v112 = (?v113 - ?v114) in (let ?v111 = (?v92 ?v112) in (let ?v116 = ?v89 in (let ?v117 = 1 in (let ?v115 = (?v116 - ?v117) in (?v88 ?v115))))))))) else ()))) in (let ?v119 = ?v82 in (let ?v120 = 1 in (let ?v118 = (?v119 - ?v120) in (?v88 ?v118))))) in (let ?v121 = (Array.create 0 0,00) in (let rec ?v122 ?v123 ?v124 = (let ?v125 = (Array.create ?v123 ?v121) in (let rec ?v126 ?v127 = (let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v132 = ?v127 in (let ?v133 = 1 in (let ?v131 = (?v132 - ?v133) in (?v126 ?v131))))) else ()))) in (let ?v136 = ?v123 in (let ?v137 = 1 in (let ?v135 = (?v136 - ?v137) in (let ?v134 = (?v126 ?v135) in ?v125)))))) in (let ?v139 = 2 in (let ?v140 = 3 in (let ?v138 = (?v122 ?v139 ?v140) in (let ?v142 = 3 in (let ?v143 = 2 in (let ?v141 = (?v122 ?v142 ?v143) in (let ?v145 = 2 in (let ?v146 = 2 in (let ?v144 = (?v122 ?v145 ?v146) in (let ?v147 = (?v138.(0).(0) <- 1,00) in (let ?v148 = (?v138.(0).(1) <- 2,00) in (let ?v149 = (?v138.(0).(2) <- 3,00) in (let ?v150 = (?v138.(1).(0) <- 4,00) in (let ?v151 = (?v138.(1).(1) <- 5,00) in (let ?v152 = (?v138.(1).(2) <- 6,00) in (let ?v153 = (?v141.(0).(0) <- 7,00) in (let ?v154 = (?v141.(0).(1) <- 8,00) in (let ?v155 = (?v141.(1).(0) <- 9,00) in (let ?v156 = (?v141.(1).(1) <- 10,00) in (let ?v157 = (?v141.(2).(0) <- 11,00) in (let ?v158 = (?v141.(2).(1) <- 12,00) in (let ?v160 = 2 in (let ?v161 = 3 in (let ?v162 = 2 in (let ?v163 = ?v138 in (let ?v164 = ?v141 in (let ?v165 = ?v144 in (let ?v159 = (?v81 ?v160 ?v161 ?v162 ?v163 ?v164 ?v165) in (let ?v168 = ?v144.(0).(0) in (let ?v167 = (truncate ?v168) in (let ?v166 = (print_int ?v167) in (let ?v170 = () in (let ?v169 = (print_newline ?v170) in (let ?v173 = ?v144.(0).(1) in (let ?v172 = (truncate ?v173) in (let ?v171 = (print_int ?v172) in (let ?v175 = () in (let ?v174 = (print_newline ?v175) in (let ?v178 = ?v144.(1).(0) in (let ?v177 = (truncate ?v178) in (let ?v176 = (print_int ?v177) in (let ?v180 = () in (let ?v179 = (print_newline ?v180) in (let ?v183 = ?v144.(1).(1) in (let ?v182 = (truncate ?v183) in (let ?v181 = (print_int ?v182) in (let ?v184 = () in (print_newline ?v184)))))))))))))))))))))))))))))))))))))))))))))))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v102 = ?v97 in (let ?v103 = 1 in (let ?v101 = (?v102 - ?v103) in (let ?v185 = (make_closure _?v96 ?v97) in (apply_closure ?v185 ?v101)))))) else ())))
Closure list: 
closure numbers: 2
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v102 = ?v97 in (let ?v103 = 1 in (let ?v101 = (?v102 - ?v103) in (let ?v185 = (make_closure _?v96 ?v97) in (apply_closure ?v185 ?v101)))))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v106 = ?v83 in (let ?v107 = 1 in (let ?v105 = (?v106 - ?v107) in (let ?v104 = (apply_closure _?v96 ?v105) in (let ?v109 = ?v93 in (let ?v110 = 1 in (let ?v108 = (?v109 - ?v110) in (let ?v186 = (make_closure _?v92 ?v97) in (apply_closure ?v186 ?v108))))))))) else ())))
Closure list: 
closure numbers: 3
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v102 = ?v97 in (let ?v103 = 1 in (let ?v101 = (?v102 - ?v103) in (let ?v185 = (make_closure _?v96 ?v97) in (apply_closure ?v185 ?v101)))))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v106 = ?v83 in (let ?v107 = 1 in (let ?v105 = (?v106 - ?v107) in (let ?v104 = (apply_closure _?v96 ?v105) in (let ?v109 = ?v93 in (let ?v110 = 1 in (let ?v108 = (?v109 - ?v110) in (let ?v186 = (make_closure _?v92 ?v97) in (apply_closure ?v186 ?v108))))))))) else ())))
	label: _?v88
	free_list: [?v87, ?v85, ?v86, ?v83, ?v84]
	args: [?v89]
	code: 
(let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let ?v113 = ?v84 in (let ?v114 = 1 in (let ?v112 = (?v113 - ?v114) in (let ?v111 = (apply_closure _?v92 ?v112) in (let ?v116 = ?v89 in (let ?v117 = 1 in (let ?v115 = (?v116 - ?v117) in (let ?v187 = (make_closure _?v88 ?v93) in (apply_closure ?v187 ?v115))))))))) else ())))
Closure list: 
closure numbers: 4
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v102 = ?v97 in (let ?v103 = 1 in (let ?v101 = (?v102 - ?v103) in (let ?v185 = (make_closure _?v96 ?v97) in (apply_closure ?v185 ?v101)))))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v106 = ?v83 in (let ?v107 = 1 in (let ?v105 = (?v106 - ?v107) in (let ?v104 = (apply_closure _?v96 ?v105) in (let ?v109 = ?v93 in (let ?v110 = 1 in (let ?v108 = (?v109 - ?v110) in (let ?v186 = (make_closure _?v92 ?v97) in (apply_closure ?v186 ?v108))))))))) else ())))
	label: _?v88
	free_list: [?v87, ?v85, ?v86, ?v83, ?v84]
	args: [?v89]
	code: 
(let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let ?v113 = ?v84 in (let ?v114 = 1 in (let ?v112 = (?v113 - ?v114) in (let ?v111 = (apply_closure _?v92 ?v112) in (let ?v116 = ?v89 in (let ?v117 = 1 in (let ?v115 = (?v116 - ?v117) in (let ?v187 = (make_closure _?v88 ?v93) in (apply_closure ?v187 ?v115))))))))) else ())))
	label: _?v126
	free_list: [?v124, ?v125]
	args: [?v127]
	code: 
(let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v132 = ?v127 in (let ?v133 = 1 in (let ?v131 = (?v132 - ?v133) in (let ?v188 = (make_closure _?v126 ?v127) in (apply_closure ?v188 ?v131)))))) else ())))
Closure list: 
closure numbers: 5
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v102 = ?v97 in (let ?v103 = 1 in (let ?v101 = (?v102 - ?v103) in (let ?v185 = (make_closure _?v96 ?v97) in (apply_closure ?v185 ?v101)))))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v106 = ?v83 in (let ?v107 = 1 in (let ?v105 = (?v106 - ?v107) in (let ?v104 = (apply_closure _?v96 ?v105) in (let ?v109 = ?v93 in (let ?v110 = 1 in (let ?v108 = (?v109 - ?v110) in (let ?v186 = (make_closure _?v92 ?v97) in (apply_closure ?v186 ?v108))))))))) else ())))
	label: _?v88
	free_list: [?v87, ?v85, ?v86, ?v83, ?v84]
	args: [?v89]
	code: 
(let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let ?v113 = ?v84 in (let ?v114 = 1 in (let ?v112 = (?v113 - ?v114) in (let ?v111 = (apply_closure _?v92 ?v112) in (let ?v116 = ?v89 in (let ?v117 = 1 in (let ?v115 = (?v116 - ?v117) in (let ?v187 = (make_closure _?v88 ?v93) in (apply_closure ?v187 ?v115))))))))) else ())))
	label: _?v126
	free_list: [?v124, ?v125]
	args: [?v127]
	code: 
(let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v132 = ?v127 in (let ?v133 = 1 in (let ?v131 = (?v132 - ?v133) in (let ?v188 = (make_closure _?v126 ?v127) in (apply_closure ?v188 ?v131)))))) else ())))
	label: _?v122
	free_list: [?v121]
	args: [?v123, ?v124]
	code: 
(let ?v125 = (Array.create ?v123 ?v121) in (let ?v136 = ?v123 in (let ?v137 = 1 in (let ?v135 = (?v136 - ?v137) in (let ?v134 = (apply_closure _?v126 ?v135) in ?v125)))))
Closure list: 
closure numbers: 6
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v102 = ?v97 in (let ?v103 = 1 in (let ?v101 = (?v102 - ?v103) in (let ?v185 = (make_closure _?v96 ?v97) in (apply_closure ?v185 ?v101)))))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v106 = ?v83 in (let ?v107 = 1 in (let ?v105 = (?v106 - ?v107) in (let ?v104 = (apply_closure _?v96 ?v105) in (let ?v109 = ?v93 in (let ?v110 = 1 in (let ?v108 = (?v109 - ?v110) in (let ?v186 = (make_closure _?v92 ?v97) in (apply_closure ?v186 ?v108))))))))) else ())))
	label: _?v88
	free_list: [?v87, ?v85, ?v86, ?v83, ?v84]
	args: [?v89]
	code: 
(let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let ?v113 = ?v84 in (let ?v114 = 1 in (let ?v112 = (?v113 - ?v114) in (let ?v111 = (apply_closure _?v92 ?v112) in (let ?v116 = ?v89 in (let ?v117 = 1 in (let ?v115 = (?v116 - ?v117) in (let ?v187 = (make_closure _?v88 ?v93) in (apply_closure ?v187 ?v115))))))))) else ())))
	label: _?v126
	free_list: [?v124, ?v125]
	args: [?v127]
	code: 
(let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v132 = ?v127 in (let ?v133 = 1 in (let ?v131 = (?v132 - ?v133) in (let ?v188 = (make_closure _?v126 ?v127) in (apply_closure ?v188 ?v131)))))) else ())))
	label: _?v122
	free_list: [?v121]
	args: [?v123, ?v124]
	code: 
(let ?v125 = (Array.create ?v123 ?v121) in (let ?v136 = ?v123 in (let ?v137 = 1 in (let ?v135 = (?v136 - ?v137) in (let ?v134 = (apply_closure _?v126 ?v135) in ?v125)))))
	label: _?v81
	free_list: null
	args: [?v82, ?v83, ?v84, ?v85, ?v86, ?v87]
	code: 
(let ?v119 = ?v82 in (let ?v120 = 1 in (let ?v118 = (?v119 - ?v120) in (apply_closure _?v88 ?v118))))
(let ?v121 = (Array.create 0 0,00) in (let ?v139 = 2 in (let ?v140 = 3 in (let ?v138 = (let ?v189 = (make_closure _?v122 ?v123 ?v124) in (apply_closure ?v189 ?v139 ?v140)) in (let ?v142 = 3 in (let ?v143 = 2 in (let ?v141 = (apply_closure _?v122 ?v142 ?v143) in (let ?v145 = 2 in (let ?v146 = 2 in (let ?v144 = (apply_closure _?v122 ?v145 ?v146) in (let ?v147 = (?v138.(0).(0) <- 1,00) in (let ?v148 = (?v138.(0).(1) <- 2,00) in (let ?v149 = (?v138.(0).(2) <- 3,00) in (let ?v150 = (?v138.(1).(0) <- 4,00) in (let ?v151 = (?v138.(1).(1) <- 5,00) in (let ?v152 = (?v138.(1).(2) <- 6,00) in (let ?v153 = (?v141.(0).(0) <- 7,00) in (let ?v154 = (?v141.(0).(1) <- 8,00) in (let ?v155 = (?v141.(1).(0) <- 9,00) in (let ?v156 = (?v141.(1).(1) <- 10,00) in (let ?v157 = (?v141.(2).(0) <- 11,00) in (let ?v158 = (?v141.(2).(1) <- 12,00) in (let ?v160 = 2 in (let ?v161 = 3 in (let ?v162 = 2 in (let ?v163 = ?v138 in (let ?v164 = ?v141 in (let ?v165 = ?v144 in (let ?v159 = (apply_direct _?v81 ?v160 ?v161 ?v162 ?v163 ?v164 ?v165) in (let ?v168 = ?v144.(0).(0) in (let ?v167 = (truncate ?v168) in (let ?v166 = (print_int ?v167) in (let ?v170 = () in (let ?v169 = (print_newline ?v170) in (let ?v173 = ?v144.(0).(1) in (let ?v172 = (truncate ?v173) in (let ?v171 = (print_int ?v172) in (let ?v175 = () in (let ?v174 = (print_newline ?v175) in (let ?v178 = ?v144.(1).(0) in (let ?v177 = (truncate ?v178) in (let ?v176 = (print_int ?v177) in (let ?v180 = () in (let ?v179 = (print_newline ?v180) in (let ?v183 = ?v144.(1).(1) in (let ?v182 = (truncate ?v183) in (let ?v181 = (print_int ?v182) in (let ?v184 = () in (print_newline ?v184)))))))))))))))))))))))))))))))))))))))))))))))))

------ Translation to Jerry ------
