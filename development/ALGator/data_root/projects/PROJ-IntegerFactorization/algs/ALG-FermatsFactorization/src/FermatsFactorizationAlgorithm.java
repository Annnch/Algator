import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Ana, Andrej
 */
public class FermatsFactorizationAlgorithm extends IntegerFactorizationAbsAlgorithm {
	
	static BigInteger zero = BigInteger.ZERO;
	static BigInteger one = BigInteger.ONE;
	static BigInteger two = new BigInteger("2");
	
	public ArrayList<BigInteger> execute(BigInteger number) {
		
		BigInteger n = number;
		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();

		if ( n.mod(two).equals(zero) ) {
			factors.add(two);
			factors.add(n.divide(two));
			return factors;
		}
		
		BigInteger x = sqrtC(number);
		BigInteger y = x.multiply(x).subtract(n);
		
		while ( !isSquare(y) ) {
			x = x.add(one);
			y = x.multiply(x).subtract(n);
		}
		
		BigInteger s = sqrtC(y);
		BigInteger a = x.subtract(s);
		BigInteger b = x.add(s);
		
		factors.add(a);
		factors.add(b);
		
		return factors;
	}

	public static BigInteger sqrtC(BigInteger N) {
		// https://csdspnest.blogspot.com/2014/03/bigintegersqrt.html

        BigInteger o = one;
        BigInteger t = two;
        
        BigInteger u = N; // upper bound of search region
        // initial search value = ceil(0.5*N)
        BigInteger r = N.mod(t).compareTo(o) == 0 ? N.divide(t).add(o) : N.divide(t);
        BigInteger b = zero; // lower bound of search region
        
        // new search value is ceiling of mid of upper and lower bounds
        while (true) {
            BigInteger r2 = r.pow(2); // square of search value
            
            if (r2.compareTo(N) > 0) {
                // too large, lower search upper bound
                u = r;
                BigInteger s = u.add(b);
                r = s.mod(t).compareTo(o) == 0 ? s.divide(t).add(o) : s.divide(t);
            } else if (r2.compareTo(N) < 0) {
                // too small, increase search lower bound
                b = r;
                BigInteger s = u.add(b);
                r = s.mod(t).compareTo(o) == 0 ? s.divide(t).add(o) : s.divide(t);
            } else {
                // find the value
                break;
            }
            
            // handle non-integer square root, 
            // whose ceiling = final upper bound
            if (u.compareTo(r) == 0) {
                break;
            }
        }
        
        return r;
    }

    public static BigInteger sqrtF(BigInteger n) {
    	// https://gist.github.com/JochemKuijpers/cd1ad9ec23d6d90959c549de5892d6cb

		BigInteger a = one;
		BigInteger b = n.shiftRight(5).add(BigInteger.valueOf(8));
		while (b.compareTo(a) >= 0) {
			BigInteger mid = a.add(b).shiftRight(1);
			if (mid.multiply(mid).compareTo(n) > 0) {
				b = mid.subtract(one);
			} else {
				a = mid.add(one);
			}
		}
		return a.subtract(one);
	}

	public static boolean isSquare(BigInteger n) {
		BigInteger f = sqrtF(n);
		BigInteger c = sqrtC(n);
		return f.equals(c);
	}
}