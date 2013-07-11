public class Token extends SymbolBase
{
  private E_Token type;
  private BigInteger data;

  public Token(E_Token type)
  {
    this.type = type;
    data = new BigInteger(0);
  }

  public String GetName()
  {
      return type.name();
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
