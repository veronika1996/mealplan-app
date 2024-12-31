package com.mealplan.mealplan_app.user_service.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserLoginDTO {

    @NotEmpty(message = "Username cannot be empty!")
    private String username;

    @NotEmpty(message = "Password cannot be empty!")
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
}
