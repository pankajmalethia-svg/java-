package com.example.transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public Account getAccount(int accNo) {
        return sessionFactory.getCurrentSession().get(Account.class, accNo);
    }

    public void updateAccount(Account account) {
        sessionFactory.getCurrentSession().update(account);
    }
}
