package com.example.mhpl.BLL;

import java.sql.Time;
import java.util.ArrayList;

import com.example.mhpl.DAO.courseDAO;
import com.example.mhpl.DAO.onlineCourseDAO;
import com.example.mhpl.DTO.courseDTO;
import com.example.mhpl.DTO.onlineCourseDTO;
import com.example.mhpl.DTO.onsiteCourseDTO;
import com.example.mhpl.DAO.onsiteCourseDAO;

public class courseInformationManageBLL {
    private courseDAO courseDAO;
    private onlineCourseDAO onlineCourseDAO;
    private onsiteCourseDAO onsiteCourseDAO;

    public ArrayList<courseDTO> getAllCourse(){
        return courseDAO.getAllCourse();
    }

    public ArrayList<onlineCourseDTO> getAllOnlineCourse(){
        return onlineCourseDAO.getAllOnlineCourse();
    }

    public ArrayList<onsiteCourseDTO> getAllOnsiteCourse(){
        return onsiteCourseDAO.getAllOnsiteCourse();
    }


    public courseDTO getCourseByID(int id){
        return courseDAO.getCourseByCourseID(id);
    }

    public onlineCourseDTO getOnlineCourseByID(int id){
        return onlineCourseDAO.getAllOnlineCourseByCourseID(id);
    }

    public onsiteCourseDTO getOnsiteCourseByID(int id){
        return onsiteCourseDAO.getOnsiteCourseByID(id);
    }

    public void addCourse(String tt, int cd, int did){
        courseDTO course = new courseDTO(0, tt, cd, did);
        courseDAO.addCourse(course);
    }

    public void addOnlineCourse(int cid, String tt, int cd, int did, String url){
        addCourse(tt, cd, did);
        onlineCourseDTO course = new onlineCourseDTO(cid, url);
        onlineCourseDAO.addOnlineCourse(course);
    }

    public void addOnsiteCourse(int cid, String tt, int cd, int did, String location, String days, Time time){
        addCourse(tt, cd, did);
        onsiteCourseDTO course = new onsiteCourseDTO(cid, location, days, time);
        onsiteCourseDAO.addOnsiteCourse(course);
    }

    public void deleteCourse(int id){
        onlineCourseDAO.deleteOnlineCourse(id);
        onsiteCourseDAO.deleteOnsiteCourse(id);
        courseDAO.deleteCourse(id);
    }

    public void updateCourse(int id, String tt, int cd, int did){
        courseDTO course = new courseDTO(id, tt, cd, did);
        courseDAO.updateCourse(course);
    }

    public ArrayList<courseDTO> getOnsiteCourseByDepartmentID(int id){
        return courseDAO.getCourseByDepartmentID(id);
    }

    public ArrayList<courseDTO> getOnsiteCourseByCredits(int cd){
        return courseDAO.getCourseByCredits(cd);
    }
}
