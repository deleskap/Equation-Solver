package pl.kurs.equationsolver.arithmetics;

import org.springframework.stereotype.Service;

@Service
public class SubtractionService implements ISubtractionService {
    @Override
    public double subtract(double x, double y) {
        return x-y;
    }
}
