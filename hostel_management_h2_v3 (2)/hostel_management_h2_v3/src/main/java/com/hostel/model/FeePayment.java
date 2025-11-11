
package com.hostel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class FeePayment {
    @Id
    private Long studentId; // one row per student

    private Double amount;
    private LocalDate date;
    private String status; // PAID or DUE

    public FeePayment(){}
    public FeePayment(Long studentId, Double amount, LocalDate date, String status){
        this.studentId=studentId; this.amount=amount; this.date=date; this.status=status;
    }
    public Long getStudentId(){return studentId;}
    public void setStudentId(Long studentId){this.studentId=studentId;}
    public Double getAmount(){return amount;}
    public void setAmount(Double amount){this.amount=amount;}
    public LocalDate getDate(){return date;}
    public void setDate(LocalDate date){this.date=date;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
}
