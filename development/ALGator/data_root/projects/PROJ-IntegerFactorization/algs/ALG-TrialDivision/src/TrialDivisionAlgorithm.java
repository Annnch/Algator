import si.fri.algotest.global.ErrorStatus;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public class TrialDivisionAlgorithm extends IntegerFactorizationAbsAlgorithm {

	static BigInteger zero = new BigInteger("0");
	static BigInteger one = new BigInteger("1");
	static BigInteger two = new BigInteger("2");
	static BigInteger three = new BigInteger("3");
	
	@Override
  public ArrayList<BigInteger> execute(BigInteger number) {
		// http://eprints.fri.uni-lj.si/1356/2/Bogataj1.pdf
		
		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
		BigInteger n = number;
		
		while ( n.mod(two).compareTo(zero) == 0 ) {
			n = n.divide(two);
			factors.add(two);
			factors.add(n);
			return factors;
		}
		
		BigInteger d = three;
		
		while ( d.multiply(d).compareTo(n) != 1 ) {
			while ( n.mod(d).equals(zero) ) {
				n = n.divide(d);
				factors.add(d);
				factors.add(n);
				return factors;
			}
			d = d.add(two);
		}
		
		if ( n.equals(one) ) {
			return factors;
		}
		
		factors.add(n);
		
		return factors;
	}

  }