package com.example.testmoodle.repositories;

import com.example.testmoodle.model.GoogleSheetData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GoogleSheetDataRepository extends JpaRepository<GoogleSheetData, Long> {
}