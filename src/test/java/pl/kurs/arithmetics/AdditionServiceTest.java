package pl.kurs.arithmetics;

import org.junit.Test;
import pl.kurs.equationsolver.arithmetics.AdditionService;

import static org.junit.Assert.*;

public class AdditionServiceTest {
    AdditionService addition = new AdditionService();

    @Test
    public void shouldReturn20() {
        assertTrue(addition.calculate(4, 16) == 20);
    }

    @Test
    public void shouldReturnMinus4() {
        assertTrue(addition.calculate(4, -8) == -4);
    }

}
