package com.example.testmoodle.controllers;

import com.example.testmoodle.components.DataFromGoogleSheet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RequestMapping("/api/from-google")
@RestController
public class DataFromGoogleSheetController {

    private final DataFromGoogleSheet dataFromGoogleSheet;

    public DataFromGoogleSheetController(DataFromGoogleSheet dataFromGoogleSheet) {
        this.dataFromGoogleSheet = dataFromGoogleSheet;
    }

    @GetMapping("/get-all-data")
    public ResponseEntity<String> getAllData() {
        // TODO: add AdviceController (global handler)
        try {
            dataFromGoogleSheet.getSpreadsheetDataFromGoogle();

            return ResponseEntity.ok("Data downloaded successfully.");
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body("Error during data downloading.");
        }
    }

}
