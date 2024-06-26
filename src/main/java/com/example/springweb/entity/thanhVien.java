package com.example.springweb.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "thanhVien")
@Table(name = "thanhvien")
public class thanhVien {
    @Id
    @Column(name = "MaTV")
    private Integer matv;

    @Column(name = "HoTen")
    private String hoten;

    @Column(name = "Khoa")
    private String khoa;

    @Column(name = "Nganh")
    private String nganh;

    @Column(name = "SDT")
    private String sdt;

    @OneToMany(mappedBy = "thanhvien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<xuLy> listXuLy;

    @OneToMany(mappedBy = "thanhvien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<thongTinSd> listThongTinSd;

    @Column(name = "Password")
    private String password;
    
    @Column(name = "Email")
    private String email;


    public thanhVien() {
        this.matv = 0;
        this.hoten = null;
        this.khoa = null;
        this.nganh = null;
        this.sdt = null;
        this.email = null;
        this.password = null;
    }

    public thanhVien(int matv, String hoten, String khoa, String nganh, String sdt) {
        this.matv = matv;
        this.hoten = hoten;
        this.khoa = khoa;
        this.nganh = nganh;
        this.sdt = sdt;
    }
    
    
    public void setMatv(int matv) {
        this.matv = matv;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getMatv() {
        return this.matv;
    }

    public String getHoten() {
        return this.hoten;
    }

    public String getKhoa() {
        return this.khoa;
    }

    public String getNganh() {
        return this.nganh;
    }

    public String getSdt() {
        return this.sdt;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public List<xuLy> getListXuLy() {
        return listXuLy;
    }

    public void setListXuLy(List<xuLy> listXuLy) {
        this.listXuLy = listXuLy;
    }

    public List<thongTinSd> getListThongTinSd() {
        return listThongTinSd;
    }

    public void setListThongTinSd(List<thongTinSd> listThongTinSd) {
        this.listThongTinSd = listThongTinSd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
