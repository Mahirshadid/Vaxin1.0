package com.mahirshadid.vax;

public class DataforAMBhos {
    String name,phone,location,type,available_hours;

    public DataforAMBhos() {
    }

    public DataforAMBhos(String name, String phone, String location, String type, String available_hours) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.type = type;
        this.available_hours = available_hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvailable_hours() {
        return available_hours;
    }

    public void setAvailable_hours(String available_hours) {
        this.available_hours = available_hours;
    }
}



