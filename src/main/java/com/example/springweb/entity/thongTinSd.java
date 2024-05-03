package com.example.springweb.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "thongTinSd")
@Table(name = "thongtinsd")
public class thongTinSd {
    @Id
    @Column(name = "MaTT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matt;

    @Column(name = "TGVao")
    private Timestamp tgvao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaTV", nullable = false, foreignKey = @ForeignKey(name = "FK_thanhvien_thongtinsd"))
    private thanhVien thanhvien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaTB", nullable = false, foreignKey = @ForeignKey(name = "FK_thietbi_thongtinsd"))
    private thietBi thietbi;

    @Column(name = "TGMuon")
    private Timestamp tgmuon;

    @Column(name = "TGTra")
    private Timestamp tgtra;

    public thongTinSd() {
        this.matt = 0;
        this.tgvao = null;
        this.tgmuon = null;
        this.tgtra = null;
        this.thanhvien = null;
        this.thietbi = null;
    }

    public thongTinSd(int matt, thanhVien thanhvien, thietBi thietbi, Timestamp tgvao, Timestamp tgmuon, Timestamp tgtra) {
        this.matt = matt;
        this.thanhvien = thanhvien;
        this.thietbi = thietbi;
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

    public Timestamp getTgvao() {
        return tgvao;
    }

    public void setTgvao(Timestamp tgvao) {
        this.tgvao = tgvao;
    }

    public Timestamp getTgmuon() {
        return tgmuon;
    }

    public void setTgmuon(Timestamp tgmuon) {
        this.tgmuon = tgmuon;
    }

    public Timestamp getTgtra() {
        return tgtra;
    }

    public void setTgtra(Timestamp tgtra) {
        this.tgtra = tgtra;
    }

    public thanhVien getThanhvien() {
        return thanhvien;
    }

    public void setThanhvien(thanhVien thanhvien) {
        this.thanhvien = thanhvien;
    }

    public thietBi getThietbi() {
        return thietbi;
    }

    public void setThietbi(thietBi thietbi) {
        this.thietbi = thietbi;
    }
}
