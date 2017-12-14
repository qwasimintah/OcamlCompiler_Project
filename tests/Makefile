SCRIPTFOLDER=../scripts/

all: test

test:
	@$(SCRIPTFOLDER)test.sh
	@$(SCRIPTFOLDER)test.sh > test_result.txt

generate:
	@$(SCRIPTFOLDER)generate.sh

diff:
	@$(SCRIPTFOLDER)test_diff.sh

test_test:
	@$(SCRIPTFOLDER)test_test.sh

clean:
	@rm -f ./result/*
