package pl.kurs.equationsolver.services;

import org.springframework.stereotype.Service;
import pl.kurs.equationsolver.arithmetics.*;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;
import pl.kurs.equationsolver.model.EquationEvent;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EquationService implements IEquationService {

    private IEquationEventService equationEventService;
    private HashMap<String, IMathOperation> mathOperations;

    public EquationService(IEquationEventService equationEventService, HashMap<String, IMathOperation> mathOperations) {
        this.equationEventService = equationEventService;
        this.mathOperations = mathOperations;
    }

    @Override
    public double calculate(String inputEquation) throws InvalidEquationFormatException, UnknownOperatorException {

        double result;

        String[] input = validateInput(inputEquation);
        LinkedList<String> list = Arrays.stream(input).collect(Collectors.toCollection(LinkedList::new));

        System.out.println("Equation steps: ");
        System.out.println(list.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "") + " =");

        while (list.stream().anyMatch(x -> x.equals("*") || x.equals("/"))) {
            for (int i = 1; i < list.size(); i = i + 2) {
                if (list.get(i).equals("*") || list.get(i).equals("/")) {
                    double res = mathOperations.get(list.get(i)).calculate(Double.parseDouble(list.get(i - 1)), Double.parseDouble(list.get(i + 1)));
                    list.set(i, String.valueOf(res));
                    list.remove(i + 1);
                    list.remove(i - 1);
                    System.out.println(list.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "") + " =");
                    break;
                }
            }
        }

        while (list.stream().anyMatch(x -> x.equals("+") || x.equals("-"))) {
            for (int i = 1; i < list.size(); i = i + 2) {
                if (list.get(i).equals("+") || list.get(i).equals("-")) {
                    double res = mathOperations.get(list.get(i)).calculate(Double.parseDouble(list.get(i - 1)), Double.parseDouble(list.get(i + 1)));
                    list.set(i, String.valueOf(res));
                    list.remove(i + 1);
                    list.remove(i - 1);
                    System.out.println(list.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "") + " =");
                    break;
                }
            }
        }
        result = Double.parseDouble(list.get(0));
        equationEventService.saveEvent(new EquationEvent(Timestamp.from(Instant.now()), inputEquation, result));
        return result;
    }


    private static String[] validateInput(String input) throws InvalidEquationFormatException, UnknownOperatorException {

        String inputString = Optional.ofNullable(input)
                .orElseThrow(() -> new InvalidEquationFormatException("Argument is null"));

        String[] split = inputString.split(" ");

        for (int i = 0; i < split.length; i = i + 2) {
            try {
                Double.parseDouble(split[i]);
            } catch (Exception e) {
                throw new InvalidEquationFormatException("Invalid equation format. Input should be as follows '2 + 2 / 2 * 2'. Remember to add spaces.");
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
        return split;
    }
}
