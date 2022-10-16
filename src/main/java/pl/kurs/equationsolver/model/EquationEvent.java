package pl.kurs.equationsolver.model;


import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "equations_history")
public class EquationEvent {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @Column(nullable = false)
    private String equation;

    @Column(nullable = false)
    private Double result;

    public EquationEvent() {
    }

    public EquationEvent(Timestamp date, String equation, double result) {
        this.date = date;
        this.equation = equation;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquationEvent that = (EquationEvent) o;
        return Double.compare(that.result, result) == 0 && Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(equation, that.equation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, equation, result);
    }

    @Override
    public String toString() {
        return "EquationEvent{" +
                "id=" + id +
                ", date=" + date +
                ", equation='" + equation + '\'' +
                ", result=" + result +
                '}';
    }
}
