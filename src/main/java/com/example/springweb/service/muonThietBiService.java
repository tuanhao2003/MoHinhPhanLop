/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.service;

import com.example.springweb.entity.muonThietBi;
import com.example.springweb.entity.thanhVien;
import com.example.springweb.entity.thietBi;
import com.example.springweb.entity.muonThietBi;
import com.example.springweb.repository.muonThietBiRepository;
import com.example.springweb.repository.thietBiRepository;
import java.awt.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hoang
 */
@Service
public class muonThietBiService {

    private static Object findByDeviceAndStartTimeBeforeAndEndTimeAfter(thietBi matb, LocalDateTime endTime, LocalDateTime startTime) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Autowired
    private muonThietBiRepository muonThietBiRepository;
    @Autowired
    private thietBiRepository thietBiRepository;
    
    public boolean canReserveDevice(thietBi thietBi, LocalDateTime startTime, LocalDateTime endTime) {
        return muonThietBiService.findByDeviceAndStartTimeBeforeAndEndTimeAfter(thietBi, endTime, startTime).isEmpty();
    }
    
    public Reservation reserveDevice(int thanhVien, int thietBi, LocalDateTime startTime, LocalDateTime endTime) {
        if (!canReserveDevice(thietBi, startTime, endTime)) {
            throw new RuntimeException("Device is not available for the selected time period.");
        }
        Reservation reservation = new Reservation();
        reservation.setMatv(matv);
        reservation.setMatb(matb);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        return muonThietBiRepository.save(reservation);
    }

    public void cleanUpExpiredReservations() {
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> expiredReservations = reservationRepository.findByEndTimeBefore(now);
        reservationRepository.deleteAll(expiredReservations);
    }

    private static class Reservation {

        public Reservation() {
        }

        private void setStartTime(LocalDateTime startTime) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setEndTime(LocalDateTime endTime) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    public void cleanUpExpiredReservations() {
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> expiredReservations = muonThietBiRepository.findByEndTimeBefore(now);
        muonThietBiRepository.deleteAll(expiredReservations);
    }
}
