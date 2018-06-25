import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import si.fri.algotest.entities.EVariable;
import si.fri.algotest.entities.VariableType;
import si.fri.algotest.entities.EResult;
import si.fri.algotest.entities.ETestSet;
import si.fri.algotest.entities.TestCase;
import si.fri.algotest.execute.DefaultTestSetIterator;
import si.fri.algotest.global.ATLog;
import si.fri.algotest.tools.ATTools;
import si.fri.algotest.global.ErrorStatus;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author Ana, Andrej
 */
public class IntegerFactorizationTestSetIterator extends DefaultTestSetIterator {

  @Override
  public TestCase getCurrent() {
    if (currentInputLine == null) {
      ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "No valid input!");
      return null;
    }

    // sort-project specific: line contains at least 3 fileds: testName, n and group
    String[] fields = currentInputLine.split(":");
    if (fields.length < 3) {
      reportInvalidDataFormat("to few fields");
      return null;
    }

    String testName = fields[0];
    String probSize;
    int numberSize;
    try {
      probSize = fields[1];
    } catch (Exception e) {
      reportInvalidDataFormat("'n' is not a number");
      return null;
    }
    String group = fields[2];

    BigInteger number = new BigInteger("1");

    switch (group) {


    case "RND": // chooses a random number

      // http://www.java2s.com/Tutorials/Java/Algorithms_How_to/Random/Generate_a_random_BigInteger_value.htm
      BigInteger bigInteger = new BigInteger(probSize);// uper limit
      BigInteger min = bigInteger.divide(new BigInteger("10"));// lower limit
      BigInteger bigInteger1 = bigInteger.subtract(min);
      Random rnd = new Random(System.currentTimeMillis());
      int maxNumBitLength = bigInteger.bitLength();

      BigInteger aRandomBigInt;

      aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
      if (aRandomBigInt.compareTo(min) < 0)
        aRandomBigInt = aRandomBigInt.add(min);
      if (aRandomBigInt.compareTo(bigInteger) >= 0)
        aRandomBigInt = aRandomBigInt.mod(bigInteger1).add(min);

      number = aRandomBigInt;

      break;


    case "RNDMILLIONPRIMES": // chooses two random prime numbers from the same million of prime numbers

      Random rnd1 = new Random(System.currentTimeMillis());

      for (int i = 0; i < 2; i++) {
        Integer r = rnd1.nextInt(1000000) + 1;

        try (Stream<String> lines = Files.lines(Paths.get(filePath + "/primes/Primes" + probSize + ".txt"))) {
          String random_prime = lines.skip(r - 1).findFirst().get();
          number = number.multiply(new BigInteger(random_prime));
        } catch (IOException e) {
          reportInvalidDataFormat(e.toString());
        }
      }

      break;

    case "RNDPRIMES": // chooses two random prime numbers

      Random rnd2 = new Random(System.currentTimeMillis());

      for (int i = 0; i < 2; i++) {
        Integer r = rnd2.nextInt(1000000) + 1;
        Integer n = rnd2.nextInt(50) + 1;

        try (Stream<String> lines = Files.lines(Paths.get(filePath + "/primes/Primes" + n + ".txt"))) {
          String random_prime = lines.skip(r - 1).findFirst().get();
          number = number.multiply(new BigInteger(random_prime));
        } catch (IOException e) {
          reportInvalidDataFormat(e.toString());
        }
      }

      break;

    }

    numberSize = number.toString().length();

    EVariable testIDPar = EResult.getTestIDParameter("Test-" + Integer.toString(lineNumber));

    EVariable parameter1 = new EVariable("Test", "Test name", VariableType.STRING, testName);
    EVariable parameter2 = new EVariable("N", "Number of elements", VariableType.INT, numberSize);
    EVariable parameter3 = new EVariable("Group", "A name of a group of tests", VariableType.STRING, group);

    IntegerFactorizationTestCase tCase = new IntegerFactorizationTestCase();
    // ID
    tCase.addParameter(testIDPar);
    // input parameters
    tCase.addParameter(parameter1);
    tCase.addParameter(parameter2);
    tCase.addParameter(parameter3);

    tCase.numberToFactorize = number;
    return tCase;
  }
}
