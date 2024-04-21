package com.example.DAL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.*;

import java.util.*;

@SuppressWarnings("rawtypes")
public class xuLyDAO {
    private SessionFactory sf;
    private Session sess;
    private Transaction trans;
    
    public xuLyDAO() {
        this.sf = new Configuration().configure().buildSessionFactory();
        this.sess = null;
        this.trans = null;
    }

    public ArrayList<xuLy> listPunishments(){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            ArrayList<xuLy> punishments = new ArrayList<xuLy>();
            Query query = this.sess.createQuery("from xuLy");
            List datas = query.list();
            for(Iterator iterator = datas.iterator();iterator.hasNext();){
                xuLy punish = (xuLy) iterator.next();
                punishments.add(punish);
            }
            
            this.trans.commit();
            return punishments;
        } catch (HibernateException he) {
            if(trans != null){
                trans.rollback();
            }
            he.printStackTrace();
        } finally{
            this.sess.close();
        }
        return null;
    }

    public xuLy punishment(int matv){
        xuLy punish = new xuLy();
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            punish = this.sess.get(xuLy.class, matv);

            this.trans.commit();
        } catch (HibernateException he) {
            if(trans != null){
                trans.rollback();
            }
            he.printStackTrace();
        } finally{
            this.sess.close();
        }
        return punish;
    }

    public boolean addPunishment(xuLy punish){
        List<xuLy> punishments = listPunishments();
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            if(!punishments.contains(punish)){
                sess.save(punish);
                this.trans.commit();
                return true;
            }
        } catch (HibernateException he) {
            if(trans != null){
                trans.rollback();
            }
            he.printStackTrace();
        } finally{
            this.sess.close();
        }
        return false;
    }

    public boolean delPunishment(xuLy punish){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();
            
            sess.delete(punish);
            
            this.trans.commit();
            return true;
        } catch (HibernateException he) {
            if(trans != null){
                trans.rollback();
            }
            he.printStackTrace();
        } finally{
            this.sess.close();
        }
        return false;
    }

    public boolean updatePunishment(xuLy xuly){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();
            xuLy punish = (xuLy) this.sess.get(xuLy.class, xuly.getMaxl());
            punish.setHinhthucxl(xuly.getHinhthucxl());
            punish.setNgayxl(xuly.getNgayxl());
            punish.setSotien(xuly.getSotien());
            punish.setThanhvien(xuly.getThanhvien());
            punish.setTrangthaixl(xuly.getTrangthaixl());

            sess.update(punish);
            
            this.trans.commit();
            return true;
        } catch (HibernateException he) {
            if(trans != null){
                trans.rollback();
            }
            he.printStackTrace();
        } finally{
            this.sess.close();
        }
        return false;
    }
}
