import java.util.*;

public abstract class SymbolBase
{
  public abstract String GetName();
  protected abstract BigInteger GetValue();

  public boolean IsSame(SymbolBase rhs)
  {
    String rhsName = rhs.GetName();
    return this.GetName().equals(rhsName);
  }

  public abstract int ParseIter(List<Token> tokens);
  public abstract void Reset();

  public abstract SymbolData ExtractData();
  public abstract SymbolBase GetClone();
}
