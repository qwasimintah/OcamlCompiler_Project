#! /bin/bash

# cd "$(dirname "$0")"/.. || exit 1
MINCAMLC=java/mincamlc
pwd
input_files=`ls mincaml/`

echo $input_files

for file in $input_files
do
      echo -e "generating \033[0;36m ${file::-3}.result and ${file::-3}.s  \033[0m"
      ocaml mincaml/$file > results/${file::-3}.result
      echo $?
      $MINCAMLC --arm mincaml/$file > results/${file::-3}.s
      echo $?
      rm ARM/*.arm ARM/*.s
      cp results/${file::-3}.s ARM/
      cd ARM/
      make
      cd -



      # echo -e "Trying on ${file::-3}.arm"
      # qemu-arm results/${file::-3}.arm > results/${file::-3}.ourresult
      # # ls results
      # diff results/${file::-3}.result results/${file::-3}.ourresult > /dev/null
      # if [ $? -eq 0 ]
      #   then
      #     echo -e '\033[0;32m PASSED \033[0m'
      #   else
      #     echo -e '\033[31m FAILED \033[0m'
      # fi
      # echo -e "\n\n"
done
