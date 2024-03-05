package com.example.mhpl.DTO;

public class studentGradeDTO {
    private int enrollmentID;
    private int courseID;
    private int studentID;
    private double grade;

    public studentGradeDTO() {
        this.enrollmentID = 0;
        this.courseID = 0;
        this.studentID = 0;
        this.grade = 0;
    }

    public studentGradeDTO(int eid, int cid, int sid, double gr) {
        this.enrollmentID = eid;
        this.courseID = cid;
        this.studentID = sid;
        this.grade = gr;
    }

    public void setenrollmentID(int eid) {
        this.enrollmentID = eid;
    }

    public void setcourseID(int cid) {
        this.courseID = cid;
    }

    public void setstudentID(int sid) {
        this.studentID = sid;
    }

    public void setgrade(double gr) {
        this.grade = gr;
    }

    public int getenrollmentID() {
        return this.enrollmentID;
    }

    public int getcourseID() {
        return this.courseID;
    }

    public int getstudentID() {
        return this.studentID;
    }

    public double getgrade() {
        return this.grade;
    }
}
