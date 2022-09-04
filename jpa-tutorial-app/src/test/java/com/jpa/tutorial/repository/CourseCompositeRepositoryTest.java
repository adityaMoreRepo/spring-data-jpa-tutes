package com.jpa.tutorial.repository;

import com.jpa.tutorial.entity.CourseComposite;
import com.jpa.tutorial.entity.NameId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class CourseCompositeRepositoryTest {
    @Autowired
    CourseCompositeRepository repo;

    @Test
    public void testCourseCoRepo(){
        NameId nameId = new NameId("Computer Science", 1);
        CourseComposite course = new CourseComposite();
        course.setCourseName(nameId.getCourseName());
        course.setId(nameId.getId());
        course.setStandard("X");
        CourseComposite saved = repo.save(course);
        CourseComposite fetched = repo.getById(nameId);
        assertThat(fetched).isNotNull();
    }

}