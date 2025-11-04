package com.sms.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;
    private String duration;

    @OneToMany(mappedBy = "course")
    private List<Student> students;

    // Getters, Setters, Constructors
}
