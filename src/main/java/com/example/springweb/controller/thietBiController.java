/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.controller;

import com.example.springweb.entity.thietBi;
import org.springframework.beans.factory.annotation.Autowired;
import org.spimportringframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
/**
 *
 * @author Hoang
 */
@Controller
@RequestMapping("/device")
@RequiredArgsConstructor

public class thietBiController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public ArrayList<thietBi> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/show-device")
    public ResponseEntity<thietBi> getDeviceById(@PathVariable int id) {
        thietBi thietBi = deviceService.getDeviceById(id);
        return ResponseEntity.ok(thietBi);
    }

    @PostMapping("/add-device")
    public ResponseEntity<thietBi> addDevice(@RequestBody thietBi thietBi) {
        thietBi newthietBi = deviceService.addDevice(thietBi);
        return ResponseEntity.status(HttpStatus.CREATED).body(newthietBi);
    }

    @PutMapping("/update-device")
    public ResponseEntity<thietBi> updateDevice(@PathVariable int id, @RequestBody thietBi motaTB) {
        thietBi updatedDevice = deviceService.updateDevice(id, motaTB);
        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/delete-device")
    public ResponseEntity<Void> deleteDevice(@PathVariable int id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
