import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Ana, Andrej
 */
public class FermatsFactorizationAlgorithm extends IntegerFactorizationAbsAlgorithm {
	static BigInteger zero = new BigInteger("0");
	static BigInteger one = new BigInteger("1");
	static BigInteger two = new BigInteger("2");
	
	public ArrayList<BigInteger> execute(BigInteger number) {
		// http://eprints.fri.uni-lj.si/1356/2/Bogataj1.pdf
		// http://wnlabs.com/pdf/Fermat_factoring_with_traditional_sieve_method.pdf
		
		BigInteger n = number;
		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();

		if ( n.mod(two).equals(zero) ) {
			factors.add(two);
			factors.add(n.divide(two));
			return factors;
		}
		
		BigInteger x = sqrtCeil(number);
		BigInteger y = x.multiply(x).subtract(n);
		
		while ( !isSquare(y) ) {
			x = x.add(one);
			y = x.multiply(x).subtract(n);
		}
		
		BigInteger s = sqrtCeil(y);
		BigInteger a = x.subtract(s);
		BigInteger b = x.add(s);
		
		factors.add(a);
		factors.add(b);
		
		return factors;
	}

	public static BigInteger sqrtCeil(BigInteger n) {
		Double d = Math.ceil(Math.sqrt(n.doubleValue()));
		n = BigInteger.valueOf(d.longValue());
		return n;
	}

	public static boolean isSquare(BigInteger n) {
		Double s = Math.sqrt(n.doubleValue());
		if ( Math.ceil(s) == Math.floor(s) ) {
			return true;
		}
		return false;
	}
}