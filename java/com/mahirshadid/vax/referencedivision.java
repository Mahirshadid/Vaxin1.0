package com.mahirshadid.vax;

public class referencedivision {
    private String refname,refbirth,refnid,refloc,refmob;

    public referencedivision() {
    }

    public referencedivision(String refname, String refbirth, String refnid, String refloc, String refmob) {
        this.refname = refname;
        this.refbirth = refbirth;
        this.refnid = refnid;
        this.refloc = refloc;
        this.refmob = refmob;
    }

    public String getRefname() {
        return refname;
    }

    public void setRefname(String refname) {
        this.refname = refname;
    }

    public String getRefbirth() {
        return refbirth;
    }

    public void setRefbirth(String refbirth) {
        this.refbirth = refbirth;
    }

    public String getRefnid() {
        return refnid;
    }

    public void setRefnid(String refnid) {
        this.refnid = refnid;
    }

    public String getRefloc() {
        return refloc;
    }

    public void setRefloc(String refloc) {
        this.refloc = refloc;
    }

    public String getRefmob() {
        return refmob;
    }

    public void setRefmob(String refmob) {
        this.refmob = refmob;
    }
}
