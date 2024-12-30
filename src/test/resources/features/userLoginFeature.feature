Feature: User Login

  As a registered user
  I want to log in to my account
  So that I can access the application features

  Scenario Outline: User attempts to log in with a JSON file request
    Given the user provides login credentials from file "<fileName>"
    When the user sends a login request
    Then the login system responds with "<responseMessage>"

    Examples:
      | fileName                                  | responseMessage                          | username    |
      | /requests/login/loginValid.json           | Login successful!                        |             |
      | /requests/login/loginInvalidPassword.json | Invalid credentials!                     |             |
      | /requests/login/loginNonExistent.json     | User not found for username: <username>  | nonExistent |
      | /requests/login/loginMissingFields.json   | Invalid request: missing required fields |             |