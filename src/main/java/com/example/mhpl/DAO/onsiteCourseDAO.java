package com.example.mhpl.DAO;

import java.sql.*;
import java.util.ArrayList;
public class onsiteCourseDAO {
    private MyDatabaseManager sql = new MyDatabaseManager() ;
    private Connection C;

    public onsiteCourseDAO() {
        this.C = this.sql.connect();
    }

    public ArrayList<onsiteCourseDTO> getAllOnsiteCourse() {
        String query = "select * from OnsiteCourse;";
        ResultSet R = null;
        ArrayList<onsiteCourseDTO> lst = new ArrayList<onsiteCourseDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new onsiteCourseDTO(R.getInt(1), R.getString(2), R.getString(3), R.getTime(4)));
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public onsiteCourseDTO getOnsiteCourseByID(int did) {
        String query = "select * from OnsiteCourse where CourseID = ?;";
        ResultSet R = null;
        onsiteCourseDTO data = new onsiteCourseDTO();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, did);
            R = S.executeQuery();
            while (R.next()) {
                data = new onsiteCourseDTO(R.getInt(1), R.getString(2), R.getString(3), R.getTime(4));
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteOnsiteCourse(int did) {
        String query = "delete from OnsiteCourse where CourseID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, did);
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOnsiteCourse(onsiteCourseDTO oc) {
        String query = "update OnsiteCourse set Location = ?, Days = ?, Time = ? where CourseID = ?";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(4, oc.getcourseID());
            S.setString(1, oc.getlocation());
            S.setString(2, oc.getdays());
            S.setTime(3, oc.gettime());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addOnsiteCourse(onsiteCourseDTO oc) {
        String query = "insert into OnsiteCourse values(?, ?, ?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, oc.getcourseID());
            S.setString(2, oc.getlocation());
            S.setString(3, oc.getdays());
            S.setTime(4, oc.gettime());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     
}
