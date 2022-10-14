package pl.kurs.services;

import org.junit.Before;
import org.junit.Test;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kurs.equationsolver.arithmetics.AdditionService;
import pl.kurs.equationsolver.arithmetics.DivisionService;
import pl.kurs.equationsolver.arithmetics.MultiplicationService;
import pl.kurs.equationsolver.arithmetics.SubstractionService;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;
import pl.kurs.equationsolver.services.EquationEventService;
import pl.kurs.equationsolver.services.EquationService;
import static org.junit.Assert.*;

public class EquationServiceTest {

//
    @Mock AdditionService additionMock;
    @Mock SubstractionService substractionMock;
    @Mock DivisionService divisionMock;
    @Mock MultiplicationService multiplicationMock;
    @Mock EquationEventService equationEventMock;
      EquationService equationService;
//
    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        equationService = new EquationService(additionMock, substractionMock, multiplicationMock, divisionMock,equationEventMock);
    }
//
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
        equationService.calculate("2 / 0");
    }

    @Test(expected = UnknownOperatorException.class)
    public void shouldThrowUnknownOperatorException() throws InvalidEquationFormatException, UnknownOperatorException {
        equationService.calculate("2 : 2");
    }

    //calculate()
    @Test
    public void shouldReturn5() throws InvalidEquationFormatException, UnknownOperatorException {
        Mockito.when(additionMock.add(2,4)).thenReturn(6.0);
        Mockito.when(multiplicationMock.multiply(2,2)).thenReturn(4.0);
        Mockito.when(divisionMock.divide(2,1)).thenReturn(1.0);
        Mockito.when(substractionMock.substract(4,2)).thenReturn(2.0);
        Mockito.when(substractionMock.substract(6,1)).thenReturn(5.0);

        assertTrue(equationService.calculate("2 + 2 * 2 - 2 / 1 ")==5.0);

    }

}
