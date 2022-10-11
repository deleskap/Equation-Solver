package pl.kurs.equationsolver.exceptions;

public class InvalidEquationFormatException extends Exception{
    public InvalidEquationFormatException(String message) {
        super(message);
    }

    public InvalidEquationFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
