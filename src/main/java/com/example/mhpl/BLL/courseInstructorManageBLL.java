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

    public boolean addInstruction(int cid, int pid){
        return courseInstructorDAO.addCourseInstructor(new courseInstructorDTO(cid, pid));
    }

    public boolean deleteInstruction(int cid){
        return courseInstructorDAO.deleteCourseInstructorByCourseID(cid);
    }
    
    public boolean updateInstruction(int cid, int pid){
        return courseInstructorDAO.updateCourse(new courseInstructorDTO(cid, pid));
    }
}
