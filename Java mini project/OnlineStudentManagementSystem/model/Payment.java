package com.sms.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private double amount;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Getters, Setters, Constructors
}
