package com.example.mhpl.DAO;

import com.example.mhpl.DTO.onsiteCourseDTO;
import java.sql.*;
import java.util.ArrayList;
public class onsiteCourseDAO {
    private MyDatabaseManager sql;
    private Connection C;

    public onsiteCourseDAO() {
        this.C = sql.connect();
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
            return null;
        }
    }

    public ArrayList<onsiteCourseDTO> getOnsiteCourseByID(int did) {
        String query = "select * from OnsiteCourse where CourseID = ?;";
        ResultSet R = null;
        ArrayList<onsiteCourseDTO> lst = new ArrayList<onsiteCourseDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, did);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new onsiteCourseDTO(R.getInt(1), R.getString(2), R.getString(3), R.getTime(4)));
            }
            return lst;
        } catch (SQLException e) {
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
            return false;
        }
    }

    public boolean updateonsiteCourse(onsiteCourseDTO oc) {
        String query = "update onsiteCourse set CourseID = ?, Name = ?, Budget = ?, StartDate = ?, Administrator = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, oc.getcourseID());
            S.setString(2, oc.getlocation());
            S.setString(3, oc.getdays());
            S.setTime(4, oc.gettime());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addonsiteCourse(onsiteCourseDTO oc) {
        String query = "insert into onsiteCourse values(?, ?, ?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, oc.getcourseID());
            S.setString(2, oc.getlocation());
            S.setString(3, oc.getdays());
            S.setTime(4, oc.gettime());
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
