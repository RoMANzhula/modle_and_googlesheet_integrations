package com.example.testmoodle.components;

import com.example.testmoodle.services.MoodleUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class MoodleUserInitializer implements CommandLineRunner {

    private final MoodleUserService moodleUserService;

    public MoodleUserInitializer(MoodleUserService moodleUserService) {
        this.moodleUserService = moodleUserService;
    }


    @Override
    public void run(String... args) throws Exception {
        // TODO: create user addition logic for the client side
        String username = "testuser8";
        String password = "123";
        String firstName = "John8";
        String lastName = "John8";
        String email = "user8@ukrls.net";

        String createResponse = moodleUserService.createUser(username, password, firstName, lastName, email);

        if (createResponse != null) {
            //request with String format
            // TODO: create a processing data method
            System.out.println("Response from Moodle: " + createResponse);

        } else {
            // TODO: create logger and exception handler
            System.out.println("Error creating user: Response is null");
        }
    }
}
