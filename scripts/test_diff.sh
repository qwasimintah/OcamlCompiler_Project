#! /bin/bash
cd "$(dirname "$0")"/.. || exit 1

if [ $# -eq 0 ]
  then
    test_list="knorm alpha_conversion reduction"
  else
    test_list=$1
fi

input_files=`ls tests/input/`
for testing_part in ${test_list}
  do
    if [ -d "tests/"${testing_part} ]
      then
      echo -e "\n \033[33m \t \t \t $testing_part \033[0m \n"
      for file in $input_files
      do
          echo -ne "testing parser on: \033[0;36m ${file:5:-3}.out : \033[0;40m"
          if diff tests/$testing_part/output/${file:5:-3}.out tests/$testing_part/expected/${file:5:-3}.exp 2> /dev/null 1> /dev/null
          then
            echo -e '\033[0;32m PASSED \033[0m'
          else
            echo -e '\033[31m FAILED \033[0m'
          fi
      done
    else
      echo -e "\033[31m The specified test folder ${testing_part} does not exist \033[0m"
    fi
  done
echo ""
