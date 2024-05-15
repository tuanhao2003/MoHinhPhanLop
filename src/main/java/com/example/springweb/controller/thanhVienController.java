package com.example.springweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.spimportringframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springweb.entity.thanhVien;
import com.example.springweb.service.thanhVienService;

import java.util.*;

@RestController
@RequestMapping(value="/thanhvien")
public class thanhVienController {
    @Autowired
    private thanhVienService service;

    @GetMapping("/getAll")
    public ArrayList<thanhVien> getAll(){
        ArrayList<thanhVien> members = new ArrayList<thanhVien>();
        this.service.getAllMember();
        return members;
    }
}
