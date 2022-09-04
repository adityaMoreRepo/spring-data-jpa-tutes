package com.jpa.tutorial.bootstrap;

import com.jpa.tutorial.entity.CourseComposite;
import com.jpa.tutorial.entity.NameId;
import com.jpa.tutorial.repository.CourseCompositeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    CourseCompositeRepository repo;

    @Override
    public void run(String... args) throws Exception {
    }
}
