package com.mealplan.mealplan_app.cucumber.glue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealplan.mealplan_app.user_service.dto.UserRegistrationDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
public class UserRegistrationSteps {

    private UserRegistrationDTO userDTO;
    private ResponseEntity<String> response;

    @LocalServerPort
    private int port;

    @Given("the user provides registration details from file {string}")
    public void theUserProvidesRegistrationDetailsFromFile(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Load the JSON file from the resources folder
        userDTO = objectMapper.readValue(new File("src/test/resources/" + fileName), UserRegistrationDTO.class);
    }

    @When("the user sends a registration request")
    public void theUserSendsARegistrationRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/meal_plan/users/register";
        try {
            response = restTemplate.postForEntity(url, userDTO, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException e)  {
            response = ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @Then("the registration system responds with {string}")
    public void theRegistrationSystemRespondsWith(String expectedMessage) {
        assertEquals(expectedMessage, response.getBody());
    }
}