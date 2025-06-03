package com.org.test.validatenametest;

import com.org.test.validatenametest.service.DataService;
import com.org.test.validatenametest.service.FactorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ValidateNameTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ValidateNameTestApplication.class, args);

        DataService dataService = (DataService) context.getBean("dataService");
        FactorService factorService = (FactorService) context.getBean("factorService");

        dataService.setDataFromJSONContent();
        factorService.validateFactor();
    }
}
