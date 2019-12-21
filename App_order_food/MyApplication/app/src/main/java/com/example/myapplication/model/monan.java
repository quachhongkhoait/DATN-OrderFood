package com.example.myapplication.model;

public class monan {
    public int mamonan;
    public String maloai;
    public String tenmonan;
    public Integer gia;
    public String hinhanhmonan;

    public monan(int mamonan, String maloai, String tenmonan, Integer gia, String hinhanhmonan) {
        this.mamonan = mamonan;
        this.maloai = maloai;
        this.tenmonan = tenmonan;
        this.gia = gia;
        this.hinhanhmonan = hinhanhmonan;
    }
    public int getMamonan() {
        return mamonan;
    }

    public void setMamonan(int mamonan) {
        this.mamonan = mamonan;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenmonan() {
        return tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        this.tenmonan = tenmonan;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public String getHinhanhmonan() {
        return hinhanhmonan;
    }

    public void setHinhanhmonan(String hinhanhmonan) {
        this.hinhanhmonan = hinhanhmonan;
    }
}
