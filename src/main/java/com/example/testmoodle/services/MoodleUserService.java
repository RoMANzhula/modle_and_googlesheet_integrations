package com.example.testmoodle.services;


import com.example.testmoodle.dto.UserRegistrationRequest;
import com.example.testmoodle.model.User;
import com.example.testmoodle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class MoodleUserService {

    @Value("${token}")
    private String token;

    @Value("${moodleBaseUrl}")
    private String moodleBaseUrl;

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public MoodleUserService(
            RestTemplate restTemplate,
            UserRepository userRepository
    ) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }


    @Transactional
    public void createUser(UserRegistrationRequest registrationRequest) {
        try {
            // query parameters
            String functionName = "core_user_create_users";
            String url = moodleBaseUrl + "/webservice/rest/server.php" + "?wstoken=" +
                    token + "&wsfunction=" + functionName;

            // form the data for the POST request
            MultiValueMap<String, String> urlParameters = new LinkedMultiValueMap<>();
            urlParameters.add("users[0][createpassword]", "0");
            urlParameters.add("users[0][username]", registrationRequest.getUsername());
            urlParameters.add("users[0][auth]", "manual");
            urlParameters.add("users[0][password]", registrationRequest.getPassword());
            urlParameters.add("users[0][firstname]", registrationRequest.getFirstName());
            urlParameters.add("users[0][lastname]", registrationRequest.getLastName());
            urlParameters.add("users[0][email]", registrationRequest.getEmail());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(urlParameters, headers);

            // send POST-request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            // save to db
            User user = new User();
            user.setUsername(registrationRequest.getUsername());
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(registrationRequest.getPassword());

            userRepository.save(user);

            System.out.println("User successfully created in Moodle and saved to the database.");

        } catch (Exception e) {
            // TODO: add logger
            e.printStackTrace();
            throw new RuntimeException("Failed to create user in Moodle: " + e.getMessage());
        }
    }

}