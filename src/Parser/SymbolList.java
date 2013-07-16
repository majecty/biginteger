public class SymbolList
{
  public static SymbolList Make(SymbolBase one)
  {
      return new SymbolList();
  }
    
  public static SymbolList Make(SymbolBase one, SymbolBase two, SymbolBase three)
  {
      return new SymbolList();
  }

  private SymbolList()
  {
  }

  public SymbolList(SymbolBase one)
  {
  }

  public SymbolList(SymbolBase one, SymbolBase two)
  {
  }

  public SymbolList(SymbolBase one, SymbolBase two, SymbolBase three)
  {
  }
}
