import si.fri.algotest.entities.TestCase;
import si.fri.algotest.global.ErrorStatus;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * 
 * @author Ana
 */
public class IntegerFactorizationTestCase extends TestCase {

	public BigInteger numberToFactorize;
  public ArrayList<BigInteger> factors;
  
  @Override
  public String toString() {
    // Note that we use a method intArrayToString that was defined in the basicsort.Tools
    // class; this class was attached to the project using the "ProjectJARs" property
    // in the BasicSort.atp configuration file.
    // For the details about basicsort.Tools class see proj/lib folder.
    return super.toString() + ", Data: " + numberToFactorize.toString();
  }
    
}