import java.util.*;

public class Statement
{
  private List<SymbolList> syntaxList;
  private BigInteger value;
  private List<SymbolBase> parsedSymbols;

  public Statement()
  {
    syntaxList = new ArrayList<SymbolList>();
    value = null;
  }

  public BigInteger GetValue()
  {
    return parsedSymbols.get(0).GetValue();
  }

  public List<Symbol> GetSymbols()
  {
    if (parsedSymbols == null)
    {
      return null;
    }

    List<Symbol> ret = new ArrayList<Symbol>();

    for (int i=0; i<parsedSymbols.size(); i++)
    {
      ret.add((Symbol)parsedSymbols.get(i));
    }

    return ret;
  }

  public boolean Parse(List<Token> tokenList)
  {
    SymbolList firstSyntax = syntaxList.get(0);

    parsedSymbols = firstSyntax.Parse(tokenList);

    return parsedSymbols != null;
  }

  public void AddSyntax(SymbolList syntax)
  {
    syntaxList.add(syntax);
  }
}
