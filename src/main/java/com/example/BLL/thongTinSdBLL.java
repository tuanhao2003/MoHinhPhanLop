package com.example.BLL;

import com.example.DAL.thanhVien;
import com.example.DAL.thietBi;
import com.example.DAL.thongTinSd;
import com.example.DAL.thongTinSdDAO;

import java.util.*;
import java.sql.Timestamp;
import java.time.Instant;

public class thongTinSdBLL {
    private thongTinSdDAO thongTinSdDAO;

    public thongTinSdBLL() {
        this.thongTinSdDAO = new thongTinSdDAO();
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
        thanhVien thanhvien = new thanhVienBLL().getMember(matv);
        thietBi thietbi = new thietBiBLL().getDevice(matb);
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
        thongTinSd clone = this.thongTinSdDAO.usageInfor(matt);
        if(this.thongTinSdDAO.usageInfor(matt) != null){
            clone.setThanhvien(new thanhVienBLL().getMember(matv));
            clone.setThietbi(new thietBiBLL().getDevice(matb));
            clone.setTgvao(tgvao);
            clone.setTgmuon(tgmuon);
            clone.setTgtra(tgtra);
            this.thongTinSdDAO.updateUsageInfor(clone);
            return true;
        }else{
            return false;
        }
    }
    
    public thongTinSd getToDayCheckIn(int matv){
        Timestamp today = Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atStartOfDay());
        thongTinSd tmp = new thongTinSd();
        tmp.setTgvao(today);
        boolean hasCheckIn = false;
        ArrayList<thongTinSd> allInfor = getUsageInfors();
        ArrayList<thongTinSd> checkInHistory = new ArrayList<thongTinSd>();
        for(thongTinSd i : allInfor){
            if(i.getThanhvien().getMatv() == matv){
                checkInHistory.add(i);
            }
        }
        for(thongTinSd j : checkInHistory){
            if(j.getTgvao().compareTo(tmp.getTgvao()) >= 0){
                tmp = j;
                hasCheckIn = true;
            }
        }
        if(hasCheckIn == false){
            return null;
        }
        return tmp;
    }
}