#! /bin/sh
cd "$(dirname "$0")"/.. || exit 1
MINCAMLC=java/mincamlc

input_files=`ls tests/input/`
for testing_part in 'ast' 'knorm'
  do
    echo -e "\n \033[33m \t \t \t $testing_part \033[0m \n"
    echo "generation in tests/$testing_part/output/"
    for file in $input_files
    do
          echo -e "generating \033[0;36m ${file:5:-3}.out \033[0m"
          $MINCAMLC tests/input/$file --$testing_part > tests/$testing_part/output/${file:5:-3}.out
    done
  done
echo ""
