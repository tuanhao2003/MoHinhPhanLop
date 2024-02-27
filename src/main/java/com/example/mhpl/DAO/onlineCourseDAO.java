package com.example.mhpl.DAO;

import java.sql.*;
import com.example.mhpl.DTO.onlineCourseDTO;
import java.util.ArrayList;

public class onlineCourseDAO {
    private MyDatabaseManager sql;
    private Connection C;

    public onlineCourseDAO() {
        this.C = sql.connect();
    }

    public ArrayList<onlineCourseDTO> getAllOnlineCourse() {
        String query = "select * from OnlineCourse;";
        ResultSet R = null;
        ArrayList<onlineCourseDTO> lst = new ArrayList<onlineCourseDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new onlineCourseDTO(R.getInt(1), R.getString(2)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public onlineCourseDTO getAllOnlineCourseByCourseID(int cid) {
        String query = "select * from OnlineCourse where CourseID = ?;";
        ResultSet R = null;
        onlineCourseDTO data = new onlineCourseDTO();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cid);
            R = S.executeQuery();
            while (R.next()) {
                data =  new onlineCourseDTO(R.getInt(1), R.getString(2));
            }
            return data;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteOnlineCourse(int cid) {
        String query = "delete from OnlineCourse where CourseID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cid);
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public boolean updateCourse(onlineCourseDTO onlCourse) {
        String query = "update OnlineCourse set CourseID = ?, PersonID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, onlCourse.getcourseID());
            S.setString(2, onlCourse.geturl());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addOnlineCourse(onlineCourseDTO onlCourse) {
        String query = "insert into OnlineCourse values(?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, onlCourse.getcourseID());
            S.setString(2, onlCourse.geturl());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void closeConnection(){
        try {
            this.C.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
