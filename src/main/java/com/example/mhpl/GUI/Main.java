package com.example.mhpl.GUI;

import java.util.ArrayList;

import com.example.mhpl.BLL.courseInformationManageBLL;
import com.example.mhpl.DTO.courseDTO;

public class Main {
    private static courseInformationManageBLL infor = new courseInformationManageBLL();
    public static void main(String[] args) {
        infor.deleteCourse(4063);
        ArrayList<courseDTO> lst = infor.getAllCourse();
        for(courseDTO i : lst){
            System.out.println(i.getcourseID() +" "+ i.gettitle());
        }
        // infor.addCourse("house", 3, 1);
    }
}
