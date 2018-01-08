SRC= java/Lexer.java java/Parser.java java/Main.java

all: ${SRC}
	$(MAKE) -C ./java all

test:
	$(MAKE) -C ./java test

clean:
	$(MAKE) -C ./java clean
