

all: BigInteger.class Calculator.class Test.class

BigInteger.class: BigInteger.java
	javac BigInteger.java

Calculator.class: Calculator.java
	javac Calculator.java

Test.class: Test.java
	javac Test.java

test: Test.class
	java Test

run:
	java BigInteger
