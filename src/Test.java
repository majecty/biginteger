import java.util.*;

public class Test
{
  public static void main(String args[])
  {
    System.out.println("Test start");

    TestSmallAdd();

    TokenizerCanParsePlusSymbol();
    TokenizerCanParseInteger();
    TokenizerCanIgnoreSpace();
    TokenizerCanParseMoreThanTwoToken();
    TokenizerCanParsePlusAndBigIntTogether();
    TokenizerCanParsePlusBIGINTPlus();

    TokenizerCanParseBigIntegerValue();
    TokenizerCanParseTwoInt();

    ParserCanParseSimpleStatement();
    ParserCanParseSimpleMultiSymbol();
    ParserCanGetValueFromExpression();

    CalculatorCanGetValue();
    CalculatorCanAdd();

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

  public static void TokenizerCanParsePlusSymbol()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+";

    ArrayList<Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == E_Token.PLUS;
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

  public static void ParserCanParseSimpleStatement()
  {
    Symbol NUM = new Symbol("NUM");
    NUM.AddSyntax(SymbolList.Make(new Token(E_Token.BIGINT)));
    Statement testStatement = new Statement();
    testStatement.AddSyntax(SymbolList.Make(NUM));

    ArrayList<Token> inputTokenList = new ArrayList<Token>();
    inputTokenList.add(new Token(E_Token.BIGINT));

    boolean parseResult = testStatement.Parse(inputTokenList);

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
    Symbol NUM = new Symbol("NUM");
    NUM.AddSyntax(SymbolList.Make(new Token(E_Token.BIGINT)));
    Statement testStatement = new Statement();
    testStatement.AddSyntax(SymbolList.Make(NUM));

    Tokenizer tokenizer = new Tokenizer();
    String input = "10";

    List<Token> tokenList = tokenizer.Parse(input);
    boolean parseSuccess = testStatement.Parse(tokenList);
    
    assert parseSuccess == true;

    BigInteger value = testStatement.GetValue();

    assert value.GetInt() == 10;
  }

  public static void CalculatorCanGetValue()
  {
    String input = "10";

    BigInteger result = Calculator.Run(input);

    assert result.GetInt() == 10;
  }

  public static void CalculatorCanAdd()
  {
    String input = "10 + 15";

    BigInteger result = Calculator.Run(input);

    assert result.GetInt() == 25;
  }
}
