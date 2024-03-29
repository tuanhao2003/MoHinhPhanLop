package com.example;

import com.example.DAL.thanhVien;
import com.example.DAL.thanhVienDAO;

public class Main {
    public static void main(String[] args) {
        thanhVienDAO memdao = new thanhVienDAO();
        for(thanhVien i : memdao.listMember()){
            System.out.println("\nMSSV: "+i.getMatv()+ "\nTen: " +i.getHoten()+"\nKhoa: "+i.getkhoa()+"\nNganh: "+i.getNganh()+"\nSDT: "+i.getSdt());
        }
    }
}