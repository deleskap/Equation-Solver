package pl.kurs.equationsolver.arithmetics;

public class DivisionService implements IMathOperation {
    @Override
    public double calculate(double x, double y) {
        if (y == 0.0)
            throw new ArithmeticException("Division by 0 unsupported");
        return x / y;
    }
}
