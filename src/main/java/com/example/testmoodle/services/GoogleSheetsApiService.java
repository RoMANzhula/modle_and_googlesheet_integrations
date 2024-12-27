package com.example.testmoodle.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;


@Service
public class GoogleSheetsApiService {

    @Value("${google.credentials.file.path}")
    private String credentialsFilePath;

    private static final HttpTransport HTTP_TRANSPORT;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing HTTP_TRANSPORT", e);
        }
    }

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public Sheets getSheetsService() throws IOException, GeneralSecurityException {
        // Downloading google's JSON access key
        GoogleCredential credential = GoogleCredential
                .fromStream(new FileInputStream(credentialsFilePath))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS))
        ;

        // Initialize Google Sheets service
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("Your Application Name")
                .build()
        ;
    }

}

        // How to create a JSON credential file (google instruction: https://developers.google.com/sheets/api/quickstart/java)
//1. Go to https://console.cloud.google.com/
//2. Left top corner -> select a project (close with Google Cloud)
//3. Click NEW PROJECT
//4. In main dropdown menu -> APIs & Services -> Library
//5. Search "Google Sheets API" and click it. Click "Enable"
//6. In main dropdown menu -> APIs & Services -> Credentials
//7. Click Create Credentials > OAuth client ID.
//8. Click Application type > Desktop app.
//9. In the Name field, type a name for the credential. This name is only shown in the Google Cloud console.
//10. Click Create. The OAuth client created screen appears, showing your new Client ID and Client secret.
//11. Click OK. The newly created credential appears under OAuth 2.0 Client IDs.
//12. Save the downloaded JSON file as credentials.json, and move the file to your working directory or another package.

