Feature: User Registration

  As a new user
  I want to register an account
  So that I can access the meal plan application

  Scenario Outline: User attempts to register with a JSON file request
    Given the user provides registration details from file "<fileName>"
    When the user sends a registration request
    Then the registration system responds with "<responseMessage>"

    Examples:
      | fileName                                                      | responseMessage                              |
      | /requests/register/registrationValid.json                     | {\"targetCalories\":1395}                    |
      | /requests/register/registrationValidMale.json                 | {\"targetCalories\":1551}                    |
      | /requests/register/registrationValidActivityLight.json        | {\"targetCalories\":1598}                    |
      | /requests/register/registrationValidActivityModerate.json     | {\"targetCalories\":1802}                    |
      | /requests/register/registrationValidActivityHigh.json         | {\"targetCalories\":2005}                    |
      | /requests/register/registrationValidActivityVeryHigh.json     | {\"targetCalories\":2209}                    |
      | /requests/register/registrationValidActivityLightMale.json    | {\"targetCalories\":1777}                    |
      | /requests/register/registrationValidActivityModerateMale.json | {\"targetCalories\":2003}                    |
      | /requests/register/registrationValidActivityHighMale.json     | {\"targetCalories\":2230}                    |
      | /requests/register/registrationValidActivityVeryHighMale.json | {\"targetCalories\":2456}                    |
      | /requests/register/registrationValidTargetWeightHigher.json   | {\"targetCalories\":1873}                    |
      | /requests/register/registrationValidTargetWeightTheSame.json  | {\"targetCalories\":1628}                    |
      | /requests/register/registrationExistingUser.json              | Username already exists: existingUser        |
      | /requests/register/registrationMissingUsername.json           | Username cannot be empty!                    |
      | /requests/register/registrationMissingPassword.json           | Password cannot be empty!                    |
      | /requests/register/registrationHeightPositive.json            | Height needs to be a positive value!         |
      | /requests/register/registrationHeightNull.json                | Height cannot be null!                       |
      | /requests/register/registrationCurrentWeightPositive.json     | Current weight needs to be a positive value! |
      | /requests/register/registrationCurrentWeightNull.json         | Current weight cannot be null!               |
      | /requests/register/registrationTargetWeightPositive.json      | Target weight needs to be a positive value!  |
      | /requests/register/registrationTargetWeightNull.json          | Target weight cannot be null!                |
      | /requests/register/registrationYearsOldNull.json              | Years need to be positive value!             |