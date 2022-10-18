package pl.kurs.equationsolver.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;
import pl.kurs.equationsolver.services.EquationService;


@ComponentScan(basePackages = "pl.kurs.equationsolver")
public class Main {
    public static void main(String[] args) throws InvalidEquationFormatException, UnknownOperatorException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        EquationService equationService = ctx.getBean(EquationService.class);

        double result = equationService.calculate(args[0]);
        System.out.println("Result = " + result);
    }
}
