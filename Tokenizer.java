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

  private String RemoveWhiteSpace(String line)
  {
    return line.replaceAll(" ", "");
  }

  public ArrayList<Token> Parse(String line)
  {
    String input = RemoveWhiteSpace(line);

    ArrayList<Token> list = new ArrayList<Token>();
    if (input.equals("+"))
    {
      list.add(new Token(E_Token.PLUS));
    }
    else
    {
      list.add(new Token(E_Token.BIGINT));
    }
    return list;
  }
}

