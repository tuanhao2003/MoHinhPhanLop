/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.repository;

import com.example.springweb.entity.thietBi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springweb.entity.muonThietBi;
import com.example.springweb.service.Impl.muonThietBiServiceImpl;
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

    ArrayList<muonThietBiServiceImpl.Reservation> findByDeviceAndStartTimeBeforeAndEndTimeAfter(thietBi device, LocalDateTime endTime, LocalDateTime startTime);
    ArrayList<muonThietBiServiceImpl.Reservation> findByEndTimeBefore(LocalDateTime endTime);
    public muonThietBiServiceImpl.Reservation save(muonThietBiServiceImpl.Reservation reservation);

    void deleteAll(ArrayList<muonThietBiServiceImpl.Reservation> expiredReservations);
}
  
