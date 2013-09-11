import java.util.ArrayList;

public class Tokenizer
{

  public class Eater
  {
    private Token currentToken;
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
      return currentToken;
    }

    public void Clear()
    {
      currentToken = null;
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
            currentToken = new Token(E_Token.PLUS);
            isFull = true;
          }
          else if (character == '-')
          {
              type = E_Token.MINUS;
              currentToken = new Token(E_Token.MINUS);
              isFull = true;
          }
          else if (character == '*')
          {
              type = E_Token.MUL;
              currentToken = new Token(E_Token.MUL);
              isFull = true;
          }
          else if (Character.isDigit(character))
          {
            currentToken = new Token(E_Token.BIGINT);
            type = E_Token.BIGINT;
            currentToken.AddTail(Character.getNumericValue(character));
          }
          break;
        case PLUS:
          assert false;
          break;
        case BIGINT:
          assert Character.isDigit(character);
          currentToken.AddTail(Character.getNumericValue(character));
          // Not Implemented.
          break;
        default:
          assert false;
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

    return list;
  }
}

