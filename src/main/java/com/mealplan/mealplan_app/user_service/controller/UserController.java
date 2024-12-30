package com.mealplan.mealplan_app.user_service.controller;

import com.mealplan.mealplan_app.user_service.dto.UserDTO;
import com.mealplan.mealplan_app.user_service.dto.UserLoginDTO;
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
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User successfully registered!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        String response = userService.login(userLoginDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
