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

  public static SymbolList Make(SymbolBase one, SymbolBase two, SymbolBase three)
  {
    SymbolList ret = new SymbolList();
    ret.AddSymbol(one);
    ret.AddSymbol(two);
    ret.AddSymbol(three);
    return ret;
  }

  public boolean Parse(List<Token> tokenList)
  {
    for (int i=0; i<symbols.size(); i++)
    {
      SymbolBase symbol = symbols.get(i);
      int eatedToken = symbol.Parse(tokenList);

      assert eatedToken <= tokenList.size();
      assert eatedToken >= 0;

      if (eatedToken < 1) break;
      else
      {
        tokenList = tokenList.subList(eatedToken, tokenList.size());
        //        tokenList.removeRange(0, eatedToken);
      }

      if (tokenList.size() == 0 && i == symbols.size() - 1)
      {
        return true;
      }
    }

    return false;
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
