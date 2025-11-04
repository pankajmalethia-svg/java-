package com.sms.model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String name;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Getters, Setters, Constructors
}
