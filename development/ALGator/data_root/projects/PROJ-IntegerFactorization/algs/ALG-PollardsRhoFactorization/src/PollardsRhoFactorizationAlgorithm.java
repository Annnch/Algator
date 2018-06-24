import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Ana, Andrej
 */
public class PollardsRhoFactorizationAlgorithm extends IntegerFactorizationAbsAlgorithm {

	static BigInteger zero = BigInteger.ZERO;
	static BigInteger one = BigInteger.ONE;
	static BigInteger two = new BigInteger("2");
	
	public ArrayList<BigInteger> execute(BigInteger number) {
		// http://eprints.fri.uni-lj.si/1356/2/Bogataj1.pdf
		// http://connellybarnes.com/documents/factoring.pdf
		// http://math.mit.edu/~goemans/18310S15/factoring-notes.pdf
		
		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
		BigInteger n = number;
		
		BigInteger xi = two;
		BigInteger yi = two;
	
		while ( true ) {
		
			xi = functionF(xi, n);
			yi = functionF(functionF(yi, n), n);
			
			BigInteger s = n.gcd(xi.subtract(yi));
			
			if ( !s.equals(one) && !s.equals(n) ) {
				factors.add(s);
				factors.add(n.divide(s));
				return factors;
			}
			
		}	

	}

	private static BigInteger functionF(BigInteger x, BigInteger n) {
		// x^2 + 1 (mod n)
		return ((x.multiply(x)).add(one)).mod(n);
	}
}