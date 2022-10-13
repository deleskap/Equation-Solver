package pl.kurs.equationsolver.arithmetics;

import org.springframework.stereotype.Service;

@Service
public class DivisionService implements IDivisionService{
    @Override
    public double divide(double x, double y) {
        return x/y;
    }
}
