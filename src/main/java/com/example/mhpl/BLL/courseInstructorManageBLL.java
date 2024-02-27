package com.example.mhpl.BLL;

import java.util.ArrayList;

import com.example.mhpl.DAO.courseDAO;
import com.example.mhpl.DAO.courseInstructorDAO;
import com.example.mhpl.DTO.courseDTO;
import com.example.mhpl.DTO.courseInstructorDTO;

public class courseInstructorManageBLL {
    private courseInstructorDAO courseInstructorDAO;
    private courseDAO courseDAO;

    public courseInstructorManageBLL() {
        this.courseInstructorDAO = new courseInstructorDAO();
        this.courseDAO = new courseDAO();
    }

    public ArrayList<courseDTO> getNonInstructedCourse(){
        ArrayList<courseInstructorDTO> coursesInstructed = this.courseInstructorDAO.getAllCourseInstructor();
        ArrayList<courseDTO> allCourse = this.courseDAO.getAllCourse();
        ArrayList<Integer> coursesInstructedID = new ArrayList<Integer>();
        for(courseInstructorDTO i : coursesInstructed){
            coursesInstructedID.add(i.getcourseID());
        }

        for(courseDTO i : allCourse){
            if(coursesInstructedID.contains(i.getcourseID())){
                allCourse.remove(i);
            }
        }
        return allCourse;
    }
}
