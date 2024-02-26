package com.example.mhpl.DAO;

import com.example.mhpl.DTO.personDTO;
import java.sql.*;
import java.util.ArrayList;
public class personDAO {
    private MyDatabaseManager sql;
    private Connection C;

    public personDAO() {
        this.C = sql.connect();
    }

    public ArrayList<personDTO> getAllPerson() {
        String query = "select * from Person;";
        ResultSet R = null;
        ArrayList<personDTO> lst = new ArrayList<personDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new personDTO(R.getInt(1), R.getString(2), R.getString(3), R.getDate(4), R.getDate(6)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<personDTO> getpersonByID(int pid) {
        String query = "select * from Person where PersonID = ?;";
        ResultSet R = null;
        ArrayList<personDTO> lst = new ArrayList<personDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, pid);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new personDTO(R.getInt(1), R.getString(2), R.getString(3), R.getDate(4), R.getDate(6)));
            }
            return lst;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteperson(int pid) {
        String query = "delete from Person where personID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, pid);
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateperson(personDTO person) {
        String query = "update Person set PersonID = ?, Lastname = ?, Firstname = ?, HireDate = ?, EnrollmentDate = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, person.getpersonID());
            S.setString(2, person.getlastName());
            S.setString(3, person.getfirstName());
            S.setDate(4, person.gethireDate());
            S.setDate(5, person.getenrollmentDate());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addperson(personDTO person) {
        String query = "insert into person values(?, ?, ?, ?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, person.getpersonID());
            S.setString(2, person.getlastName());
            S.setString(3, person.getfirstName());
            S.setDate(4, person.gethireDate());
            S.setDate(5, person.getenrollmentDate());
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
