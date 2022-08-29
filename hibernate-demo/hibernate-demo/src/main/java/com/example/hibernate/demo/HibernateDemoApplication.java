package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.User;
import com.example.hibernate.demo.service.UserDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class HibernateDemoApplication implements CommandLineRunner {

    @Autowired
    UserDaoService userDaoService;

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .firstName("Adi")
                .role("admin")
                .build();
        log.info("after user generation");
        Long id = userDaoService.insert(user);
        log.info("This user with id " + id + " is added into the database.");
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateDemoApplication.class, args);
    }

}
