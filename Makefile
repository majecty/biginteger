

all: BigInteger.class Calculator.class Test.class Tokenizer.class SymbolParser.class

BigInteger.class: BigInteger.java
	javac BigInteger.java

Calculator.class: Calculator.java
	javac Calculator.java

Tokenizer.class: Tokenizer.java
	javac Tokenizer.java

SymbolParser.class: SymbolParser.java
	javac SymbolParser.java

Test.class: Test.java
	javac Test.java

test: all Test.class
	java -ea Test

run:
	java BigInteger
