package com.example.DAL;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "xuLy")
@Table(name = "xuly")
public class xuLy {
    @Id
    @Column(name = "MaXL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maxl;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaTV", nullable = false, foreignKey = @ForeignKey(name = "fk_thanhVien_xuLy"))
    private thanhVien thanhvien;

    @Column(name = "HinhThucXL")
    private String hinhthucxl;

    @Column(name = "SoTien")
    private int sotien;

    @Column(name = "NgayXL")
    private Timestamp ngayxl;

    @Column(name = "TrangThaiXL")
    private int trangthaixl;


    public xuLy() {
        this.maxl = 0;
        this.thanhvien = null;
        this.hinhthucxl = null;
        this.sotien = 0;
        this.ngayxl = null;
        this.trangthaixl = 0;
    }

    public xuLy(int maxl, thanhVien thanhvien, String hinhthucxl, int sotien, Timestamp ngayxl, int trangthaixl) {
        this.maxl = maxl;
        this.thanhvien = thanhvien;
        this.hinhthucxl = hinhthucxl;
        this.sotien = sotien;
        this.ngayxl = ngayxl;
        this.trangthaixl = trangthaixl;
    }

    public int getMaxl() {
        return maxl;
    }

    public String getHinhthucxl() {
        return hinhthucxl;
    }

    public int getSotien() {
        return sotien;
    }

    public Timestamp getNgayxl() {
        return ngayxl;
    }

    public int getTrangthaixl() {
        return trangthaixl;
    }
    

    public void setMaxl(int maxl) {
        this.maxl = maxl;
    }

    public void setHinhthucxl(String hinhthucxl) {
        this.hinhthucxl = hinhthucxl;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public void setNgayxl(Timestamp ngayxl) {
        this.ngayxl = ngayxl;
    }

    public void setTrangthaixl(int trangthaixl) {
        this.trangthaixl = trangthaixl;
    }

    public thanhVien getThanhvien() {
        return thanhvien;
    }

    public void setThanhvien(thanhVien thanhvien) {
        this.thanhvien = thanhvien;
    }
}
