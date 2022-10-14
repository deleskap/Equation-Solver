package pl.kurs.equationsolver.services;

import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;

public interface IEquationService {
    double calculate (String inputEquation) throws InvalidEquationFormatException, UnknownOperatorException;
}
