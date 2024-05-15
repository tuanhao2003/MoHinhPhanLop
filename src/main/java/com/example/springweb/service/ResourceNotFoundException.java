/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.service;

/**
 *
 * @author Hoang
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String device_not_found) {
        super(device_not_found);
    }
    
}
