------ AST ------
(let rec mul l m n a b c = (let rec loop1 i = (if (not (0 <= i)) then () else (let rec loop2 j = (if (not (0 <= j)) then () else (let rec loop3 k = (if (not (0 <= k)) then () else (let ?v0 = (c.(i).(j) <- (c.(i).(j) +. (a.(i).(k) *. b.(k).(j)))) in (loop3 (k - 1)))) in (let ?v1 = (loop3 (m - 1)) in (loop2 (j - 1))))) in (let ?v2 = (loop2 (n - 1)) in (loop1 (i - 1))))) in (loop1 (l - 1))) in (let dummy = (Array.create 0 0,00) in (let rec make m n = (let mat = (Array.create m dummy) in (let rec init i = (if (not (0 <= i)) then () else (let ?v3 = (mat.(i) <- (Array.create n 0,00)) in (init (i - 1)))) in (let ?v4 = (init (m - 1)) in mat))) in (let a = (make 2 3) in (let b = (make 3 2) in (let c = (make 2 2) in (let ?v24 = (a.(0).(0) <- 1,00) in (let ?v23 = (a.(0).(1) <- 2,00) in (let ?v22 = (a.(0).(2) <- 3,00) in (let ?v21 = (a.(1).(0) <- 4,00) in (let ?v20 = (a.(1).(1) <- 5,00) in (let ?v19 = (a.(1).(2) <- 6,00) in (let ?v18 = (b.(0).(0) <- 7,00) in (let ?v17 = (b.(0).(1) <- 8,00) in (let ?v16 = (b.(1).(0) <- 9,00) in (let ?v15 = (b.(1).(1) <- 10,00) in (let ?v14 = (b.(2).(0) <- 11,00) in (let ?v13 = (b.(2).(1) <- 12,00) in (let ?v12 = (mul 2 3 2 a b c) in (let ?v11 = (print_int (truncate c.(0).(0))) in (let ?v10 = (print_newline ()) in (let ?v9 = (print_int (truncate c.(0).(1))) in (let ?v8 = (print_newline ()) in (let ?v7 = (print_int (truncate c.(1).(0))) in (let ?v6 = (print_newline ()) in (let ?v5 = (print_int (truncate c.(1).(1))) in (print_newline ())))))))))))))))))))))))))))

------ K-Normalization ------
(let rec mul l m n a b c = (let rec loop1 i = (let ?v25 = 0 in (let ?v26 = i in (if (?v25 <= ?v26) then (let rec loop2 j = (let ?v27 = 0 in (let ?v28 = j in (if (?v27 <= ?v28) then (let rec loop3 k = (let ?v29 = 0 in (let ?v30 = k in (if (?v29 <= ?v30) then (let ?v0 = (c.(i).(j) <- (c.(i).(j) +. (a.(i).(k) *. b.(k).(j)))) in (let ?v31 = (let ?v32 = k in (let ?v33 = 1 in (?v32 - ?v33))) in (loop3 ?v31))) else ()))) in (let ?v1 = (let ?v34 = (let ?v35 = m in (let ?v36 = 1 in (?v35 - ?v36))) in (loop3 ?v34)) in (let ?v37 = (let ?v38 = j in (let ?v39 = 1 in (?v38 - ?v39))) in (loop2 ?v37)))) else ()))) in (let ?v2 = (let ?v40 = (let ?v41 = n in (let ?v42 = 1 in (?v41 - ?v42))) in (loop2 ?v40)) in (let ?v43 = (let ?v44 = i in (let ?v45 = 1 in (?v44 - ?v45))) in (loop1 ?v43)))) else ()))) in (let ?v46 = (let ?v47 = l in (let ?v48 = 1 in (?v47 - ?v48))) in (loop1 ?v46))) in (let dummy = (Array.create 0 0,00) in (let rec make m n = (let mat = (Array.create m dummy) in (let rec init i = (let ?v49 = 0 in (let ?v50 = i in (if (?v49 <= ?v50) then (let ?v3 = (mat.(i) <- (Array.create n 0,00)) in (let ?v51 = (let ?v52 = i in (let ?v53 = 1 in (?v52 - ?v53))) in (init ?v51))) else ()))) in (let ?v4 = (let ?v54 = (let ?v55 = m in (let ?v56 = 1 in (?v55 - ?v56))) in (init ?v54)) in mat))) in (let a = (let ?v57 = 2 in (let ?v58 = 3 in (make ?v57 ?v58))) in (let b = (let ?v59 = 3 in (let ?v60 = 2 in (make ?v59 ?v60))) in (let c = (let ?v61 = 2 in (let ?v62 = 2 in (make ?v61 ?v62))) in (let ?v24 = (a.(0).(0) <- 1,00) in (let ?v23 = (a.(0).(1) <- 2,00) in (let ?v22 = (a.(0).(2) <- 3,00) in (let ?v21 = (a.(1).(0) <- 4,00) in (let ?v20 = (a.(1).(1) <- 5,00) in (let ?v19 = (a.(1).(2) <- 6,00) in (let ?v18 = (b.(0).(0) <- 7,00) in (let ?v17 = (b.(0).(1) <- 8,00) in (let ?v16 = (b.(1).(0) <- 9,00) in (let ?v15 = (b.(1).(1) <- 10,00) in (let ?v14 = (b.(2).(0) <- 11,00) in (let ?v13 = (b.(2).(1) <- 12,00) in (let ?v12 = (let ?v63 = 2 in (let ?v64 = 3 in (let ?v65 = 2 in (let ?v66 = a in (let ?v67 = b in (let ?v68 = c in (mul ?v63 ?v64 ?v65 ?v66 ?v67 ?v68))))))) in (let ?v11 = (let ?v69 = (let ?v70 = c.(0).(0) in (truncate ?v70)) in (print_int ?v69)) in (let ?v10 = (let ?v71 = () in (print_newline ?v71)) in (let ?v9 = (let ?v72 = (let ?v73 = c.(0).(1) in (truncate ?v73)) in (print_int ?v72)) in (let ?v8 = (let ?v74 = () in (print_newline ?v74)) in (let ?v7 = (let ?v75 = (let ?v76 = c.(1).(0) in (truncate ?v76)) in (print_int ?v75)) in (let ?v6 = (let ?v77 = () in (print_newline ?v77)) in (let ?v5 = (let ?v78 = (let ?v79 = c.(1).(1) in (truncate ?v79)) in (print_int ?v78)) in (let ?v80 = () in (print_newline ?v80))))))))))))))))))))))))))))

