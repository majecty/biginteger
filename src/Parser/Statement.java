import java.util.*;

public class Statement
{
  private List<SymbolList> syntaxList;
  private BigInteger value;
  private List<SymbolBase> symbols;

  public Statement()
  {
    syntaxList = new ArrayList<SymbolList>();
    value = null;
  }

  public BigInteger GetValue()
  {
    return symbols.get(0).GetValue();
  }

  public boolean Parse(List<Token> tokenList)
  {
    SymbolList firstSyntax = syntaxList.get(0);

    symbols = firstSyntax.Parse(tokenList);

    return symbols != null;
    /*
      for (int i=0; i<firstSyntax.GetLength(); i++)
      {
      SymbolBase symbol = firstSyntax.Get(i);
      }
    */
  }

  public void AddSyntax(SymbolList syntax)
  {
    syntaxList.add(syntax);
  }
}
