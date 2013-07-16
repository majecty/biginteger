import java.util.ArrayList;

public class Statement
{
    private ArrayList<SymbolList> syntaxList;

    public Statement()
    {
        syntaxList = new ArrayList<SymbolList>();
    }
    
    public boolean Parse(ArrayList<Token> tokenList)
    {
        return false;
    }

    public void AddSyntax(SymbolList syntax)
    {
        syntaxList.add(syntax);
    }
}
