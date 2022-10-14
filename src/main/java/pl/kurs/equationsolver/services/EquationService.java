package pl.kurs.equationsolver.services;

import org.springframework.stereotype.Service;
import pl.kurs.equationsolver.arithmetics.*;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;
import pl.kurs.equationsolver.model.EquationEvent;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
public class EquationService implements IEquationService {
    private IAdditionService addition;
    private ISubstractionService substraction;
    private IMultiplicationService multiplication;
    private IDivisionService division;
    private IEquationEventService equationEventService;

    public EquationService(IAdditionService addition, ISubstractionService substraction, IMultiplicationService multiplication, IDivisionService division, IEquationEventService equationEventService) {
        this.addition = addition;
        this.substraction = substraction;
        this.multiplication = multiplication;
        this.division = division;
        this.equationEventService = equationEventService;
    }

    @Override
    public double calculate(String inputEquation) throws InvalidEquationFormatException, UnknownOperatorException {
        validateInput(inputEquation);
        String[] input = inputEquation.split(" ");
        double result;

        LinkedList<String> list = Arrays.stream(input).collect(Collectors.toCollection(LinkedList::new));

        System.out.println(list);

        while (list.stream().anyMatch(x -> x.equals("*") || x.equals("/"))) {
            for (int i = 1; i < list.size(); i = i + 2) {
                if (list.get(i).equals("*")) {
                    double m = multiplication.multiply(Double.parseDouble(list.get(i - 1)), Double.parseDouble(list.get(i + 1)));
                    list.set(i, String.valueOf(m));
                    list.remove(i + 1);
                    list.remove(i - 1);
                    System.out.println(list);
                    break;
                } else if (list.get(i).equals("/")) {
                    double d = division.divide(Double.parseDouble(list.get(i - 1)), Double.parseDouble(list.get(i + 1)));
                    list.set(i, String.valueOf(d));
                    list.remove(i + 1);
                    list.remove(i - 1);
                    System.out.println(list);
                    break;
                }
            }
        }
        while (list.stream().anyMatch(x -> x.equals("+") || x.equals("-"))) {
            for (int i = 1; i < list.size(); i = i + 2) {
                if (list.get(i).equals("+")) {
                    double a = addition.add(Double.parseDouble(list.get(i - 1)), Double.parseDouble(list.get(i + 1)));
                    list.set(i, String.valueOf(a));
                    list.remove(i + 1);
                    list.remove(i - 1);
                    System.out.println(list);
                    break;
                } else if (list.get(i).equals("-")) {
                    double s = substraction.substract(Double.parseDouble(list.get(i - 1)), Double.parseDouble(list.get(i + 1)));
                    list.set(i, String.valueOf(s));
                    list.remove(i + 1);
                    list.remove(i - 1);
                    System.out.println(list);
                    break;
                }
            }
        }
        result = Double.parseDouble(list.get(0));
        equationEventService.saveEvent(
                new EquationEvent(
                        Timestamp.from(Instant.now()),
                        inputEquation,
                        result
                )
        );

        return result;
    }


    private static void validateInput(String input) throws InvalidEquationFormatException, UnknownOperatorException {
        String[] split = input.split(" ");

        for (int i = 0; i < split.length; i = i + 2) {
            try {
                Double.parseDouble(split[i]);
            } catch (Exception e) {
                throw new InvalidEquationFormatException("Invalid equation format.");
            }
        }
        for (int i = 1; i < split.length; i = i + 2) {
            if (!(split[i].equals("+") || split[i].equals("-") || split[i].equals("/") || split[i].equals("*"))) {
                throw new UnknownOperatorException("Unknown operator: " + split[i]);
            }
        }

        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("/") && Double.parseDouble(split[i + 1]) == 0) {
                throw new InvalidEquationFormatException("Cannot divide by 0.");
            }
        }
    }
}
