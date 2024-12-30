package com.mealplan.mealplan_app.cucumber.glue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealplan.mealplan_app.user_service.dto.UserLoginDTO;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
public class UserLoginSteps {

    private UserLoginDTO userLoginDTO;
    private ResponseEntity<String> response;

    @LocalServerPort
    private int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setupTestData() {
        jdbcTemplate.execute("INSERT INTO app_user (username, password, height, current_weight, target_weight, target_calories) VALUES ('existingUser', 'password123', 175, 70, 65, 2000)");
        jdbcTemplate.execute("INSERT INTO app_user (username, password, height, current_weight, target_weight, target_calories) VALUES ('existingUser2', 'password234', 180, 80, 75, 2200)");
    }


    @Given("the user provides login credentials from file {string}")
    public void theUserProvidesLoginCredentialsFromFile(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Load the JSON file from the resources folder
        userLoginDTO = objectMapper.readValue(new File("src/test/resources/" + fileName), UserLoginDTO.class);
    }

    @When("the user sends a login request")
    public void theUserSendsALoginRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/meal_plan/users/login";
        try {
            response = restTemplate.postForEntity(url, userLoginDTO, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response = ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @Then("the login system responds with {string}")
    public void theLoginSystemRespondsWith(String expectedMessage) {
        assertEquals(expectedMessage, response.getBody());
    }
}
