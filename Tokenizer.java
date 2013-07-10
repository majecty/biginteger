import java.util.ArrayList;

public class Tokenizer
{
  public enum E_Token
  {
    NONE,
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

  public class Eater
  {
    private E_Token type;
    private boolean isFull;

    public Eater()
    {
      Clear();
    }

    public Token GetToken()
    {
      if (type == E_Token.NONE)
      {
        return null;
      }
      return new Token(type);
    }

    public void Clear()
    {
      type = E_Token.NONE;
      isFull = false;
    }

    public boolean CanEat(char character)
    {
      if (type == E_Token.BIGINT)
      {
        return Character.isDigit(character);
      }
      return isFull == false;
    }

    public void Eat(char character)
    {
      switch(type)
      {
        case NONE:
          if (character == '+')
          {
            type = E_Token.PLUS;
            isFull = true;
          }
          else if (Character.isDigit(character))
          {
            type = E_Token.BIGINT;
          }
          break;
        case PLUS:
          // Not Implemented.
          break;
        case BIGINT:
          // Not Implemented.
          break;
      }
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
    String noSpaceInput = RemoveWhiteSpace(line);

    ArrayList<Token> list = new ArrayList<Token>();

    Eater eater = new Eater();
    for (int i=0; i < noSpaceInput.length(); i++)
    {
      if (eater.CanEat(noSpaceInput.charAt(i)) == false)
      {
        list.add(eater.GetToken());
        assert eater.GetToken() != null;
        eater.Clear();
      }

      eater.Eat(noSpaceInput.charAt(i));
    }

    Token lastToken = eater.GetToken();
    if (lastToken != null)
    {
      list.add(lastToken);
    }

//    if (noSpaceInput.equals("+"))
//    {
//      list.add(new Token(E_Token.PLUS));
//    }
//    else
//    {
//      list.add(new Token(E_Token.BIGINT));
//    }
    return list;
  }
}

