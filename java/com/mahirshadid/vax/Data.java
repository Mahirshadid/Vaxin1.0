package com.mahirshadid.vax;

public class Data {
    String nam,pass,passcon,mob,nid,loc,birth,key;

    public Data() {
    }

    public Data(String nam, String birth, String pass, String passcon, String mob, String nid, String loc, String key) {
        this.nam = nam;
        this.birth = birth;
        this.pass = pass;
        this.passcon = passcon;
        this.mob = mob;
        this.nid = nid;
        this.loc = loc;
        this.key = key;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPasscon() {
        return passcon;
    }

    public void setPasscon(String passcon) {
        this.passcon = passcon;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}



