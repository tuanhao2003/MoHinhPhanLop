package com.example.mhpl.DTO;

import java.sql.Date;

public class departmentDTO {
    private int departmentID;
    private String name;
    private double budget;
    private Date startDate;
    private int administrator;

    public departmentDTO() {
        this.departmentID = 0;
        this.name = null;
        this.budget = 0;
        this.startDate = null;
        this.administrator = 0;
    }

    public departmentDTO(int did, String na, double bg, Date sd, int ad) {
        this.departmentID = did;
        this.name = na;
        this.budget = bg;
        this.startDate = sd;
        this.administrator = ad;
    }

    public void setdepartmentID(int did) {
        this.departmentID = did;
    }

    public void setname(String na) {
        this.name = na;
    }

    public void setbudget(double bg) {
        this.budget = bg;
    }

    public void setstartDate(Date sd) {
        this.startDate = sd;
    }

    public void setadministrator(int ad) {
        this.administrator = ad;
    }

    public int getdepartmentID() {
        return this.departmentID;
    }

    public String getname() {
        return this.name;
    }

    public double getbudget() {
        return this.budget;
    }

    public Date getstartDate() {
        return this.startDate;
    }
    public int getadministrator() {
        return this.administrator;
    }
}
