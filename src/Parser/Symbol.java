import java.util.*;

public class Symbol extends SymbolBase
{
  private String name;
  private ArrayList<SymbolList> syntaxList;

  public String GetName()
  {
      return name;
  }

  public Symbol(String name)
  {
    this.name = name;
    syntaxList = new ArrayList<SymbolList>();
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
