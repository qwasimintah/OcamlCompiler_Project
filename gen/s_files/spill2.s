------ AST ------
(let rec f ?v0 = 12345 in (let rec g y = (y + 1) in (let z = (Array.create 10 1) in (let x = (f ()) in (let y = 67890 in (let z0 = z.(0) in (let z1 = (z0 + z0) in (let z2 = (z1 + z1) in (let z3 = (z2 + z2) in (let z4 = (z3 + z3) in (let z5 = (z4 + z4) in (let z6 = (z5 + z5) in (let z7 = (z6 + z6) in (let z8 = (z7 + z7) in (let z9 = (z8 + z8) in (let z10 = (z9 + z9) in (let z11 = (z10 + z10) in (let z12 = (z11 + z11) in (let z13 = (z12 + z12) in (let z14 = (z13 + z13) in (let z15 = (z14 + z14) in (print_int (if (z.(1) = 0) then (g y) else ((((((((((((((((z0 + z1) + z2) + z3) + z4) + z5) + z6) + z7) + z8) + z9) + z10) + z11) + z12) + z13) + z14) + z15) + x))))))))))))))))))))))))

------ K-Normalization ------
(let rec f ?v0 = 12345 in (let rec g y = (let ?v1 = y in (let ?v2 = 1 in (?v1 + ?v2))) in (let z = (Array.create 10 1) in (let x = (let ?v3 = () in (f ?v3)) in (let y = 67890 in (let z0 = z.(0) in (let z1 = (let ?v4 = z0 in (let ?v5 = z0 in (?v4 + ?v5))) in (let z2 = (let ?v6 = z1 in (let ?v7 = z1 in (?v6 + ?v7))) in (let z3 = (let ?v8 = z2 in (let ?v9 = z2 in (?v8 + ?v9))) in (let z4 = (let ?v10 = z3 in (let ?v11 = z3 in (?v10 + ?v11))) in (let z5 = (let ?v12 = z4 in (let ?v13 = z4 in (?v12 + ?v13))) in (let z6 = (let ?v14 = z5 in (let ?v15 = z5 in (?v14 + ?v15))) in (let z7 = (let ?v16 = z6 in (let ?v17 = z6 in (?v16 + ?v17))) in (let z8 = (let ?v18 = z7 in (let ?v19 = z7 in (?v18 + ?v19))) in (let z9 = (let ?v20 = z8 in (let ?v21 = z8 in (?v20 + ?v21))) in (let z10 = (let ?v22 = z9 in (let ?v23 = z9 in (?v22 + ?v23))) in (let z11 = (let ?v24 = z10 in (let ?v25 = z10 in (?v24 + ?v25))) in (let z12 = (let ?v26 = z11 in (let ?v27 = z11 in (?v26 + ?v27))) in (let z13 = (let ?v28 = z12 in (let ?v29 = z12 in (?v28 + ?v29))) in (let z14 = (let ?v30 = z13 in (let ?v31 = z13 in (?v30 + ?v31))) in (let z15 = (let ?v32 = z14 in (let ?v33 = z14 in (?v32 + ?v33))) in (let ?v34 = (let ?v35 = z.(1) in (let ?v36 = 0 in (if (?v35 = ?v36) then (let ?v37 = y in (g ?v37)) else (let ?v38 = (let ?v40 = (let ?v42 = (let ?v44 = (let ?v46 = (let ?v48 = (let ?v50 = (let ?v52 = (let ?v54 = (let ?v56 = (let ?v58 = (let ?v60 = (let ?v62 = (let ?v64 = (let ?v66 = (let ?v68 = z0 in (let ?v69 = z1 in (?v68 + ?v69))) in (let ?v67 = z2 in (?v66 + ?v67))) in (let ?v65 = z3 in (?v64 + ?v65))) in (let ?v63 = z4 in (?v62 + ?v63))) in (let ?v61 = z5 in (?v60 + ?v61))) in (let ?v59 = z6 in (?v58 + ?v59))) in (let ?v57 = z7 in (?v56 + ?v57))) in (let ?v55 = z8 in (?v54 + ?v55))) in (let ?v53 = z9 in (?v52 + ?v53))) in (let ?v51 = z10 in (?v50 + ?v51))) in (let ?v49 = z11 in (?v48 + ?v49))) in (let ?v47 = z12 in (?v46 + ?v47))) in (let ?v45 = z13 in (?v44 + ?v45))) in (let ?v43 = z14 in (?v42 + ?v43))) in (let ?v41 = z15 in (?v40 + ?v41))) in (let ?v39 = x in (?v38 + ?v39)))))) in (print_int ?v34)))))))))))))))))))))))

