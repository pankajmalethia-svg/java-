
package com.hostel.repository;

import com.hostel.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    Optional<Complaint> findByStudentId(Long studentId);
    void deleteByStudentId(Long studentId);
}
