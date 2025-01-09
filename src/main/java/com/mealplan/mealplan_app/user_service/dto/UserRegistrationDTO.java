package com.mealplan.mealplan_app.user_service.dto;

import com.mealplan.mealplan_app.user_service.enums.ActivityLevel;
import com.mealplan.mealplan_app.user_service.enums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UserRegistrationDTO {

    @NotEmpty(message = "Username cannot be empty!")
    private String username;

    @NotEmpty(message = "Password cannot be empty!")
    private String password;

    @NotNull(message = "Height cannot be null!")
    @Positive(message = "Height needs to be a positive value!")
    private Integer height;

    @NotNull(message = "Current weight cannot be null!")
    @Positive(message = "Current weight needs to be a positive value!")
    private Integer currentWeight;

    @NotNull(message = "Target weight cannot be null!")
    @Positive(message = "Target weight needs to be a positive value!")
    private Integer targetWeight;

    private ActivityLevel activityLevel;

    private Gender gender;

    @NotNull(message = "Years cannot be null")
    @Positive(message = "Years need to be positive value!")
    private Integer yearsOld;

    private Integer targetCalories;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Integer getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Integer targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Integer getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(Integer targetCalories) {
        this.targetCalories = targetCalories;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(Integer yearsOld) {
        this.yearsOld = yearsOld;
    }
}
