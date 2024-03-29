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

    public List<thanhVien> listMember(){
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();
            List<thanhVien> members = new ArrayList<thanhVien>();
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
        try {
            this.sess = this.sf.openSession();
            this.trans = this.sess.beginTransaction();
            thanhVien mem = new thanhVien();
            Query query = this.sess.createQuery("from thanhVien mem where mem.matv= :matv");
            query.setParameter("matv", matv);
            List datas = query.list();
            
            for(Iterator iterator = datas.iterator();iterator.hasNext();){
                mem = (thanhVien) iterator.next();
            }
            this.trans.commit();
            return mem;
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
}
