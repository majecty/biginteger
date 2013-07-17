import java.util.*;

public class Calculator
{
  public BigInteger Add(BigInteger lhs, BigInteger rhs)
  {
    return new BigInteger(lhs.value + rhs.value);
  }

  public static BigInteger Run(String input)
  {
    Symbol NUM = new Symbol("NUM")
      {
        public BigInteger GetValue()
        {
          return symbols.get(0).GetValue();
        }
      };
    NUM.AddSyntax(SymbolList.Make(new Token(E_Token.BIGINT)));

    Statement testStatement = new Statement();
    testStatement.AddSyntax(SymbolList.Make(NUM));

    Tokenizer tokenizer = new Tokenizer();
    List<Token> tokenList = tokenizer.Parse(input);

    testStatement.Parse(tokenList);
    List<Symbol> symbols = testStatement.GetSymbols();

    return Calc(symbols);
  }

  private static BigInteger Calc(List<Symbol> symbolList)
  {
    if (symbolList.size() == 1)
    {
      Symbol symbol = symbolList.get(0);
      BigInteger value = symbol.GetValue();

      return value;
    }

    return null;
  }
}
