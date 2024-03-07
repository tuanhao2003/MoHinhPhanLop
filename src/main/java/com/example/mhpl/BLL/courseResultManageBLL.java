package com.example.mhpl.BLL;

import com.example.mhpl.DAO.personDAO;
import com.example.mhpl.DAO.studentGradeDAO;
import com.example.mhpl.DTO.personDTO;
import com.example.mhpl.DTO.studentDTO;
import com.example.mhpl.DTO.studentGradeDTO;
import java.sql.Date;
import java.util.ArrayList;

public class courseResultManageBLL {
    private studentGradeDAO studentGradeDAO;
    private studentDTO studentDTO; 
    private personDAO personDAO;
    private int courseID;

    public courseResultManageBLL() {
        this.studentGradeDAO = new studentGradeDAO();
        this.studentDTO = new studentDTO();
        this.personDAO = new personDAO();
        this.courseID = 0;
    }
    
    public ArrayList<studentGradeDTO> getCourseResult(){
        ArrayList<studentGradeDTO> gradeList = new ArrayList<studentGradeDTO>();
        for(studentDTO i : getCourseStudent()){
            gradeList.add(getByCourseAndStudentID(i.getpersonID()));
        }
        return gradeList;
    }
    
    public boolean saveResult(int eid, int sid, double grade){
        try {
            studentGradeDTO stuGrade = new studentGradeDTO(eid, this.courseID, sid, grade);
            studentGradeDAO.updateStudentGrade(stuGrade);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public studentGradeDTO getByCourseAndStudentID(int sid){
        return studentGradeDAO.getStudentGradeByCourseAndStudentID(this.courseID, sid);
    }
    
    public ArrayList<studentDTO> getAllStudent(){
        ArrayList<personDTO> psl = this.personDAO.getAllPerson();
        ArrayList<studentDTO> data = new ArrayList<studentDTO>();
        for(personDTO i : psl){
            if(i.getenrollmentDate() != null){
                data.add(convertToStudent(i));
            }
        }
        return data;
    }
    
    public void setCourseID(int cid){
        this.courseID = cid;
    }
    
    public int getCourseID(){
        return this.courseID;
    }
    
    public ArrayList<studentDTO> getCourseStudent(){
        ArrayList<Integer> studentInCourseID = new ArrayList<Integer>();
        studentGradeDAO.getStudentGradeByCourseID(this.courseID).forEach(stu -> {
            studentInCourseID.add(stu.getstudentID());
        });

        ArrayList<studentDTO> result = new ArrayList<studentDTO>();
        for(studentDTO i : getAllStudent()){
            if(studentInCourseID.contains(i.getpersonID())){
                result.add(i);
            }
        }
        return result;
    }
    
    private studentDTO convertToStudent(personDTO ps){
        return new studentDTO(ps.getpersonID(), ps.getlastName(), ps.getfirstName(), ps.getenrollmentDate());
    }
    
    public void addStudentToCourse(int sid){
        this.studentGradeDAO.addstudentGrade(new studentGradeDTO(0, this.courseID, sid, 0.0));
    }
    public void addStudentToCourse(String fn, String ln){
        this.personDAO.addPerson(new studentDTO(0, ln, fn, new Date(System.currentTimeMillis())));
        this.studentGradeDAO.addstudentGrade(new studentGradeDTO(0, this.courseID, this.personDAO.getLastestPersonID(), 0));
    }
    
    public ArrayList<studentDTO> getNoneInCourse(){
        ArrayList<Integer> courseStudentID = new ArrayList<Integer>();
        getCourseStudent().forEach(stu -> courseStudentID.add(stu.getpersonID()));
        
        ArrayList<studentDTO> remainCourse = new ArrayList<studentDTO>();
        for(studentDTO i : getAllStudent()){
            if(!courseStudentID.contains(i.getpersonID())){
                remainCourse.add(i);
            }
        }
        return remainCourse;
    }
}