------ AlphaConversion ------
(let rec ?v70 ?v71 = 12345 in (let rec ?v72 ?v73 = (let ?v74 = ?v73 in (let ?v75 = 1 in (?v74 + ?v75))) in (let ?v76 = (Array.create 10 1) in (let ?v77 = (let ?v78 = () in (?v70 ?v78)) in (let ?v79 = 67890 in (let ?v80 = ?v76.(0) in (let ?v81 = (let ?v82 = ?v80 in (let ?v83 = ?v80 in (?v82 + ?v83))) in (let ?v84 = (let ?v85 = ?v81 in (let ?v86 = ?v81 in (?v85 + ?v86))) in (let ?v87 = (let ?v88 = ?v84 in (let ?v89 = ?v84 in (?v88 + ?v89))) in (let ?v90 = (let ?v91 = ?v87 in (let ?v92 = ?v87 in (?v91 + ?v92))) in (let ?v93 = (let ?v94 = ?v90 in (let ?v95 = ?v90 in (?v94 + ?v95))) in (let ?v96 = (let ?v97 = ?v93 in (let ?v98 = ?v93 in (?v97 + ?v98))) in (let ?v99 = (let ?v100 = ?v96 in (let ?v101 = ?v96 in (?v100 + ?v101))) in (let ?v102 = (let ?v103 = ?v99 in (let ?v104 = ?v99 in (?v103 + ?v104))) in (let ?v105 = (let ?v106 = ?v102 in (let ?v107 = ?v102 in (?v106 + ?v107))) in (let ?v108 = (let ?v109 = ?v105 in (let ?v110 = ?v105 in (?v109 + ?v110))) in (let ?v111 = (let ?v112 = ?v108 in (let ?v113 = ?v108 in (?v112 + ?v113))) in (let ?v114 = (let ?v115 = ?v111 in (let ?v116 = ?v111 in (?v115 + ?v116))) in (let ?v117 = (let ?v118 = ?v114 in (let ?v119 = ?v114 in (?v118 + ?v119))) in (let ?v120 = (let ?v121 = ?v117 in (let ?v122 = ?v117 in (?v121 + ?v122))) in (let ?v123 = (let ?v124 = ?v120 in (let ?v125 = ?v120 in (?v124 + ?v125))) in (let ?v126 = (let ?v127 = ?v76.(1) in (let ?v128 = 0 in (if (?v127 = ?v128) then (let ?v129 = ?v79 in (?v72 ?v129)) else (let ?v130 = (let ?v131 = (let ?v132 = (let ?v133 = (let ?v134 = (let ?v135 = (let ?v136 = (let ?v137 = (let ?v138 = (let ?v139 = (let ?v140 = (let ?v141 = (let ?v142 = (let ?v143 = (let ?v144 = (let ?v145 = ?v80 in (let ?v146 = ?v81 in (?v145 + ?v146))) in (let ?v147 = ?v84 in (?v144 + ?v147))) in (let ?v148 = ?v87 in (?v143 + ?v148))) in (let ?v149 = ?v90 in (?v142 + ?v149))) in (let ?v150 = ?v93 in (?v141 + ?v150))) in (let ?v151 = ?v96 in (?v140 + ?v151))) in (let ?v152 = ?v99 in (?v139 + ?v152))) in (let ?v153 = ?v102 in (?v138 + ?v153))) in (let ?v154 = ?v105 in (?v137 + ?v154))) in (let ?v155 = ?v108 in (?v136 + ?v155))) in (let ?v156 = ?v111 in (?v135 + ?v156))) in (let ?v157 = ?v114 in (?v134 + ?v157))) in (let ?v158 = ?v117 in (?v133 + ?v158))) in (let ?v159 = ?v120 in (?v132 + ?v159))) in (let ?v160 = ?v123 in (?v131 + ?v160))) in (let ?v161 = ?v77 in (?v130 + ?v161)))))) in (print_int ?v126)))))))))))))))))))))))

