import java.util.*;

public class Calculator
{
    public static BigInteger GetValueFrom(Symbol symbol)
    {
        if (symbol.GetName() == "NUM")
        {
            List<SymbolData> parsedDatas = symbol.GetParsedDatas();
            assert parsedDatas != null;
            assert parsedDatas.size() != 0;
            for (int i=0; i<parsedDatas.size(); i++)
            {
                SymbolData data = parsedDatas.get(0);
                if (data.name == "BIGINT")
                {
                    return data.value;
                }
            }

            assert false;
        }

        return null;
    }

  public static BigInteger Add(BigInteger lhs, BigInteger rhs)
  {
    return new BigInteger(lhs.value + rhs.value);
  }

  public static BigInteger Run(String input)
  {
    Symbol NUM = new Symbol("NUM")
    {
      protected BigInteger GetValue()
      {
        return parsedDatas.get(0).value;
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

    Tokenizer tokenizer = new Tokenizer();
    List<Token> tokenList = tokenizer.Parse(input);

    for (int i=0; i<statements.size(); i++)
    {
      Statement statement = statements.get(i);

      statement.Parse(tokenList);

      List<SymbolData> symbols = statement.GetSymbolDatas();
      if (symbols == null)
      {
        continue;
      }

      BigInteger ret = Calc(symbols);
      if (ret != null)
      {
        return ret;
      }
    }

    return null;
  }

  private static BigInteger Calc(List<SymbolData> symbolDataList)
  {
    if (symbolDataList.size() == 1)
    {
      SymbolData symbol = symbolDataList.get(0);
      BigInteger value = symbol.value;

      return value;
    }
    else if (symbolDataList.size() == 3)
    {
      SymbolData num1 = symbolDataList.get(0);
      SymbolData op = symbolDataList.get(1);
      SymbolData num2 = symbolDataList.get(2);

      if (num1.name != "NUM" ||
          op.name != "OP_PLUS" ||
          num2.name != "NUM")
      {
        return null;
      }

      return Add(num1.value, num2.value);
    }

    return null;
  }
}
