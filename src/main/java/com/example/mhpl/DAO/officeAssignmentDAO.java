package com.example.mhpl.DAO;

import java.sql.*;
import java.util.ArrayList;

public class officeAssignmentDAO {
    private MyDatabaseManager sql = new MyDatabaseManager() ;
    private Connection C;

    public officeAssignmentDAO() {
        this.C = this.sql.connect();
    }

    public ArrayList<officeAssignmentDTO> getAllofficeAssignment() {
        String query = "select * from OfficeAssignment;";
        ResultSet R = null;
        ArrayList<officeAssignmentDTO> lst = new ArrayList<officeAssignmentDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new officeAssignmentDTO(R.getInt(1), R.getString(2), R.getTimestamp(3)));
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<officeAssignmentDTO> getOfficeAssignmentByInstructorID(int iid) {
        String query = "select * from OfficeAssignment where InstructorID = ?;";
        ResultSet R = null;
        ArrayList<officeAssignmentDTO> lst = new ArrayList<officeAssignmentDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, iid);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new officeAssignmentDTO(R.getInt(1), R.getString(2), R.getTimestamp(3)));
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteOfficeAssignment(int iid) {
        String query = "delete from OfficeAssignment where InstructorID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, iid);
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOfficeAssignment(officeAssignmentDTO obm) {
        String query = "update officeAssignment set InstructorID = ?, Location = ?, Timestamp = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, obm.getinstructorID());
            S.setString(2, obm.getlocation());
            S.setTimestamp(3, obm.gettimeStamp());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addOfficeAssignment(officeAssignmentDTO obm) {
        String query = "insert into officeAssignment values(?, ?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, obm.getinstructorID());
            S.setString(2, obm.getlocation());
            S.setTimestamp(3, obm.gettimeStamp());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     
}
