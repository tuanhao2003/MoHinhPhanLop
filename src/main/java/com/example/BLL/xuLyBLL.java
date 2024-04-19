package com.example.BLL;

import com.example.DAL.thanhVien;
import com.example.DAL.thanhVienDAO;
import com.example.DAL.xuLy;
import com.example.DAL.xuLyDAO;

import java.util.*;
import java.sql.Timestamp;

public class xuLyBLL {
    private xuLyDAO xuLyDAO;
    private thanhVienDAO thanhVienDAO;

    public xuLyBLL() {
        this.xuLyDAO = new xuLyDAO();
        this.thanhVienDAO = new thanhVienDAO();
    }

    public xuLy getPunishment(int ID){
        xuLy punish = null;
        punish = this.xuLyDAO.punishment(ID);
        return punish;
    }

    public List<xuLy> getPunishments(){
        List<xuLy> punishList = null;
        punishList = this.xuLyDAO.listPunishments();
        return punishList;
    }

    public boolean addPunishment(int maxl, int matv, String hinhthucxl, int sotien, Timestamp ngayxl, int trangthaixl) {
            thanhVien thanhvien = thanhVienDAO.member(matv);
            boolean success = this.xuLyDAO.addPunishment(new xuLy(maxl, thanhvien, hinhthucxl, sotien, null, trangthaixl));
            return success;
    }


    public boolean deletePunishment(int ID){
        if(this.xuLyDAO.punishment(ID) != null){
            this.xuLyDAO.delPunishment(this.xuLyDAO.punishment(ID));
            return true;
        }else{
            return false;
        }
    }

    public boolean updatePunishment(int maxl, int matv, String hinhthucxl, int sotien, Timestamp ngayxl, int trangthaixl) {
        if(this.xuLyDAO.punishment(maxl) != null){
            this.xuLyDAO.punishment(maxl).setHinhthucxl(hinhthucxl);
            this.xuLyDAO.punishment(maxl).setThanhvien(thanhVienDAO.member(matv));
            this.xuLyDAO.punishment(maxl).setSotien(sotien);
            this.xuLyDAO.punishment(maxl).setNgayxl(ngayxl);
            this.xuLyDAO.punishment(maxl).setTrangthaixl(trangthaixl);
            this.xuLyDAO.updatePunishment(this.xuLyDAO.punishment(maxl));
            return true;
        }else{
            return false;
        }
    }
}