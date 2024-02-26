package com.example.mhpl.DTO;

public class courseInstructorDTO {
    private int courseID;
    private int personID;

    public courseInstructorDTO() {
        this.courseID = 0;
        this.personID = 0;

    }

    public courseInstructorDTO(int cid, int pid) {
        this.courseID = cid;
        this.personID = pid;

    }

    public void setcourseID(int cid) {
        this.courseID = cid;
    }

    public void setpersonID(int pid) {
        this.personID = pid;
    }

    public int getcourseID() {
        return this.courseID;
    }

    public int getpersonID() {
        return this.personID;
    }
}
