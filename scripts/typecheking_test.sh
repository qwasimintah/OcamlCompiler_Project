#! /bin/bash

MINCAMLC=java/mincamlc

input_files=`ls mincaml`
for file in $input_files
do
      echo -ne "Typechecking for \033[0;36m $file\033[0m"
      $MINCAMLC -t mincaml/$file > /dev/null 2> /dev/null
      if [ $? -eq 0 ]
        then
          echo -e '\033[0;32m PASSED \033[0m'
        else
          echo -e '\033[31m FAILED \033[0m'
      fi
done
