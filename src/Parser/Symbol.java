import java.util.*;

public class Symbol extends SymbolBase
{
  private String name;
  private BigInteger value;
  private ArrayList<SymbolList> syntaxList;
  protected List<SymbolBase> symbols;

  public String GetName()
  {
      return name;
  }

  public Symbol(String name)
  {
    this.name = name;
    syntaxList = new ArrayList<SymbolList>();
    symbols = new ArrayList<SymbolBase>();
    value = null;
  }

  public BigInteger GetValue()
  {
    return null;
  }

  public int Parse(List<Token> tokens)
  {
    SymbolList syntax = syntaxList.get(0);
    List<Token> originalTokens = tokens;

    for (int i=0; i<syntax.GetLength(); i++)
    {
      SymbolBase symbol = syntax.Get(i);
      int eatedNumber = symbol.Parse(tokens);

      if (eatedNumber < 1)
      {
        break;
      }
      else
      {
        symbols.add(symbol);
        tokens = tokens.subList(eatedNumber, tokens.size());
      }

      if (i == syntax.GetLength() - 1)
      {
        return originalTokens.size() - tokens.size();
      }
    }

    return 0;
  }

  public void AddSyntax(SymbolList syntax)
  {
    syntaxList.add(syntax);
  }
}
