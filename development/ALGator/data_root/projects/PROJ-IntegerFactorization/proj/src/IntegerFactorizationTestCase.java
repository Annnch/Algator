import si.fri.algotest.entities.TestCase;
import si.fri.algotest.global.ErrorStatus;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * 
 * @author Ana, Andrej
 */
public class IntegerFactorizationTestCase extends TestCase {

  public BigInteger numberToFactorize;
  public ArrayList<BigInteger> factors;

  @Override
  public String toString() {

    return super.toString() + ", Data: " + numberToFactorize.toString();
  }

}