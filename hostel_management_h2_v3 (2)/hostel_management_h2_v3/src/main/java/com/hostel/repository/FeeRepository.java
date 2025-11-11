
package com.hostel.repository;

import com.hostel.model.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<FeePayment, Long> {
    FeePayment findByStudentId(Long studentId);
}
