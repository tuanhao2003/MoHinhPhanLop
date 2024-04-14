package com.example.DAL;

import java.sql.*;
import java.util.ArrayList;

public class thietbiDAO {
    //private final MyDatabaseManager sql;
    private MyDatabaseManager sql = new MyDatabaseManager();
    private Connection conn;
    public thietbiDAO() {
        this.conn = this.sql.connect();
    }

    public ArrayList<thietBi> getThietbis() {
        ArrayList<thietBi> thietbis = new ArrayList<>();
        String sql = "SELECT * FROM thietbi";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                thietBi thietbi = new thietBi(rs.getInt("MaTB"), rs.getString("TenTB"), rs.getString("MoTa"));
                thietbis.add(thietbi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thietbis;
    }

    public void addThietbi(thietBi thietbi) {
        String sql = "INSERT INTO thietbi(MaTB, TenTB, MoTa) VALUES(?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, thietbi.getMaTB());
            pstmt.setString(2, thietbi.getTenTB());
            pstmt.setString(3, thietbi.getMoTaTB());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateThietbi(thietBi thietbi) {
        String sql = "UPDATE thietbi SET MaTB = ?, TenTB = ?, MoTa = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thietbi.getTenTB());
            pstmt.setString(2, thietbi.getMoTaTB());
            pstmt.setInt(3, thietbi.getMaTB());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteThietbi(int idTB) {
        String sql = "DELETE FROM thietbi WHERE MaTB = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idTB);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
}
    }
    public thietBi getThietbiById(int idTB) {
        String sql = "SELECT * FROM thietbi WHERE MaTB = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idTB);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                thietBi thietbi = new thietBi(rs.getInt("MaTB"), rs.getString("TenTB"), rs.getString("MoTa"));
                return thietbi;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }