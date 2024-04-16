package com.example.DAL;

import javax.persistence.*;

@Entity
@Table(name = "thietbi")
public class thietBi {
    @Id
    @Column
    private int MaTB;
    @Column
    private String TenTB;
    @Column
    private String MoTaTB;


    public thietBi() {
        this.MaTB = 0;
        this.TenTB = "";
        this.MoTaTB = "";
    }

    public thietBi(int MaTB, String TenTB, String MoTa) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTa;
    }

    public void setMaTB(int MaTB) {
        this.MaTB = MaTB;
    }

    public void setTenTB(String TenTB) {
        this.TenTB = TenTB;
    }

    public void setMoTaTB(String MoTaTB) {
        this.MoTaTB = MoTaTB;
    }

    public int getMaTB() {
        return this.MaTB;
    }

    public String getTenTB() {
        return this.TenTB;
    }

    public String getMoTaTB() {
        return MoTaTB;
    }
}
