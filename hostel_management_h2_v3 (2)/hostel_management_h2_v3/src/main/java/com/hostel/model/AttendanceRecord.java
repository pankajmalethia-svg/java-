
package com.hostel.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"studentId","date"}))
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private LocalDate date;
    private boolean present;

    public AttendanceRecord(){}
    public AttendanceRecord(Long studentId, LocalDate date, boolean present){
        this.studentId=studentId; this.date=date; this.present=present;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Long getStudentId(){return studentId;}
    public void setStudentId(Long studentId){this.studentId=studentId;}
    public LocalDate getDate(){return date;}
    public void setDate(LocalDate date){this.date=date;}
    public boolean isPresent(){return present;}
    public void setPresent(boolean present){this.present=present;}
}
