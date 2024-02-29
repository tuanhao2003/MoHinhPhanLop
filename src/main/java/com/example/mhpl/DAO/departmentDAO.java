package com.example.mhpl.DAO;

import java.sql.*;
import java.util.ArrayList;

import com.example.mhpl.DTO.departmentDTO;

public class departmentDAO {
    private MyDatabaseManager sql = new MyDatabaseManager() ;
    private Connection C;

    public departmentDAO() {
        this.C = this.sql.connect();
    }

    public ArrayList<departmentDTO> getAllDepartment() {
        String query = "select * from Department;";
        ResultSet R = null;
        ArrayList<departmentDTO> lst = new ArrayList<departmentDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new departmentDTO(R.getInt(1), R.getString(2), R.getDouble(3), R.getDate(4), R.getInt(5)));
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public departmentDTO getDepartmentByID(int did) {
        String query = "select * from Department where DepartmentID = ?;";
        ResultSet R = null;
        departmentDTO data = new departmentDTO();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, did);
            R = S.executeQuery();
            while (R.next()) {
                data = new departmentDTO(R.getInt(1), R.getString(2), R.getDouble(3), R.getDate(4), R.getInt(5));
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteDepartment(int did) {
        String query = "delete from Department where DepartmentID = ?;";
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

    public boolean updateDepartment(departmentDTO dpm) {
        String query = "update Department set DepartmentID = ?, Name = ?, Budget = ?, StartDate = ?, Administrator = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, dpm.getdepartmentID());
            S.setString(2, dpm.getname());
            S.setDouble(3, dpm.getbudget());
            S.setDate(4, dpm.getstartDate());
            S.setInt(5, dpm.getadministrator());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addDepartment(departmentDTO dpm) {
        String query = "insert into Department(Name, Budget, StartDate, Administrator) values(?, ?, ?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setString(1, dpm.getname());
            S.setDouble(2, dpm.getbudget());
            S.setDate(3, dpm.getstartDate());
            S.setInt(4, dpm.getadministrator());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     
}
