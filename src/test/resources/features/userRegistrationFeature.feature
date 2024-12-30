Feature: User Registration

  As a new user
  I want to register an account
  So that I can access the meal plan application

  Scenario Outline: User attempts to register with a JSON file request
    Given the user provides registration details from file "<fileName>"
    When the user sends a registration request
    Then the registration system responds with "<responseMessage>"

    Examples:
      | fileName                                         | responseMessage                          |
      | /requests/register/registrationValid.json        | User successfully registered!            |
      | /requests/register/registrationExistingUser.json | Username already exists: existingUser    |
      | /requests/register/registrationInvalid.json  | Invalid request: missing required fields |