SCRIPTFOLDER=../scripts/



all: generate diff

generate:
	@$(SCRIPTFOLDER)generate.sh ${test}
	@$(SCRIPTFOLDER)generate.sh ${test} > test_result.txt

back_generate:
	@$(SCRIPTFOLDER)back_generate.sh

diff:
	@$(SCRIPTFOLDER)test_diff.sh ${test}
	@$(SCRIPTFOLDER)test_diff.sh ${test} > test_result.txt

back_diff:
	@$(SCRIPTFOLDER)back_test_diff.sh

test_test:
	@$(SCRIPTFOLDER)test_test.sh

clean:
	@rm */output/*
