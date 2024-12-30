package com.mealplan.mealplan_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.mealplan.mealplan_app")
@RestController
public class MealPlanAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealPlanAppApplication.class, args);
	}

	@GetMapping
	public String hello() {
		return "Hello World";
	}
}
