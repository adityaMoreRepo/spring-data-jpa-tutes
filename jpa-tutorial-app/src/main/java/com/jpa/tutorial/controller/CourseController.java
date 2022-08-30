package com.jpa.tutorial.controller;

import com.jpa.tutorial.entity.Course;
import com.jpa.tutorial.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseRepository repo;

    @GetMapping("/all")
    public Page<Course> findAll(@RequestParam int page,
                                @RequestParam int size) {
        return repo.findAll(PageRequest.of(page, size));
    }


}
