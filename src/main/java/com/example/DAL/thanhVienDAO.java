package com.example.DAL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.*;

import java.util.*;

@SuppressWarnings("rawtypes")
public class thanhVienDAO {
    private SessionFactory sf;
    private Session sess;
    private Transaction trans;
    
    public thanhVienDAO() {
        this.sf = new Configuration().configure().buildSessionFactory();
        this.sess = null;
        this.trans = null;
    }

    public ArrayList<thanhVien> listMembers(){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            ArrayList<thanhVien> members = new ArrayList<thanhVien>();
            Query query = this.sess.createQuery("from thanhVien");
            List datas = query.list();
            for(Iterator iterator = datas.iterator();iterator.hasNext();){
                thanhVien mem = (thanhVien) iterator.next();
                members.add(mem);
            }
            
            this.trans.commit();
            return members;
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

    public thanhVien member(int matv){
        thanhVien mem = new thanhVien();
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            mem = this.sess.get(thanhVien.class, matv);

            this.trans.commit();
        } catch (HibernateException he) {
            if(trans != null){
                trans.rollback();
            }
            he.printStackTrace();
        } finally{
            this.sess.close();
        }
        return mem;
    }

    public boolean addMember(thanhVien mem){
        List<thanhVien> members = listMembers();
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();

            if(!members.contains(mem)){
                sess.save(mem);
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

    public boolean delMember(thanhVien mem){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();
            
            sess.delete(mem);
            
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

    public boolean updateMember(thanhVien mem){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();
            thanhVien getFromSession = (thanhVien) this.sess.get(thanhVien.class, mem.getMatv());
            getFromSession.setHoten(mem.getHoten());
            getFromSession.setKhoa(mem.getKhoa());
            getFromSession.setNganh(mem.getNganh());
            getFromSession.setSdt(mem.getSdt());
            getFromSession.setEmail(mem.getEmail());
            getFromSession.setPassword(mem.getPassword());
            
            sess.update(getFromSession);
            
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
