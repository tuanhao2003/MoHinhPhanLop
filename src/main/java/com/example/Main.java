package com.example;

import com.example.BLL.thanhVienBLL;
import com.example.BLL.thietBiBLL;
import com.example.DAL.thanhVien;
import com.example.DAL.thietBi;

public class Main {
    public static void main(String[] args) {
        thietBiBLL i = new thietBiBLL();
        thanhVienBLL j = new thanhVienBLL();
        // for(thanhVien a : j.getMembers()){
        //     System.out.println(a.getMatv());
        //     System.out.println(a.getHoten());
        //     System.out.println(a.getNganh());
        //     System.out.println(a.getKhoa());
        //     System.out.println(a.getSdt());
        // }
        for(thietBi a : i.getDevices()){
            System.out.println(a.getMaTB());
            System.out.println(a.getTenTB());
            System.out.println(a.getMoTaTB());
        }
    }

}