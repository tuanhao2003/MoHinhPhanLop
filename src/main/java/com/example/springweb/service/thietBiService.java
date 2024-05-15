/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.service;

import com.example.springweb.entity.thietBi;
import com.example.springweb.repository.thietBiRepository;
import java.awt.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hoang
 */
@Service
public class thietBiService {
    @Autowired
    private thietBiRepository deviceRepository;

    public List<thietBi> getAllthietBi() {
        return thietBiRepository.findAll();
    }

    public thietBi getthietBiById(int matb) {
        return deviceRepository.findById(matb).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    }

    public thietBi addDevice(thietBi thietBi) {
        return deviceRepository.save(thietBi);
    }

    public thietBi updateDevice(int matb, String tentb, String motatb) {
        thietBi thietBi = getthietBiById(matb);
        thietBi.setMatb(thietBi.getMatb());
        thietBi.setTentb(thietBi.getTentb());
        thietBi.setMotatb(thietBi.getMotatb());
        
        return thietBiRepository.save(thietBi);
    }

    public void deleteDevice(Long id) {
        thietBi thietBi = getthietBiById(matb);
        thietBiRepository.delete(thietBi);
    }
}

