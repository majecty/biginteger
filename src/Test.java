import java.util.*;

public class Test
{
  public static void main(String args[])
  {
    System.out.println("Test start");

    TestSmallAdd();
    TestPrintBigInteger();
    TestPrintLong();
    TestBigIntEquality();
    TestAddBigInteger();
    TestAddNegative();
    TestAddLong();
    TestManyAdd();

    TokenizerCanParsePlusSymbol();
    TokenizerCanParseInteger();
    TokenizerCanParseMinusSymbol();
    TokenizerCanIgnoreSpace();
    TokenizerCanParseMoreThanTwoToken();
    TokenizerCanParsePlusAndBigIntTogether();
    TokenizerCanParsePlusBIGINTPlus();

    TokenizerCanParseBigIntegerValue();
    TokenizerCanParseTwoInt();

    SymbolCanParseOneToken();
    SymbolCanParseTwoSyntax();

    ParserCanParseSimpleStatement();
    ParserCanParseSimpleMultiSymbol();
    ParserCanGetValueFromExpression();

    CalculatorCanGetValue();
    CalculatorCanGetMinusValue();
    CalculatorCanAdd();
    CalculatorCanSub();
    CalculatorCanAddTestMany();
    CalculatorCanSubTestMany();

    System.out.println("Test end.");
  }


  public static void TestSmallAdd()
  {
    BigInteger lhs = new BigInteger(3);
    BigInteger rhs = new BigInteger(3);
    BigInteger result = null;

    result = Calculator.Add(lhs, rhs);

    assert result.GetInt() == 6;
  }

  public static void TestPrintBigInteger()
  {
      BigInteger bigInt = new BigInteger(133);

      assert bigInt.toString().equals("133");
  }

  public static void TestPrintLong()
  {
      BigInteger bigInt = new BigInteger(2);
      for(int i=0; i<20; i++)
      {
          bigInt.Push(0);
      }

      assert bigInt.toString().equals("200000000000000000000");
  }

  public static void TestAddBigInteger()
  {
      BigInteger lhs = new BigInteger(2432);
      BigInteger rhs = new BigInteger(12321);

      BigInteger expected = new BigInteger(14753);
      BigInteger actual = lhs.Add(rhs);

      assert actual.IsEqual(expected);
  }

  public static void TestAddNegative()
  {
      BigInteger lhs = new BigInteger(-3);
      BigInteger rhs = new BigInteger(5);

      BigInteger expected = new BigInteger(2);
      BigInteger actual = lhs.Add(rhs);

      assert actual.IsEqual(expected);
  }

  public static void TestAddLong()
  {
      BigInteger lhs = new BigInteger(2);
      BigInteger rhs = new BigInteger(3);

      for (int i=0; i<20; i++)
      {
          lhs.Push(0);
          rhs.Push(0);
      }

      String expected = "500000000000000000000";
      String actual = lhs.Add(rhs).toString();

      assert actual.equals(expected);
  }

  public static void TestBigIntEquality()
  {
      BigInteger lhs = new BigInteger(20);
      BigInteger rhs = new BigInteger(20);

      assert lhs.IsEqual(rhs);
  }

  public static void TestManyAdd()
  {
      BigInteger bigInt1 = new BigInteger(-10);
      BigInteger bigInt2 = new BigInteger(-20);
      BigInteger bigInt3 = new BigInteger(23);
      BigInteger bigInt4 = new BigInteger(4);

      System.out.println(bigInt1.Add(bigInt2).toString());
      assert bigInt1.Add(bigInt2).toString().equals("-30");
      assert bigInt1.Add(bigInt3).toString().equals("10");
      assert bigInt1.Add(bigInt4).toString().equals("-6");
      assert bigInt3.Add(bigInt2).toString().equals("-30");
      assert bigInt4.Add(bigInt2).toString().equals("-30");

  }

  public static void TokenizerCanParsePlusSymbol()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+";

