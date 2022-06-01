package com.example.onthi2;

public class Contact {
    private Integer Id;
    private String TenSv;
    private String MaSv;

    public Contact(Integer id, String maSv, String tenSv) {
        Id = id;
        TenSv = tenSv;
        MaSv = maSv;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTenSv() {
        return TenSv;
    }

    public void setTenSv(String tenSv) {
        TenSv = tenSv;
    }

    public String getMaSv() {
        return MaSv;
    }

    public void setMaSv(String maSv) {
        MaSv = maSv;
    }
}
