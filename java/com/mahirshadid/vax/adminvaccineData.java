package com.mahirshadid.vax;

public class adminvaccineData {
    String nid,location,date1,vac;

    public adminvaccineData(String nid, String location, String date1, String vac) {
        this.nid = nid;
        this.location = location;
        this.date1 = date1;
        this.vac = vac;

    }


    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getVac() {
        return vac;
    }

    public void setVac(String vac) {
        this.vac = vac;
    }

}
