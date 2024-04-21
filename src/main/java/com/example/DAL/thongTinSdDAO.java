package com.example.DAL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.*;

import java.util.*;

@SuppressWarnings("rawtypes")
public class thongTinSdDAO {
    private SessionFactory sf;
    private Session sess;
    private Transaction trans;
    
    public thongTinSdDAO() {
        this.sf = new Configuration().configure().buildSessionFactory();
        this.sess = null;
        this.trans = null;
    }

    public ArrayList<thongTinSd> listUsageInfors(){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            ArrayList<thongTinSd> usageInfors = new ArrayList<thongTinSd>();
            Query query = this.sess.createQuery("from thongTinSd");
            List datas = query.list();
            for(Iterator iterator = datas.iterator();iterator.hasNext();){
                thongTinSd punish = (thongTinSd) iterator.next();
                usageInfors.add(punish);
            }
            
            this.trans.commit();
            return usageInfors;
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

    public thongTinSd usageInfor(int matv){
        thongTinSd punish = new thongTinSd();
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            punish = this.sess.get(thongTinSd.class, matv);

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

    public boolean addUsageInfor(thongTinSd punish){
        List<thongTinSd> usageInfors = listUsageInfors();
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            if(!usageInfors.contains(punish)){
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

    public boolean delUsageInfor(thongTinSd punish){
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

    public boolean updateUsageInfor(thongTinSd punish){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();
            
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
