package com.example.DAL;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "xuly")
public class xuLy {
    @Id
    @Column
    private int maxl;
    @Column
    private int matv;
    @Column
    private String hinhthucxl;
    @Column
    private int sotien;
    @Column
    private Date ngayxl;
    @Column
    private int trangthaixl;

    public xuLy() {
        this.maxl = 0;
        this.matv = 0;
        this.hinhthucxl = null;
        this.sotien = 0;
        this.ngayxl = null;
        this.trangthaixl = 0;
    }

    public xuLy(int maxl, int matv, String hinhthucxl, int sotien, Date ngayxl, int trangthaixl) {
        this.maxl = maxl;
        this.matv = matv;
        this.hinhthucxl = hinhthucxl;
        this.sotien = sotien;
        this.ngayxl = ngayxl;
        this.trangthaixl = trangthaixl;
    }

    public int getMaxl() {
        return maxl;
    }

    public int getMatv() {
        return matv;
    }

    public String getHinhthucxl() {
        return hinhthucxl;
    }

    public int getSotien() {
        return sotien;
    }

    public Date getNgayxl() {
        return ngayxl;
    }

    public int getTrangthaixl() {
        return trangthaixl;
    }

    public void setMaxl(int maxl) {
        this.maxl = maxl;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public void setHinhthucxl(String hinhthucxl) {
        this.hinhthucxl = hinhthucxl;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public void setNgayxl(Date ngayxl) {
        this.ngayxl = ngayxl;
    }

    public void setTrangthaixl(int trangthaixl) {
        this.trangthaixl = trangthaixl;
    }
}
