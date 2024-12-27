package com.example.testmoodle.services;


import java.util.List;

public interface GoogleSheetDataService {

    void processAndSaveData(List<List<Object>> data);

}
