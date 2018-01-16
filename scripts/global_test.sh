#! /bin/bash

cd "$(dirname "$0")"/.. || exit 1
MINCAMLC=java/mincamlc

input_files=`ls mincaml`
for file in $input_files
do
      echo -ne "generating\033[0;36m ${file::-3}.s\033[0m"
      $MINCAMLC mincaml/$file -o gen/s_files/${file::-2}s 2> /dev/null
      if [ $? -eq 0 ]
        then
          echo -e '\033[0;32m PASSED \033[0m'
        else
            echo -e '\033[31m FAILED \033[0m'
      fi
done
cd gen/s_files/
echo -e '\n \033[31m RUNNING make \033[0m \n'
make -s
echo -e '\n\n'
mv *.arm ../arm_files/
cd ../arm_files
arm_files=`ls *.arm`
for file in $arm_files
do
      echo -ne "running qemu on :\033[0;36m $file \033[0m"
      qemu-arm $file
            if [ $? -eq 0 ]
              then
                echo -e '\033[0;32m PASSED \033[0m'
              else
                  echo -e '\033[31m FAILED \033[0m'
            fi
done
