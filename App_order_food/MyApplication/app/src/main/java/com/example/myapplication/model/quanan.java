package com.example.myapplication.model;

import java.io.Serializable;

public class quanan implements Serializable {
    private int maqa;
    private String tenqa;
    private String hinhqa;
    private String diachiqa;

    public quanan(int maqa, String tenqa, String hinhqa, String diachiqa) {
        this.maqa = maqa;
        this.tenqa = tenqa;
        this.hinhqa = hinhqa;
        this.diachiqa = diachiqa;
    }

    public int getMaqa() {
        return maqa;
    }

    public void setMaqa(int maqa) {
        this.maqa = maqa;
    }

    public String getTenqa() {
        return tenqa;
    }

    public void setTenqa(String tenqa) {
        this.tenqa = tenqa;
    }

    public String getHinhqa() {
        return hinhqa;
    }

    public void setHinhqa(String hinhqa) {
        this.hinhqa = hinhqa;
    }

    public String getDiachiqa() {
        return diachiqa;
    }

    public void setDiachiqa(String diachiqa) {
        this.diachiqa = diachiqa;
    }
}
