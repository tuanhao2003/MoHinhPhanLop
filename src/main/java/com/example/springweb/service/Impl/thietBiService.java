package com.example.springweb.service.Impl;

import com.example.springweb.entity.thietBi;
import java.util.List;

public interface thietBiService {
    List<thietBi> getAllthietBi();
    thietBi getthietBiById(int matb);
    thietBi addDevice(thietBi thietBi);
    thietBi updateDevice(int matb, String tentb, String motatb);
    void deleteDevice(int matb);
}