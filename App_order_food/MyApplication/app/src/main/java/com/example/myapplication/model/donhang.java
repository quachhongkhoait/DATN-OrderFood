package com.example.myapplication.model;

public class donhang {
    private int madh;
    private String diachi;
    private int tongtien;
    private int countmonan;
    private String anhquanan;

    public donhang(int madh, String diachi, int tongtien, int countmonan, String anhquanan) {
        this.madh = madh;
        this.diachi = diachi;
        this.tongtien = tongtien;
        this.countmonan = countmonan;
        this.anhquanan = anhquanan;
    }

    public int getMadh() {
        return madh;
    }

    public void setMadh(int madh) {
        this.madh = madh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getCountmonan() {
        return countmonan;
    }

    public void setCountmonan(int countmonan) {
        this.countmonan = countmonan;
    }

    public String getAnhquanan() {
        return anhquanan;
    }

    public void setAnhquanan(String anhquanan) {
        this.anhquanan = anhquanan;
    }
}
