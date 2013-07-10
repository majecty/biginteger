
public class Test
{
  public static void main(String args[])
  {
    System.out.println("Test start");

    TestSmallAdd();

    System.out.println("Test end.");
  }

  public static void TestSmallAdd()
  {
    BigInteger lhs = new BigInteger(3);
    BigInteger rhs = new BigInteger(3);
    BigInteger result = null;
    Calculator calc = new Calculator();

    result = calc.Add(lhs, rhs);

    assert result.GetInt() == 6;
  }
}
