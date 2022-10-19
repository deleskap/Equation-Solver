package pl.kurs.equationsolver.arithmetics;

public class AdditionService implements IMathOperation {
    @Override
    public double calculate(double x, double y) {
        return x + y;
    }
}
