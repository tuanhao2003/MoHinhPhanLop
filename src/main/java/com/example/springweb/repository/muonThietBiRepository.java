/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.repository;

import com.example.springweb.entity.thietBi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springweb.entity.muonThietBi;
import com.example.springweb.service.muonThietBiService;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Hoang
 */
@Repository
public interface muonThietBiRepository extends CrudRepository<muonThietBi, Long> {
    //List<muonThietBi> findByDeviceAndEndTimeAfter(int matb, LocalDateTime time);
    //Optional<muonThietBi> findByDeviceAndStartTimeBeforeAndEndTimeAfter(int matb, LocalDateTime start, LocalDateTime end);

    ArrayList<muonThietBiService.Reservation> findByDeviceAndStartTimeBeforeAndEndTimeAfter(thietBi device, LocalDateTime endTime, LocalDateTime startTime);
    ArrayList<muonThietBiService.Reservation> findByEndTimeBefore(LocalDateTime endTime);
    public muonThietBiService.Reservation save(muonThietBiService.Reservation reservation);

    void deleteAll(ArrayList<muonThietBiService.Reservation> expiredReservations);
}
  
