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

		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
		BigInteger n = number;
		BigInteger d = two;

		BigInteger sqrtN = sqrt(n);

		while (d.compareTo(sqrtN) != 1) {
			if (n.mod(d).equals(zero)) {
				n = n.divide(d);
				factors.add(d);
				factors.add(n);
				return factors;
			}
			d = d.add(one);
		}

		if (n.equals(one)) {
			return factors;
		}

		factors.add(n);

		return factors;
	}

	private static BigInteger sqrt(BigInteger n) {
		// https://gist.github.com/JochemKuijpers/cd1ad9ec23d6d90959c549de5892d6cb
		
		BigInteger a = BigInteger.ONE;
		BigInteger b = n.shiftRight(5).add(BigInteger.valueOf(8));
		while (b.compareTo(a) >= 0) {
			BigInteger mid = a.add(b).shiftRight(1);
			if (mid.multiply(mid).compareTo(n) > 0) {
				b = mid.subtract(BigInteger.ONE);
			} else {
				a = mid.add(BigInteger.ONE);
			}
		}
		return a.subtract(BigInteger.ONE);
	}

}
