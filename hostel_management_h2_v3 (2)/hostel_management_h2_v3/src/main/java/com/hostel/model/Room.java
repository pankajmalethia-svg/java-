
package com.hostel.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Room {
    @Id
    private String roomNo;
    private int capacity;

    @ElementCollection
    private Set<Long> studentIds = new LinkedHashSet<>();

    public Room(){}
    public Room(String roomNo, int capacity){
        this.roomNo=roomNo; this.capacity=capacity;
    }

    public String getRoomNo(){return roomNo;}
    public void setRoomNo(String roomNo){this.roomNo=roomNo;}
    public int getCapacity(){return capacity;}
    public void setCapacity(int capacity){this.capacity=capacity;}
    public Set<Long> getStudentIds(){return studentIds;}
    public void setStudentIds(Set<Long> s){this.studentIds=s;}
    public boolean isFull(){return studentIds.size()>=capacity;}
}
