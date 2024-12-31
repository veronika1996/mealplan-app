package com.mealplan.mealplan_app.user_service.service;

import com.mealplan.mealplan_app.user_service.dto.UserLoginDTO;
import com.mealplan.mealplan_app.user_service.dto.UserRegistrationDTO;
import com.mealplan.mealplan_app.user_service.entity.UserEntity;
import com.mealplan.mealplan_app.user_service.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_ERROR = "User not found for username: ";
    private static final String LOGIN_SUCCESSFUL = "Login successful!";
    private static final String INVALID_CREDENTIALS = "Invalid credentials!";
    private static final String USERNAME_ALREADY_EXIST = "Username already exists: ";


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(@Valid UserRegistrationDTO userDTO) {

        //TODO Add check if user already exists to have recommendation to go to login page - frontend

        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException(USERNAME_ALREADY_EXIST + userDTO.getUsername());
        }

        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        //we are encoding the password
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setHeight(userDTO.getHeight());
        user.setCurrentWeight(userDTO.getCurrentWeight());
        user.setTargetWeight(userDTO.getTargetWeight());
        user.setTargetCalories(calculateTargetCalories(userDTO.getCurrentWeight(), userDTO.getHeight()));

        userRepository.save(user);
    }


    public String login(@Valid UserLoginDTO userLoginDTO) {

        //TODO Add check for required fields

        try {
            // checking if user exists
            UserDetails userDetails = loadUserByUsername(userLoginDTO.getUsername());

            //checking if the password is correct
            if (passwordEncoder.matches(userLoginDTO.getPassword(), userDetails.getPassword())) {
                return LOGIN_SUCCESSFUL;
            } else {
                throw new IllegalArgumentException(INVALID_CREDENTIALS);
            }

        } catch (UsernameNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private int calculateTargetCalories(int currentWeight, int height) {
        //TODO this will be hardcoded for first commit, implement logic
        return 2000;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //checking if user already exists
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            UserEntity userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(USER_NOT_FOUND_ERROR + username);
        }
    }
}
