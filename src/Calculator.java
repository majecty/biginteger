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

    Symbol OP_PLUS = new Symbol("OP_PLUS");
    OP_PLUS.AddSyntax(SymbolList.Make(new Token(E_Token.PLUS)));

    Statement onlyNumStatement = new Statement();
    onlyNumStatement.AddSyntax(SymbolList.Make(NUM));

    Statement addStatement = new Statement();
    addStatement.AddSyntax(SymbolList.Make(NUM, OP_PLUS, NUM));

    List<Statement> statements = new ArrayList<Statement>();

    statements.add(addStatement);
    statements.add(onlyNumStatement);

    for (int i=0; i<statements.size(); i++)
    {
      Tokenizer tokenizer = new Tokenizer();
      List<Token> tokenList = tokenizer.Parse(input);

      onlyNumStatement.Parse(tokenList);
      List<Symbol> symbols = onlyNumStatement.GetSymbols();

      BigInteger ret = Calc(symbols);
      if (ret != null)
      {
        return ret;
      }
    }

    return null;
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
