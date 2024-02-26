package com.example.mhpl.DAO;

import java.sql.*;
import java.util.ArrayList;

import com.example.mhpl.DTO.courseDTO;

public class courseDAO {
    private MyDatabaseManager sql;
    private Connection C;

    public courseDAO() {
        this.C = sql.connect();
    }

    public ArrayList<courseDTO> getAllCourse() {
        String query = "select * from Course;";
        ResultSet R = null;
        ArrayList<courseDTO> lst = new ArrayList<courseDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new courseDTO(R.getInt(1), R.getString(2), R.getInt(3), R.getInt(4)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<courseDTO> getCourseByCourseID(int cid) {
        String query = "select * from Course where CourseID = ?;";
        ResultSet R = null;
        ArrayList<courseDTO> lst = new ArrayList<courseDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cid);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new courseDTO(R.getInt(1), R.getString(2), R.getInt(3), R.getInt(4)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<courseDTO> getCourseByDepartmentID(int did) {
        String query = "select * from Course where DepartmentID = ?;";
        ResultSet R = null;
        ArrayList<courseDTO> lst = new ArrayList<courseDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, did);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new courseDTO(R.getInt(1), R.getString(2), R.getInt(3), R.getInt(4)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<courseDTO> getCourseByCredits(int cd) {
        String query = "select * from Course where Credits = ?;";
        ResultSet R = null;
        ArrayList<courseDTO> lst = new ArrayList<courseDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cd);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new courseDTO(R.getInt(1), R.getString(2), R.getInt(3), R.getInt(4)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteCourse(int cid) {
        String query = "delete from Course where CourseID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cid);
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateCourse(courseDTO course) {
        String query = "update Course set CourseID = ?, Title = ?, Credits = ?, DepartmentID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, course.getcourseID());
            S.setString(2, course.gettitle());
            S.setInt(3, course.getcredits());
            S.setInt(4, course.getdepartmentID());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addCourse(courseDTO course) {
        String query = "insert into Department values(?, ?, ?, ?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, course.getcourseID());
            S.setString(2, course.gettitle());
            S.setInt(3, course.getcredits());
            S.setInt(5, course.getdepartmentID());
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
