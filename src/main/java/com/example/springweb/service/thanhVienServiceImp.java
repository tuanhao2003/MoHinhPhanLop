package com.example.springweb.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springweb.entity.thanhVien;
import com.example.springweb.repository.thanhVienRepository;

@Service
@SuppressWarnings("rawtypes")
public class thanhVienServiceImp implements thanhVienService {
    @Autowired
    private thanhVienRepository thanhVienRepository;

    @Override
    public ArrayList<thanhVien> getAllMember() {
        try {
            ArrayList<thanhVien> members = new ArrayList<thanhVien>();
            Iterable data = this.thanhVienRepository.findAll();
            for(Iterator iterator = data.iterator();iterator.hasNext();){
                thanhVien mem = (thanhVien) iterator.next();
                members.add(mem);
            }
            return members;
        } catch (Exception e) {
            return null;
        }
    }
    
}
