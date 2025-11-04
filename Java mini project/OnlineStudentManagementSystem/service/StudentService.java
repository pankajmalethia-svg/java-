package com.sms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sms.dao.StudentDAO;
import com.sms.model.Student;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    public void addStudent(Student student) {
        studentDAO.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> viewAll() {
        return studentDAO.list();
    }

    @Transactional
    public void updateStudent(Student student) {
        studentDAO.save(student);
    }

    @Transactional
    public void deleteStudent(int id) {
        studentDAO.delete(id);
    }
}
