Feature: User Registration

  As a new user
  I want to register an account
  So that I can access the meal plan application

  Scenario Outline: User attempts to register with a JSON file request
    Given the user provides registration details from file "<fileName>"
    When the user sends a registration request
    Then the registration system responds with "<responseMessage>"

    Examples:
      | fileName                                                  | responseMessage                              |
      | /requests/register/registrationValid.json                 | User successfully registered!                |
      | /requests/register/registrationExistingUser.json          | Username already exists: existingUser        |
      | /requests/register/registrationMissingUsername.json       | Username cannot be empty!                    |
      | /requests/register/registrationMissingPassword.json       | Password cannot be empty!                    |
      | /requests/register/registrationHeightPositive.json        | Height needs to be a positive value!         |
      | /requests/register/registrationHeightNull.json            | Height cannot be null!                       |
      | /requests/register/registrationCurrentWeightPositive.json | Current weight needs to be a positive value! |
      | /requests/register/registrationCurrentWeightNull.json     | Current weight cannot be null!               |
      | /requests/register/registrationTargetWeightPositive.json  | Target weight needs to be a positive value!  |
      | /requests/register/registrationTargetWeightNull.json      | Target weight cannot be null!                |