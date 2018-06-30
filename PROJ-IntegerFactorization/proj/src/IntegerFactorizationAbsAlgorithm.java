import si.fri.algotest.entities.VariableSet;
import si.fri.algotest.entities.EVariable;
import si.fri.algotest.entities.VariableType;
import si.fri.algotest.entities.TestCase;
import si.fri.algotest.execute.AbsAlgorithm;
import si.fri.algotest.global.ErrorStatus;

import java.math.BigInteger;
import java.util.ArrayList;


/**
 *
 * @author Ana, Andrej
 */
public abstract class IntegerFactorizationAbsAlgorithm extends AbsAlgorithm {

  IntegerFactorizationTestCase factorizationTestCase;

  @Override
  public TestCase getTestCase() {
    return factorizationTestCase;
  }

  @Override
  public ErrorStatus init(TestCase test) {
    if (test instanceof IntegerFactorizationTestCase) {
      factorizationTestCase = (IntegerFactorizationTestCase) test;
      return ErrorStatus.STATUS_OK;
    } else
      return ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR_CANT_PERFORM_TEST, "Invalid test:" + test);
  }
  
  @Override
  public void run() {
    factorizationTestCase.factors = execute(factorizationTestCase.numberToFactorize);
  }

  
  @Override
  public VariableSet done() {
    VariableSet result = new VariableSet(factorizationTestCase.getParameters());

    BigInteger r = BigInteger.ONE;

    for ( BigInteger f : factorizationTestCase.factors ) {
      r = r.multiply(f);
    }

    boolean checkOK = r.equals(factorizationTestCase.numberToFactorize);
    
    EVariable passPar = new EVariable("Check", "", VariableType.STRING, checkOK ? "OK" : "NOK");
    result.addVariable(passPar, true);

    return result;
  }   

  protected abstract ArrayList<BigInteger> execute(BigInteger number);
  
}
