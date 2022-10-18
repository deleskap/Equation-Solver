package pl.kurs.equationsolver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.kurs.equationsolver.arithmetics.*;

import java.util.HashMap;

@Configuration
@ComponentScan(basePackages = "pl.kurs.equationsolver")
public class BeansConfig {

    private AdditionService addition;
    private SubtractionService subtraction;
    private MultiplicationService multiplication;
    private DivisionService division;

    public BeansConfig(AdditionService addition, SubtractionService subtraction, MultiplicationService multiplication, DivisionService division) {
        this.addition = addition;
        this.subtraction = subtraction;
        this.multiplication = multiplication;
        this.division = division;
    }

    @Bean
    public HashMap<String, IMathOperation> getOperations() {
        HashMap<String, IMathOperation> operationHashMap = new HashMap<>();
        operationHashMap.put("+", addition);
        operationHashMap.put("-", subtraction);
        operationHashMap.put("*", multiplication);
        operationHashMap.put("/", division);
        return operationHashMap;
    }
}
