import java.util.*;

public class Symbol extends SymbolBase
{
  private String name;
  //private BigInteger value;
  private ArrayList<SymbolList> syntaxList;
  protected List<SymbolData> parsedDatas;

  public static Symbol CreateSymbol(SymbolBase symbolBase)
  {
    return null;
  }

  public String toString()
  {
    String log = "Name: name\n";
/*    if (value != null)
    {
      log += "Value: " + value.toString() + "\n";
      }*/
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
    
/*    if (value != null)
    {
      newSymbol.value = value.GetClone();  
    }*/
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
    //    value = null;
  }

  protected BigInteger GetValue()
  {
      return Calculator.GetValueFrom(this);
    //return null;
    //return value.GetClone();
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

//  public int Parse(List<Token> tokens)
//  {
//    assert tokens.size() > 0;
//    parsedDatas = new ArrayList<SymbolData>();
//
//    SymbolList syntax = syntaxList.get(0);
//    List<Token> originalTokens = tokens;
//
//    for (int i=0; i<syntax.GetLength(); i++)
//    {
//      SymbolBase symbol = syntax.Get(i);
//      int eatedNumber = symbol.Parse(tokens);
//
//      if (eatedNumber < 1)
//      {
//        break;
//      }
//      else
//      {
//        parsedDatas.add(symbol.ExtractData());
//        //        value = symbol.GetValue();
//        //parsedDatas.add(symbol.GetClone());
//        tokens = tokens.subList(eatedNumber, tokens.size());
//      }
//
//      if (i == syntax.GetLength() - 1)
//      {
//        return originalTokens.size() - tokens.size();
//      }
//    }
//
//    return 0;
//  }

  public void AddSyntax(SymbolList syntax)
  {
    syntaxList.add(syntax);
  }
}
