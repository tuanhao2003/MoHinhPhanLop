/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.controller;

import com.example.springweb.service.muonThietBiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.spimportringframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Hoang
 */
@RestController
@RequestMapping("/thietbi-muon")
public class muonThietBiController {
    @Autowired
    private muonThietBiService muonThietBiService;
    
    @PostMapping("/muon")
    public ResponseEntity<String> reserveDevice(@RequestParam int maTV, @RequestParam int maTB, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime){
        try{
            muonThietBiService.reserveDevice(maTV, maTB, startTime, endTime, thietBi);
            return ResponseEntity.ok("Đặt chỗ thành công!");
        } catch (RuntimeExeption e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
