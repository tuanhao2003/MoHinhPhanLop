package com.example.DAL;

public class thietBi {
    private int MaTB;
    private String TenTB;
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

    public void setMaTB(int idTB) {
        this.MaTB = idTB;
    }

    public void setTenTB(String nameTB) {
        this.TenTB = nameTB;
    }

    public void setMoTaTB(String detailTB) {
        this.MoTaTB = detailTB;
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
