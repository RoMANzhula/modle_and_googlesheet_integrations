package com.example.testmoodle.components;


import com.example.testmoodle.model.GoogleSheetData;
import com.example.testmoodle.services.GoogleSheetDataService;
import com.example.testmoodle.services.GoogleSheetsApiService;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


@Component
public class DataFromGoogleSheet {

    private final GoogleSheetsApiService googleSheetsApiService;
    private final GoogleSheetDataService googleSheetDataService;
    private final String spreadsheetId;
    private final String range;

    public DataFromGoogleSheet(
            GoogleSheetsApiService googleSheetsApiService,
            GoogleSheetDataService googleSheetDataService,
            @Value("${google.sheets.spreadsheetId}") String spreadsheetId,
            @Value("${google.sheets.range}") String range
    ) {
        this.googleSheetsApiService = googleSheetsApiService;
        this.googleSheetDataService = googleSheetDataService;
        this.spreadsheetId = spreadsheetId;
        this.range = range;
    }

    // Getting data from Google Sheets and initializing objects
    public void getSpreadsheetDataFromGoogle() throws GeneralSecurityException, IOException {

        Sheets sheetsService = googleSheetsApiService.getSheetsService();

        try {
            // API call to retrieve values from a table
            ValueRange response =
                    sheetsService
                    .spreadsheets()
                    .values()
                    .get(spreadsheetId, range)
                    .execute()
            ;

            // Getting values from the answer from the entire (whale) range
            List<List<Object>> values = response.getValues();
            if (values != null && !values.isEmpty()) {
                System.out.println("Data fetched successfully. Processing...");

                // save data to db
                googleSheetDataService.processAndSaveData(values);

                System.out.println("Data processed and saved successfully.");
            } else {
                System.out.println("No data found in the spreadsheet.");
            }

        } catch (IOException e) {
            // TODO: add logger
            e.printStackTrace();
        }

    }

}
