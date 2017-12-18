#! /bin/bash
echo -e "\n--------------------------------------------------------------------------\n"
echo -e "\t \t \t \033[0;32m[ VALID PART ] \033[33m -> TEST\033[0m \n"
for test_case in tests/test_test/valid/*.ex
do
    echo -e "testing parser on: $test_case"
    if $MINCAMLC "$test_case" 2> /dev/null 1> /dev/null
    then
        echo -e '\033[0;32m[ PASSED ]\033[0m'
    else
        echo -e '\033[0;31m[ FAILED ]\033[0m'
    fi
done

echo -e "\n--------------------------------------------------------------------------\n"
echo -e "\t \t \t \033[0;31m[ INVALID  PART] \033[33m ->TEST \033[0m \n"
for test_case in tests/test_test/invalid/*.ex
do
    echo -e "testing parser on: $test_case"
    if $MINCAMLC "$test_case" 2> /dev/null 1> /dev/null
    then
        echo -e '\033[0;31m[ FAILED ]\033[0m'
    else
        echo -e '\033[0;32m[ PASSED ]\033[0m'
    fi
done
echo
