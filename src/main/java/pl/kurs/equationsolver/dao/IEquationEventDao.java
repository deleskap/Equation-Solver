package pl.kurs.equationsolver.dao;

import pl.kurs.equationsolver.model.EquationEvent;

public interface IEquationEventDao {
    void save(EquationEvent event);
}
