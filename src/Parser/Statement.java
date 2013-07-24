import java.util.*;

public class Statement
{
  private List<SymbolList> syntaxList;
  private BigInteger value;
  private List<SymbolData> parsedSymbols;

  public Statement()
  {
    syntaxList = new ArrayList<SymbolList>();
    value = null;
  }

  public BigInteger GetValue()
  {
      System.out.println(parsedSymbols);
    return parsedSymbols.get(0).value;
  }

  public List<SymbolData> GetSymbolDatas()
  {
    if (parsedSymbols == null)
    {
      return null;
    }

    List<SymbolData> ret = new ArrayList<SymbolData>();

    for (int i=0; i<parsedSymbols.size(); i++)
    {
      ret.add(parsedSymbols.get(i));
    }

    return ret;
  }

  public boolean Parse(List<Token> tokenList)
  {
    SymbolList firstSyntax = syntaxList.get(0);

    parsedSymbols = firstSyntax.ParseAll(tokenList);

    return parsedSymbols != null;
  }

  public void AddSyntax(SymbolList syntax)
  {
    syntaxList.add(syntax);
  }
}
