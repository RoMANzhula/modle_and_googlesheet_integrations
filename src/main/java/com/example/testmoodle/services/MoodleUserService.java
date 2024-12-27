package com.example.testmoodle.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
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

    public MoodleUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String createUser(
            String username,
            String password,
            String firstName,
            String lastName,
            String email
    ) {
        try {
            // query parameters
            String functionName = "core_user_create_users";
            String url = moodleBaseUrl + "/webservice/rest/server.php" + "?wstoken=" +
                    token + "&wsfunction=" + functionName
            ;

            // form the data for the POST request
            MultiValueMap<String, String> urlParameters = new LinkedMultiValueMap<>();
            urlParameters.add("users[0][createpassword]", "0");
            urlParameters.add("users[0][username]", username);
            urlParameters.add("users[0][auth]", "manual");
            urlParameters.add("users[0][password]", password);
            urlParameters.add("users[0][firstname]", firstName);
            urlParameters.add("users[0][lastname]", lastName);
            urlParameters.add("users[0][email]", email);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(urlParameters, headers);

            // send POST-request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            String responseBody = response.getBody(); // get the result from the response in String format

            // TODO: create save data method
            System.out.println(responseBody); // temporary processing

            return responseBody;
        } catch (Exception e) {
            // TODO: add logger
            e.printStackTrace();

        }

        return null;
    }

}