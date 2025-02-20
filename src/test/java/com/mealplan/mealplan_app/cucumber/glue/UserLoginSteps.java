package com.mealplan.mealplan_app.cucumber.glue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealplan.mealplan_app.user_service.dto.UserLoginDTO;
import com.mealplan.mealplan_app.user_service.repository.UserRepository;
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

    private static final String INSERT_USER_QUERY =
            "INSERT INTO app_user (username, password, height, current_weight, target_weight, target_calories) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private UserLoginDTO userLoginDTO;
    private ResponseEntity<String> response;

    @LocalServerPort
    private int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setupTestData() {
        userRepository.deleteAll();
        insertUser("existingUser", "$2a$12$144tJ3p1AtyqZUITAz97UOIPEikSU6Zb2c5BW5NlGzddmoIK0tJLW",
                175, 70, 65, 2000);
        insertUser("existingUser2", "$2a$12$x5n62h9H7QeXTpL5/RlvBOeci/vt9m0tIXkXro9FPF2g6.2Zd6Onu",
                180, 80, 75, 2200);
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

    public void insertUser(String username, String password, int height, int currentWeight, int targetWeight, int targetCalories) {
        jdbcTemplate.update(INSERT_USER_QUERY, username, password, height, currentWeight, targetWeight, targetCalories);
    }
}
