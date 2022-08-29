package com.jpa.tutorial.controller;

import com.jpa.tutorial.entity.Subject;
import com.jpa.tutorial.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class SubjectController {
    @Autowired
    SubjectRepository repo;

    @GetMapping("/subject/{id}")
    public Subject getSubject(@PathVariable int id){
        return repo.findBySubjectId(id)
                .orElseThrow(EntityNotFoundException::new);
    }
    @GetMapping("/subjects")
    public List<Subject> getSubjects(){
        return repo.findAll();
    }

    @GetMapping("/subjectsByName")
    public List<Subject> getSubjectsByName(@RequestParam String name){
        return repo.findSubjectBySubjectName(name);
    }

    @PostMapping("/subject")
    public Subject saveSubject(@RequestBody Subject subject){
        return repo.save(subject);
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable int id){
        Subject subject = getSubject(id);
        repo.delete(subject);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }



}
