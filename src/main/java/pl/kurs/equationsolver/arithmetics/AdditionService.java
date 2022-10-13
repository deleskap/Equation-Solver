package pl.kurs.equationsolver.arithmetics;

import org.springframework.stereotype.Service;

@Service
public class AdditionService implements IAdditionService{
    @Override
    public double add(double x, double y) {
        return x+y;
    }
}
