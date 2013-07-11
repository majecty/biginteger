import java.util.ArrayList;

public class SymbolParser
{
  private Symbol Operator;
  private Symbol Op_Plus;

  private void Initialize()
  {
    // Statement := Number Operator Number
    // Nnumber := PLUS BIGINT
    //      | BIGINT
    // Operator := PLUS
    //ArrayList<Tokenizer.Token> statement = new ArrayList<Tokenizer.Token>();
    //

    
    Op_Plus = new Symbol("Op_Plus");
    Op_Plus.AddSyntax(
            SymbolList.Make(new Token(E_Token.PLUS)));
    Operator = new Symbol("Operator");
  }

  public SymbolParser()
  {
  }

  public void ParseSymbol(ArrayList<Token> tokenList)
  {
  }
}
