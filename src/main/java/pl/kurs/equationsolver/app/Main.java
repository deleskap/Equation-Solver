package pl.kurs.equationsolver.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;
import pl.kurs.equationsolver.service.EquationService;


@ComponentScan(basePackages = "pl.kurs.equationsolver")
public class Main {
    public static void main(String[] args) throws InvalidEquationFormatException, UnknownOperatorException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        EquationService equationService = ctx.getBean(EquationService.class);

        //input validation
        String inputString = args[0];
        validateInput(inputString);

        double result = equationService.calculate(inputString);
//        System.out.println(inputString + " = " + result);
//





    }

    private static void validateInput(String input) throws InvalidEquationFormatException, UnknownOperatorException {
        String[] split = input.split(" ");

        for (int i=0; i<split.length; i=i+2){
            try{
                Double.parseDouble(split[i]);
            }
            catch (Exception e) {
                throw new InvalidEquationFormatException("Invalid equation format.");
            }
        }
        for (int i=1; i<split.length; i=i+2){
            if (!(split[i].equals("+") || split[i].equals("-")  || split[i].equals("/")  || split[i].equals("*"))) {
                throw new UnknownOperatorException("Unknown operator: "+ split[i]);
            }
        }

        for (int i=0; i<split.length; i++){
            if (split[i].equals("/") && Double.parseDouble(split[i+1])==0){
                throw new InvalidEquationFormatException("Cannot divide by 0.");
            }
        }

    }
}
