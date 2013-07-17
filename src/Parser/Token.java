import java.util.*;

public class Token extends SymbolBase
{
  private E_Token type;
  private BigInteger data;

  public String toString()
  {
    String log="Type: " + type.toString() + "\n";
    if (data != null)
    {
      log += data.toString() + "\n";
    }

    return log;
  }

  public Token(E_Token type)
  {
    this.type = type;
    data = new BigInteger(0);
  }

  public String GetName()
  {
    return type.name();
  }

  public SymbolBase GetClone()
  {
    Token ret = new Token(type);
    if (data != null)
    {
      ret.data = data.GetClone();
    }
    return ret;
  }

  public int Parse(List<Token> tokens)
  {
    Token token = tokens.get(0);
    if (this.IsSame(token))
    {
      data = token.GetValue();
      return 1;
    }
    return 0;
  }

  public BigInteger GetValue()
  {
    if (type == E_Token.BIGINT)
    {
      return data.GetClone();
    }
    return null;
  }

  public int GetValueInt()
  {
    assert type == E_Token.BIGINT;

    return data.value;
  }

  public void AddTail(int num)
  {
    assert num >= 0;
    assert num < 10;
    assert type == E_Token.BIGINT;

    data = new BigInteger(data.value * 10 + num);
  }

  public E_Token GetType()
  {
    return type;
  }
}
