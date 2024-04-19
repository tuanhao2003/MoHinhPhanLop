package com.example.DAL;

import javax.persistence.*;

@Entity(name = "thietBi")
@Table(name = "thietbi")
public class thietBi {
    @Id
    @Column(name = "MaTB")
    private int matb;

    @Column(name = "TenTB")
    private String tentb;

    @Column(name = "MoTaTB")
    private String motatb;

    public thietBi() {
        this.matb = 0;
        this.tentb = null;
        this.motatb = null;
    }

    public thietBi(int matb, String tentb, String motatb) {
        this.matb = matb;
        this.tentb = tentb;
        this.motatb = motatb;
    }

    public int getMatb() {
        return matb;
    }

    public String getTentb() {
        return tentb;
    }

    public String getMotatb() {
        return motatb;
    }

    public void setMatb(int matb) {
        this.matb = matb;
    }

    public void setTentb(String tentb) {
        this.tentb = tentb;
    }

    public void setMotatb(String motatb) {
        this.motatb = motatb;
    }
}
