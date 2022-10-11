package pl.kurs.equationsolver.dao;

import org.springframework.stereotype.Service;
import pl.kurs.equationsolver.model.EquationEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Service
public class EquationEventDao implements IEquationEventDao {

    @PersistenceUnit
    private EntityManagerFactory factory;

    @Override
    public void save(EquationEvent event) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(event);
        tx.commit();
        manager.close();
    }
}
