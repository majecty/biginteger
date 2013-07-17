import java.util.*;

public abstract class SymbolBase
{
  public abstract String GetName();
  public abstract BigInteger GetValue();

  public boolean IsSame(SymbolBase rhs)
  {
    String rhsName = rhs.GetName();
    return this.GetName().equals(rhsName);
  }

  // Return number of eated token number
  public abstract int Parse(List<Token> tokens);

  public abstract SymbolBase GetClone();
}
