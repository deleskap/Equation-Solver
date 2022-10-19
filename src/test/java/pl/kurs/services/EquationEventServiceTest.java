package pl.kurs.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kurs.equationsolver.app.Main;
import pl.kurs.equationsolver.model.EquationEvent;
import pl.kurs.equationsolver.services.EquationEventService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Main.class})
@ActiveProfiles("dev")
public class EquationEventServiceTest {

    @Autowired
    private EquationEventService service;

    @Autowired
    private EntityManagerFactory factory;

    @Test
    public void shouldSaveToDatabase() {
        //given
        EquationEvent eventToSave = new EquationEvent(Timestamp.valueOf("2022-10-16 21:14:44.71098"), "2 + 2", 4.0);
        //when
        service.saveEvent(eventToSave);
        //then
        assertEquals(eventToSave, factory.createEntityManager().merge(eventToSave));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNull() {
        service.saveEvent(null);
    }

    @Test(expected = PersistenceException.class)
    public void shouldThrowPersistenceExceptionWhenDateIsNull() {
        EquationEvent eventToSave = new EquationEvent(null, "2 + 2", 4.0);
        service.saveEvent(eventToSave);
    }

    @Test(expected = PersistenceException.class)
    public void shouldThrowPersistenceExceptionWhenEquationsIsNull() {
        EquationEvent eventToSave = new EquationEvent(Timestamp.valueOf("2022-10-16 21:14:44.71098"), null, 4.0);
        service.saveEvent(eventToSave);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowPersistenceExceptionWhenResultIsNull() {
        EquationEvent eventToSave = new EquationEvent(Timestamp.valueOf("2022-10-16 21:14:44.71098"), "2 + 2", Double.parseDouble(null));
        service.saveEvent(eventToSave);
    }



}
