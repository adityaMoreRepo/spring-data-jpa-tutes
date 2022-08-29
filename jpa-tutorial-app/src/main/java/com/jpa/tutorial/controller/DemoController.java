package com.jpa.tutorial.controller;

import com.jpa.tutorial.DAO.SubjectDaoImp;
import com.jpa.tutorial.entity.Subject;
import com.jpa.tutorial.DAO.SubjectDaoImpUsingJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {
    @Autowired
    SubjectDaoImp repo;
    @GetMapping("/subjects")
    public List<Subject> getSubjects(){
        return repo.findAll();
    }

    @GetMapping("/subject/{id}")
    public Subject getSubject(@PathVariable int id){
        return repo.findById(id);
    }

    @GetMapping("/subjectByName/{name}")
    public List<Subject> getSubject(@PathVariable String name){
        return repo.findByNameNative(name);
    }

    @PostMapping("/subject")
    public Subject saveSubject(@RequestBody Subject subject){
        return repo.saveSubject(subject);
    }

    @PutMapping("/subject")
    public Subject updateSubject(@RequestBody Subject subject){
        return repo.updateSubject(subject);
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable int id){
        repo.deleteSubjectById(id);
        return new ResponseEntity<>("Subject Deleted", HttpStatus.OK);
    }

}
