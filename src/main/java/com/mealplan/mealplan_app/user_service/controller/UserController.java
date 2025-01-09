package com.mealplan.mealplan_app.user_service.controller;

import com.mealplan.mealplan_app.user_service.dto.UserLoginDTO;
import com.mealplan.mealplan_app.user_service.dto.UserRegistrationDTO;
import com.mealplan.mealplan_app.user_service.dto.UserRegistrationResponseDTO;
import com.mealplan.mealplan_app.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<UserRegistrationResponseDTO> registerUser(@RequestBody UserRegistrationDTO userDTO) {
        int targetCalories = userService.registerUser(userDTO);
        UserRegistrationResponseDTO response = new UserRegistrationResponseDTO(targetCalories);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    };


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        String response = userService.login(userLoginDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
