package com.example.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private AccountDAO dao;

    @Transactional
    public void transferMoney(int fromAcc, int toAcc, double amount) {
        Account acc1 = dao.getAccount(fromAcc);
        Account acc2 = dao.getAccount(toAcc);

        acc1.setBalance(acc1.getBalance() - amount);
        acc2.setBalance(acc2.getBalance() + amount);

        dao.updateAccount(acc1);
        dao.updateAccount(acc2);

        System.out.println("✅ Transfer Successful: " + amount + " from " + acc1.getName() + " to " + acc2.getName());
    }
}