------ AlphaConversion ------
(let rec ?v81 ?v82 ?v83 ?v84 ?v85 ?v86 ?v87 = (let rec ?v88 ?v89 = (let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let rec ?v92 ?v93 = (let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let rec ?v96 ?v97 = (let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v101 = (let ?v102 = ?v97 in (let ?v103 = 1 in (?v102 - ?v103))) in (?v96 ?v101))) else ()))) in (let ?v104 = (let ?v105 = (let ?v106 = ?v83 in (let ?v107 = 1 in (?v106 - ?v107))) in (?v96 ?v105)) in (let ?v108 = (let ?v109 = ?v93 in (let ?v110 = 1 in (?v109 - ?v110))) in (?v92 ?v108)))) else ()))) in (let ?v111 = (let ?v112 = (let ?v113 = ?v84 in (let ?v114 = 1 in (?v113 - ?v114))) in (?v92 ?v112)) in (let ?v115 = (let ?v116 = ?v89 in (let ?v117 = 1 in (?v116 - ?v117))) in (?v88 ?v115)))) else ()))) in (let ?v118 = (let ?v119 = ?v82 in (let ?v120 = 1 in (?v119 - ?v120))) in (?v88 ?v118))) in (let ?v121 = (Array.create 0 0,00) in (let rec ?v122 ?v123 ?v124 = (let ?v125 = (Array.create ?v123 ?v121) in (let rec ?v126 ?v127 = (let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v131 = (let ?v132 = ?v127 in (let ?v133 = 1 in (?v132 - ?v133))) in (?v126 ?v131))) else ()))) in (let ?v134 = (let ?v135 = (let ?v136 = ?v123 in (let ?v137 = 1 in (?v136 - ?v137))) in (?v126 ?v135)) in ?v125))) in (let ?v138 = (let ?v139 = 2 in (let ?v140 = 3 in (?v122 ?v139 ?v140))) in (let ?v141 = (let ?v142 = 3 in (let ?v143 = 2 in (?v122 ?v142 ?v143))) in (let ?v144 = (let ?v145 = 2 in (let ?v146 = 2 in (?v122 ?v145 ?v146))) in (let ?v147 = (?v138.(0).(0) <- 1,00) in (let ?v148 = (?v138.(0).(1) <- 2,00) in (let ?v149 = (?v138.(0).(2) <- 3,00) in (let ?v150 = (?v138.(1).(0) <- 4,00) in (let ?v151 = (?v138.(1).(1) <- 5,00) in (let ?v152 = (?v138.(1).(2) <- 6,00) in (let ?v153 = (?v141.(0).(0) <- 7,00) in (let ?v154 = (?v141.(0).(1) <- 8,00) in (let ?v155 = (?v141.(1).(0) <- 9,00) in (let ?v156 = (?v141.(1).(1) <- 10,00) in (let ?v157 = (?v141.(2).(0) <- 11,00) in (let ?v158 = (?v141.(2).(1) <- 12,00) in (let ?v159 = (let ?v160 = 2 in (let ?v161 = 3 in (let ?v162 = 2 in (let ?v163 = ?v138 in (let ?v164 = ?v141 in (let ?v165 = ?v144 in (?v81 ?v160 ?v161 ?v162 ?v163 ?v164 ?v165))))))) in (let ?v166 = (let ?v167 = (let ?v168 = ?v144.(0).(0) in (truncate ?v168)) in (print_int ?v167)) in (let ?v169 = (let ?v170 = () in (print_newline ?v170)) in (let ?v171 = (let ?v172 = (let ?v173 = ?v144.(0).(1) in (truncate ?v173)) in (print_int ?v172)) in (let ?v174 = (let ?v175 = () in (print_newline ?v175)) in (let ?v176 = (let ?v177 = (let ?v178 = ?v144.(1).(0) in (truncate ?v178)) in (print_int ?v177)) in (let ?v179 = (let ?v180 = () in (print_newline ?v180)) in (let ?v181 = (let ?v182 = (let ?v183 = ?v144.(1).(1) in (truncate ?v183)) in (print_int ?v182)) in (let ?v184 = () in (print_newline ?v184))))))))))))))))))))))))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v81 ?v82 ?v83 ?v84 ?v85 ?v86 ?v87 = (let rec ?v88 ?v89 = (let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let rec ?v92 ?v93 = (let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let rec ?v96 ?v97 = (let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v101 = (let ?v102 = ?v97 in (let ?v103 = 1 in (?v102 - ?v103))) in (?v96 ?v101))) else ()))) in (let ?v104 = (let ?v105 = (let ?v106 = ?v83 in (let ?v107 = 1 in (?v106 - ?v107))) in (?v96 ?v105)) in (let ?v108 = (let ?v109 = ?v93 in (let ?v110 = 1 in (?v109 - ?v110))) in (?v92 ?v108)))) else ()))) in (let ?v111 = (let ?v112 = (let ?v113 = ?v84 in (let ?v114 = 1 in (?v113 - ?v114))) in (?v92 ?v112)) in (let ?v115 = (let ?v116 = ?v89 in (let ?v117 = 1 in (?v116 - ?v117))) in (?v88 ?v115)))) else ()))) in (let ?v119 = ?v82 in (let ?v120 = 1 in (let ?v118 = (?v119 - ?v120) in (?v88 ?v118))))) in (let ?v121 = (Array.create 0 0,00) in (let rec ?v122 ?v123 ?v124 = (let ?v125 = (Array.create ?v123 ?v121) in (let rec ?v126 ?v127 = (let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v131 = (let ?v132 = ?v127 in (let ?v133 = 1 in (?v132 - ?v133))) in (?v126 ?v131))) else ()))) in (let ?v136 = ?v123 in (let ?v137 = 1 in (let ?v135 = (?v136 - ?v137) in (let ?v134 = (?v126 ?v135) in ?v125)))))) in (let ?v139 = 2 in (let ?v140 = 3 in (let ?v138 = (?v122 ?v139 ?v140) in (let ?v142 = 3 in (let ?v143 = 2 in (let ?v141 = (?v122 ?v142 ?v143) in (let ?v145 = 2 in (let ?v146 = 2 in (let ?v144 = (?v122 ?v145 ?v146) in (let ?v147 = (?v138.(0).(0) <- 1,00) in (let ?v148 = (?v138.(0).(1) <- 2,00) in (let ?v149 = (?v138.(0).(2) <- 3,00) in (let ?v150 = (?v138.(1).(0) <- 4,00) in (let ?v151 = (?v138.(1).(1) <- 5,00) in (let ?v152 = (?v138.(1).(2) <- 6,00) in (let ?v153 = (?v141.(0).(0) <- 7,00) in (let ?v154 = (?v141.(0).(1) <- 8,00) in (let ?v155 = (?v141.(1).(0) <- 9,00) in (let ?v156 = (?v141.(1).(1) <- 10,00) in (let ?v157 = (?v141.(2).(0) <- 11,00) in (let ?v158 = (?v141.(2).(1) <- 12,00) in (let ?v160 = 2 in (let ?v161 = 3 in (let ?v162 = 2 in (let ?v163 = ?v138 in (let ?v164 = ?v141 in (let ?v165 = ?v144 in (let ?v159 = (?v81 ?v160 ?v161 ?v162 ?v163 ?v164 ?v165) in (let ?v168 = ?v144.(0).(0) in (let ?v167 = (truncate ?v168) in (let ?v166 = (print_int ?v167) in (let ?v170 = () in (let ?v169 = (print_newline ?v170) in (let ?v173 = ?v144.(0).(1) in (let ?v172 = (truncate ?v173) in (let ?v171 = (print_int ?v172) in (let ?v175 = () in (let ?v174 = (print_newline ?v175) in (let ?v178 = ?v144.(1).(0) in (let ?v177 = (truncate ?v178) in (let ?v176 = (print_int ?v177) in (let ?v180 = () in (let ?v179 = (print_newline ?v180) in (let ?v183 = ?v144.(1).(1) in (let ?v182 = (truncate ?v183) in (let ?v181 = (print_int ?v182) in (let ?v184 = () in (print_newline ?v184)))))))))))))))))))))))))))))))))))))))))))))))))))

