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

            int minusCount = 0;
            for (int i=0; i<parsedDatas.size(); i++)
            {
                SymbolData data = parsedDatas.get(i);
                if (data.name == "MINUS")
                {
                    minusCount += 1;
                }
                else if (data.name == "BIGINT")
                {
                    if (minusCount % 2 == 1)
                    {
                        BigInteger ret = data.value.GetClone();
                        ret.ChangePlMa();
                        return ret;
                    }
                    else
                    {
                        return data.value;
                    }
                }
            }

            assert false;
        }

        return null;
    }

  public static BigInteger Add(BigInteger lhs, BigInteger rhs)
  {
      return lhs.Add(rhs);
  }

  public static BigInteger Sub(BigInteger lhs, BigInteger rhs)
  {
      return lhs.Sub(rhs);
  }

  public static BigInteger Mul(BigInteger lhs, BigInteger rhs)
  {
      return lhs.Mul(rhs);
  }

  public static BigInteger Run(String input)
  {
    Symbol NUM = new Symbol("NUM");
    NUM.AddSyntax(SymbolList.Make(new Token(E_Token.BIGINT)));
    NUM.AddSyntax(SymbolList.Make(
                new Token(E_Token.PLUS),
                new Token(E_Token.BIGINT)));
    NUM.AddSyntax(SymbolList.Make(
                new Token(E_Token.MINUS),
                new Token(E_Token.BIGINT)));

    Symbol OP_PLUS = new Symbol("OP_PLUS");
    OP_PLUS.AddSyntax(SymbolList.Make(new Token(E_Token.PLUS)));

    Symbol OP_SUB = new Symbol("OP_SUB");
    OP_SUB.AddSyntax(SymbolList.Make(new Token(E_Token.MINUS)));

    Symbol OP_MUL = new Symbol("OP_MUL");
    OP_MUL.AddSyntax(SymbolList.Make(new Token(E_Token.MUL)));

    Statement onlyNumStatement = new Statement();
    onlyNumStatement.AddSyntax(SymbolList.Make(NUM));

    Statement addStatement = new Statement();
    addStatement.AddSyntax(SymbolList.Make(NUM, OP_PLUS, NUM));

    Statement subStatement = new Statement();
    subStatement.AddSyntax(SymbolList.Make(NUM, OP_SUB, NUM));

    Statement mulStatement = new Statement();
    mulStatement.AddSyntax(SymbolList.Make(NUM, OP_MUL, NUM));

    List<Statement> statements = new ArrayList<Statement>();

    statements.add(addStatement);
    statements.add(onlyNumStatement);
    statements.add(subStatement);
    statements.add(mulStatement);

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
          num2.name != "NUM")
      {
        return null;
      }

      if (op.name == "OP_PLUS")
      {
          return Add(num1.value, num2.value);
      }

      else if (op.name == "OP_SUB")
      {
          return Sub(num1.value, num2.value);
      }
      else if (op.name == "OP_MUL")
      {
          return Mul(num1.value, num2.value);
      }

      return null;
    }

    return null;
  }
}
