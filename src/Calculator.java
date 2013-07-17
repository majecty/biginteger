import java.util.*;

public class Calculator
{
  public BigInteger Add(BigInteger lhs, BigInteger rhs)
  {
    return new BigInteger(lhs.value + rhs.value);
  }

  public static BigInteger Calc(List<SymbolBase> symbolList)
  {
    if (symbolList.size() == 1)
    {
      SymbolBase symbol = symbolList.get(0);
      BigInteger value = symbol.GetValue();

      return value;
    }

    return null;
  }
}
