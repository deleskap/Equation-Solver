package pl.kurs.equationsolver.arithmetics;

import org.springframework.stereotype.Service;

@Service
public class MultiplicationService implements IMultiplicationService{
    @Override
    public double multiply(double x, double y) {
        return x*y;
    }
}
