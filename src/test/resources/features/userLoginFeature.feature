Feature: User Login

  As a registered user
  I want to log in to my account
  So that I can access the application features

  Scenario Outline: User attempts to log in with a JSON file request
    Given the user provides login credentials from file "<fileName>"
    When the user sends a login request
    Then the login system responds with "<responseMessage>"

    Examples:
      | fileName                                  | responseMessage                          |
      | /requests/login/loginValid.json           | Login successful!                        |
      | /requests/login/loginInvalidPassword.json | Invalid credentials!                     |
      | /requests/login/loginNonExistent.json     | User not found for username: nonExistent |
      | /requests/login/loginMissingUsername.json | Username cannot be empty!                |
      | /requests/login/loginMissingPassword.json | Password cannot be empty!                |
