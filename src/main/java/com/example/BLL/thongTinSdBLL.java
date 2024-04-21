package com.example.BLL;

import com.example.DAL.thanhVien;
import com.example.DAL.thanhVienDAO;
import com.example.DAL.thietBi;
import com.example.DAL.thietBiDAO;
import com.example.DAL.thongTinSd;
import com.example.DAL.thongTinSdDAO;

import java.util.*;
import java.sql.Timestamp;

public class thongTinSdBLL {
    private thongTinSdDAO thongTinSdDAO;
    private thanhVienDAO thanhVienDAO;
    private thietBiDAO thietBiDAO;

    public thongTinSdBLL() {
        this.thongTinSdDAO = new thongTinSdDAO();
        this.thanhVienDAO = new thanhVienDAO();
        this.thietBiDAO = new thietBiDAO();
    }

    public thongTinSd getUsageInfor(int ID){
        thongTinSd punish = null;
        punish = this.thongTinSdDAO.usageInfor(ID);
        return punish;
    }

    public ArrayList<thongTinSd> getUsageInfors(){
        ArrayList<thongTinSd> punishList = null;
        punishList = this.thongTinSdDAO.listUsageInfors();
        return punishList;
    }

    public boolean addUsageInfor(int matv, int matb, Timestamp tgvao, Timestamp tgmuon, Timestamp tgtra) {
        thanhVien thanhvien = this.thanhVienDAO.member(matv);
        thietBi thietbi = this.thietBiDAO.device(matb);
        boolean success = this.thongTinSdDAO.addUsageInfor(new thongTinSd(0, thanhvien, thietbi, tgvao, tgmuon, tgtra));
        return success;
    }


    public boolean deleteUsageInfor(int ID){
        if(this.thongTinSdDAO.usageInfor(ID) != null){
            this.thongTinSdDAO.delUsageInfor(this.thongTinSdDAO.usageInfor(ID));
            return true;
        }else{
            return false;
        }
    }

    public boolean updateUsageInfor(int matt, int matv, int matb, Timestamp tgvao, Timestamp tgmuon, Timestamp tgtra) {
        if(this.thongTinSdDAO.usageInfor(matt) != null){
            this.thongTinSdDAO.usageInfor(matt).setThanhvien(thanhVienDAO.member(matv));
            this.thongTinSdDAO.usageInfor(matt).setThietbi(thietBiDAO.device(matb));
            this.thongTinSdDAO.usageInfor(matt).setTgvao(tgvao);
            this.thongTinSdDAO.usageInfor(matt).setTgmuon(tgmuon);
            this.thongTinSdDAO.usageInfor(matt).setTgtra(tgtra);
            this.thongTinSdDAO.updateUsageInfor(this.thongTinSdDAO.usageInfor(matt));
            return true;
        }else{
            return false;
        }
    }
}