package com.example.mhpl.DTO;

public class courseDTO {
    private int courseID;
    private String title;
    private int credits;
    private int departmentID;

    public courseDTO() {
        this.courseID = 0;
        this.title = null;
        this.credits = 0;
        this.departmentID = 0;
    }

    public courseDTO(int cid, String tt, int cd, int did) {
        this.courseID = cid;
        this.title = tt;
        this.credits = cd;
        this.departmentID = did;
    }

    public void setcourseID(int cid) {
        this.courseID = cid;
    }

    public void settitle(String tt) {
        this.title = tt;
    }

    public void setcredits(int cd) {
        this.credits = cd;
    }

    public void setdepartmentID(int did) {
        this.departmentID = did;
    }

    public int getcourseID() {
        return this.courseID;
    }

    public String gettitle() {
        return this.title;
    }

    public int getcredits() {
        return this.credits;
    }

    public int getdepartmentID() {
        return this.departmentID;
    }
}
