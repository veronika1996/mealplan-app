package com.mealplan.mealplan_app.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "com/mealplan/mealplan_app/cucumber/glue",
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-reports.html"
        }
)
@ActiveProfiles("test")
public class CucumberIntegrationTest {
}
