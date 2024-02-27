package com.example.mhpl.DAO;

import java.sql.*;
import java.util.ArrayList;

import com.example.mhpl.DTO.courseInstructorDTO;

public class courseInstructorDAO {
    private MyDatabaseManager sql = new MyDatabaseManager() ;
    private Connection C;

    public courseInstructorDAO() {
        this.C = this.sql.connect();
    }

    public ArrayList<courseInstructorDTO> getAllCourseInstructor() {
        String query = "select * from CourseInstructor;";
        ResultSet R = null;
        ArrayList<courseInstructorDTO> lst = new ArrayList<courseInstructorDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new courseInstructorDTO(R.getInt(1), R.getInt(2)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<courseInstructorDTO> getAllCourseInstructorByCourseID(int cid) {
        String query = "select * from CourseInstructor where CourseID = ?;";
        ResultSet R = null;
        ArrayList<courseInstructorDTO> lst = new ArrayList<courseInstructorDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cid);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new courseInstructorDTO(R.getInt(1), R.getInt(2)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<courseInstructorDTO> getAllCourseInstructorByPersonID(int pid) {
        String query = "select * from CourseInstructor where PersonID = ?;";
        ResultSet R = null;
        ArrayList<courseInstructorDTO> lst = new ArrayList<courseInstructorDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, pid);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new courseInstructorDTO(R.getInt(1), R.getInt(2)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteCourseInstructorByCourseID(int cid) {
        String query = "delete from CourseInstructor where CourseID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cid);
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteCourseInstructorByPersonID(int pid) {
        String query = "delete from CourseInstructor where PersonID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, pid);
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateCourse(courseInstructorDTO courseIns) {
        String query = "update CourseInstructor set CourseID = ?, PersonID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, courseIns.getcourseID());
            S.setInt(2, courseIns.getpersonID());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addCourseInstructor(courseInstructorDTO courseIns) {
        String query = "insert into CourseInstructor values(?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, courseIns.getcourseID());
            S.setInt(2, courseIns.getpersonID());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

     
}
