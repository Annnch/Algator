/**
 *
 * @author Ana
 */
public class BrentsFactorizationAlgorithm extends IntegerFactorizationAbsAlgorithm {
	static BigInteger one = new BigInteger("1");
	static BigInteger two = new BigInteger("2");
	
	public static ArrayList<BigInteger> factorizaton(BigInteger number) {
		// http://eprints.fri.uni-lj.si/1356/2/Bogataj1.pdf
		// http://connellybarnes.com/documents/factoring.pdf
		
		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
		BigInteger n = number;
		
		BigInteger xi = two;
		BigInteger xm = two;
		
		BigInteger i = one;
		
		while ( true ) {
			
			xi = (xi.multiply(xi).add(one)).mod(n);
			BigInteger s = n.gcd(xi.subtract(xm));
			
			if ( s.compareTo(one) != 0 && s.compareTo(n) != 0  ) {
				factors.add(s);
				factors.add(n.divide(s));
				return factors;
			}
			
			if ( integralPowerOf2(i) ) {
				xm = xi;
			}
			
			i = i.add(one);
		}	

	}

	private static boolean integralPowerOf2(BigInteger z) { // can be different base than 2
		BigInteger pow2 = Constants.one;
		while ( pow2.compareTo(z) != 1 ) { // pow2 <= z
			if ( pow2.compareTo(z) == 0 ) { // pow2 = z
				return true;
			}
			pow2 = pow2.multiply(Constants.two);
		}
		return false;
	}
}