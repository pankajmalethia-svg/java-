
package com.hostel.service;

import com.hostel.model.AttendanceRecord;
import com.hostel.repository.AttendanceRepository;
import com.hostel.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository repo;
    private final StudentRepository students;

    public AttendanceService(AttendanceRepository repo, StudentRepository students){ this.repo=repo; this.students=students; }

    public List<AttendanceRecord> all(){ return repo.findAll(); }

    @Transactional
    public void mark(Long studentId, LocalDate date, boolean present){
        if(!students.existsById(studentId)) throw new NotFoundException("No such student with ID " + studentId);
        AttendanceRecord r = repo.findByStudentIdAndDate(studentId, date).orElseGet(() -> new AttendanceRecord(studentId, date, present));
        r.setPresent(present);
        repo.save(r);
    }
}
