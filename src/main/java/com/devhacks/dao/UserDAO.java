package com.devhacks.dao;

import com.devhacks.model.User;
import com.devhacks.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ciprian on 10/27/2017.
 */
@Repository
public class UserDAO {
    public User getUserById(@NotNull Integer user_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> userList = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM User e where e.id=" + user_id).list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                userList.add((User) iterator.next());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList.size() == 0 ? null : userList.get(0);
    }

    public User getUserByUsername(@NotNull String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> userList = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM User e where e.username='" + username + "'").list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                userList.add((User) iterator.next());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList.size() == 0 ? null : userList.get(0);
    }

    public List<User> getAllUsers(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> userList = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM User e").list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                userList.add((User) iterator.next());
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
}
