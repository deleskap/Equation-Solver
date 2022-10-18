package pl.kurs.arithmetics;

import org.junit.Test;
import pl.kurs.equationsolver.arithmetics.DivisionService;

import static org.junit.Assert.assertTrue;

public class DivisionServiceTest {
    DivisionService division = new DivisionService();

    @Test
    public void shouldReturn5() {
        assertTrue(division.calculate(100, 20) == 5);
    }

    @Test
    public void shouldReturnMinus4() {
        assertTrue(division.calculate(4, -1) == -4);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldThrowArithmeticExceptionWhenZero() {
        division.calculate(4, 0);
    }
}
