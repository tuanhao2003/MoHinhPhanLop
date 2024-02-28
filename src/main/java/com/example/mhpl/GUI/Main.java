package com.example.mhpl.GUI;

import java.sql.Time;
import java.util.ArrayList;

import com.example.mhpl.BLL.courseInformationManageBLL;
import com.example.mhpl.DAO.onlineCourseDAO;
import com.example.mhpl.DTO.courseDTO;
import com.example.mhpl.DTO.onlineCourseDTO;
import com.example.mhpl.DTO.onsiteCourseDTO;

public class Main {
    private static courseInformationManageBLL infor = new courseInformationManageBLL();

    public static void main(String[] args) {
        // infor.deleteCourse(4066);
        // infor.addCourse("house", 3, 1);
        // infor.updateCourse(4064, "not house", 3, 7);
        // System.out.println(infor.getLastedCourse().getcourseID());
        // ArrayList<courseDTO> lst = infor.getAllCourse();
        // for(courseDTO i : lst2\){
        // System.out.println(i.getcourseID() +" "+ i.gettitle());
        // }

        // infor.addOnlineCourse("house", 2, 3, "https://facebook.com");
        // infor.updateOnlineCourse(4065, "https://youtube.com");
        // ArrayList<onlineCourseDTO> lst2 = infor.getAllOnlineCourse();
        // for(onlineCourseDTO i : lst2){
        // System.out.println(i.getcourseID() +" "+ i.geturl());
        // }

        // infor.addNewOnsiteCourse("house", 3, 7,"nguyen hue", "29/12", new Time(500));
        // infor.updateOnsiteCourse(4065, "home", "29/10/2024", new Time(4000));
        // ArrayList<onsiteCourseDTO> lst3 = infor.getAllOnsiteCourse();
        // for (onsiteCourseDTO i : lst3) {
        //     System.out.println(i.getcourseID() + " " + i.getlocation());
        // }
    }
}
