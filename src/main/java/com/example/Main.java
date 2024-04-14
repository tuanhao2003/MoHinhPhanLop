package com.example;

import com.example.BLL.thanhVienBLL;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        thanhVienBLL membll = new thanhVienBLL();
        System.out.println(membll.getMembers().size());
       
        Scanner inp = new Scanner(System.in);
        System.out.println("input path:");
        String path=inp.nextLine();
        membll.addMembersViaExcel(path);
        inp.close();
        System.out.println(membll.getMembers().size());
    }

}