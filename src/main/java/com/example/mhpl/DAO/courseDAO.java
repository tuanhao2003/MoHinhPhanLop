package com.example.mhpl.DAO;

import java.sql.*;
import java.util.ArrayList;

import com.example.mhpl.DTO.courseDTO;

public class courseDAO {
    private MyDatabaseManager sql = new MyDatabaseManager() ;
    private Connection C;

    public courseDAO() {
        this.C = this.sql.connect();
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
            e.printStackTrace();
            return null;
        }
    }

    public courseDTO getLastedCourse() {
        String query = "select * from Course;";
        ResultSet R = null;
        courseDTO data = new courseDTO();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                data = new courseDTO(R.getInt(1), R.getString(2), R.getInt(3), R.getInt(4));
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public courseDTO getCourseByCourseID(int cid) {
        String query = "select * from Course where CourseID = ?;";
        ResultSet R = null;
        courseDTO data = new courseDTO();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cid);
            R = S.executeQuery();
            while (R.next()) {
                data = new courseDTO(R.getInt(1), R.getString(2), R.getInt(3), R.getInt(4));
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<courseDTO> getCourseByTitle(String tt) {
        String query = "select * from Course where Title like ?;";
        ResultSet R = null;
        ArrayList<courseDTO> lst = new ArrayList<courseDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setString(1, '%'+tt+'%');
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new courseDTO(R.getInt(1), R.getString(2), R.getInt(3), R.getInt(4)));
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCourse(courseDTO course) {
        String query = "update Course set Title = ?, Credits = ?, DepartmentID = ? where CourseID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(4, course.getcourseID());
            S.setString(1, course.gettitle());
            S.setInt(2, course.getcredits());
            S.setInt(3, course.getdepartmentID());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addCourse(courseDTO course) {
        String query = "insert into Course(Title, Credits, DepartmentID) values(?, ?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setString(1, course.gettitle());
            S.setInt(2, course.getcredits());
            S.setInt(3, course.getdepartmentID());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     
}
