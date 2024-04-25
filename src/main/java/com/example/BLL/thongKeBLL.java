package com.example.BLL;

import com.example.DAL.thanhVien;
import com.example.DAL.thietBi;
import com.example.DAL.thongTinSd;
import com.example.DAL.xuLy;
import java.sql.Timestamp;
import java.util.*;

public class thongKeBLL {
    public thongKeBLL(){}
    
    private ArrayList<thongTinSd> getAllTTSD(){
        return new thongTinSdBLL().getUsageInfors();
    }
    
    private ArrayList<xuLy> getAllXuLy(){
        return new xuLyBLL().getPunishments();
    }
    
    public ArrayList<thongTinSd> getInforByTime(Timestamp start, Timestamp end){
        ArrayList<thongTinSd> filtered = new ArrayList<thongTinSd>();
        for(thongTinSd i : getAllTTSD()){
            if(i.getTgvao().compareTo(start)>0 && i.getTgvao().compareTo(end)<0){
                filtered.add(i);
            }
        }
        for(int i =0; i< filtered.size()-1; i++){
            for(int j = i+1; j<filtered.size(); j++){
                if(filtered.get(i).getTgvao().compareTo(filtered.get(i).getTgvao()) > 0){
                    thongTinSd tmp = filtered.get(i);
                    filtered.set(i, filtered.get(j));
                    filtered.set(j, tmp);
                }
            }
        }
        return filtered;
    }
    
    public ArrayList<thongTinSd> getInforByMemberMajor(String major){
        ArrayList<thongTinSd> filtered = new ArrayList<thongTinSd>();
        for(thongTinSd i : getAllTTSD()){
            if(i.getThanhvien().getKhoa().contains(major)){
                filtered.add(i);
            }
        }
        for(int i =0; i< filtered.size()-1; i++){
            for(int j = i+1; j<filtered.size(); j++){
                if(filtered.get(i).getTgvao().compareTo(filtered.get(i).getTgvao()) > 0){
                    thongTinSd tmp = filtered.get(i);
                    filtered.set(i, filtered.get(j));
                    filtered.set(j, tmp);
                }
            }
        }
        return filtered;
    }
    public ArrayList<thongTinSd> getInforByMemberSubMajor(String submajor){
        ArrayList<thongTinSd> filtered = new ArrayList<thongTinSd>();
        for(thongTinSd i : getAllTTSD()){
            if(i.getThanhvien().getNganh().contains(submajor)){
                filtered.add(i);
            }
        }
        for(int i =0; i< filtered.size()-1; i++){
            for(int j = i+1; j<filtered.size(); j++){
                if(filtered.get(i).getTgvao().compareTo(filtered.get(i).getTgvao()) > 0){
                    thongTinSd tmp = filtered.get(i);
                    filtered.set(i, filtered.get(j));
                    filtered.set(j, tmp);
                }
            }
        }
        return filtered;
    }
    
    public ArrayList<thongTinSd> getInforByDeviceName(String name){
        ArrayList<thongTinSd> filtered = new ArrayList<thongTinSd>();
        for(thongTinSd i : getAllTTSD()){
            if(i.getThietbi().getTentb().contains(name)){
                filtered.add(i);
            }
        }
        for(int i =0; i< filtered.size()-1; i++){
            for(int j = i+1; j<filtered.size(); j++){
                if(filtered.get(i).getTgmuon().compareTo(filtered.get(i).getTgmuon()) > 0){
                    thongTinSd tmp = filtered.get(i);
                    filtered.set(i, filtered.get(j));
                    filtered.set(j, tmp);
                }
            }
        }
        return filtered;
    }
    
    public ArrayList<thongTinSd> getInforByLendTime(Timestamp start, Timestamp end){
        ArrayList<thongTinSd> filtered = new ArrayList<thongTinSd>();
        for(thongTinSd i : getAllTTSD()){
            if(i.getTgmuon() != null){
                if(i.getTgmuon().compareTo(start)>0 && i.getTgmuon().compareTo(end)<0){
                    filtered.add(i);
                }
            }
        }
        for(int i =0; i< filtered.size()-1; i++){
            for(int j = i+1; j<filtered.size(); j++){
                if(filtered.get(i).getTgmuon().compareTo(filtered.get(i).getTgmuon()) > 0){
                    thongTinSd tmp = filtered.get(i);
                    filtered.set(i, filtered.get(j));
                    filtered.set(j, tmp);
                }
            }
        }
        return filtered;
    }
    
    public ArrayList<thongTinSd> getInforByDeviceLending(){
        ArrayList<thongTinSd> filtered = new ArrayList<thongTinSd>();
        for(thongTinSd i : getAllTTSD()){
            if(i.getTgtra()==null){
                filtered.add(i);
            }
        }
        for(int i =0; i< filtered.size()-1; i++){
            for(int j = i+1; j<filtered.size(); j++){
                if(filtered.get(i).getTgmuon().compareTo(filtered.get(i).getTgmuon()) > 0){
                    thongTinSd tmp = filtered.get(i);
                    filtered.set(i, filtered.get(j));
                    filtered.set(j, tmp);
                }
            }
        }
        return filtered;
    }
    
    public ArrayList<xuLy> getProcessingPunishment(){
        ArrayList<xuLy> processing = new ArrayList<xuLy>();
        for(xuLy i : getAllXuLy()){
            if(i.getTrangthaixl() != 0){
                processing.add(i);
            }
        }
        return processing;
    }
    
    public ArrayList<xuLy> getProcessedPunishment(){
        ArrayList<xuLy> processing = new ArrayList<xuLy>();
        for(xuLy i : getAllXuLy()){
            if(i.getTrangthaixl() == 0){
                processing.add(i);
            }
        }
        return processing;
    }
    
    public xuLy getPunishment(int maxl){
        return new xuLyBLL().getPunishment(maxl);
    }
    
    public thanhVien getMember(int matv){
        return new thanhVienBLL().getMember(matv);
    }
    
    public thietBi getDevice(int matb){
        return new thietBiBLL().getDevice(matb);
    }
}
