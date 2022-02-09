package com.mahirshadid.vax;

public class Dataofdatesearch2 {
    String nid,location,date1,vac,date2;


    public Dataofdatesearch2() {
    }

    public Dataofdatesearch2(String nid, String location, String date1, String vac, String date2) {
        this.nid = nid;
        this.location = location;
        this.date1 = date1;
        this.vac = vac;
        this.date2 = date2;

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

    public String getDate2() { return date2; }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

}
