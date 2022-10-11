package pl.kurs.equationsolver.exceptions;

public class UnknownOperatorException extends Exception{
    public UnknownOperatorException(String message) {
        super(message);
    }

    public UnknownOperatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