------ Reduction of Nested Let-Expressions ------
(let rec ?v70 ?v71 = 12345 in (let rec ?v72 ?v73 = (let ?v74 = ?v73 in (let ?v75 = 1 in (?v74 + ?v75))) in (let ?v76 = (Array.create 10 1) in (let ?v78 = () in (let ?v77 = (?v70 ?v78) in (let ?v79 = 67890 in (let ?v80 = ?v76.(0) in (let ?v82 = ?v80 in (let ?v83 = ?v80 in (let ?v81 = (?v82 + ?v83) in (let ?v85 = ?v81 in (let ?v86 = ?v81 in (let ?v84 = (?v85 + ?v86) in (let ?v88 = ?v84 in (let ?v89 = ?v84 in (let ?v87 = (?v88 + ?v89) in (let ?v91 = ?v87 in (let ?v92 = ?v87 in (let ?v90 = (?v91 + ?v92) in (let ?v94 = ?v90 in (let ?v95 = ?v90 in (let ?v93 = (?v94 + ?v95) in (let ?v97 = ?v93 in (let ?v98 = ?v93 in (let ?v96 = (?v97 + ?v98) in (let ?v100 = ?v96 in (let ?v101 = ?v96 in (let ?v99 = (?v100 + ?v101) in (let ?v103 = ?v99 in (let ?v104 = ?v99 in (let ?v102 = (?v103 + ?v104) in (let ?v106 = ?v102 in (let ?v107 = ?v102 in (let ?v105 = (?v106 + ?v107) in (let ?v109 = ?v105 in (let ?v110 = ?v105 in (let ?v108 = (?v109 + ?v110) in (let ?v112 = ?v108 in (let ?v113 = ?v108 in (let ?v111 = (?v112 + ?v113) in (let ?v115 = ?v111 in (let ?v116 = ?v111 in (let ?v114 = (?v115 + ?v116) in (let ?v118 = ?v114 in (let ?v119 = ?v114 in (let ?v117 = (?v118 + ?v119) in (let ?v121 = ?v117 in (let ?v122 = ?v117 in (let ?v120 = (?v121 + ?v122) in (let ?v124 = ?v120 in (let ?v125 = ?v120 in (let ?v123 = (?v124 + ?v125) in (let ?v127 = ?v76.(1) in (let ?v128 = 0 in (let ?v126 = (if (?v127 = ?v128) then (let ?v129 = ?v79 in (?v72 ?v129)) else (let ?v130 = (let ?v131 = (let ?v132 = (let ?v133 = (let ?v134 = (let ?v135 = (let ?v136 = (let ?v137 = (let ?v138 = (let ?v139 = (let ?v140 = (let ?v141 = (let ?v142 = (let ?v143 = (let ?v144 = (let ?v145 = ?v80 in (let ?v146 = ?v81 in (?v145 + ?v146))) in (let ?v147 = ?v84 in (?v144 + ?v147))) in (let ?v148 = ?v87 in (?v143 + ?v148))) in (let ?v149 = ?v90 in (?v142 + ?v149))) in (let ?v150 = ?v93 in (?v141 + ?v150))) in (let ?v151 = ?v96 in (?v140 + ?v151))) in (let ?v152 = ?v99 in (?v139 + ?v152))) in (let ?v153 = ?v102 in (?v138 + ?v153))) in (let ?v154 = ?v105 in (?v137 + ?v154))) in (let ?v155 = ?v108 in (?v136 + ?v155))) in (let ?v156 = ?v111 in (?v135 + ?v156))) in (let ?v157 = ?v114 in (?v134 + ?v157))) in (let ?v158 = ?v117 in (?v133 + ?v158))) in (let ?v159 = ?v120 in (?v132 + ?v159))) in (let ?v160 = ?v123 in (?v131 + ?v160))) in (let ?v161 = ?v77 in (?v130 + ?v161)))) in (print_int ?v126))))))))))))))))))))))))))))))))))))))))))))))))))))))))

------ ClosureConversion ------
