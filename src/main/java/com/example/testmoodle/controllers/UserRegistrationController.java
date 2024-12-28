package com.example.testmoodle.controllers;

import com.example.testmoodle.dto.UserRegistrationRequest;
import com.example.testmoodle.services.MoodleUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRegistrationController {

    private final MoodleUserService moodleUserService;

    public UserRegistrationController(MoodleUserService moodleUserService) {
        this.moodleUserService = moodleUserService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody UserRegistrationRequest request
    ) {
        moodleUserService.createUser(request);

        return ResponseEntity.ok().body("Registration ws successful.");
    }

}
