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


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNull(){
        service.saveEvent(null);
    }

    @Test
    public void shouldSaveToDatabase(){
        //given
        EquationEvent eventToSave = new EquationEvent(Timestamp.valueOf("2022-10-16 21:14:44.71098"),"2 + 2", 4.0);
        //when
        service.saveEvent(eventToSave);
        //then
        assertEquals(eventToSave, factory.createEntityManager().find(EquationEvent.class, 1L));

    }




}
