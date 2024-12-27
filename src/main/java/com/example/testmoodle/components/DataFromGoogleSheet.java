package com.example.testmoodle.components;


import com.example.testmoodle.model.GoogleSheetData;
import com.example.testmoodle.services.GoogleSheetsApiService;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


@Component
public class DataFromGoogleSheet {

    // Getting data from Google Sheets and initializing objects
    public static List<GoogleSheetData> getCoursesData() throws GeneralSecurityException, IOException {
        // Initialize Google Sheets API service
        Sheets sheetsService = GoogleSheetsApiService.getSheetsService();

        // Table ID and range to retrieve data from

        //  https://docs.google.com/spreadsheets/d/HERE_IS_A_spreadsheetId_IN_URL/edit?gid=0#gid=0
        String spreadsheetId = "1_b-nVtasExample9jYKTHLB34CgoDeT-asQGtm6cABCD";

        // use name of sheet and range for processing

        String range = "Sheet1!A:E";
            // for one cell
//        String range = "Sheet1!C3";
//        String range = "Sheet1!E4";

        try {
            // API call to retrieve values from a table
            ValueRange response = sheetsService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();

            // Getting values from the answer from one cell
//            List<List<Object>> values = response.getValues();
//            if (values != null && !values.isEmpty()) {
//                for (List<Object> row : values) {
//                    if (!row.isEmpty()) {
//                        System.out.printf("Value from the cell: %s\n", row.get(0));
//                    }
//                }
//            } else {
//                System.out.println("No data to output.");
//            }

            // Getting values from the answer from the entire (whale) range
            List<List<Object>> values = response.getValues();
            if (values != null && !values.isEmpty()) {
                for (List<Object> row : values) {
                    for (Object cell : row) {
                        System.out.print(cell + "\t");
                    }
                    System.out.println(); // break to a new line after each line
                }
            } else {
                System.out.println("No data to output.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Method for processing data
    private static List<GoogleSheetData> processSaveData(List<List<Object>> values) {
        // TODO: add processing data (now we get all data to terminal)
        return null;
    }

    // Testing - get data from google spreadsheet
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        getCoursesData();
    }

}
