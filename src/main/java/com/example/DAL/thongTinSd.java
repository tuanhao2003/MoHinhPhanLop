package com.example.DAL;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "thongtinsd")
public class thongTinSd {
    @Id
    @Column
    private int matt;
    @Column
    private int matv;
    @Column
    private int matb;
    @Column
    private Date tgvao;
    @Column
    private Date tgmuon;
    @Column
    private Date tgtra;
    
    public thongTinSd() {
        this.matt = 0;
        this.matv = 0;
        this.matb = 0;
        this.tgvao = null;
        this.tgmuon = null;
        this.tgtra = null;
    }

    public thongTinSd(int matt, int matv, int matb, Date tgvao, Date tgmuon, Date tgtra) {
        this.matt = matt;
        this.matv = matv;
        this.matb = matb;
        this.tgvao = tgvao;
        this.tgmuon = tgmuon;
        this.tgtra = tgtra;
    }

    public int getMatt() {
        return matt;
    }

    public void setMatt(int matt) {
        this.matt = matt;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public int getMatb() {
        return matb;
    }

    public void setMatb(int matb) {
        this.matb = matb;
    }

    public Date getTgvao() {
        return tgvao;
    }

    public void setTgvao(Date tgvao) {
        this.tgvao = tgvao;
    }

    public Date getTgmuon() {
        return tgmuon;
    }

    public void setTgmuon(Date tgmuon) {
        this.tgmuon = tgmuon;
    }

    public Date getTgtra() {
        return tgtra;
    }

    public void setTgtra(Date tgtra) {
        this.tgtra = tgtra;
    }
}
