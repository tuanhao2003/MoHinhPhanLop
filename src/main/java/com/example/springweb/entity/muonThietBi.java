/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
/**
 *
 * @author Hoang
 */
@Entity(name = "muonthietBi")
@Table(name = "muonthietbi")
public class muonThietBi {
    @Id
    @Column(name = "MaTV")
    private int matv;
    
    @Column(name = "MaTB")
    private int matb;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    public muonThietBi(int matv, int matb){
        this.matv = matv;
        this.matb = matb;
    }
    
    public int getMatv(){
        return matv;
    }
    
    public int getMatb(){
        return matb;
    }
    
    public void setMatv(int matv) {
        this.matv = matv;
    }

    public void setMatb(int matb) {
        this.matb = matb;
    }

}
