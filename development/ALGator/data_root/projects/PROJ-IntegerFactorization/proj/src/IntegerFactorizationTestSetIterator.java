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
 * @author Ana
 */
public class IntegerFactorizationTestSetIterator extends DefaultTestSetIterator {
   
  @Override
  public TestCase getCurrent() {
    if (currentInputLine == null) {
      ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "No valid input!");
      return null;
    }
    
    // sort-project specific: line contains at least 3 fileds: testName, n and group
    String [] fields = currentInputLine.split(":"); 
    if (fields.length < 3) {
      reportInvalidDataFormat("to few fields");
      return null;
    }
    
    String testName = fields[0];
    int probSize;
    try {
      probSize = Integer.parseInt(fields[1]);
    } catch (Exception e) {
      reportInvalidDataFormat("'n' is not a number");
      return null;
    }
    String group = fields[2];


    
    int rndSize = 100; // the size of the rnadom numbers used in the array (used with RND group). 

    
    // unique identificator of a test
    EVariable testIDPar = EResult.getTestIDParameter("Test-" + Integer.toString(lineNumber));
    
    EVariable parameter1 = new EVariable("Test",  "Test name",                    VariableType.STRING, testName);
    EVariable parameter2 = new EVariable("N",     "Number of elements",           VariableType.INT,    probSize);
    EVariable parameter3 = new EVariable("Group", "A name of a group of tests",   VariableType.STRING, group);
    
    IntegerFactorizationTestCase tCase = new IntegerFactorizationTestCase();
    // ID
    tCase.addParameter(testIDPar);
    // input parameters
    tCase.addParameter(parameter1);
    tCase.addParameter(parameter2);
    tCase.addParameter(parameter3);
    
    // read the input data (content of an aray to sort); to source of the
    // data is defined by the group parameter
    BigInteger number = new BigInteger("1");
    //int [] array = new int[probSize];
    //int i=0;
    switch (group) {
      /*case "INLINE":
    if (fields.length < 4) {
          reportInvalidDataFormat("to few fields");
          return null;
        }
    String data[] = fields[3].split(" ");
    if (data.length != probSize) {
      reportInvalidDataFormat("invalid number of inline data");
      return null;
    }
    
    try {
      for (i = 0; i < probSize; i++) 
        //array[i] = BigInteger.parseInt(data[i]);
    } catch (Exception e) {
      reportInvalidDataFormat("invalid type of inline data - data " + (i+1));
      return null;
    }
    break;*/
      case "RND":
    Random rnd = new Random(System.currentTimeMillis());
        try {rndSize = probSize;} catch (Exception e) {rndSize=Integer.MAX_VALUE;}

    for (int i = 0; i < 2; i++) {
        number = number.multiply(BigInteger.valueOf(rnd.nextInt(rndSize)));
      }

    break;
       case "RNDPRIMES":  // first parameter is filemane, second the offset (from where numbers are read)

       Random rnd1 = new Random(System.currentTimeMillis());

    for (int i = 0; i < 2; i++) {
        Integer r = rnd1.nextInt(1000000) + 1; 

        try (Stream<String> lines = Files.lines(Paths.get(filePath + "/primes/Primes" + probSize + ".txt"))) {
            String random_prime = lines.skip(r-1).findFirst().get();
            number = number.multiply(new BigInteger(random_prime));
        } catch (IOException e) {
          reportInvalidDataFormat(e.toString());
        }
      }
      probSize = number.toString().length();
    
    }


    
    EVariable parameter4 = new EVariable("RndSize", "The size of RND nummers",   VariableType.INT, rndSize);
    tCase.addParameter(parameter4);



    tCase.numberToFactorize = number;
    return tCase; 
  } 
}
 