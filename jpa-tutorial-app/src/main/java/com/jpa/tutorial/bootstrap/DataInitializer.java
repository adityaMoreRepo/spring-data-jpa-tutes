package com.jpa.tutorial.bootstrap;

import com.jpa.tutorial.entity.Course;
import com.jpa.tutorial.entity.CourseComposite;
import com.jpa.tutorial.entity.NameId;
import com.jpa.tutorial.repository.CourseCompositeRepository;
import com.jpa.tutorial.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Profile("postgres")
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    CourseRepository repo;

    @Override
    public void run(String... args) throws Exception {
//        PageRequest pageRequest = PageRequest.of(0, 4);
//        Page<Course> all = repo.findAll(pageRequest);
//        all.forEach(course -> System.out.println(course.getCourseId()));
    }
}
