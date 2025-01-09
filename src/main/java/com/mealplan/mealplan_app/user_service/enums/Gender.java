package com.mealplan.mealplan_app.user_service.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}