------ ClosureConversion ------
Closure list: 
closure numbers: 1
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v101 = (let ?v102 = ?v97 in (let ?v103 = 1 in (?v102 - ?v103))) in (apply_closure _?v96 ?v101))) else ())))
Closure list: 
closure numbers: 2
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v101 = (let ?v102 = ?v97 in (let ?v103 = 1 in (?v102 - ?v103))) in (apply_closure _?v96 ?v101))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v104 = (let ?v105 = (let ?v106 = ?v83 in (let ?v107 = 1 in (?v106 - ?v107))) in (apply_closure _?v96 ?v105)) in (let ?v108 = (let ?v109 = ?v93 in (let ?v110 = 1 in (?v109 - ?v110))) in (apply_closure _?v92 ?v108))) else ())))
Closure list: 
closure numbers: 3
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v101 = (let ?v102 = ?v97 in (let ?v103 = 1 in (?v102 - ?v103))) in (apply_closure _?v96 ?v101))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v104 = (let ?v105 = (let ?v106 = ?v83 in (let ?v107 = 1 in (?v106 - ?v107))) in (apply_closure _?v96 ?v105)) in (let ?v108 = (let ?v109 = ?v93 in (let ?v110 = 1 in (?v109 - ?v110))) in (apply_closure _?v92 ?v108))) else ())))
	label: _?v88
	free_list: [?v87, ?v85, ?v86, ?v83, ?v84]
	args: [?v89]
	code: 
(let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let ?v111 = (let ?v112 = (let ?v113 = ?v84 in (let ?v114 = 1 in (?v113 - ?v114))) in (apply_closure _?v92 ?v112)) in (let ?v115 = (let ?v116 = ?v89 in (let ?v117 = 1 in (?v116 - ?v117))) in (apply_closure _?v88 ?v115))) else ())))
Closure list: 
closure numbers: 4
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v101 = (let ?v102 = ?v97 in (let ?v103 = 1 in (?v102 - ?v103))) in (apply_closure _?v96 ?v101))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v104 = (let ?v105 = (let ?v106 = ?v83 in (let ?v107 = 1 in (?v106 - ?v107))) in (apply_closure _?v96 ?v105)) in (let ?v108 = (let ?v109 = ?v93 in (let ?v110 = 1 in (?v109 - ?v110))) in (apply_closure _?v92 ?v108))) else ())))
	label: _?v88
	free_list: [?v87, ?v85, ?v86, ?v83, ?v84]
	args: [?v89]
	code: 
