import java.util.*;

public class Symbol extends SymbolBase
{
  private String name;
  private BigInteger value;
  private ArrayList<SymbolList> syntaxList;
  protected List<SymbolBase> symbols;

  public static Symbol CreateSymbol(SymbolBase symbolBase)
  {
    return null;
  }

  public String toString()
  {
    String log = "Name: name\n";
    if (value != null)
    {
      log += "Value: " + value.toString() + "\n";
    }
    log += syntaxList.toString() + "\n";
    log += symbols.toString();

    return log;
  }

  public SymbolBase GetClone()
  {
    Symbol newSymbol = new Symbol(name);
    
    if (value != null)
    {
      newSymbol.value = value.GetClone();  
    }
    
    newSymbol.syntaxList = this.syntaxList;

    for (int i=0; i<this.symbols.size(); i++)
    {
      newSymbol.symbols.add(symbols.get(i));
    }

    return newSymbol;
  }

  public String GetName()
  {
      return name;
  }

  public Symbol(String name)
  {
    this.name = name;
    syntaxList = new ArrayList<SymbolList>();
    symbols = new ArrayList<SymbolBase>();
    value = null;
  }

  public BigInteger GetValue()
  {
    return value.GetClone();
  }

  public int Parse(List<Token> tokens)
  {
    assert tokens.size() > 0;

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
        value = symbol.GetValue();
        symbols.add(symbol.GetClone());
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
