
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	  BigInteger.java \
	  Calculator.java \
	  Tokenizer.java \
	  SymbolParser.java \
	  SymbolBase.java \
	  Test.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

test: classes Test.class
	java -ea Test

run:
	java BigInteger
