package pl.kurs.arithmetics;

import org.junit.Test;
import pl.kurs.equationsolver.arithmetics.SubtractionService;

import static org.junit.Assert.assertTrue;

public class SubtractionServiceTest {
    SubtractionService subtraction = new SubtractionService();

    @Test
    public void shouldReturn20() {
        assertTrue(subtraction.calculate(100, 5) == 95.0);
    }

    @Test
    public void shouldReturnMinus15() {
        assertTrue(subtraction.calculate(100, 115) == -15.0);
    }

}
