package com.example.myapplication.model;

public class giohang {
    public int mama;
    public String tenma;
    public int giama;
    public String anhma;
    public int soluong;
    public int tongtien;

    public giohang(int mama, String tenma, int giama, String anhma, int soluong, int tongtien) {
        this.mama = mama;
        this.tenma = tenma;
        this.giama = giama;
        this.anhma = anhma;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public int getMama() {
        return mama;
    }

    public void setMama(int mama) {
        this.mama = mama;
    }

    public String getTenma() {
        return tenma;
    }

    public void setTenma(String tenma) {
        this.tenma = tenma;
    }

    public int getGiama() {
        return giama;
    }

    public void setGiama(int giama) {
        this.giama = giama;
    }

    public String getAnhma() {
        return anhma;
    }

    public void setAnhma(String anhma) {
        this.anhma = anhma;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
