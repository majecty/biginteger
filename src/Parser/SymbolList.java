import java.util.*;

public class SymbolList
{
  ArrayList<SymbolBase> symbols;

  public static SymbolList Make(SymbolBase one)
  {
    SymbolList ret = new SymbolList();
    ret.AddSymbol(one);
    return ret;
  }

  public static SymbolList Make(SymbolBase one, SymbolBase two)
  {
    SymbolList ret = new SymbolList();
    ret.AddSymbol(one);
    ret.AddSymbol(two);
    return ret;
  }

  public static SymbolList Make(SymbolBase one, SymbolBase two, SymbolBase three)
  {
    SymbolList ret = new SymbolList();
    ret.AddSymbol(one);
    ret.AddSymbol(two);
    ret.AddSymbol(three);
    return ret;
  }

  public List<SymbolData>  Parse(List<Token> tokenList)
  {
    assert tokenList.size() > 0;
    List<SymbolData> parsedSymbols = new ArrayList<SymbolData>();

    for (int i=0; i<symbols.size(); i++)
    {
      if (tokenList.size() < 1)
      {
        break;
      }

      SymbolBase symbol = Get(i);
      int eatedToken = symbol.Parse(tokenList);

      assert eatedToken <= tokenList.size();
      assert eatedToken >= 0;

      if (eatedToken < 1) break;
      else
      {
        parsedSymbols.add(symbol.ExtractData());
        tokenList = tokenList.subList(eatedToken, tokenList.size());
      }

      if (tokenList.size() == 0 && i == symbols.size() - 1)
      {
        return parsedSymbols;
      }
    }

    return null;
  }

  public SymbolBase Get(int i)
  {
    return symbols.get(i);
  }

  public int GetLength()
  {
    return symbols.size();
  }

  private void AddSymbol(SymbolBase symbol)
  {
    symbols.add(symbol);
  }

  private SymbolList()
  {
    symbols = new ArrayList<SymbolBase>();
  }
}
