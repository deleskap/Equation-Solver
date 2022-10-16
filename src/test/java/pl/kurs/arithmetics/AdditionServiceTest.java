package pl.kurs.arithmetics;

import org.junit.Test;
import pl.kurs.equationsolver.arithmetics.AdditionService;

import java.util.Optional;

import static org.junit.Assert.*;

public class AdditionServiceTest {
    AdditionService addition = new AdditionService();

    @Test
    public void shouldReturn20(){
        assertTrue(addition.add(4,16)==20.0);
    }

}
