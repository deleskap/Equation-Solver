package pl.kurs.arithmetics;

import org.junit.Test;
import pl.kurs.equationsolver.arithmetics.MultiplicationService;

import static org.junit.Assert.assertTrue;

public class MultiplicationServiceTest {
    MultiplicationService multiplication = new MultiplicationService();

    @Test
    public void shouldReturn20(){
        assertTrue(multiplication.multiply(4,5)==20.0);
    }

}
