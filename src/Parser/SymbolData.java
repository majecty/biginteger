import java.util.*;

class SymbolData
{
  public final String name;
  public final BigInteger value;
  public final SymbolBase parent;
  public final List<SymbolData> symbols;

  public SymbolData(String name, BigInteger value, SymbolBase parent,
                    List<SymbolData> symbols)
  {
    this.name = name;
    this.value = value;
    this.parent = parent;
    this.symbols = symbols;
  }


  public String toString()
  {
    String log = "Name: " + name + ", ";
    if (value != null)
    {
      log += "Value: " + value.toString() + ", ";
    }
    return log;
  }
}
