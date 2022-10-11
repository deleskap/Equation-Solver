package pl.kurs.equationsolver.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class EquationEvent {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private Timestamp date;

    public EquationEvent() {
    }

    public EquationEvent(Timestamp date) {
        this.date = date;
    }
}
