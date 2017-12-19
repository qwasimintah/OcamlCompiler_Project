SCRIPTFOLDER=../scripts/



all: generate diff

test:
	@$(SCRIPTFOLDER)test.sh
	@$(SCRIPTFOLDER)test.sh > test_result.txt

generate:
	@$(SCRIPTFOLDER)generate.sh ${test}

back_generate:
	@$(SCRIPTFOLDER)back_generate.sh

diff:
	@$(SCRIPTFOLDER)test_diff.sh ${test}

back_diff:
	@$(SCRIPTFOLDER)back_test_diff.sh

test_test:
	@$(SCRIPTFOLDER)test_test.sh

yo:
	@$(SCRIPTFOLDER)yo.sh

clean:
	@rm -f ./result/*
