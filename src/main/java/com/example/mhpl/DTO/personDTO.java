package com.example.mhpl.DTO;

import java.sql.Date;

public class personDTO {
    private int personID;
    private String lastName;
    private String firstName;
    private Date hireDate;
    private Date enrollmentDate;

    public personDTO() {
        this.personID = 0;
        this.lastName = null;
        this.firstName = null;
        this.hireDate = null;
        this.enrollmentDate = null;
    }

    public personDTO(int pid, String ln, String fn, Date hd, Date ed) {
        this.personID = pid;
        this.lastName = ln;
        this.firstName = fn;
        this.hireDate = hd;
        this.enrollmentDate = ed;
    }

    public void setpersonID(int pid) {
        this.personID = pid;
    }

    public void setlastName(String ln) {
        this.lastName = ln;
    }

    public void setfirstName(String fn) {
        this.firstName = fn;
    }

    public void sethireDate(Date hd) {
        this.hireDate = hd;
    }

    public void setenrollmentDate(Date ed) {
        this.enrollmentDate = ed;
    }

    public int getpersonID() {
        return this.personID;
    }

    public String getlastName() {
        return this.lastName;
    }

    public String getfirstName() {
        return this.firstName;
    }

    public Date gethireDate() {
        return this.hireDate;
    }

    public Date getenrollmentDate() {
        return this.enrollmentDate;
    }
}
