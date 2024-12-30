package com.mealplan.mealplan_app.user_service.dto;

public class UserDTO {

    private String username;
    private String password;
    private int height;
    private int currentWeight;
    private int targetWeight;
    private int targetCalories;

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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(int targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(int targetCalories) {
        this.targetCalories = targetCalories;
    }
}