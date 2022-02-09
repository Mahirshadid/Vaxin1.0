package com.mahirshadid.vax;

public class othervaccineData {
    String name,birthyear,mobile,key,location,emergency,time;

    public othervaccineData(){

    }

    public othervaccineData(String name,String birthyear,String mobile,String key,String location,String emergency,String time){
        this.name=name;
        this.birthyear=birthyear;
        this.mobile=mobile;
        this.key=key;
        this.location=location;
        this.emergency=emergency;
        this.time=time;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
