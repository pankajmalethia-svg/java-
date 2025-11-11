
package com.hostel.service;

import com.hostel.model.Complaint;
import com.hostel.repository.ComplaintRepository;
import com.hostel.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ComplaintService {
    private final ComplaintRepository repo;
    private final StudentRepository students;

    public ComplaintService(ComplaintRepository repo, StudentRepository students){ this.repo=repo; this.students=students; }

    public List<Complaint> all(){ return repo.findAll(); }

    @Transactional
    public void saveOrUpdate(Complaint c){
        if(!students.existsById(c.getStudentId())) throw new NotFoundException("No such student with ID " + c.getStudentId());
        Complaint existing = repo.findByStudentId(c.getStudentId()).orElse(null);
        if(existing==null){
            if(c.getStatus()==null) c.setStatus("OPEN");
            repo.save(c);
        } else {
            existing.setCategory(c.getCategory()!=null ? c.getCategory() : existing.getCategory());
            existing.setDescription(c.getDescription()!=null ? c.getDescription() : existing.getDescription());
            if(c.getStatus()!=null) existing.setStatus(c.getStatus());
            repo.save(existing);
        }
    }
}
