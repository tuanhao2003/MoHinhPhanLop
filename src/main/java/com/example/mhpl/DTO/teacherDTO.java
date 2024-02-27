package com.example.mhpl.DTO;

import java.sql.Date;

public class teacherDTO extends personDTO{
    public teacherDTO() {
        super();
    }

    public teacherDTO(int pid, String ln, String fn, Date hd) {
        super(pid, ln, fn, hd, null);
    }

    @Override
    public void setenrollmentDate(Date hd) {
        System.out.println("teacher cannot have enrollment date");
    }

    @Override
    public Date getenrollmentDate() {
        return null;
    }
}
