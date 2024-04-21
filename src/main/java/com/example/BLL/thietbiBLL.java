package com.example.BLL;

import com.example.DAL.thietBi;
import com.example.DAL.thietBiDAO;

import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class thietBiBLL {
    private thietBiDAO thietBiDAO;

    public thietBiBLL() {
        this.thietBiDAO = new thietBiDAO();
    }

    public thietBi getDevice(int ID){
        thietBi devi = null;
        devi = this.thietBiDAO.device(ID);
        return devi;
    }

    public ArrayList<thietBi> getDevices(){
        ArrayList<thietBi> deviList = null;
        deviList = this.thietBiDAO.listDevices();
        return deviList;
    }

    public boolean addDevice(int ID, String name, String description) {
            boolean success = this.thietBiDAO.addDevice(new thietBi(ID, name, description));
            return success;
    }

    public boolean addDevicesViaExcel(String filePath) {
        try {
            FileInputStream F = new FileInputStream(filePath);
            Workbook WB = WorkbookFactory.create(F);
            Sheet S = WB.getSheetAt(1);

            for (Row R : S) {
                
                if(R != S.getRow(0)){
                    if(R.getCell(0)==null || R.getCell(0).getCellType() == CellType.BLANK){
                        break;
                    }else{
                        int ID = (int) R.getCell(0).getNumericCellValue();
                        String name = R.getCell(1).getStringCellValue();
                        String description = R.getCell(2).getStringCellValue();
                        
                        this.thietBiDAO.addDevice(new thietBi(ID, name, description));
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


    public boolean deleteDevice(int ID){
        if(this.thietBiDAO.device(ID) != null){
            this.thietBiDAO.delDevice(this.thietBiDAO.device(ID));
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteDevices(int requiredCode){
        List<thietBi> delList = new ArrayList<thietBi>(); 
        List<thietBi> allMem = new ArrayList<thietBi>();
        allMem = this.thietBiDAO.listDevices();
        for(thietBi i : allMem){
            String[] listChar = Integer.toString(i.getMatb()).split(null);
            int deviCourse = Integer.parseInt(listChar[2]+listChar[3]);
            if(deviCourse == requiredCode){
                delList.add(i);
            }
        }

        if(delList.size() != 0){
            for(thietBi i : delList){
                this.thietBiDAO.delDevice(i);
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean updateDevice(int ID, String name, String description) {
        if(this.thietBiDAO.device(ID) != null){
            this.thietBiDAO.device(ID).setTentb(name);
            this.thietBiDAO.device(ID).setMotatb(description);
            this.thietBiDAO.updateDevice(this.thietBiDAO.device(ID));
            return true;
        }else{
            return false;
        }
    }
}