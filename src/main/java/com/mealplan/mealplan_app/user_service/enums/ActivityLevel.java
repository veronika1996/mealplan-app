package com.mealplan.mealplan_app.user_service.enums;

public enum ActivityLevel {
    SEDENTARY(1.2, "Minimal activity (sedentary lifestyle)"),
    LIGHT_ACTIVITY(1.375, "Light activity (exercise 1-3 days per week)"),
    MODERATE_ACTIVITY(1.55, "Moderate activity (exercise 3-5 days per week)"),
    HIGH_ACTIVITY(1.725, "High activity (exercise 6-7 days per week)"),
    VERY_HIGH_ACTIVITY(1.9, "Very high activity (physical job or intense training)");

    private final Double factor;
    private final String description;

    ActivityLevel(Double factor, String description) {
        this.factor = factor;
        this.description = description;
    }

    public Double getFactor() {
        return factor;
    }

    public String getDescription() {
        return description;
    }
}
