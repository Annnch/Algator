import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Ana, Andrej
 */
public class BrentsFactorizationAlgorithm extends IntegerFactorizationAbsAlgorithm {

	static BigInteger one = BigInteger.ONE;
	static BigInteger two = new BigInteger("2");

	public ArrayList<BigInteger> execute(BigInteger number) {

		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
		BigInteger n = number;

		BigInteger xi = two;
		BigInteger xm = two;

		BigInteger i = one;

		while (true) {

			xi = (xi.multiply(xi).add(one)).mod(n);
			BigInteger s = n.gcd(xi.subtract(xm));

			if (!s.equals(one) && !s.equals(n)) {
				factors.add(s);
				factors.add(n.divide(s));
				return factors;
			}

			if (integralPowerOf2(i)) {
				xm = xi;
			}

			i = i.add(one);
		}

	}

	private static boolean integralPowerOf2(BigInteger z) { 
		if (z.and(z.subtract(BigInteger.ONE)) == 0) {
			return true;
		} else
			return false;
	}
}