package pl.kurs.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kurs.equationsolver.app.Main;
import pl.kurs.equationsolver.arithmetics.*;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;
import pl.kurs.equationsolver.services.EquationEventService;
import pl.kurs.equationsolver.services.EquationService;

import java.util.HashMap;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Main.class})
@ActiveProfiles("dev")
public class EquationServiceTest {

    @Mock
    EquationEventService equationEventMock;
    @Autowired
    HashMap<String, IMathOperation> operationHashMap;

    EquationService equationService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        equationService = new EquationService(equationEventMock, operationHashMap);
    }

    // validateInput()
    @Test(expected = InvalidEquationFormatException.class)
    public void shouldThrowInvalidEquationExceptionWhenLetter() throws InvalidEquationFormatException, UnknownOperatorException {
        equationService.calculate("x + 2");
    }

    @Test(expected = InvalidEquationFormatException.class)
    public void shouldThrowInvalidEquationExceptionWhenNoSpace() throws InvalidEquationFormatException, UnknownOperatorException {
        equationService.calculate("2+ 2");
    }

    @Test(expected = InvalidEquationFormatException.class)
    public void shouldThrowInvalidEquationExceptionWhenDivisionByZero() throws InvalidEquationFormatException, UnknownOperatorException {
        equationService.calculate("3 + 2 / 0");
    }

    @Test(expected = UnknownOperatorException.class)
    public void shouldThrowUnknownOperatorExceptionWhenColon() throws InvalidEquationFormatException, UnknownOperatorException {
        equationService.calculate("2 : 2");
    }

    @Test(expected = InvalidEquationFormatException.class)
    public void shouldThrowInvalidEquationFormatExceptionWhenExtraSpace() throws InvalidEquationFormatException, UnknownOperatorException {
        equationService.calculate("2  / 2");
    }

    //    //calculate()
    @Test
    public void shouldReturn4() throws InvalidEquationFormatException, UnknownOperatorException {

        assertTrue(equationService.calculate("2 + 2 * 2 - 2 / 1") == 4.0);
    }

    @Test
    public void shouldReturn5() throws InvalidEquationFormatException, UnknownOperatorException {
        assertTrue(equationService.calculate("2 + 3") == 5.0);
    }

    @Test
    public void shouldReturnMinus1() throws InvalidEquationFormatException, UnknownOperatorException {
        assertTrue(equationService.calculate("2 + -3") == -1.0);
    }

    @Test
    public void shouldReturn9801() throws InvalidEquationFormatException, UnknownOperatorException {
        assertTrue(equationService.calculate("99 * 99") == 9801);
    }

    @Test
    public void shouldReturn2() throws InvalidEquationFormatException, UnknownOperatorException {
        assertTrue(equationService.calculate("4 / 2       ") == 2);
    }

    @Test
    public void shouldReturn3() throws InvalidEquationFormatException, UnknownOperatorException {
        assertTrue(equationService.calculate("5 - 2") == 3);
    }
}
