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

    public ArrayList<onlineCourseDTO> getAllOnlineCourseByCourseID(int cid) {
        String query = "select * from OnlineCourse where CourseID = ?;";
        ResultSet R = null;
        ArrayList<onlineCourseDTO> lst = new ArrayList<onlineCourseDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cid);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new onlineCourseDTO(R.getInt(1), R.getString(2)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteOnlineCourseByCourseID(int cid) {
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
        String query = "update onlineCourse set CourseID = ?, PersonID = ?;";
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

    public boolean addonlineCourse(onlineCourseDTO onlCourse) {
        String query = "insert into onlineCourse values(?, ?);";
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
