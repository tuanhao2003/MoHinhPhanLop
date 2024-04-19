package com.example.BLL;

import com.example.DAL.thanhVien;
import com.example.DAL.thanhVienDAO;

import java.io.FileInputStream;
import java.util.*;
// import java.sql.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class thanhVienBLL {
    private thanhVienDAO thanhVienDAO;
    // private xuLyDAO xuLyDAO;
    public thanhVienBLL() {
        this.thanhVienDAO = new thanhVienDAO();
    }

    public thanhVien getMember(int ID){
        thanhVien mem = null;
        mem = this.thanhVienDAO.member(ID);
        return mem;
    }

    public List<thanhVien> getMembers(){
        List<thanhVien> memList = null;
        memList = this.thanhVienDAO.listMembers();
        return memList;
    }

    public boolean addMember(int ID, String fullname, String major, String subMajor, String phone) {
            boolean success = this.thanhVienDAO.addMember(new thanhVien(ID, fullname, major, subMajor, phone));
            return success;
    }

    public boolean addMembersViaExcel(String filePath) {
        try {
            FileInputStream F = new FileInputStream(filePath);
            Workbook WB = WorkbookFactory.create(F);
            Sheet S = WB.getSheetAt(0);

            for (Row R : S) {
                if(R != S.getRow(0)){

                    int ID = (int) R.getCell(0).getNumericCellValue();
                    String fullName = R.getCell(1).getStringCellValue();
                    String major = R.getCell(2).getStringCellValue();
                    String subMajor = R.getCell(3).getStringCellValue();
                    String phone = R.getCell(4).getStringCellValue();
                    thanhVien newMem = new thanhVien(ID, fullName, major, subMajor, phone);
                    newMem.setPassword(R.getCell(5).getStringCellValue());
                    newMem.setEmail(R.getCell(6).getStringCellValue());
                    this.thanhVienDAO.addMember(newMem);
                }
            }
            WB.close();
            F.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteMember(int ID){
        if(this.thanhVienDAO.member(ID) != null){
            this.thanhVienDAO.delMember(this.thanhVienDAO.member(ID));
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteMembers(int courseNum){
        List<thanhVien> delList = new ArrayList<thanhVien>(); 
        List<thanhVien> allMem = new ArrayList<thanhVien>();
        allMem = this.thanhVienDAO.listMembers();
        for(thanhVien i : allMem){
            String[] listChar = Integer.toString(i.getMatv()).split(null);
            int memCourse = Integer.parseInt(listChar[2]+listChar[3]);
            if(memCourse == courseNum){
                delList.add(i);
            }
        }

        if(delList.size() != 0){
            for(thanhVien i : delList){
                this.thanhVienDAO.delMember(i);
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean updateMember(int ID, String fullname, String major, String subMajor, String phone) {
        if(this.thanhVienDAO.member(ID) != null){
            this.thanhVienDAO.member(ID).setHoten(fullname);
            this.thanhVienDAO.member(ID).setKhoa(major);
            this.thanhVienDAO.member(ID).setNganh(subMajor);
            this.thanhVienDAO.member(ID).setSdt(phone);
            this.thanhVienDAO.updateMember(this.thanhVienDAO.member(ID));
            return true;
        }else{
            return false;
        }
    }

    // public boolean Punishment(int matv){
    //     boolean viPham = false;
    //     int mucDoViPham = 0;
    //     List<xuLy> allXuLy = this.xyLyDAO.listXyLy();
    //     for(xuLy i : allXuLy){
    //         if(i.getMaTv() == matv){
    //             viPham = true;
    //             mucDoViPham < i.getTrangThaiXL() ? mucDoViPham = i.getTrangThaiXL() : mucDoViPham = mucDoViPham;
    //         }
    //     }

    //     if(viPham){
    //         switch (mucDoViPham) {
    //             case 0:
    //                 // canh bao
    //                 break;
    //             case 1:
    //                 // canh bao
    //                 break;
    //             case 2:
    //                 // ban
    //                 break;
    //         }
    //         return false;
    //     }else{
    //         return true;
    //     }
    // }

}