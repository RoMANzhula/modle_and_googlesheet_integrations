package com.example.testmoodle.model;

import jakarta.persistence.*;

@Entity
@Table(name = "google_sheet_data")
public class GoogleSheetData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "column_a")
    private String columnA;

    @Column(name = "column_b")
    private String columnB;

    @Column(name = "column_c")
    private String columnC;

    @Column(name = "column_d")
    private String columnD;

    @Column(name = "column_e")
    private String columnE;


    public GoogleSheetData() {
    }

    public GoogleSheetData(String columnA, String columnB, String columnC, String columnD, String columnE) {
        this.columnA = columnA;
        this.columnB = columnB;
        this.columnC = columnC;
        this.columnD = columnD;
        this.columnE = columnE;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumnA() {
        return columnA;
    }

    public void setColumnA(String columnA) {
        this.columnA = columnA;
    }

    public String getColumnB() {
        return columnB;
    }

    public void setColumnB(String columnB) {
        this.columnB = columnB;
    }

    public String getColumnC() {
        return columnC;
    }

    public void setColumnC(String columnC) {
        this.columnC = columnC;
    }

    public String getColumnD() {
        return columnD;
    }

    public void setColumnD(String columnD) {
        this.columnD = columnD;
    }

    public String getColumnE() {
        return columnE;
    }

    public void setColumnE(String columnE) {
        this.columnE = columnE;
    }

    @Override
    public String toString() {
        return "GoogleSheetData{" +
                "id=" + id +
                ", columnA='" + columnA + '\'' +
                ", columnB='" + columnB + '\'' +
                ", columnC='" + columnC + '\'' +
                ", columnD='" + columnD + '\'' +
                ", columnE='" + columnE + '\'' +
                '}'
        ;
    }

}

