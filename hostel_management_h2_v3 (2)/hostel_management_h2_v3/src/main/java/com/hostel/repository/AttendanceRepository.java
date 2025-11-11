
package com.hostel.repository;

import com.hostel.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Long> {
    Optional<AttendanceRecord> findByStudentIdAndDate(Long studentId, LocalDate date);
    void deleteByStudentId(Long studentId);
}
