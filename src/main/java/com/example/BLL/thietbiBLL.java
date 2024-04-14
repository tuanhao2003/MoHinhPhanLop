package com.example.BLL;

import com.example.DAL.thietbiDAO;
import com.example.DAL.thietBi;
public class thietbiBLL {
    private thietbiDAO thietbiDAO = new thietbiDAO();
    public thietbiBLL() {
        this.thietbiDAO = new thietbiDAO();
    }

    public void addThietbi(thietBi thietbi) {
        this.thietbiDAO.addThietbi(thietbi);
    }

    public void updateThietbi(thietBi thietbi) {
        this.thietbiDAO.updateThietbi(thietbi);
    }

    public void deleteThietbi(int idTB) {
        this.thietbiDAO.deleteThietbi(idTB);
    }

    public void getThietbis() {
        this.thietbiDAO.getThietbis();
    }
}
