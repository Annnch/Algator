import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Ana, Andrej
 */
public class TrialDivisionAlgorithm extends IntegerFactorizationAbsAlgorithm {

	static BigInteger zero = BigInteger.ZERO;
	static BigInteger one = BigInteger.ONE;
	static BigInteger two = new BigInteger("2");
	
	@Override
  	public ArrayList<BigInteger> execute(BigInteger number) {
		// http://eprints.fri.uni-lj.si/1356/2/Bogataj1.pdf
		
		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
		BigInteger n = number;		
		BigInteger d = two;
		
		while ( d.multiply(d).compareTo(n) != 1 ) {
			if ( n.mod(d).equals(zero) ) {
				n = n.divide(d);
				factors.add(d);
				factors.add(n);
				return factors;
			}
			d = d.add(one);
		}
		
		if ( n.equals(one) ) {
			return factors;
		}
		
		factors.add(n);
		
		return factors;
	}

  }