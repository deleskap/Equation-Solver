package pl.kurs.equationsolver.services;

import org.springframework.stereotype.Service;
import pl.kurs.equationsolver.dao.EquationEventDao;
import pl.kurs.equationsolver.model.EquationEvent;

import java.util.Optional;

@Service
public class EquationEventService implements IEquationEventService{

    private EquationEventDao equationEventDao;

    public EquationEventService(EquationEventDao equationEventDao) {
        this.equationEventDao = equationEventDao;
    }

    @Override
    public void saveEvent(EquationEvent event) {
        equationEventDao.save(Optional.ofNullable(event).orElseThrow(() -> new IllegalArgumentException()));
    }
}
