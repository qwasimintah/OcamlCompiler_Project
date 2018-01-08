SRC= java/Lexer.java java/Parser.java java/Main.java

all: ${SRC}
	$(MAKE) -C ./java all


Lexer.java: Lexer.flex
	jflex/bin/jflex Lexer.flex

Parser.java: Parser.cup
	java -jar java-cup-11b.jar -nonterms -expect 150 -interface -parser Parser Parser.cup

test:
	@ echo " \n\n\t Generating the tests ...\n\n"
	@ ../scripts/generate.sh
	@ echo "\n\n\t Testing ...\n\n"
	@ ../scripts/test_diff.sh
	@ cd -

clean:
	$(MAKE) -C ./java clean
