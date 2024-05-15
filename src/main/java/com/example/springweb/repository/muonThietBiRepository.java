/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springweb.entity.muonThietBi;
import com.example.springweb.service.muonThietBiService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Hoang
 */
@Repository
public interface muonThietBiRepository extends CrudRepository<muonThietBi, Long> {
    List<muonThietBi> findByDeviceAndEndTimeAfter(int matb, LocalDateTime time);
    Optional<muonThietBi> findByDeviceAndStartTimeBeforeAndEndTimeAfter(int matb, LocalDateTime start, LocalDateTime end);

    public muonThietBiService.Reservation save(muonThietBiService.Reservation reservation);
}
  
