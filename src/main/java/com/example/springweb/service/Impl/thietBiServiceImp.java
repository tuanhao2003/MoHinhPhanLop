package com.example.springweb.service.Impl;

import com.example.springweb.entity.thietBi;
import com.example.springweb.repository.thietBiRepository;
import com.example.springweb.service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class thietBiServiceImp implements thietBiService{
    @Autowired
    private thietBiRepository deviceRepository;

    public ArrayList<thietBi> getAllthietBi() {
        return thietBiRepository.findAll();
    }

    public thietBi getthietBiById(int matb) {
        return thietBiRepository.findById(matb).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    }

    public thietBi addDevice(thietBi thietBi) {
        return thietBiRepository.save(thietBi);
    }

    public thietBi updateDevice(int matb, String tentb, String motatb) {
        thietBi thietBi = getthietBiById(matb);
        thietBi.setMatb(matb);
        thietBi.setTentb(tentb);
        thietBi.setMotatb(motatb);

        return thietBiRepository.save(thietBi);
    }

    public void deleteDevice(int matb) {
        thietBi thietBi = getthietBiById(matb);
        thietBiRepository.delete(thietBi);
    }
}