
JFLAGS = -g -classpath . -d . -sourcepath src
JC = javac
.SUFFIXES: .java .class

# .java.class:

%.class: src/%.java
	$(JC) $(JFLAGS) $<

SOURCES = \
	  src/BigInteger.java \
	  src/Calculator.java \
	  src/Tokenizer.java \
	  src/Token.java \
	  src/E_Token.java \
	  src/SymbolParser.java \
	  src/SymbolBase.java \
	  src/Test.java

CLASSES = \
	  BigInteger.class \
	  Calculator.class \
	  Tokenizer.class \
	  Token.class \
	  E_Token.class \
	  SymbolParser.class \
	  SymbolBase.class \
	  Test.class



default: classes

classes: $(CLASSES)

#classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

test: classes Test.class
	java -ea Test

run:
	java BigInteger
