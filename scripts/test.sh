#! /bin/sh
cd "$(dirname "$0")"/.. || exit 1

# TODO change this to point to your mincamlc executable if it's different, or add
# it to your PATH. Use the appropriate option to run the parser as soon
# as it is implemented
MINCAMLC=java/mincamlc

# run all test cases in syntax/valid and make sure they are parsed without error
# run all test cases in syntax/invalid and make sure the parser returns an error

# TODO extends this script to run test in subdirectories
#

#        SYNTAX PART

echo -e "\n--------------------------------------------------------------------------\n"
echo -e "\t \t \t \033[0;32m[ VALID PART ] \033[33m ->SYNTAX \033[0m \n"
for test_case in tests/syntax/valid/*.ml
do
    echo "testing parser on: $test_case"
    if $MINCAMLC "$test_case" 2> /dev/null 1> /dev/null
    then
        echo -e '\033[0;32m[ PASSED ]\033[0m'
    else
        echo -e '\033[0;31m[ FAILED ]\033[0m'
    fi
done

echo -e "\n--------------------------------------------------------------------------\n"
echo -e "\t \t \t \033[0;31m[ INVALID  PART] \033[33m ->SYNTAX \033[0m \n"
for test_case in tests/syntax/invalid/*.ml
do
    echo "testing parser on: $test_case"
    if $MINCAMLC "$test_case" 2> /dev/null 1> /dev/null
    then
        echo -e '\033[0;31m[ FAILED ]\033[0m'
    else
        echo -e '\033[0;32m[ PASSED ]\033[0m'
    fi
done

#                       TYPECHECKING PART

echo -e "\n--------------------------------------------------------------------------\n"
echo -e "\t \t \t \033[0;32m[ VALID PART ] \033[33m ->TYPECHECKING \033[0m \n"
for test_case in tests/typechecking/valid/*.ml
do
    echo "testing parser on: $test_case"
    if $MINCAMLC "$test_case" 2> /dev/null 1> /dev/null
    then
        echo -e '\033[0;32m[ PASSED ]\033[0m'
    else
        echo -e '\033[0;31m[ FAILED ]\033[0m'
    fi
done

echo -e "\n--------------------------------------------------------------------------\n"
echo -e "\t \t \t \033[0;31m[ INVALID  PART] \033[33m ->TYPECHECKING \033[0m \n"
for test_case in tests/typechecking/invalid/*.ml
do
    echo "testing parser on: $test_case"
    if $MINCAMLC "$test_case" 2> /dev/null 1> /dev/null
    then
        echo -e '\033[0;31m[ FAILED ]\033[0m'
    else
        echo -e '\033[0;32m[ PASSED ]\033[0m'
    fi
echo -e "\n\n"
done
