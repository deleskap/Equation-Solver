package pl.kurs.equationsolver.service;

import org.springframework.stereotype.Service;
import pl.kurs.equationsolver.arithmetics.*;
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
    public double calculate(String inputEquation) {
        String[] input = inputEquation.split(" ");
        double result;

        LinkedList<String> list = Arrays.stream(input).collect(Collectors.toCollection(LinkedList::new));

        System.out.println(list);

        while (list.stream().anyMatch(x-> x.equals("*")||x.equals("/"))) {
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
        while (list.stream().anyMatch(x-> x.equals("+")||x.equals("-"))) {
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
}
