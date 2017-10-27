package com.devhacks.dao;

import com.devhacks.model.Developer;
import com.devhacks.model.User;
import com.devhacks.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ciprian on 10/27/2017.
 */
@Repository
public class DeveloperDao {

    public List<Developer> getAllDevelopers(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Developer> userList = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM Developer d").list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                userList.add((Developer) iterator.next());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    public void addNewDeveloper(Developer journeyData){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(journeyData);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
