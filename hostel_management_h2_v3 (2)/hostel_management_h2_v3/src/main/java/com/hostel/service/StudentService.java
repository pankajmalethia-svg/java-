
package com.hostel.service;

import com.hostel.model.Student;
import com.hostel.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository students;
    private final RoomRepository rooms;
    private final FeeRepository fees;
    private final AttendanceRepository attendance;
    private final ComplaintRepository complaints;

    public StudentService(StudentRepository students, RoomRepository rooms, FeeRepository fees, AttendanceRepository attendance, ComplaintRepository complaints){
        this.students=students; this.rooms=rooms; this.fees=fees; this.attendance=attendance; this.complaints=complaints;
    }

    public List<Student> all(){ return students.findAll(); }
    public void save(Student s){ students.save(s); }
    public boolean exists(Long id){ return students.existsById(id); }

    @Transactional
    public void delete(Long id){
        // remove from all rooms' studentIds
        rooms.findAll().forEach(r -> {
            if (r.getStudentIds().remove(id)) rooms.save(r);
        });
        // clear related records
        fees.deleteById(id);
        attendance.deleteByStudentId(id);
        complaints.deleteByStudentId(id);
        students.deleteById(id);
    }
}
