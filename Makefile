

all: BigInteger.class Calculator.class Test.class Tokenizer.class

BigInteger.class: BigInteger.java
	javac BigInteger.java

Calculator.class: Calculator.java
	javac Calculator.java

Tokenizer.class: Tokenizer.java
	javac Tokenizer.java

Test.class: Test.java
	javac Test.java

test: all Test.class
	java -ea Test

run:
	java BigInteger
