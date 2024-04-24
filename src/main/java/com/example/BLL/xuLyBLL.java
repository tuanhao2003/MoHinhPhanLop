package com.example.BLL;

import com.example.DAL.thanhVien;
import com.example.DAL.xuLy;
import com.example.DAL.xuLyDAO;

import java.util.*;
import java.sql.Timestamp;

public class xuLyBLL {
    private xuLyDAO xuLyDAO;

    public xuLyBLL() {
        this.xuLyDAO = new xuLyDAO();
    }

    public xuLy getPunishment(int ID){
        xuLy punish = null;
        punish = this.xuLyDAO.punishment(ID);
        return punish;
    }

    public ArrayList<xuLy> getPunishments(){
        ArrayList<xuLy> punishList = null;
        punishList = this.xuLyDAO.listPunishments();
        return punishList;
    }

    public boolean addPunishment(int matv, String hinhthucxl, int sotien, Timestamp ngayxl, int trangthaixl) {
        thanhVien thanhvien = new thanhVienBLL().getMember(matv);
        boolean success = this.xuLyDAO.addPunishment(new xuLy(0, thanhvien, hinhthucxl, sotien, null, trangthaixl));
        return success;
    }
    
    public ArrayList<thanhVien> getAllMembers(){
        return new thanhVienBLL().getMembers();
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
        xuLy clone = this.xuLyDAO.punishment(maxl);
        if(clone != null){
            clone.setHinhthucxl(hinhthucxl);
            clone.setThanhvien(new thanhVienBLL().getMember(matv));
            clone.setSotien(sotien);
            clone.setNgayxl(ngayxl);
            clone.setTrangthaixl(trangthaixl);
            this.xuLyDAO.updatePunishment(clone);
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList<thanhVien> findOffender(String name){
        ArrayList<thanhVien> listOffenders = new ArrayList<thanhVien>();
        new thanhVienBLL().getMembers().forEach(mem -> {
            if(mem.getHoten().equals(name)){
                listOffenders.add(mem);
            }
        });
        
        return listOffenders;
    }
    
    public xuLy getMostSeariousOffened(int matv){
        xuLy tmp = new xuLy();
        tmp.setTrangthaixl(0);
        for(xuLy i : getPunishments()){
            if(i.getTrangthaixl() > tmp.getTrangthaixl()){
                tmp = i;
            }
        }
        
        return tmp;
    }
}