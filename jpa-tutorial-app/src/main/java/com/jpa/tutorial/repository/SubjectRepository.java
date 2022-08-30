package com.jpa.tutorial.repository;

import com.jpa.tutorial.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findBySubjectId(int id);
    List<Subject> findSubjectBySubjectName(String name);
    //find all by not null to get a stream of objects for some operations
    Stream<Subject> findAllBySubjectNameNotNull();
    //we can also use Async for public methods to perform concurrency using JMV
    //task manager for improving performance of queries as RDBMS systems are good at
    //handling asynchronous queries. For more study Async in spring boot
    //Note queryBy, findBy, readBy all are basically same
    @Async
    Future<Subject> queryBySubjectName(String name);

    // JPQL query
//    @Query("SELECT s FROM subject s WHERE s.subject_id = ?1")
//    Subject findQuery(int id);
}
