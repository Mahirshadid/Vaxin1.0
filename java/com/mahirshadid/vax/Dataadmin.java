package com.mahirshadid.vax;

public class Dataadmin {
    String phone;
    String date;

    public Dataadmin() {
    }

    public Dataadmin(String phone,String date) {
        this.phone = phone;
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}



