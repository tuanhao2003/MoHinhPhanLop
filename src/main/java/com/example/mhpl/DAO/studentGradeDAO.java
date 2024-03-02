package com.example.mhpl.DAO;

import com.example.mhpl.DTO.studentGradeDTO;
import java.sql.*;
import java.util.ArrayList;
public class studentGradeDAO {
    private MyDatabaseManager sql = new MyDatabaseManager() ;
    private Connection C;

    public studentGradeDAO() {
        this.C = this.sql.connect();
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
            e.printStackTrace();
            return null;
        }
    }

    public studentGradeDTO getStudentGradeByID(int eid) {
        String query = "select * from StudentGrade where EnrollmentID = ?;";
        ResultSet R = null;
        studentGradeDTO data = new studentGradeDTO();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, eid);
            R = S.executeQuery();
            while (R.next()) {
                data =  new studentGradeDTO(R.getInt(1), R.getInt(2), R.getInt(3), R.getInt(4));
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<studentGradeDTO> getStudentGradeByCourseID(int cid) {
        String query = "select * from StudentGrade where CourseID = ?;";
        ResultSet R = null;
        ArrayList<studentGradeDTO> lst = new ArrayList<studentGradeDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, cid);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new studentGradeDTO(R.getInt(1), R.getInt(2), R.getInt(3), R.getInt(4)));
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
    }

     
}
