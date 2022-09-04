package com.jpa.tutorial.controller;

import com.jpa.tutorial.entity.CourseComposite;
import com.jpa.tutorial.entity.NameId;
import com.jpa.tutorial.repository.CourseCompositeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course_composite")
public class CourseCompositeController {
    @Autowired
    CourseCompositeRepository repo;

    @PostMapping("/save")
    public CourseComposite save(@RequestBody CourseComposite course) {
        return repo.save(course);
    }

    @GetMapping("/course")
    public CourseComposite get(
            @RequestParam String courseName,
            @RequestParam int id
    ) {
        NameId nameId = new NameId(courseName, id);
        return repo.getReferenceById(nameId);
    }

}
