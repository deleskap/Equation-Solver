package pl.kurs.equationsolver.arithmetics;

import org.springframework.stereotype.Service;

@Service
public class SubstractionService implements ISubstractionService{
    @Override
    public double substract(double x, double y) {
        return x-y;
    }
}
