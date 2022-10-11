package pl.kurs.equationsolver.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;

@ComponentScan(basePackages = "pl.kurs.equationsolver")
public class Main {
    public static void main(String[] args) throws UnknownOperatorException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);


    }
}
