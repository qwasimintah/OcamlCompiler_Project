SCRIPTFOLDER=../scripts/

all: test

test:
	@$(SCRIPTFOLDER)test.sh
	@$(SCRIPTFOLDER)test.sh > test_result.txt

generate:
	@$(SCRIPTFOLDER)generate.sh

back_generate:
	@$(SCRIPTFOLDER)back_generate.sh

diff:
	@$(SCRIPTFOLDER)test_diff.sh

back_diff:
	@$(SCRIPTFOLDER)back_test_diff.sh

test_test:
	@$(SCRIPTFOLDER)test_test.sh

clean:
	@rm -f ./result/*
