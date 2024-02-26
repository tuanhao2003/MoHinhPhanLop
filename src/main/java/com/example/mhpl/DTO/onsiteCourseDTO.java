package com.example.mhpl.DTO;

import java.sql.Time;

public class onsiteCourseDTO {
    private int courseID;
    private String location;
    private String days;
    private Time time;

    public onsiteCourseDTO() {
        this.courseID = 0;
        this.location = null;
        this.days = null;
        this.time = null;
    }

    public onsiteCourseDTO(int cid, String lct, String dd, Time tm) {
        this.courseID = cid;
        this.location = lct;
        this.days = dd;
        this.time = tm;
    }

    public void setcourseID(int cid) {
        this.courseID = cid;
    }

    public void setlocation(String lct) {
        this.location = lct;
    }

    public void setdays(String dd) {
        this.days = dd;
    }

    public void settime(Time tm) {
        this.time = tm;
    }

    public int getcourseID() {
        return this.courseID;
    }

    public String getlocation() {
        return this.location;
    }

    public String getdays() {
        return this.days;
    }

    public Time gettime() {
        return this.time;
    }
}
