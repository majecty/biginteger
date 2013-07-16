import java.util.ArrayList;

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

  private void AddSymbol(SymbolBase symbol)
  {
      symbols.add(symbol);
  }

  private SymbolList()
  {
      symbols = new ArrayList<SymbolBase>();
  }
}
