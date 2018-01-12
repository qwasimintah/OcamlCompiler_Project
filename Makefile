SRC= java/Lexer.java java/Parser.java java/Main.java

all: ${SRC}
	$(MAKE) -C ./java all

test:
	$(MAKE) -C ./java test

teacher_test:
	./scripts/global_test.sh

clean:
	$(MAKE) -C ./java clean

clean_test:
	rm gen/s_files/*.s
	rm gen/arm_files/*.arm
