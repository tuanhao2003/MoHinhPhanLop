package com.example.mhpl.DTO;

public class onlineCourseDTO {
    private int courseID;
    private String url;

    public onlineCourseDTO() {
        this.courseID = 0;
        this.url = null;

    }

    public onlineCourseDTO(int cid, String u) {
        this.courseID = cid;
        this.url = u;

    }

    public void setcourseID(int cid) {
        this.courseID = cid;
    }

    public void seturl(String u) {
        this.url = u;
    }

    public int getcourseID() {
        return this.courseID;
    }

    public String geturl() {
        return this.url;
    }
}
