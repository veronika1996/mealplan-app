package com.mealplan.mealplan_app.cucumber.glue;

import com.mealplan.mealplan_app.MealPlanAppApplication;
import com.mealplan.mealplan_app.user_service.config.GlobalExceptionHandler;
import com.mealplan.mealplan_app.user_service.config.SecurityConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = {MealPlanAppApplication.class, SecurityConfig.class, GlobalExceptionHandler.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class SpringIntegrationTest {

    @Test
    public void test() {
        System.out.println("Running Cucumber tests...");
    }
}
