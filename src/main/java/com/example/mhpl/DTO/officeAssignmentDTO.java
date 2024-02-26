package com.example.mhpl.DTO;

import java.sql.*;

public class officeAssignmentDTO {
    private int instructorID;
    private String location;
    private Timestamp timeStamp;

    public officeAssignmentDTO() {
        this.instructorID = 0;
        this.location = null;
        this.timeStamp = null;
    }

    public officeAssignmentDTO(int iid, String lct, Timestamp ts) {
        this.instructorID = iid;
        this.location = lct;
        this.timeStamp = ts;
    }

    public void setinstructorID(int iid) {
        this.instructorID = iid;
    }

    public void setlocation(String lct) {
        this.location = lct;
    }

    public void settimeStamp(Timestamp ts) {
        this.timeStamp = ts;
    }

    public int getinstructorID() {
        return this.instructorID;
    }

    public String getlocation() {
        return this.location;
    }

    public Timestamp gettimeStamp() {
        return this.timeStamp;
    }
}
