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

        return firstSyntax.Parse(tokenList);
/*
        for (int i=0; i<firstSyntax.GetLength(); i++)
        {
            SymbolBase symbol = firstSyntax.Get(i);
        }
*/
    }

    public void AddSyntax(SymbolList syntax)
    {
        syntaxList.add(syntax);
    }
}
