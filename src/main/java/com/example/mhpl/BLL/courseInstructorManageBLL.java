package com.example.mhpl.BLL;

import java.util.ArrayList;

import com.example.mhpl.DAO.courseDAO;
import com.example.mhpl.DAO.courseInstructorDAO;
import com.example.mhpl.DAO.departmentDAO;
import com.example.mhpl.DAO.onlineCourseDAO;
import com.example.mhpl.DAO.onsiteCourseDAO;
import com.example.mhpl.DTO.courseDTO;
import com.example.mhpl.DTO.courseInstructorDTO;
import com.example.mhpl.DAO.personDAO;
import com.example.mhpl.DAO.studentGradeDAO;
import com.example.mhpl.DTO.personDTO;
import com.example.mhpl.DTO.teacherDTO;
import java.sql.Date;

public class courseInstructorManageBLL {
    private courseInstructorDAO courseInstructorDAO;
    private courseDAO courseDAO;
    private departmentDAO departmentDAO;
    private personDAO personDAO;
    private studentGradeDAO studentGradeDAO;
    private onlineCourseDAO onlineCourseDAO;
    private onsiteCourseDAO onsiteCourseDAO;

    public courseInstructorManageBLL() {
        this.courseInstructorDAO = new courseInstructorDAO();
        this.courseDAO = new courseDAO();
        this.departmentDAO = new departmentDAO();
        this.personDAO = new personDAO();
        this.studentGradeDAO = new studentGradeDAO();
        this.onlineCourseDAO = new onlineCourseDAO();
        this.onsiteCourseDAO = new onsiteCourseDAO();
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

    public boolean addInstruction(int cid, int tid){
        return courseInstructorDAO.addCourseInstructor(new courseInstructorDTO(cid, tid));
    }

    public boolean deleteInstruction(int cid){
        return courseInstructorDAO.deleteCourseInstructorByCourseID(cid);
    }
    
    public boolean updateInstruction(int cid, int pid){
        return courseInstructorDAO.updateCourse(new courseInstructorDTO(cid, pid));
    }
    
    
    public teacherDTO convertToTeacher(personDTO ps){
        return new teacherDTO(ps.getpersonID(), ps.getlastName(), ps.getfirstName(), ps.gethireDate());
    }
    
    public ArrayList<teacherDTO> getAllTeacher(){
        ArrayList<personDTO> psl = this.personDAO.getAllPerson();
        ArrayList<teacherDTO> data = new ArrayList<teacherDTO>();
        for(personDTO i : psl){
            if(i.gethireDate() != null){
                data.add(convertToTeacher(i));
            }
        }
        return data;
    }
    
    public ArrayList<teacherDTO> getCourseTeacher(int cid){
        ArrayList<Integer> teacherInstrucedID = new ArrayList<Integer>();
        courseInstructorDAO.getAllCourseInstructorByCourseID(cid).forEach(tc -> {
            teacherInstrucedID.add(tc.getpersonID());
        });

        ArrayList<teacherDTO> result = new ArrayList<teacherDTO>();
        for(teacherDTO i : getAllTeacher()){
            if(teacherInstrucedID.contains(i.getpersonID())){
                result.add(i);
            }
        }
        return result;
    }
    
    public void assignTeacherToCourse(int cid, int tid){
        this.courseInstructorDAO.addCourseInstructor(new courseInstructorDTO(cid, tid));
    }
    public void assignTeacherToCourse(String fn, String ln, int cid){
        this.personDAO.addPerson(new teacherDTO(0, ln, fn, new Date(System.currentTimeMillis())));
        this.courseInstructorDAO.addCourseInstructor(new courseInstructorDTO(cid, personDAO.getLastestPersonID()));
    }
    
    public void deleteCourse(int id){
        this.studentGradeDAO.deleteStudentGradeByCourseID(id);
        this.onlineCourseDAO.deleteOnlineCourse(id);
        this.onsiteCourseDAO.deleteOnsiteCourse(id);
        this.courseDAO.deleteCourse(id);
    }
}
