/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.service;

import com.example.springweb.entity.thietBi;
import com.example.springweb.repository.muonThietBiRepository;
import com.example.springweb.repository.thietBiRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hoang
 */
@Service
public class muonThietBiService {

    @Autowired
    private muonThietBiRepository muonThietBiRepository;
    @Autowired
    private thietBiRepository thietBiRepository;

    public boolean canReserveDevice(thietBi thietBi, LocalDateTime startTime, LocalDateTime endTime) {
        // Implement this method to return a list of reservations
        return muonThietBiRepository.findByDeviceAndStartTimeBeforeAndEndTimeAfter(thietBi, endTime, startTime).isEmpty();
    }

    public Reservation reserveDevice(int matv, int matb, LocalDateTime startTime, LocalDateTime endTime, thietBi thietBi) {
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
        ArrayList<Reservation> expiredReservations = muonThietBiRepository.findByEndTimeBefore(now);
        muonThietBiRepository.deleteAll(expiredReservations);
    }

    public static class Reservation {
        private int matv;
        private int matb;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public void setMatv(int matv) {
            this.matv = matv;
        }

        public void setMatb(int matb) {
            this.matb = matb;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }
    }
}
