package pl.kurs.equationsolver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.kurs.equationsolver.arithmetics.*;

import java.util.HashMap;

@Configuration
public class BeansConfig {

    @Bean
    public HashMap<String, IMathOperation> getOperations() {
        HashMap<String, IMathOperation> operationHashMap = new HashMap<>();
        operationHashMap.put("+", new AdditionService());
        operationHashMap.put("-", new SubtractionService());
        operationHashMap.put("*", new MultiplicationService());
        operationHashMap.put("/", new DivisionService());
        return operationHashMap;
    }
}
