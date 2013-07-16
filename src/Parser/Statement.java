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
        SymbolList firstSyntax = syntaxList.get(0);

        for (int i=0; i<firstSyntax.Length; i++)
        {
            SymbolBase symbol = getsymbol;
        }
        return false;
    }

    public void AddSyntax(SymbolList syntax)
    {
        syntaxList.add(syntax);
    }
}
