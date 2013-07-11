import java.util.ArrayList;

public class Symbol extends SymbolBase
{
  private String name;
  private ArrayList<SymbolList> syntaxList;

  public String GetName()
  {
      return name;
  }

  public Symbol(String name)
  {
    this.name = name;
    syntaxList = new ArrayList<SymbolList>();
  }

  public void AddSyntax(SymbolList syntax)
  {
    syntaxList.add(syntax);
  }
}
