package com.example.mhpl.BLL;

import java.sql.Time;
import java.util.ArrayList;

import com.example.mhpl.DAO.courseDAO;
import com.example.mhpl.DAO.courseInstructorDAO;
import com.example.mhpl.DAO.departmentDAO;
import com.example.mhpl.DAO.onlineCourseDAO;
import com.example.mhpl.DTO.courseDTO;
import com.example.mhpl.DTO.onlineCourseDTO;
import com.example.mhpl.DTO.onsiteCourseDTO;
import com.example.mhpl.DTO.personDTO;
import com.example.mhpl.DTO.studentDTO;
import com.example.mhpl.DTO.teacherDTO;
import com.example.mhpl.DAO.onsiteCourseDAO;
import com.example.mhpl.DAO.personDAO;
import com.example.mhpl.DAO.studentGradeDAO;
import com.example.mhpl.DTO.departmentDTO;

public class courseInformationManageBLL {
    private courseDAO courseDAO;
    private onlineCourseDAO onlineCourseDAO;
    private onsiteCourseDAO onsiteCourseDAO;
    private departmentDAO departmentDAO;
    private courseInstructorDAO courseInstructorDAO;
    private personDAO personDAO;
    private studentGradeDAO studentGradeDAO;

    public courseInformationManageBLL(){
        this.courseDAO = new courseDAO();
        this.onlineCourseDAO = new onlineCourseDAO();
        this.onsiteCourseDAO = new onsiteCourseDAO();
        this.departmentDAO = new departmentDAO();
        this.courseInstructorDAO = new courseInstructorDAO();
        this.personDAO = new personDAO();
        this.studentGradeDAO = new studentGradeDAO();
    }

    public ArrayList<courseDTO> getAllCourse(){
        return courseDAO.getAllCourse();
    }
    
    public ArrayList<courseDTO> getInstructedCourse(){
        ArrayList<courseDTO> instructedCourseList = new ArrayList<courseDTO>();
        getAllCourse().forEach(course -> {
            if("Active".equals(getCourseStatus(course.getcourseID()))) {
                instructedCourseList.add(course);
            }
        });
        return instructedCourseList;
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
    
    public String getDepartmentName(int cid){
        courseDTO course = getCourseByID(cid);
        return this.departmentDAO.getDepartmentByID(course.getdepartmentID()).getname();
    }
    
    public String getCourseStatus(int cid){
        return this.courseInstructorDAO.getAllCourseInstructorByCourseID(cid).isEmpty() ? "Inactive" : "Active";
    }
    
    public String getCourseType(int cid){
        ArrayList<Integer> onlineCourseIDList = new ArrayList<Integer>();
        ArrayList<Integer> onsiteCourseIDList = new ArrayList<Integer>();
        for(onlineCourseDTO i : getAllOnlineCourse()){
            onlineCourseIDList.add(i.getcourseID());
        }
        for(onsiteCourseDTO i : getAllOnsiteCourse()){
            onsiteCourseIDList.add(i.getcourseID());
        }
        return onlineCourseIDList.contains(cid) ? "Online" : (onsiteCourseIDList.contains(cid) ? "Offline" : "New course");
    }
    
    public ArrayList<courseDTO> getCourseByDepartmentID(int id){
        return courseDAO.getCourseByDepartmentID(id);
    }

    public ArrayList<courseDTO> getCourseByCredits(int cd){
        return courseDAO.getCourseByCredits(cd);
    }
    
    public ArrayList<courseDTO> getCourseByTitle(String tt){
        return courseDAO.getCourseByTitle(tt);
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
    
    public ArrayList<studentDTO> getCourseStudent(int cid){
        ArrayList<Integer> studentInCourseID = new ArrayList<Integer>();
        studentGradeDAO.getStudentGradeByCourseID(cid).forEach(stu -> {
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

    public teacherDTO getCourseTeacher(int cid){
        ArrayList<Integer> teacherInstrucedID = new ArrayList<Integer>();
        courseInstructorDAO.getAllCourseInstructorByCourseID(cid).forEach(tc -> {
            teacherInstrucedID.add(tc.getpersonID());
        });

        teacherDTO result = new teacherDTO();
        for(teacherDTO i : getAllTeacher()){
            if(teacherInstrucedID.contains(i.getpersonID())){
                result = i;
            }
        }
        return result;
    }
    
    public ArrayList<departmentDTO> getAllDepartment(){
        return departmentDAO.getAllDepartment();
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
    public boolean addTeacher(teacherDTO tc){
        return this.personDAO.addPerson(tc);
    }

    public boolean addStudent(studentDTO sd){
        return this.personDAO.addPerson(sd);
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

    
    
    
    
    public void deleteCourse(int id){
        onlineCourseDAO.deleteOnlineCourse(id);
        onsiteCourseDAO.deleteOnsiteCourse(id);
        courseDAO.deleteCourse(id);
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
    

    
    

    

    public teacherDTO convertToTeacher(personDTO ps){
        return new teacherDTO(ps.getpersonID(), ps.getlastName(), ps.getfirstName(), ps.gethireDate());
    }

    public studentDTO convertToStudent(personDTO ps){
        return new studentDTO(ps.getpersonID(), ps.getlastName(), ps.getfirstName(), ps.getenrollmentDate());
    }

    
    public ArrayList<courseDTO> sortCourseByID(ArrayList<courseDTO> allCourse){
        boolean sorted = false;
        ArrayList<courseDTO> listAdjusted = allCourse;
        for(int i = 0; i< listAdjusted.size()-1; i++){
            for(int j = i+1; j< listAdjusted.size(); j++){
                if(listAdjusted.get(i).getcourseID() > listAdjusted.get(j).getcourseID()){
                    courseDTO tmp = listAdjusted.get(i);
                    listAdjusted.set(i, listAdjusted.get(j));
                    listAdjusted.set(j, tmp);
                    sorted = true;
                }
            }
        }
        if(sorted == false){
            for(int i = 0; i< listAdjusted.size()-1; i++){
                for(int j = i+1; j< listAdjusted.size(); j++){
                    if(listAdjusted.get(i).getcourseID() < listAdjusted.get(j).getcourseID()){
                        courseDTO tmp = listAdjusted.get(i);
                        listAdjusted.set(i, listAdjusted.get(j));
                        listAdjusted.set(j, tmp);
                    }
                }
            }
        }
        return listAdjusted;
    }
}
