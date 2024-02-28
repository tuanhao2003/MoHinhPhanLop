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

    public courseInformationManageBLL(){
        this.courseDAO = new courseDAO();
        this.onlineCourseDAO = new onlineCourseDAO();
        this.onsiteCourseDAO = new onsiteCourseDAO();
    }

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

    public courseDTO getLastedCourse(){
        return courseDAO.getLastedCourse();
    }

    public void addCourse(String tt, int cd, int did){
        courseDTO course = new courseDTO(0, tt, cd, did);
        courseDAO.addCourse(course);
    }

    public void addNewOnlineCourse(String tt, int cd, int did, String url){
        addCourse(tt, cd, did);
        onlineCourseDTO course = new onlineCourseDTO(getLastedCourse().getcourseID() ,url);
        onlineCourseDAO.addOnlineCourse(course);
    }

    public void addNewOnsiteCourse(String tt, int cd, int did, String location, String days, Time time){
        addCourse(tt, cd, did);
        onsiteCourseDTO course = new onsiteCourseDTO(getLastedCourse().getcourseID(), location, days, time);
        onsiteCourseDAO.addOnsiteCourse(course);
    }

    public void addOnlineCourse(int cid, String url){
        onlineCourseDTO course = new onlineCourseDTO(cid ,url);
        onlineCourseDAO.addOnlineCourse(course);
    }

    public void addOnsiteCourse(int cid, String location, String days, Time time){
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

    public void updateOnlineCourse(int id, String url){
        onlineCourseDTO course = new onlineCourseDTO(id, url);
        onlineCourseDAO.updateOnlineCourse(course);
    }

    public void updateOnsiteCourse(int id, String lct, String days, Time time){
        onsiteCourseDTO course = new onsiteCourseDTO(id, lct, days, time);
        onsiteCourseDAO.updateOnsiteCourse(course);
    }

    public ArrayList<courseDTO> getCourseByDepartmentID(int id){
        return courseDAO.getCourseByDepartmentID(id);
    }

    public ArrayList<courseDTO> getCourseByCredits(int cd){
        return courseDAO.getCourseByCredits(cd);
    }

    public ArrayList<courseDTO> filterOnlineCourse(ArrayList<courseDTO> allCourse){
        ArrayList<courseDTO> listAdjusted = new ArrayList<courseDTO>();
        ArrayList<Integer> onlineCourseIDList = new ArrayList<Integer>();
        for(onlineCourseDTO i : getAllOnlineCourse()){
            onlineCourseIDList.add(i.getcourseID());
        }
        for(courseDTO i : allCourse){
            if(onlineCourseIDList.contains(i.getcourseID())){
                listAdjusted.add(i);
            }
        }
        return listAdjusted;
    }

    public ArrayList<courseDTO> filterOnsiteCourse(ArrayList<courseDTO> allCourse){
        ArrayList<courseDTO> listAdjusted = new ArrayList<courseDTO>();
        ArrayList<Integer> onsiteCourseIDList = new ArrayList<Integer>();
        for(onsiteCourseDTO i : getAllOnsiteCourse()){
            onsiteCourseIDList.add(i.getcourseID());
        }
        for(courseDTO i : allCourse){
            if(onsiteCourseIDList.contains(i.getcourseID())){
                listAdjusted.add(i);
            }
        }
        return listAdjusted;
    }

    public ArrayList<courseDTO> sortCourseByID(ArrayList<courseDTO> allCourse){
        ArrayList<courseDTO> listAdjusted = allCourse;
        for(int i = 0; i< listAdjusted.size()-1; i++){
            for(int j = i+1; j< listAdjusted.size(); j++){
                if(listAdjusted.get(i).getcourseID() > listAdjusted.get(j).getcourseID()){
                    courseDTO tmp = listAdjusted.get(i);
                    listAdjusted.set(i, listAdjusted.get(j));
                    listAdjusted.set(j, tmp);
                }
            }
        }
        return listAdjusted;
    }
}
