
public abstract class SymbolBase
{
  public abstract String GetName();
  public boolean IsSame(SymbolBase rhs)
  {
    String rhsName = rhs.GetName();
    return this.GetName().equals(rhsName);
  }
}