(let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let ?v111 = (let ?v112 = (let ?v113 = ?v84 in (let ?v114 = 1 in (?v113 - ?v114))) in (apply_closure _?v92 ?v112)) in (let ?v115 = (let ?v116 = ?v89 in (let ?v117 = 1 in (?v116 - ?v117))) in (apply_closure _?v88 ?v115))) else ())))
	label: _?v126
	free_list: [?v124, ?v125]
	args: [?v127]
	code: 
(let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v131 = (let ?v132 = ?v127 in (let ?v133 = 1 in (?v132 - ?v133))) in (apply_closure _?v126 ?v131))) else ())))
Closure list: 
closure numbers: 5
	label: _?v96
	free_list: [?v89, ?v87, ?v85, ?v86, ?v93]
	args: [?v97]
	code: 
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v101 = (let ?v102 = ?v97 in (let ?v103 = 1 in (?v102 - ?v103))) in (apply_closure _?v96 ?v101))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v104 = (let ?v105 = (let ?v106 = ?v83 in (let ?v107 = 1 in (?v106 - ?v107))) in (apply_closure _?v96 ?v105)) in (let ?v108 = (let ?v109 = ?v93 in (let ?v110 = 1 in (?v109 - ?v110))) in (apply_closure _?v92 ?v108))) else ())))
	label: _?v88
	free_list: [?v87, ?v85, ?v86, ?v83, ?v84]
	args: [?v89]
	code: 
(let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let ?v111 = (let ?v112 = (let ?v113 = ?v84 in (let ?v114 = 1 in (?v113 - ?v114))) in (apply_closure _?v92 ?v112)) in (let ?v115 = (let ?v116 = ?v89 in (let ?v117 = 1 in (?v116 - ?v117))) in (apply_closure _?v88 ?v115))) else ())))
	label: _?v126
	free_list: [?v124, ?v125]
	args: [?v127]
	code: 
(let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v131 = (let ?v132 = ?v127 in (let ?v133 = 1 in (?v132 - ?v133))) in (apply_closure _?v126 ?v131))) else ())))
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
(let ?v98 = 0 in (let ?v99 = ?v97 in (if (?v98 <= ?v99) then (let ?v100 = (?v87.(?v89).(?v93) <- (?v87.(?v89).(?v93) +. (?v85.(?v89).(?v97) *. ?v86.(?v97).(?v93)))) in (let ?v101 = (let ?v102 = ?v97 in (let ?v103 = 1 in (?v102 - ?v103))) in (apply_closure _?v96 ?v101))) else ())))
	label: _?v92
	free_list: [?v89, ?v87, ?v85, ?v86, ?v83]
	args: [?v93]
	code: 
(let ?v94 = 0 in (let ?v95 = ?v93 in (if (?v94 <= ?v95) then (let ?v104 = (let ?v105 = (let ?v106 = ?v83 in (let ?v107 = 1 in (?v106 - ?v107))) in (apply_closure _?v96 ?v105)) in (let ?v108 = (let ?v109 = ?v93 in (let ?v110 = 1 in (?v109 - ?v110))) in (apply_closure _?v92 ?v108))) else ())))
	label: _?v88
	free_list: [?v87, ?v85, ?v86, ?v83, ?v84]
	args: [?v89]
	code: 
(let ?v90 = 0 in (let ?v91 = ?v89 in (if (?v90 <= ?v91) then (let ?v111 = (let ?v112 = (let ?v113 = ?v84 in (let ?v114 = 1 in (?v113 - ?v114))) in (apply_closure _?v92 ?v112)) in (let ?v115 = (let ?v116 = ?v89 in (let ?v117 = 1 in (?v116 - ?v117))) in (apply_closure _?v88 ?v115))) else ())))
	label: _?v126
	free_list: [?v124, ?v125]
	args: [?v127]
	code: 
