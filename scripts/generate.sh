#! /bin/bash

cd "$(dirname "$0")"/.. || exit 1
MINCAMLC=java/mincamlc

# cd java
# make clean 2> /dev/null 1> /dev/null
# make 2> /dev/null 1> /dev/null
# if [ $? -ne 0 ]
# then
#   echo -e "\n\n\033[31m Failed to make the main programm in the java folder.\n\n \033[0m"
#   exit 1
# fi
# cd ..
# clear

if [ $# -eq 0 ]
  then
    test_list="ast knorm alpha_conversion reduction arm"
  else
    test_list=$1
fi

input_files=`ls tests/input/`
for testing_part in ${test_list}
  do
    if [ -d "tests/${testing_part}/output" ]
      then
      echo -e "\n \033[33m \t \t \t $testing_part \033[0m \n"
      echo "generation in tests/$testing_part/output/"
      for file in $input_files
      do
            echo -e "generating \033[0;36m ${file:5:-3}.out \033[0m"
            $MINCAMLC tests/input/$file --$testing_part > tests/$testing_part/output/${file:5:-3}.out
      done
    else
      echo -e "\033[31m The specified test folder ${testing_part} does not exist \033[0m"
  fi
  done
echo ""
