import java.util.*;

public class SymbolList
{
  ArrayList<SymbolBase> symbols;

  public SymbolList GetClone()
  {
      SymbolList ret = new SymbolList();
      for (int i=0; i<symbols.size(); i++)
      {
          ret.AddSymbol(symbols.get(i).GetClone());
      }

      return ret;
  }

  public static SymbolList Make(SymbolBase one)
  {
    SymbolList ret = new SymbolList();
    ret.AddSymbol(one.GetClone());
    return ret;
  }

  public static SymbolList Make(SymbolBase one, SymbolBase two)
  {
    SymbolList ret = new SymbolList();
    ret.AddSymbol(one.GetClone());
    ret.AddSymbol(two.GetClone());
    return ret;
  }

  public static SymbolList Make(SymbolBase one, SymbolBase two, SymbolBase three)
  {
    SymbolList ret = new SymbolList();
    ret.AddSymbol(one.GetClone());
    ret.AddSymbol(two.GetClone());
    ret.AddSymbol(three.GetClone());
    return ret;
  }

  public void Reset()
  {
      for (int i=0; i<GetLength(); i++)
      {
          SymbolBase symbol = Get(i);
          symbol.Reset();
      }
  }

  private int GetEatedTokensNum()
  {
      int ret = 0;
      for (int i=0; i<GetLength(); i++)
      {
          SymbolBase symbol = Get(i);
          SymbolData data = symbol.ExtractData();
          if (data.symbols == null)
          {
              ret += 1;
          }
          else
          {
              ret += data.symbols.size();
          }
      }
      return ret;
  }

  public List<SymbolData> ExtractParsedDatas()
  {
      List<SymbolData> ret = new ArrayList<SymbolData>();
      for (int i=0; i<GetLength(); i++)
      {
          SymbolBase symbol = Get(i);
          ret.add(symbol.ExtractData());
      }
      return ret;
  }

  public int ParseIter(List<Token> tokens)
  {
      boolean result = ParseRecursive(tokens, 0);
      if (result)
      {
          return GetEatedTokensNum();
      }
      else
      {
          return 0;
      }
  }

  public boolean ParseRecursive(List<Token> tokenList, int i)
  {
      boolean isSymbolEnd = GetLength() == i;
      boolean isTokenEnd = tokenList.size() == 0;

      if (isSymbolEnd) return true;
      if (isTokenEnd) return false;
      
      SymbolBase symbol = Get(i);

      while(true)
      {
          int eatedTokenNum = symbol.ParseIter(tokenList);
          if (eatedTokenNum < 1)
          {
              symbol.Reset();
              return false;
          }

          List<Token> newTokenList =
              tokenList.subList(eatedTokenNum, tokenList.size());

          boolean result = ParseRecursive(newTokenList, i+1);

          if (result == true)
          {
              return true;
          }
      }
  }

  public List<SymbolData>  ParseAll(List<Token> tokenList)
  {
      Reset();
      while(true)
      {
          int result = ParseIter(tokenList);

          if (result < 1)
          {
              return null;
          }
          if (result == tokenList.size())
          {
              return ExtractParsedDatas();
          }
      }
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
