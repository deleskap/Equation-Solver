package pl.kurs.arithmetics;

import org.junit.Test;
import pl.kurs.equationsolver.arithmetics.SubtractionService;

import static org.junit.Assert.assertTrue;

public class SubtractionServiceTest {
    SubtractionService subtraction = new SubtractionService();

    @Test
    public void shouldReturn20(){
        assertTrue(subtraction.subtract(100,5)==95.0);
    }

}
