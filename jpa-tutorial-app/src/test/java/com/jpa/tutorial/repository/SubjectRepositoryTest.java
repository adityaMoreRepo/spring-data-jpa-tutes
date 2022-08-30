package com.jpa.tutorial.repository;

import com.jpa.tutorial.entity.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class SubjectRepositoryTest {
    @Autowired
    SubjectRepository repo;
    @Test
    @Commit
    public void testCreateSubject(){
        Subject subject = Subject.builder()
                .subjectName("Math")
                .marksObtained(50)
                .totalMarks(100)
                .passingMarks(35)
                .build();
        Subject sub = repo.save(subject);

        assertThat(sub.getSubjectId()).isNotNull();
    }

    @Test
    public void testSubjectStream(){
        AtomicInteger count = new AtomicInteger();
        repo.findAllBySubjectNameNotNull().forEach(subject ->{
            count.incrementAndGet();
        });
        assertThat(count.get()).isEqualTo(1);
    }

    @Test
    public void testSubjectFuture() throws InterruptedException, ExecutionException {
        Future<Subject> subjectFuture = repo.queryBySubjectName("Math");
        Subject subject = subjectFuture.get();
        assertNotNull(subject);
    }

//    @Test
//    public void testSubjectWithQuery(){
//        Subject subject = repo.findQuery(1);
//        assertThat(subject).isNotNull();
//    }
}