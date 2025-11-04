package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sms.model.Student;
import com.sms.dao.StudentDAO;

@Service
public class FeeService {

    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    public void payFee(int studentId, double amount) {
        Student s = studentDAO.get(studentId);
        if (s.getBalance() >= amount) {
            s.setBalance(s.getBalance() - amount);
            studentDAO.save(s);
            System.out.println("✅ Payment successful!");
        } else {
            throw new RuntimeException("❌ Insufficient balance!");
        }
    }

    @Transactional
    public void refundFee(int studentId, double amount) {
        Student s = studentDAO.get(studentId);
        s.setBalance(s.getBalance() + amount);
        studentDAO.save(s);
        System.out.println("💰 Refund processed!");
    }
}
