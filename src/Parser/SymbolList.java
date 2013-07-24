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

  public int ParseIterOld(List<Token> tokenList)
  {
      return 0;
////      Stack<SymbolBase> parseStack = new Stack<SymbolBase>();
//      Stack<int> parsedTokenNum = new Stack<int>();
//      int currentTokenIndex = 0;
//
//      List<Token> originalTokens = tokenList;
//      List<Token> restTokens = tokenList;
//      int currentSymbolIndex = 0;
//
//      while(true)
//      {
//          restTokens = restTokens.subList(currentTokenIndex, restTokens.size());
//
//          SymbolBase currentSymbol = Get(currentSymbolIndex);
//
//          int parsedNum = currentSymbol.ParseIter(restTokens);
//
//          if (parsedNum < 1)
//          {
//              currentSymbol.Reset();
//              currentSymbolIndex -= 1;
//              currentTokenIndex -= parsedTokenNum.pop();
//          }
//          else
//          {
//              currentSymbol += 1;
//              currentTokenIndex += parsedNum;
//          }
//      }
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
          //System.out.println(data);
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
      //System.out.println(tokens);
      boolean result = ParseRecursive(tokens, 0);
      //System.out.println("ParseRecursive Result : " + result);
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
      //System.out.println("ParseRecursive : " + i);
      boolean isSymbolEnd = GetLength() == i;
      boolean isTokenEnd = tokenList.size() == 0;

      if (isSymbolEnd) return true;
      if (isTokenEnd) return false;
      
//      if (isSymbolEnd && isTokenEnd) return true;
//      if (isSymbolEnd || isTokenEnd) return false;

      SymbolBase symbol = Get(i);
      //symbol.Reset();

      int count = 0;
      while(true)
      {
          count ++;
          //System.out.println(count);
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
              //System.out.println(symbol);
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
          //System.out.println("ParseIter Result : " + result);
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

  public List<SymbolData>  ParseAll_Old(List<Token> tokenList)
  {
      return null;
//    assert tokenList.size() > 0;
//    List<SymbolData> parsedSymbols = new ArrayList<SymbolData>();
//
//    for (int i=0; i<symbols.size(); i++)
//    {
//      if (tokenList.size() < 1)
//      {
//        break;
//      }
//
//      SymbolBase symbol = Get(i);
//      int eatedToken = symbol.Parse(tokenList);
//
//      assert eatedToken <= tokenList.size();
//      assert eatedToken >= 0;
//
//      if (eatedToken < 1) break;
//      else
//      {
//        parsedSymbols.add(symbol.ExtractData());
//        tokenList = tokenList.subList(eatedToken, tokenList.size());
//      }
//
//      if (tokenList.size() == 0 && i == symbols.size() - 1)
//      {
//        return parsedSymbols;
//      }
//    }
//
//    return null;
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
