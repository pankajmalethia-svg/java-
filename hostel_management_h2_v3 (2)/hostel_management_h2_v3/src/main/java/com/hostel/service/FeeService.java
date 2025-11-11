
package com.hostel.service;

import com.hostel.model.FeePayment;
import com.hostel.repository.FeeRepository;
import com.hostel.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class FeeService {
    private final FeeRepository repo;
    private final StudentRepository students;

    public FeeService(FeeRepository repo, StudentRepository students){ this.repo=repo; this.students=students; }

    public List<FeePayment> all(){ return repo.findAll(); }

    @Transactional
    public void addOrUpdate(Long studentId, Double amount, String status){
        if(!students.existsById(studentId)) throw new NotFoundException("No such student with ID " + studentId);
        FeePayment existing = repo.findByStudentId(studentId);
        if(existing == null){
            repo.save(new FeePayment(studentId, amount, LocalDate.now(), status));
        } else {
            existing.setAmount(amount);
            existing.setStatus(status);
            existing.setDate(LocalDate.now());
            repo.save(existing);
        }
    }
}
