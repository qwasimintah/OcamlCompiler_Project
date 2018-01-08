
SRC= Lexer.java Parser.java Main.java

all: ${SRC}
	javac -sourcepath . -cp java-cup-11b-runtime.jar:. *.java
	@ cp mincamlc ../scripts

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
	rm -rf *.class *~ **/*.class **/**/*.class