(let ?v128 = 0 in (let ?v129 = ?v127 in (if (?v128 <= ?v129) then (let ?v130 = (?v125.(?v127) <- (Array.create ?v124 0,00)) in (let ?v131 = (let ?v132 = ?v127 in (let ?v133 = 1 in (?v132 - ?v133))) in (apply_closure _?v126 ?v131))) else ())))
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
(let ?v121 = (Array.create 0 0,00) in (let ?v139 = 2 in (let ?v140 = 3 in (let ?v138 = (apply_closure _?v122 ?v139 ?v140) in (let ?v142 = 3 in (let ?v143 = 2 in (let ?v141 = (apply_closure _?v122 ?v142 ?v143) in (let ?v145 = 2 in (let ?v146 = 2 in (let ?v144 = (apply_closure _?v122 ?v145 ?v146) in (let ?v147 = (?v138.(0).(0) <- 1,00) in (let ?v148 = (?v138.(0).(1) <- 2,00) in (let ?v149 = (?v138.(0).(2) <- 3,00) in (let ?v150 = (?v138.(1).(0) <- 4,00) in (let ?v151 = (?v138.(1).(1) <- 5,00) in (let ?v152 = (?v138.(1).(2) <- 6,00) in (let ?v153 = (?v141.(0).(0) <- 7,00) in (let ?v154 = (?v141.(0).(1) <- 8,00) in (let ?v155 = (?v141.(1).(0) <- 9,00) in (let ?v156 = (?v141.(1).(1) <- 10,00) in (let ?v157 = (?v141.(2).(0) <- 11,00) in (let ?v158 = (?v141.(2).(1) <- 12,00) in (let ?v160 = 2 in (let ?v161 = 3 in (let ?v162 = 2 in (let ?v163 = ?v138 in (let ?v164 = ?v141 in (let ?v165 = ?v144 in (let ?v159 = (apply_direct _?v81 ?v160 ?v161 ?v162 ?v163 ?v164 ?v165) in (let ?v168 = ?v144.(0).(0) in (let ?v167 = (truncate ?v168) in (let ?v166 = (print_int ?v167) in (let ?v170 = () in (let ?v169 = (print_newline ?v170) in (let ?v173 = ?v144.(0).(1) in (let ?v172 = (truncate ?v173) in (let ?v171 = (print_int ?v172) in (let ?v175 = () in (let ?v174 = (print_newline ?v175) in (let ?v178 = ?v144.(1).(0) in (let ?v177 = (truncate ?v178) in (let ?v176 = (print_int ?v177) in (let ?v180 = () in (let ?v179 = (print_newline ?v180) in (let ?v183 = ?v144.(1).(1) in (let ?v182 = (truncate ?v183) in (let ?v181 = (print_int ?v182) in (let ?v184 = () in (print_newline ?v184)))))))))))))))))))))))))))))))))))))))))))))))))

------ Translation to Jerry ------
******** main *********
NOTHING	tmpVar17 (backend.variables.VInteger@3f99bd52)
ASSIGN	?v139 (backend.variables.VInteger@2c7b84de) 2
NOTHING	tmpVar18 (backend.variables.VInteger@4f023edb)
ASSIGN	?v140 (backend.variables.VInteger@3fee733d) 3
NOTHING	?v139 (backend.variables.VInteger@2c7b84de)
NOTHING	?v140 (backend.variables.VInteger@3fee733d)
ASSIGN	?v138 (backend.variables.Variable@5acf9800) backend.instructions.InstructionCALL@3a71f4dd
NOTHING	tmpVar19 (backend.variables.VInteger@7adf9f5f)
ASSIGN	?v142 (backend.variables.VInteger@4617c264) 3
NOTHING	tmpVar20 (backend.variables.VInteger@85ede7b)
ASSIGN	?v143 (backend.variables.VInteger@36baf30c) 2
NOTHING	?v142 (backend.variables.VInteger@4617c264)
NOTHING	?v143 (backend.variables.VInteger@36baf30c)
ASSIGN	?v141 (backend.variables.Variable@7a81197d) backend.instructions.InstructionCALL@5674cd4d
NOTHING	tmpVar21 (backend.variables.VInteger@63961c42)
ASSIGN	?v145 (backend.variables.VInteger@5ca881b5) 2
NOTHING	tmpVar22 (backend.variables.VInteger@65b54208)
ASSIGN	?v146 (backend.variables.VInteger@24d46ca6) 2
NOTHING	?v145 (backend.variables.VInteger@5ca881b5)
NOTHING	?v146 (backend.variables.VInteger@24d46ca6)
ASSIGN	?v144 (backend.variables.Variable@4517d9a3) backend.instructions.InstructionCALL@1be6f5c3
NOTHING	tmpVar23 (backend.variables.VInteger@6b884d57)
ASSIGN	?v160 (backend.variables.VInteger@372f7a8d) 2
NOTHING	tmpVar24 (backend.variables.VInteger@38af3868)
ASSIGN	?v161 (backend.variables.VInteger@2f92e0f4) 3
NOTHING	tmpVar25 (backend.variables.VInteger@77459877)
ASSIGN	?v162 (backend.variables.VInteger@28a418fc) 2
NOTHING	?v138 (backend.variables.Variable@5acf9800)
ASSIGN	?v163 (backend.variables.Variable@5305068a) backend.variables.Variable@5acf9800
NOTHING	?v141 (backend.variables.Variable@7a81197d)
ASSIGN	?v164 (backend.variables.Variable@1f32e575) backend.variables.Variable@7a81197d
NOTHING	?v144 (backend.variables.Variable@4517d9a3)
ASSIGN	?v165 (backend.variables.Variable@279f2327) backend.variables.Variable@4517d9a3
NOTHING	?v160 (backend.variables.VInteger@372f7a8d)
NOTHING	?v161 (backend.variables.VInteger@2f92e0f4)
NOTHING	?v162 (backend.variables.VInteger@28a418fc)
NOTHING	?v163 (backend.variables.Variable@5305068a)
NOTHING	?v164 (backend.variables.Variable@1f32e575)
NOTHING	?v165 (backend.variables.Variable@279f2327)
ASSIGN	?v159 (backend.variables.Variable@2ff4acd0) backend.instructions.InstructionCALL@5b2133b1
NOTHING	null
ASSIGN	?v167 (backend.variables.Variable@54bedef2) backend.instructions.InstructionCALL@72ea2f77
NOTHING	?v167 (backend.variables.Variable@54bedef2)
ASSIGN	?v166 (backend.variables.Variable@5caf905d) backend.instructions.InstructionCALL@33c7353a
NOTHING	null
ASSIGN	?v169 (backend.variables.Variable@27716f4) backend.instructions.InstructionCALL@681a9515
NOTHING	null
ASSIGN	?v172 (backend.variables.Variable@8efb846) backend.instructions.InstructionCALL@3af49f1c
NOTHING	?v172 (backend.variables.Variable@8efb846)
ASSIGN	?v171 (backend.variables.Variable@2a84aee7) backend.instructions.InstructionCALL@19469ea2
NOTHING	null
ASSIGN	?v174 (backend.variables.Variable@a09ee92) backend.instructions.InstructionCALL@13221655
NOTHING	null
ASSIGN	?v177 (backend.variables.Variable@30f39991) backend.instructions.InstructionCALL@2f2c9b19
NOTHING	?v177 (backend.variables.Variable@30f39991)
ASSIGN	?v176 (backend.variables.Variable@452b3a41) backend.instructions.InstructionCALL@31befd9f
NOTHING	null
ASSIGN	?v179 (backend.variables.Variable@4a574795) backend.instructions.InstructionCALL@1c20c684
NOTHING	null
ASSIGN	?v182 (backend.variables.Variable@f6f4d33) backend.instructions.InstructionCALL@1fb3ebeb
NOTHING	?v182 (backend.variables.Variable@f6f4d33)
ASSIGN	?v181 (backend.variables.Variable@23fc625e) backend.instructions.InstructionCALL@548c4f57
NOTHING	null
CALL	label22 null 

