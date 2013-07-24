import java.util.*;

public class Symbol extends SymbolBase
{
  private String name;
  private ArrayList<SymbolList> syntaxList;
  protected List<SymbolData> parsedDatas;

  public static Symbol CreateSymbol(SymbolBase symbolBase)
  {
    return null;
  }

  public String toString()
  {
    String log = "Name: name\n";
    log += syntaxList.toString() + "\n";
    log += parsedDatas.toString();

    return log;
  }

  public List<SymbolData> GetParsedDatas()
  {
      return parsedDatas;
  }

  public SymbolData ExtractData()
  {
    return new SymbolData(name, GetValue(),
                          this, parsedDatas);
  }

  public SymbolBase GetClone()
  {
    Symbol newSymbol = new Symbol(name);
    
    for (int i=0; i<syntaxList.size(); i++)
    {
        newSymbol.syntaxList.add(syntaxList.get(i).GetClone());
    }

    for (int i=0; i<this.parsedDatas.size(); i++)
    {
      newSymbol.parsedDatas.add(parsedDatas.get(i));
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
    parsedDatas = new ArrayList<SymbolData>();
  }

  protected BigInteger GetValue()
  {
      return Calculator.GetValueFrom(this);
  }

  public void Reset()
  {
      iteration = 0;
      parsedDatas = new ArrayList<SymbolData>();
  }

  private int iteration = 0;

  public int ParseIter(List<Token> tokens)
  {
      if (iteration >= syntaxList.size())
      {
          return 0;
      }

      SymbolList currentSyntax = syntaxList.get(iteration);
      int num = currentSyntax.ParseIter(tokens);

      if (num < 1)
      {
          iteration += 1;
          return ParseIter(tokens);
      }
      parsedDatas = currentSyntax.ExtractParsedDatas();
      return num;
  }

  public void AddSyntax(SymbolList syntax)
  {
    syntaxList.add(syntax);
  }
}
