package com.jpa.tutorial.repository;

import com.jpa.tutorial.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;
    @Commit
    @Test
    public void testFindCourseByIdUsingQuery(){
        Course course = Course.builder()
                .standard(9)
                .build();
        courseRepository.save(course);
        Course course1 = courseRepository.findCourseById(1);
        assertThat(course1).isNotNull();
    }

    @Test
    public void testFindCourseByStandardWithQuery(){
        List<Course> courses = courseRepository.findCourseByStandardNativeQuery(9);
        System.out.println(courses);
        assertThat(courses).isNotNull();
    }
    @Test
    public void givenId_whenFindById_thenReturnCourse(){
        Course course = Course.builder()
                .standard(9)
                .build();
        courseRepository.save(course);
        Optional<Course> byId = courseRepository.findById(1);
        byId.ifPresent(c -> System.out.println(c.getCourseId()));
        assertThat(byId.get()).isNotNull();
        assertThat(byId.get().getCourseId()).isEqualTo(1);
    }
}