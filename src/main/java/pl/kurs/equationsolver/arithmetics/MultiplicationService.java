package pl.kurs.equationsolver.arithmetics;

import org.springframework.stereotype.Service;

@Service
public class MultiplicationService implements IMathOperation {
    @Override
    public double calculate(double x, double y) {
        return x * y;
    }
}