******** label6 *********
NOTHING	tmpVar5 (backend.variables.VInteger@1218025c)
ASSIGN	?v98 (backend.variables.VInteger@266474c2) 0
NOTHING	?v97 (backend.variables.Variable@816f27d)
ASSIGN	?v99 (backend.variables.Variable@6f94fa3e) backend.variables.Variable@816f27d
IF	backend.variables.VBoolean@87aac27 THEN label7 ELSE label8

******** label7 *********
NOTHING	?v97 (backend.variables.Variable@816f27d)
ASSIGN	?v102 (backend.variables.Variable@5e481248) backend.variables.Variable@816f27d
NOTHING	tmpVar7 (backend.variables.VInteger@3e3abc88)
ASSIGN	?v103 (backend.variables.VInteger@66d3c617) 1
SUB	backend.variables.Variable@5e481248 backend.variables.VInteger@66d3c617
NOTHING	null
CALL	label6 null 

******** label8 *********


******** label4 *********
NOTHING	tmpVar3 (backend.variables.VInteger@6ce253f1)
ASSIGN	?v94 (backend.variables.VInteger@5e2de80c) 0
NOTHING	?v93 (backend.variables.Variable@53d8d10a)
ASSIGN	?v95 (backend.variables.Variable@1d44bcfa) backend.variables.Variable@53d8d10a
IF	backend.variables.VBoolean@e9e54c2 THEN label5 ELSE label9

******** label5 *********
NOTHING	null
ASSIGN	?v106 (backend.variables.Variable@63947c6b) null
NOTHING	tmpVar8 (backend.variables.VInteger@65ab7765)
ASSIGN	?v107 (backend.variables.VInteger@2b193f2d) 1
SUB	backend.variables.Variable@63947c6b backend.variables.VInteger@2b193f2d
NOTHING	null
CALL	label6 null 
NOTHING	?v93 (backend.variables.Variable@53d8d10a)
ASSIGN	?v109 (backend.variables.Variable@355da254) backend.variables.Variable@53d8d10a
NOTHING	tmpVar9 (backend.variables.VInteger@1b28cdfa)
ASSIGN	?v110 (backend.variables.VInteger@4dc63996) 1
SUB	backend.variables.Variable@355da254 backend.variables.VInteger@4dc63996
NOTHING	null
CALL	label4 null 

******** label9 *********


******** label2 *********
NOTHING	tmpVar1 (backend.variables.VInteger@eed1f14)
ASSIGN	?v90 (backend.variables.VInteger@511d50c0) 0
NOTHING	?v89 (backend.variables.Variable@7229724f)
ASSIGN	?v91 (backend.variables.Variable@60e53b93) backend.variables.Variable@7229724f
IF	backend.variables.VBoolean@4c873330 THEN label3 ELSE label10

******** label3 *********
NOTHING	null
ASSIGN	?v113 (backend.variables.Variable@d716361) null
NOTHING	tmpVar10 (backend.variables.VInteger@119d7047)
ASSIGN	?v114 (backend.variables.VInteger@6ff3c5b5) 1
SUB	backend.variables.Variable@d716361 backend.variables.VInteger@6ff3c5b5
NOTHING	null
CALL	label4 null 
NOTHING	?v89 (backend.variables.Variable@7229724f)
ASSIGN	?v116 (backend.variables.Variable@3764951d) backend.variables.Variable@7229724f
NOTHING	tmpVar11 (backend.variables.VInteger@776ec8df)
ASSIGN	?v117 (backend.variables.VInteger@4b1210ee) 1
SUB	backend.variables.Variable@3764951d backend.variables.VInteger@4b1210ee
NOTHING	null
CALL	label2 null 

******** label10 *********


******** label1 *********
NOTHING	?v82 (backend.variables.Variable@4eec7777)
ASSIGN	?v119 (backend.variables.Variable@4d7e1886) backend.variables.Variable@4eec7777
NOTHING	tmpVar12 (backend.variables.VInteger@3b07d329)
ASSIGN	?v120 (backend.variables.VInteger@3cd1a2f1) 1
SUB	backend.variables.Variable@4d7e1886 backend.variables.VInteger@3cd1a2f1
ASSIGN	?v118 (backend.variables.VInteger@2f0e140b) backend.instructions.InstructionSUB@41629346
NOTHING	?v118 (backend.variables.VInteger@2f0e140b)
CALL	label2 backend.variables.VInteger@2f0e140b 