    ArrayList<Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == E_Token.PLUS;
  }

  public static void TokenizerCanParseMinusSymbol()
  {
      Tokenizer tokenizer = new Tokenizer();
      String input = "-";

      ArrayList<Token> tokenList = tokenizer.Parse(input);

      assert tokenList.get(0).GetType() == E_Token.MINUS;
  }

  public static void TokenizerCanParseInteger()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "10";

    ArrayList<Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == E_Token.BIGINT;
  }

  public static void TokenizerCanIgnoreSpace()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+ ";

    ArrayList<Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == E_Token.PLUS;
  }

  public static void TokenizerCanParseMoreThanTwoToken()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+ +";

    ArrayList<Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == E_Token.PLUS;
    assert tokenList.get(1).GetType() == E_Token.PLUS;
  }

  public static void TokenizerCanParsePlusAndBigIntTogether()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "10 + 10";

    ArrayList<Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == E_Token.BIGINT;
    assert tokenList.get(1).GetType() == E_Token.PLUS;
    assert tokenList.get(2).GetType() == E_Token.BIGINT;
    assert tokenList.size() == 3;
  }

  public static void TokenizerCanParsePlusBIGINTPlus()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+ 10 +";

    ArrayList<Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == E_Token.PLUS;
    assert tokenList.get(1).GetType() == E_Token.BIGINT;
    assert tokenList.get(2).GetType() == E_Token.PLUS;
    assert tokenList.size() == 3;
  }

  public static void TokenizerCanParseBigIntegerValue()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "378";

    ArrayList<Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == E_Token.BIGINT;
    assert tokenList.get(0).GetValueInt() == 378;
  }

  public static void TokenizerCanParseTwoInt()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "378 + 183";

    ArrayList<Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == E_Token.BIGINT;
    assert tokenList.get(0).GetValueInt() == 378;
    assert tokenList.get(2).GetType() == E_Token.BIGINT;
    assert tokenList.get(2).GetValueInt() == 183;
  }

  public static void SymbolCanParseOneToken()
  {
      Symbol NUM = new Symbol("NUM");
      NUM.AddSyntax(SymbolList.Make(new Token(E_Token.BIGINT)));

      List<Token> inputTokenList = new ArrayList<Token>();
      inputTokenList.add(new Token(E_Token.BIGINT));

      int parsed = NUM.ParseIter(inputTokenList);

      assert parsed == 1;
  }

  public static void SymbolCanParseTwoSyntax()
  {
      Symbol NUM = new Symbol("NUM");
      NUM.AddSyntax(SymbolList.Make(new Token(E_Token.BIGINT)));
      NUM.AddSyntax(SymbolList.Make(new Token(E_Token.PLUS),
                  new Token(E_Token.BIGINT)));

      List<Token> inputTokenList = new ArrayList<Token>();
      inputTokenList.add(new Token(E_Token.BIGINT));
      List<Token> inputTokenList2 = new ArrayList<Token>();
      inputTokenList2.add(new Token(E_Token.PLUS));
      inputTokenList2.add(new Token(E_Token.BIGINT));

      int parsed = NUM.ParseIter(inputTokenList);
      assert parsed == 1;

      NUM.Reset();

      parsed = NUM.ParseIter(inputTokenList2);
      assert parsed == 2;
  }

  public static void ParserCanParseSimpleStatement()
  {
    Symbol NUM = new Symbol("NUM");
    NUM.AddSyntax(SymbolList.Make(new Token(E_Token.BIGINT)));
    Statement testStatement = new Statement();
    testStatement.AddSyntax(SymbolList.Make(NUM));

    ArrayList<Token> inputTokenList = new ArrayList<Token>();
    inputTokenList.add(new Token(E_Token.BIGINT));

    boolean parseResult = testStatement.Parse(inputTokenList);

    //System.out.println("Result is " + parseResult);

    assert parseResult == true;
  }

  static void ParserCanParseSimpleMultiSymbol()
  {
    Symbol NUM = new Symbol("NUM");
    NUM.AddSyntax(SymbolList.Make(new Token(E_Token.BIGINT)));
    Symbol Op_PLUS = new Symbol("OP_PLUS");
    Op_PLUS.AddSyntax(SymbolList.Make(new Token(E_Token.PLUS)));

    Statement testStatement = new Statement();
    testStatement.AddSyntax(SymbolList.Make(NUM, Op_PLUS, NUM));

    ArrayList<Token> inputTokenList = new ArrayList<Token>();
    inputTokenList.add(new Token(E_Token.BIGINT));
    inputTokenList.add(new Token(E_Token.PLUS));
    inputTokenList.add(new Token(E_Token.BIGINT));

    boolean parseResult = testStatement.Parse(inputTokenList);

    assert parseResult == true;
  }

  public static void ParserCanGetValueFromExpression()
  {
    Symbol NUM = new Symbol("NUM")
      {
        protected  BigInteger GetValue()
        {
          return parsedDatas.get(0).value;
        }
      };
    NUM.AddSyntax(SymbolList.Make(new Token(E_Token.BIGINT)));
    Statement testStatement = new Statement();
    testStatement.AddSyntax(SymbolList.Make(NUM));

    Tokenizer tokenizer = new Tokenizer();
    String input = "10";

    List<Token> tokenList = tokenizer.Parse(input);
    boolean parseSuccess = testStatement.Parse(tokenList);
    
    assert parseSuccess == true;

    BigInteger value = testStatement.GetValue();

    System.out.println("Value is " + value.GetInt());

    assert value.GetInt() == 10;
  }

  public static void CalculatorCanGetValue()
  {
    String input = "10";

    BigInteger result = Calculator.Run(input);

    assert result.GetInt() == 10;
  }

  public static void CalculatorCanGetMinusValue()
  {
      String input = "-10";

      BigInteger result = Calculator.Run(input);

      assert result.GetInt() == -10;
  }

  public static void CalculatorCanAdd()
  {
    String input = "10 + 15";

    BigInteger result = Calculator.Run(input);

    assert result.GetInt() == 25;
  }

  public static void CalculatorCanSub()
  {
      String input = "20 - 15";

      BigInteger result = Calculator.Run(input);

      assert result.GetInt() == 5;
  }

  public static void CalculatorCanAddTestMany()
  {
    String input = "+10 + +15";
    String input2 = "10 + 5";
    String input3 = "10 + 12315";
    String input4 = "1 + 15";
    String input5 = "0 + 15";
    String input6 = "910 + 15";
    String input7 = "120 + 17";

    BigInteger result = Calculator.Run(input);
    BigInteger result2 = Calculator.Run(input2);
    BigInteger result3 = Calculator.Run(input3);
    BigInteger result4 = Calculator.Run(input4);
    BigInteger result5 = Calculator.Run(input5);
    BigInteger result6 = Calculator.Run(input6);
    BigInteger result7 = Calculator.Run(input7);

    assert result.GetInt() == 25;
    assert result2.GetInt() == 15;
    assert result3.GetInt() == 12325;
    assert result4.GetInt() == 16;
    assert result5.GetInt() == 15;
    assert result6.GetInt() == 925;
    assert result7.GetInt() == 137;
  }

  public static void CalculatorCanSubTestMany()
  {
      String input = "-10 - -20";
      String input2 = "10 - -30";
      String input3 = "-10 + - 30";

      BigInteger result = Calculator.Run(input);
      BigInteger result2 = Calculator.Run(input2);
      BigInteger result3 = Calculator.Run(input3);

      assert result.GetInt() == 10;
      assert result2.GetInt() == 40;
      assert result3.GetInt() == -40;
  }
}
