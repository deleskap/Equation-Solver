package pl.kurs.arithmetics;

import org.junit.Test;
import pl.kurs.equationsolver.arithmetics.DivisionService;
import static org.junit.Assert.assertTrue;

public class DivisionServiceTest {
    DivisionService division = new DivisionService();

    @Test
    public void shouldReturn5(){
        assertTrue(division.divide(100,20)==5);
    }

}
