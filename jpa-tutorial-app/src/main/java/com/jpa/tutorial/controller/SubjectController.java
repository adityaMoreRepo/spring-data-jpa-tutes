package com.jpa.tutorial.controller;

import com.jpa.tutorial.entity.Course;
import com.jpa.tutorial.entity.Subject;
import com.jpa.tutorial.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequestMapping("/v1")
public class SubjectController {
    @Autowired
    SubjectRepository repo;

    @GetMapping("/subject/{id}")
    public Subject getSubject(@PathVariable int id) {
        return repo.findBySubjectId(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping("/subjects")
    public List<Subject> getSubjects() {
        return repo.findAll();
    }

    @GetMapping("/subjectsByName")
    public List<Subject> getSubjectsByName(@RequestParam String name) {
        return repo.findSubjectBySubjectName(name);
    }

    @PostMapping("/subject")
    public Subject saveSubject(@RequestBody Subject subject) {
        return repo.save(subject);
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable int id) {
        Subject subject = getSubject(id);
        repo.delete(subject);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }

    @GetMapping("/subject/all")
    public Page<Subject> findAll(Pageable pageable) {
        // == Note ==
        // Pageable will pass page and size
        // page will contain page no. and size to calculate the offset
        //size is limit itself. Hence, the logic to calculate offset is
        // offset = page * size
        //e.g.
        // pageSize=10 , offset=0  page=0 -> page no 1
        //pageSize=10, offset=10, page=1 -> page no 2
        //size=10, offset=20, page=2  -> page no 3
        return repo.findAll(pageable);
    }

    @GetMapping("/subject/all/sort")
    public Page<Subject> findAllByPagingAndSorting(@RequestParam Optional<Integer> size,
                                                   @RequestParam Optional<Integer> page,
                                                   @RequestParam Optional<String> sortBy) {
        return repo.findAll(PageRequest.of(page.orElse(0), size.orElse(4), ASC, sortBy.orElse("SubjectId")));
    }


}
