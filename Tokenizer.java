import java.util.ArrayList;

public class Tokenizer
{
  public enum E_Token
  {
    BIGINT,
    PLUS
  }

  public class Token
  {
    private E_Token type;

    public Token(E_Token type)
    {
      this.type = type;
    }

    public E_Token GetType()
    {
      return type;
    }
  }

  public Tokenizer()
  {
  }

  public ArrayList<Token> Parse(String line)
  {
    ArrayList<Token> list = new ArrayList<Token>();
    list.add(new Token(E_Token.PLUS));
    return list;
  }
}

