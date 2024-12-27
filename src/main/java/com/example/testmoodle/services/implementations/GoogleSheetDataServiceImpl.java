package com.example.testmoodle.services.implementations;

import com.example.testmoodle.model.GoogleSheetData;
import com.example.testmoodle.repositories.GoogleSheetDataRepository;
import com.example.testmoodle.services.GoogleSheetDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class GoogleSheetDataServiceImpl implements GoogleSheetDataService {

    private final GoogleSheetDataRepository googleSheetDataRepository;

    public GoogleSheetDataServiceImpl(GoogleSheetDataRepository googleSheetDataRepository) {
        this.googleSheetDataRepository = googleSheetDataRepository;
    }


    @Override
    @Transactional
    public void processAndSaveData(List<List<Object>> values) {
        List<GoogleSheetData> dataList = new ArrayList<>();

        for (List<Object> row : values) {
            if (row.size() >= 5) {
                GoogleSheetData data = new GoogleSheetData(
                        row.get(0).toString(),
                        row.get(1).toString(),
                        row.get(2).toString(),
                        row.get(3).toString(),
                        row.get(4).toString()
                );

                dataList.add(data);
            }
        }

        googleSheetDataRepository.saveAll(dataList);
    }

}
