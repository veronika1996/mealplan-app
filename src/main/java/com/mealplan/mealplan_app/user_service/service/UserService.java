package com.mealplan.mealplan_app.user_service.service;

import com.mealplan.mealplan_app.user_service.dto.UserLoginDTO;
import com.mealplan.mealplan_app.user_service.dto.UserRegistrationDTO;
import com.mealplan.mealplan_app.user_service.entity.UserEntity;
import com.mealplan.mealplan_app.user_service.enums.ActivityLevel;
import com.mealplan.mealplan_app.user_service.enums.Gender;
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

    public Integer registerUser(@Valid UserRegistrationDTO userDTO) {

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
        user.setYearsOld(userDTO.getYearsOld());
        user.setGender(userDTO.getGender());
        user.setActivityLevel(userDTO.getActivityLevel());
        user.setTargetCalories(calculateTargetCalories(userDTO.getGender(), userDTO.getYearsOld(),
                userDTO.getActivityLevel(), userDTO.getTargetWeight(), userDTO.getCurrentWeight(), userDTO.getHeight()));

        UserEntity savedUser = userRepository.save(user);
        return savedUser.getTargetCalories();
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

    private Integer calculateTargetCalories(Gender gender, Integer yearsOld, ActivityLevel activityLevel,
                                        Integer targetWeight, Integer currentWeight, Integer height) {
        // Calculate BMR (Basal Metabolic Rate)
        Double bmr = getBmr(gender, yearsOld, currentWeight, height);

        // Adjust BMR based on activity level to get TDEE (Total Daily Energy Expenditure)
        Double tdee = bmr * activityLevel.getFactor();

        // Determine calorie deficit or surplus
        return getCalorieAdjustment(tdee, targetWeight, currentWeight).intValue();
    }

    private static Double getBmr(Gender gender, Integer yearsOld, Integer currentWeight, Integer height) {
        if (gender == Gender.MALE) {
            return  66 + (13.7 * currentWeight) + (5 * height) - (6.8 * yearsOld);
        } else {
            return  655 + (9.6 * currentWeight) + (1.8 * height) - (4.7 * yearsOld);
        }
    }

    private Double getCalorieAdjustment(Double tdee, Integer targetWeight, Integer currentWeight) {
        if (targetWeight < currentWeight) {
            // Calorie deficit for weight loss, 20% less
            return tdee - tdee*0.2;
        } else if (targetWeight > currentWeight) {
            // Calorie surplus for weight gain, 15% more
            return tdee + tdee*0.15; // Adjust based on desired weight gain speed
        } else {
            // Maintenance calories
            return tdee;
        }
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
