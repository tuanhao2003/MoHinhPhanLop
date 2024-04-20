package com.example.DAL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.*;

import java.util.*;

@SuppressWarnings("rawtypes")
public class thietBiDAO {
    private SessionFactory sf;
    private Session sess;
    private Transaction trans;
    
    public thietBiDAO() {
        this.sf = new Configuration().configure().buildSessionFactory();
        this.sess = null;
        this.trans = null;
    }

    public ArrayList<thietBi> listDevices(){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            ArrayList<thietBi> devices = new ArrayList<thietBi>();
            Query query = this.sess.createQuery("from thietBi");
            List datas = query.list();
            for(Iterator iterator = datas.iterator();iterator.hasNext();){
                thietBi devi = (thietBi) iterator.next();
                devices.add(devi);
            }
            
            this.trans.commit();
            return devices;
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

    public thietBi device(int matb){
        thietBi devi = new thietBi();
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            devi = this.sess.get(thietBi.class, matb);

            this.trans.commit();
        } catch (HibernateException he) {
            if(trans != null){
                trans.rollback();
            }
            he.printStackTrace();
        } finally{
            this.sess.close();
        }
        return devi;
    }

    public boolean addDevice(thietBi devi){
        List<thietBi> devices = listDevices();
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            if(!devices.contains(devi)){
                sess.save(devi);
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

    public boolean delDevice(thietBi devi){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();
            
            sess.delete(devi);
            
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

    public boolean updateDevice(thietBi devi){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();
            thietBi getFromSession = this.sess.get(thietBi.class, devi.getMatb());
            getFromSession.setTentb(devi.getTentb());
            getFromSession.setMotatb(devi.getMotatb());
            
            sess.update(devi);
            
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
