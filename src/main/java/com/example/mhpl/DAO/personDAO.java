package com.example.mhpl.DAO;

import com.example.mhpl.DTO.personDTO;

import java.sql.*;
import java.util.ArrayList;
public class personDAO {
    private MyDatabaseManager sql = new MyDatabaseManager() ;
    private Connection C;

    public personDAO() {
        this.C = this.sql.connect();
    }

    public ArrayList<personDTO> getAllPerson() {
        String query = "select * from Person;";
        ResultSet R = null;
        ArrayList<personDTO> lst = new ArrayList<personDTO>();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            R = S.executeQuery();
            while (R.next()) {
                lst.add(new personDTO(R.getInt(1), R.getString(2), R.getString(3), R.getDate(4), R.getDate(5)));
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public personDTO getPersonByID(int pid) {
        String query = "select * from Person where PersonID = ?;";
        ResultSet R = null;
        personDTO data = new personDTO();
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, pid);
            R = S.executeQuery();
            while (R.next()) {
                data = new personDTO(R.getInt(1), R.getString(2), R.getString(3), R.getDate(4), R.getDate(5));
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deletePerson(int pid) {
        String query = "delete from Person where personID = ?;";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setInt(1, pid);
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePerson(personDTO person) {
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
            e.printStackTrace();
            return false;
        }
    }

    public boolean addPerson(personDTO person) {
        String query = "insert into Person(Lastname, Firstname, HireDate, EnrollmentDate) values(?, ?, ?, ?, ?);";
        try {
            PreparedStatement S = this.C.prepareStatement(query);
            S.setString(1, person.getlastName());
            S.setString(2, person.getfirstName());
            S.setDate(3, person.gethireDate());
            S.setDate(4, person.getenrollmentDate());
            S.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int getLastestPersonID(){
        return getAllPerson().get(getAllPerson().size()-1).getpersonID();
    }
}
