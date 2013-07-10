import java.util.ArrayList;

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

    System.out.println("Test end.");
  }


  public static void TestSmallAdd()
  {
    BigInteger lhs = new BigInteger(3);
    BigInteger rhs = new BigInteger(3);
    BigInteger result = null;
    Calculator calc = new Calculator();

    result = calc.Add(lhs, rhs);

    assert result.GetInt() == 6;
  }

  public static void TokenizerCanParsePlusSymbol()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+";

    ArrayList<Tokenizer.Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == Tokenizer.E_Token.PLUS;
  }

  public static void TokenizerCanParseInteger()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "10";

    ArrayList<Tokenizer.Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == Tokenizer.E_Token.BIGINT;
  }

  public static void TokenizerCanIgnoreSpace()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+ ";

    ArrayList<Tokenizer.Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == Tokenizer.E_Token.PLUS;
  }

  public static void TokenizerCanParseMoreThanTwoToken()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+ +";

    ArrayList<Tokenizer.Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == Tokenizer.E_Token.PLUS;
    assert tokenList.get(1).GetType() == Tokenizer.E_Token.PLUS;
  }

  public static void TokenizerCanParsePlusAndBigIntTogether()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+ 10";

    ArrayList<Tokenizer.Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == Tokenizer.E_Token.PLUS;
    assert tokenList.get(1).GetType() == Tokenizer.E_Token.BIGINT;
    assert tokenList.size() == 2;
  }

  public static void TokenizerCanParsePlusBIGINTPlus()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "+ 10 +";

    ArrayList<Tokenizer.Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == Tokenizer.E_Token.PLUS;
    assert tokenList.get(1).GetType() == Tokenizer.E_Token.BIGINT;
    assert tokenList.get(2).GetType() == Tokenizer.E_Token.PLUS;
    assert tokenList.size() == 3;
  }

  public static void TokenizerCanParseBigIntegerValue()
  {
    Tokenizer tokenizer = new Tokenizer();
    String input = "378";

    ArrayList<Tokenizer.Token> tokenList = tokenizer.Parse(input);

    assert tokenList.get(0).GetType() == Tokenizer.E_Token.BIGINT;
    assert tokenList.get(0).GetValueInt() == 378;
  }
}