******** label12 *********
NOTHING	tmpVar13 (backend.variables.VInteger@404b9385)
ASSIGN	?v128 (backend.variables.VInteger@7440e464) 0
NOTHING	?v127 (backend.variables.Variable@6d311334)
ASSIGN	?v129 (backend.variables.Variable@49476842) backend.variables.Variable@6d311334
IF	backend.variables.VBoolean@682a0b20 THEN label13 ELSE label14

******** label13 *********
NOTHING	?v127 (backend.variables.Variable@6d311334)
ASSIGN	?v132 (backend.variables.Variable@78308db1) backend.variables.Variable@6d311334
NOTHING	tmpVar15 (backend.variables.VInteger@3d075dc0)
ASSIGN	?v133 (backend.variables.VInteger@27c170f0) 1
SUB	backend.variables.Variable@78308db1 backend.variables.VInteger@27c170f0
NOTHING	null
CALL	label12 null 

******** label14 *********


******** label11 *********
NOTHING	?v123 (backend.variables.Variable@214c265e)
ASSIGN	?v136 (backend.variables.Variable@5451c3a8) backend.variables.Variable@214c265e
NOTHING	tmpVar16 (backend.variables.VInteger@448139f0)
ASSIGN	?v137 (backend.variables.VInteger@2626b418) 1
SUB	backend.variables.Variable@5451c3a8 backend.variables.VInteger@2626b418
ASSIGN	?v135 (backend.variables.VInteger@5a07e868) backend.instructions.InstructionSUB@7cca494b
NOTHING	?v135 (backend.variables.VInteger@5a07e868)
ASSIGN	?v134 (backend.variables.Variable@76ed5528) backend.instructions.InstructionCALL@7ba4f24f
NOTHING	null


------ Register Allocation ------
***main : Variables state***
Var/Arg ?v179 (backend.variables.Variable@4a574795)  : r5/-
Var/Arg ?v169 (backend.variables.Variable@27716f4)  : r8/-
Var/Arg ?v164 (backend.variables.Variable@1f32e575)  : r9/[fp + 12]
Var/Arg ?v167 (backend.variables.Variable@54bedef2)  : r5/r2
Var/Arg ?v138 (backend.variables.Variable@5acf9800)  : r6/-
Var/Arg ?v163 (backend.variables.Variable@5305068a)  : r7/[fp + 8]
Var/Arg ?v140 (backend.variables.VInteger@3fee733d)  : r5/r3
Var/Arg ?v162 (backend.variables.VInteger@28a418fc)  : r8/[fp + 4]
Var/Arg ?v174 (backend.variables.Variable@a09ee92)  : r11/-
Var/Arg ?v182 (backend.variables.Variable@f6f4d33)  : r6/-
Var/Arg ?v145 (backend.variables.VInteger@5ca881b5)  : r10/r2
Var/Arg ?v160 (backend.variables.VInteger@372f7a8d)  : r4/r2
Var/Arg ?v177 (backend.variables.Variable@30f39991)  : r4/-
Var/Arg ?v181 (backend.variables.Variable@23fc625e)  : r8/-
Var/Arg ?v171 (backend.variables.Variable@2a84aee7)  : r9/-
Var/Arg ?v159 (backend.variables.Variable@2ff4acd0)  : r10/-
Var/Arg ?v139 (backend.variables.VInteger@2c7b84de)  : r4/r2
Var/Arg ?v161 (backend.variables.VInteger@2f92e0f4)  : r6/r3
Var/Arg ?v172 (backend.variables.Variable@8efb846)  : r7/r3
Var/Arg ?v176 (backend.variables.Variable@452b3a41)  : r10/-
Var/Arg ?v146 (backend.variables.VInteger@24d46ca6)  : r11/r3
Var/Arg ?v166 (backend.variables.Variable@5caf905d)  : r6/-
Var/Arg ?v142 (backend.variables.VInteger@4617c264)  : r7/r2
Var/Arg ?v144 (backend.variables.Variable@4517d9a3)  : r5/-
Var/Arg ?v143 (backend.variables.VInteger@36baf30c)  : r8/r3
Var/Arg ?v165 (backend.variables.Variable@279f2327)  : r11/[fp + 16]
Var/Arg ?v141 (backend.variables.Variable@7a81197d)  : r9/-
***main : Arguments state***

***label6 : Variables state***
Var/Arg ?v102 (backend.variables.Variable@5e481248)  : r6/-
Var/Arg ?v103 (backend.variables.VInteger@66d3c617)  : r7/-
Var/Arg ?v98 (backend.variables.VInteger@266474c2)  : r4/-
Var/Arg ?v99 (backend.variables.Variable@6f94fa3e)  : r5/-
***label6 : Arguments state***
Var/Arg ?v97 (backend.variables.Variable@816f27d)  : -/r2

***label4 : Variables state***
Var/Arg ?v110 (backend.variables.VInteger@4dc63996)  : r9/-
Var/Arg ?v94 (backend.variables.VInteger@5e2de80c)  : r4/-
Var/Arg ?v107 (backend.variables.VInteger@2b193f2d)  : r7/-
Var/Arg ?v109 (backend.variables.Variable@355da254)  : r8/-
Var/Arg ?v95 (backend.variables.Variable@1d44bcfa)  : r5/-
Var/Arg ?v106 (backend.variables.Variable@63947c6b)  : r6/-
***label4 : Arguments state***
Var/Arg ?v93 (backend.variables.Variable@53d8d10a)  : -/r2

