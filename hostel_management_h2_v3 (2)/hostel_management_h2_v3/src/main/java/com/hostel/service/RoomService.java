
package com.hostel.service;

import com.hostel.model.Room;
import com.hostel.repository.RoomRepository;
import com.hostel.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository rooms;
    private final StudentRepository students;

    public RoomService(RoomRepository rooms, StudentRepository students){
        this.rooms=rooms; this.students=students;
    }

    public List<Room> all(){ return rooms.findAll(); }
    public void save(Room r){ rooms.save(r); }
    public void delete(String roomNo){ rooms.deleteById(roomNo); }

    @Transactional
    public boolean allocate(String roomNo, Long studentId){
        if(!students.existsById(studentId)) throw new NotFoundException("No such student with ID " + studentId);
        Room r = rooms.findById(roomNo).orElseThrow(() -> new NotFoundException("No such room " + roomNo));
        if(r.isFull()) return false;
        r.getStudentIds().add(studentId);
        rooms.save(r);
        return true;
    }

    @Transactional
    public void deallocate(String roomNo, Long studentId){
        Room r = rooms.findById(roomNo).orElseThrow(() -> new NotFoundException("No such room " + roomNo));
        r.getStudentIds().remove(studentId);
        rooms.save(r);
    }
}
