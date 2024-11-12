package com.munaf.SpringJDBC2.service;

import com.munaf.SpringJDBC2.demo.Students;
import com.munaf.SpringJDBC2.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {
    private StudentsRepository repo;

//    public StudentsRepository getRepo() {
//        return repo;
//    }
//
//    @Autowired
//    public void setRepo(StudentsRepository repo) {
//        this.repo = repo;
//    }

//    @Autowired
    StudentsService(StudentsRepository repo){
        this.repo = repo;
    }


    public void add(Students s) {
        repo.save(s);
    }

    public List<Students> getStudents() {
        return repo.findAll();
    }

    public void updateStudentMarks(Students s, int marks) {
        repo.updateMarks(s, marks);
    }

    public void deleteStudent(Students s) {
        repo.deleteRow(s);
    }
}