***label2 : Variables state***
Var/Arg ?v113 (backend.variables.Variable@d716361)  : r6/-
Var/Arg ?v91 (backend.variables.Variable@60e53b93)  : r5/-
Var/Arg ?v114 (backend.variables.VInteger@6ff3c5b5)  : r7/-
Var/Arg ?v116 (backend.variables.Variable@3764951d)  : r8/-
Var/Arg ?v117 (backend.variables.VInteger@4b1210ee)  : r9/-
Var/Arg ?v90 (backend.variables.VInteger@511d50c0)  : r4/-
***label2 : Arguments state***
Var/Arg ?v89 (backend.variables.Variable@7229724f)  : -/r2

***label1 : Variables state***
Var/Arg ?v120 (backend.variables.VInteger@3cd1a2f1)  : r5/-
Var/Arg ?v118 (backend.variables.VInteger@2f0e140b)  : r6/r2
Var/Arg ?v119 (backend.variables.Variable@4d7e1886)  : r4/-
***label1 : Arguments state***
Var/Arg ?v82 (backend.variables.Variable@4eec7777)  : -/r3
Var/Arg ?v83 (backend.variables.Variable@3b9a45b3)  : -/r2
Var/Arg ?v84 (backend.variables.Variable@7699a589)  : -/[fp + 4]
Var/Arg ?v85 (backend.variables.Variable@58372a00)  : -/[fp + 8]
Var/Arg ?v86 (backend.variables.Variable@4dd8dc3)  : -/[fp + 12]
Var/Arg ?v87 (backend.variables.Variable@6d03e736)  : -/[fp + 16]

***label12 : Variables state***
Var/Arg ?v132 (backend.variables.Variable@78308db1)  : r6/-
Var/Arg ?v133 (backend.variables.VInteger@27c170f0)  : r7/-
Var/Arg ?v128 (backend.variables.VInteger@7440e464)  : r4/-
Var/Arg ?v129 (backend.variables.Variable@49476842)  : r5/-
***label12 : Arguments state***
Var/Arg ?v127 (backend.variables.Variable@6d311334)  : -/r2

***label11 : Variables state***
Var/Arg ?v134 (backend.variables.Variable@76ed5528)  : r7/-
Var/Arg ?v136 (backend.variables.Variable@5451c3a8)  : r4/-
Var/Arg ?v137 (backend.variables.VInteger@2626b418)  : r5/-
Var/Arg ?v135 (backend.variables.VInteger@5a07e868)  : r6/r2
***label11 : Arguments state***
Var/Arg ?v123 (backend.variables.Variable@214c265e)  : -/r3
Var/Arg ?v124 (backend.variables.Variable@568db2f2)  : -/r2


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

	SUB sp, #72
	LDR r4, =2
	LDR r5, =3
	LDR r7, =3
	LDR r8, =2
	LDR r10, =2
	LDR r11, =2
	LDR r4, =2
	LDR r6, =3
	LDR r8, =2
	MOV r7, r6
	MOV r9, r9
	MOV r11, r5
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label22
	LDMFD sp!, {r4-r12}
	ADD sp, #72

	@MAIN EPILOGUE
	ADD sp, #4
	MOV sp, fp
	LDR fp, [sp]
	ADD sp, #4

	MOV r7, #1
	swi 0
_label6:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	LDR r4, =0
	MOV r5, r2
	CMP r4 , r5
	BLE label7
	B label8
label7:
	MOV r6, r2
	LDR r0, =1
	LDR r7, =1
	SUB r0, r6, r7
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label6
	LDMFD sp!, {r4-r12}
	b cont1
label8:
	b cont1
cont1:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label4:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	LDR r4, =0
	MOV r5, r2
	CMP r4 , r5
	BLE label5
	B label9
label5:
	LDR r0, =1
	LDR r7, =1
	SUB r0, r6, r7
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label6
	LDMFD sp!, {r4-r12}
	MOV r8, r2
	LDR r0, =1
	LDR r9, =1
	SUB r0, r8, r9
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label4
	LDMFD sp!, {r4-r12}
	b cont2
label9:
	b cont2
cont2:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label2:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	LDR r4, =0
	MOV r5, r2
	CMP r4 , r5
	BLE label3
	B label10
label3:
	LDR r0, =1
	LDR r7, =1
	SUB r0, r6, r7
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label4
	LDMFD sp!, {r4-r12}
	MOV r8, r2
	LDR r0, =1
	LDR r9, =1
	SUB r0, r8, r9
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label2
	LDMFD sp!, {r4-r12}
	b cont3
label10:
	b cont3
cont3:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label1:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r3
	LDR r5, =1
	SUB r0, r4, r5
	MOV r6, r0
	STMFD sp!,{r4-r12}
	MOV r2, r6
	BL _label2
	LDMFD sp!, {r4-r12}

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label12:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	LDR r4, =0
	MOV r5, r2
	CMP r4 , r5
	BLE label13
	B label14
label13:
	MOV r6, r2
	LDR r0, =1
	LDR r7, =1
	SUB r0, r6, r7
	STMFD sp!,{r4-r12}
	MOV r2, r0
	BL _label12
	LDMFD sp!, {r4-r12}
	b cont4
label14:
	b cont4
cont4:

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr

_label11:
	@FUNCTION PROLOGUE
	STMFD sp!, {fp, lr}
	ADD fp, sp, #4

	MOV r4, r3
	LDR r5, =1
	SUB r0, r4, r5
	MOV r6, r0

	@FUNCTION EPILOGUE
	SUB sp, fp, #4
	LDMFD sp!, {fp, lr}
	BX lr


