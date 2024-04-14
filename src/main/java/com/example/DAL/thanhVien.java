package com.example.DAL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="thanhvien")
public class thanhVien {
    @Id
    @Column(name="MaTV")
    private int matv;
    @Column(name="HoTen")
    private String hoten;
    @Column(name="Khoa")
    private String khoa;
    @Column(name="Nganh")
    private String nganh;
    @Column(name="SDT")
    private String sdt;

    public thanhVien() {
        this.matv = 0;
        this.hoten = null;
        this.khoa = null;
        this.nganh = null;
        this.sdt = null;
    }

    public thanhVien(int matv, String hoten, String khoa, String nganh, String sdt) {
        this.matv = matv;
        this.hoten = hoten;
        this.khoa = khoa;
        this.nganh = nganh;
        this.sdt = sdt;
    }

    public void setMatv(int matv){
        this.matv = matv;
    }
    public void setHoten(String hoten){
        this.hoten = hoten;
    }
    public void setkhoa(String khoa){
        this.khoa = khoa;
    }
    public void setNganh(String nganh){
        this.nganh = nganh;
    }
    public void setSdt(String sdt){
        this.sdt = sdt;
    }


    public int getMatv(){
        return this.matv;
    }
    public String getHoten(){
        return this.hoten;
    }
    public String getkhoa(){
        return this.khoa;
    }
    public String getNganh(){
        return this.nganh;
    }
    public String getSdt(){
        return this.sdt;
    }
}
