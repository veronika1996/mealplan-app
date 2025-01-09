package com.mealplan.mealplan_app.user_service.dto;

public class UserRegistrationResponseDTO {

    private Integer targetCalories;

    public UserRegistrationResponseDTO(Integer targetCalories) {
        this.targetCalories = targetCalories;
    }

    public Integer getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(Integer targetCalories) {
        this.targetCalories = targetCalories;
    }
    
}
