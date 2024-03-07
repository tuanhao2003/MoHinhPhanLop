package com.example.mhpl.BLL;

import java.util.ArrayList;

import com.example.mhpl.DAO.courseDAO;
import com.example.mhpl.DAO.courseInstructorDAO;
import com.example.mhpl.DAO.departmentDAO;
import com.example.mhpl.DTO.courseDTO;
import com.example.mhpl.DTO.courseInstructorDTO;
import com.example.mhpl.DTO.teacherDTO;

public class courseInstructorManageBLL {
    private courseInstructorDAO courseInstructorDAO;
    private courseDAO courseDAO;
    private departmentDAO departmentDAO;

    public courseInstructorManageBLL() {
        this.courseInstructorDAO = new courseInstructorDAO();
        this.courseDAO = new courseDAO();
        this.departmentDAO = new departmentDAO();
    }

    public ArrayList<courseDTO> getNonInstructedCourse(){
        ArrayList<courseDTO> courseRemain = new ArrayList<courseDTO>();
        ArrayList<Integer> coursesInstructedID = new ArrayList<Integer>();
        this.courseInstructorDAO.getAllCourseInstructor().forEach(ins -> coursesInstructedID.add(ins.getcourseID()));

        for(courseDTO i : this.courseDAO.getAllCourse()){
            if(!coursesInstructedID.contains(i.getcourseID())){
                courseRemain.add(i);
            }
        }
        return courseRemain;
    }
    
    public courseDTO getCourseByID(int id){
        return courseDAO.getCourseByCourseID(id);
    }
    
    public String getDepartmentName(int cid){
        courseDTO course = getCourseByID(cid);
        return this.departmentDAO.getDepartmentByID(course.getdepartmentID()).getname();
    }
    
    public String getCourseStatus(int cid){
        return this.courseInstructorDAO.getAllCourseInstructorByCourseID(cid).isEmpty() ? "Inactive" : "Active";
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
    
//    public ArrayList<teacherDTO> getNoneInCourse(){
//        ArrayList<Integer> courseStudentID = new ArrayList<Integer>();
//        getCourseStudent().forEach(stu -> courseStudentID.add(stu.getpersonID()));
//        
//        ArrayList<teacherDTO> remainCourse = new ArrayList<teacherDTO>();
//        for(teacherDTO i : getAllStudent()){
//            if(!courseStudentID.contains(i.getpersonID())){
//                remainCourse.add(i);
//            }
//        }
//        return remainCourse;
//    }
}
