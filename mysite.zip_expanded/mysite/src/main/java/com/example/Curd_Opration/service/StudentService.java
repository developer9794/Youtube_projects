package com.example.Curd_Opration.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Curd_Opration.domain.student;
import com.example.Curd_Opration.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
    private StudentRepository repo;
	
	public List<student> listAll() {
        return repo.findAll();
    }
     
    public void save(student std) {
        repo.save(std);
    }
     
    public student get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }

}