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
