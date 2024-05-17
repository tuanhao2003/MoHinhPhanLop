package com.example.springweb.service.Impl;

import com.example.springweb.entity.thietBi;
import java.time.LocalDateTime;

public interface muonThietBiService {
    boolean canReserveDevice(thietBi thietBi, LocalDateTime startTime, LocalDateTime endTime);
    muonThietBiServiceImpl.Reservation reserveDevice(int matv, int matb, LocalDateTime startTime, LocalDateTime endTime, thietBi thietBi);
    void cleanUpExpiredReservations();
}