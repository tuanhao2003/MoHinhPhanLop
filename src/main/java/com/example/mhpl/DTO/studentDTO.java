package com.example.mhpl.DTO;

import java.sql.Date;

public class studentDTO extends personDTO {

    public studentDTO() {
        super();
    }

    public studentDTO(int pid, String ln, String fn, Date ed) {
        super(pid, ln, fn, null, ed);
    }
    
    @Override
    public void sethireDate(Date hd) {
        System.out.println("Student cannot have hire date");
    }

    @Override
    public Date gethireDate() {
        return null;
    }
}
