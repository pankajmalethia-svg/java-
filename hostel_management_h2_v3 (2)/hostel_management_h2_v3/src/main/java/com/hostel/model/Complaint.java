
package com.hostel.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"studentId"}))
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private String category;
    @Column(length=1000)
    private String description;
    private String status;

    public Complaint(){}
    public Complaint(Long id, Long studentId, String category, String description, String status){
        this.id=id; this.studentId=studentId; this.category=category; this.description=description; this.status=status;
    }
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Long getStudentId(){return studentId;}
    public void setStudentId(Long studentId){this.studentId=studentId;}
    public String getCategory(){return category;}
    public void setCategory(String category){this.category=category;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
}
