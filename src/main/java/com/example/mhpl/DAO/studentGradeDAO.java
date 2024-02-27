package com.example.mhpl.DAO;

import com.example.mhpl.DTO.studentGradeDTO;
import java.sql.*;
import java.util.ArrayList;
public class studentGradeDAO {
    private MyDatabaseManager sql;
    private Connection C;

    public studentGradeDAO() {
        this.C = sql.connect();
    }

    public ArrayList<studentGradeDTO> getAllStudentGrade() {
        String query = "select * from StudentGrade;";
        ResultSet R = null;
        ArrayList<studentGradeDTO> lst = new ArrayList<studentGradeDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new studentGradeDTO(R.getInt(1), R.getInt(2), R.getInt(3), R.getInt(4)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<studentGradeDTO> getStudentGradeByID(int eid) {
        String query = "select * from StudentGrade where EnrollmentID = ?;";
        ResultSet R = null;
        ArrayList<studentGradeDTO> lst = new ArrayList<studentGradeDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, eid);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new studentGradeDTO(R.getInt(1), R.getInt(2), R.getInt(3), R.getInt(4)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteStudentGrade(int eid) {
        String query = "delete from StudentGrade where EnrollmentID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, eid);
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateStudentGrade(studentGradeDTO studentGrade) {
        String query = "update StudentGrade set EnrollmentID = ?, CourseID = ?, StudentID = ?, Grade = ?";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, studentGrade.getenrollmentID());
            S.setInt(2, studentGrade.getcourseID());
            S.setInt(3, studentGrade.getstudentID());
            S.setInt(4, studentGrade.getgrade());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addstudentGrade(studentGradeDTO studentGrade) {
        String query = "insert into StudentGrade values(?, ?, ?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, studentGrade.getenrollmentID());
            S.setInt(2, studentGrade.getcourseID());
            S.setInt(3, studentGrade.getstudentID());
            S.setInt(4, studentGrade.getgrade());
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
