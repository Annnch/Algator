/**
 *
 * @author Ana
 */
public class FermatsFactorizationAlgorithm extends IntegerFactorizationAbsAlgorithm {
	static BigInteger one = new BigInteger("1");
	
	public static ArrayList<BigInteger> factorizaton(BigInteger number) {
		// http://eprints.fri.uni-lj.si/1356/2/Bogataj1.pdf
		// http://wnlabs.com/pdf/Fermat_factoring_with_traditional_sieve_method.pdf
		
		BigInteger n = number;
		
		BigInteger x = sqrtCeil(number);
		BigInteger y = x.multiply(x).subtract(n);
		
		while ( !isSquare(y) ) {
			x = x.add(one);
			y = x.multiply(x).subtract(n);
		}
		
		BigInteger s = sqrtCeil(y);
		BigInteger a = x.subtract(s);
		BigInteger b = x.add(s);
		
		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
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