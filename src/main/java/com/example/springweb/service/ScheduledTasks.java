/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.service;
import com.example.springweb.service.Impl.muonThietBiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {

    @Autowired
    private muonThietBiServiceImpl reservationService;

    @Scheduled(fixedRate = 3600000) // 1 hour in milliseconds
    public void cleanUpExpiredReservations() {
        reservationService.cleanUpExpiredReservations();
    }
}
