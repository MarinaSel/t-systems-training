package calculator;

import static org.junit.Assert.*;
import org.junit.Test;
import java.math.*;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void expressionWithSpaces(){
        BigDecimal bigDecimal = new BigDecimal(6);
        assertEquals(calculator.calculate(" 2  * 3  "), bigDecimal.setScale(4, RoundingMode.UNNECESSARY));
    }

    @Test
    public void expressionWithInvalidOperations(){
        assertNull(calculator.calculate("-3+* 5/20* *6"));
    }

    @Test
    public void expressionWithInvalidSymbols(){
        assertNull(calculator.calculate("-3*5/20*6/smth"));
    }

    @Test
    public void roundExpression(){
        BigDecimal bigDecimal = new BigDecimal(3.3333);
        assertEquals(calculator.calculate("2 * 5 / 3"), bigDecimal.setScale(4, RoundingMode.HALF_DOWN));
    }

    @Test
    public void expressionWithAllOperations(){
        BigDecimal bigDecimal = new BigDecimal(-18.8571);
        assertEquals(calculator.calculate("-3 * 8 - 2 * -2 + 8 / 7"), bigDecimal.setScale(4, RoundingMode.HALF_DOWN));
    }

    @Test
    public void nullExpression(){
        assertNull(calculator.calculate(null));
    }

    @Test
    public void emptyExpression(){
        assertNull(calculator.calculate(""));
    }

    @Test
    public void expressionWithParentheses(){
        BigDecimal bigDecimal = new BigDecimal(-10);
        assertEquals(calculator.calculate("(5+3)*2+8-(10+6*(5.0 - 1.0) )"), bigDecimal.setScale(4, RoundingMode.UNNECESSARY));
    }
}
