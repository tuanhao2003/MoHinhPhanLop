package com.example.BLL;

import com.example.DAL.thanhVien;
import com.example.DAL.thanhVienDAO;
import com.example.DAL.thietBi;
import com.example.DAL.thongTinSd;
import com.example.DAL.xuLy;

import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.CellType;
import java.sql.Timestamp;
import java.time.Instant;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class thanhVienBLL {

    private thanhVienDAO thanhVienDAO;

    public thanhVienBLL() {
        this.thanhVienDAO = new thanhVienDAO();
    }

    public thanhVien getMember(int ID) {
        thanhVien mem = null;
        mem = this.thanhVienDAO.member(ID);
        return mem;
    }

    public ArrayList<thanhVien> getMembers() {
        ArrayList<thanhVien> memList = null;
        memList = this.thanhVienDAO.listMembers();
        return memList;
    }

    public boolean addMember(int ID, String fullname, String major, String subMajor, String phone) {
        boolean success = this.thanhVienDAO.addMember(new thanhVien(ID, fullname, major, subMajor, phone));
        return success;
    }

    public boolean addMember(int ID, String fullname, String major, String subMajor, String phone, String email, String password) {
        boolean checkAvailable = false;
        for(thanhVien mem : getMembers()){
            if(mem.getMatv() == ID){
                checkAvailable = true;
                break;
            }
        }
        if(checkAvailable == false){
            thanhVien newMem = new thanhVien(ID, fullname, major, subMajor, phone);
            newMem.setEmail(email);
            newMem.setPassword(password);
            this.thanhVienDAO.addMember(newMem);
            return true;
        }else{
            return false;
        }
    }

    public boolean addMembersViaExcel(String filePath) {
        try {
            FileInputStream F = new FileInputStream(filePath);
            Workbook WB = WorkbookFactory.create(F);
            Sheet S = WB.getSheetAt(0);

            for (Row R : S) {
                if (R != S.getRow(0)) {
                    if (R.getCell(0) == null || R.getCell(0).getCellType() == CellType.BLANK) {
                        break;
                    } else {
                        int ID = (int) R.getCell(0).getNumericCellValue();
                        boolean checkAvailable = false;
                        for(thanhVien mem : getMembers()){
                            if(mem.getMatv() == ID){
                                checkAvailable = true;
                                break;
                            }
                        }
                        if(checkAvailable == false){
                            String fullName = R.getCell(1).getStringCellValue();
                            String major = R.getCell(2).getStringCellValue();
                            String subMajor = R.getCell(3).getStringCellValue();
                            String phone = R.getCell(4).getStringCellValue();
                            thanhVien newMem = new thanhVien(ID, fullName, major, subMajor, phone);
                            newMem.setPassword(Double.toString(R.getCell(5).getNumericCellValue()));
                            newMem.setEmail(R.getCell(6).getStringCellValue());
                            this.thanhVienDAO.addMember(newMem);
                        }
                    }
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

    public boolean deleteMember(int ID) {
        if (this.thanhVienDAO.member(ID) != null) {
            for(xuLy xl : new xuLyBLL().getPunishments()){
                if(xl.getThanhvien().getMatv() == ID){
                    new xuLyBLL().deletePunishment(xl.getMaxl());
                }
            }
            
            for(thongTinSd ttsd : new thongTinSdBLL().getUsageInfors()){
                if(ttsd.getThanhvien().getMatv() == ID){
                    new thongTinSdBLL().deleteUsageInfor(ttsd.getMatt());
                }
            }
            
            this.thanhVienDAO.delMember(this.thanhVienDAO.member(ID));
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteMembers(int courseNum) {
        List<thanhVien> delList = new ArrayList<thanhVien>();
        for (thanhVien i : getMembers()) {
            String[] listChar = Integer.toString(i.getMatv()).split("");
            int memCourse = Integer.parseInt(listChar[2] + listChar[3]);
            if (memCourse == courseNum) {
                delList.add(i);
            }
        }

        if (delList.size() != 0) {
            for (thanhVien i : delList) {
                deleteMember(i.getMatv());
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean updateMember(int ID, String fullname, String major, String subMajor, String phone) {
        thanhVien updated = this.thanhVienDAO.member(ID);
        if (updated != null) {
            updated.setHoten(fullname);
            updated.setKhoa(major);
            updated.setNganh(subMajor);
            updated.setSdt(phone);
            this.thanhVienDAO.updateMember(updated);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateMember(int ID, String fullname, String major, String subMajor, String phone, String email, String password) {
        thanhVien updated = this.thanhVienDAO.member(ID);
        if (this.thanhVienDAO.member(ID) != null) {
            updated.setHoten(fullname);
            updated.setKhoa(major);
            updated.setNganh(subMajor);
            updated.setSdt(phone);
            updated.setEmail(email);
            updated.setPassword(password);
            this.thanhVienDAO.updateMember(updated);
            return true;
        } else {
            return false;
        }
    }

    public int checkOffender(int matv){
        ArrayList<xuLy> offenedHistory = new ArrayList<xuLy>();
        for (xuLy i : new xuLyBLL().getPunishments()) {
            if (i.getThanhvien().getMatv() == matv) {
                offenedHistory.add(i);
            }
        }
        if(!offenedHistory.isEmpty()){
            int maxLevel = 0;
            for(xuLy j : offenedHistory){
                if(j.getTrangthaixl() > maxLevel){
                    maxLevel = j.getTrangthaixl();
                }
                switch (maxLevel) {
                    case 0:
                        return -1;
                    case 1:
                        return 0;
                    case 2:
                        return 1;
                    default:
                        return -1;
                }
            }
        }
        return -1;
    }
    
    public int checkIn(int matv){
        if(checkOffender(matv) == -1 ||checkOffender(matv) == 0){
            new thongTinSdBLL().addUsageInfor(matv, 0, Timestamp.from(Instant.now()), null, null);
        }
        return checkOffender(matv);
    }
    
    public ArrayList<thietBi> getDevicesBorrowing(int matv){
        ArrayList<thietBi> borrowing = new ArrayList<thietBi>();
        ArrayList<Integer> lenDevicesId = new ArrayList<Integer>();
        new thongTinSdBLL().getUsageInfors().forEach(infor -> {
            if (infor.getTgtra() == null) {
                if (infor.getThietbi() != null && infor.getThanhvien().getMatv() == matv) {
                    if(!lenDevicesId.contains(infor.getThietbi().getMatb())){
                        lenDevicesId.add(infor.getThietbi().getMatb());
                    }
                }
            }
        });
        new thietBiBLL().getDevices().forEach(devi -> {
            if (lenDevicesId.contains(devi.getMatb())) {
                borrowing.add(devi);
            }
        });
        return borrowing;
    }

    public ArrayList<thietBi> getDevicesForLen() {
        ArrayList<thietBi> lenableDevices = new ArrayList<thietBi>();
        ArrayList<Integer> lenDevicesId = new ArrayList<Integer>();
        new thongTinSdBLL().getUsageInfors().forEach(infor -> {
            if (infor.getTgtra() == null) {
                if (infor.getThietbi() != null) {
                    if(!lenDevicesId.contains(infor.getThietbi().getMatb())){
                        lenDevicesId.add(infor.getThietbi().getMatb());
                    }
                }
            }
        });
        new thietBiBLL().getDevices().forEach(devi -> {
            if (!lenDevicesId.contains(devi.getMatb())) {
                lenableDevices.add(devi);
            }
        });
        return lenableDevices;
    }

    public boolean lenDevice(int matv, int matb) {
        thongTinSd tmp = new thongTinSdBLL().getToDayCheckIn(matv);
        if (tmp != null) {
            if(tmp.getThietbi() == null){
                new thongTinSdBLL().updateUsageInfor(tmp.getMatt(), matv, matb, tmp.getTgvao(), Timestamp.from(Instant.now()), null);
            }else {
                new thongTinSdBLL().addUsageInfor(matv, matb, tmp.getTgvao(), Timestamp.from(Instant.now()), null);
            }
            return true;
        }
        return false;
    }
    
    public boolean backDevice(int matv, int matb) {
        new thongTinSdBLL().getUsageInfors().forEach(infor -> {
            if (infor.getTgtra() == null) {
                if (infor.getThietbi() != null && infor.getThanhvien().getMatv() == matv) {
                    if(infor.getThietbi().getMatb() == matb){
                        new thongTinSdBLL().updateUsageInfor(infor.getMatt(), matv, matb, infor.getTgvao(), infor.getTgmuon(), Timestamp.from(Instant.now()));
                    }
                }
            }
        });
        return false;
    }
    public boolean isCheckInToday(int matv){
        if(new thongTinSdBLL().getToDayCheckIn(matv) != null){
            return true;
        }else{
            return false;
        }
    }
}
