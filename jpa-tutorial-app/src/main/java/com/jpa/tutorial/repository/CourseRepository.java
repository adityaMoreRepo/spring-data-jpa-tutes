package com.jpa.tutorial.repository;

import com.jpa.tutorial.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    //Hibernate Query
    //It is powered by OOP and uses same Object and properties
    //use in the java code
    //Place-holder types
    // we can use named parameter with :parameterName
    // or positioned parameter with ?1 or ?2 etc
    // for properly binding them we should use @Param
    // if they do not match
    @Query("select c from Course c where c.courseId = :id")
    Course findCourseById(@Param("id") int id);

    // Native MySQL queries
    @Query(
            value = "SELECT * FROM course c WHERE c.standard = :standard",
            nativeQuery = true
    )
    List<Course> findCourseByStandardNativeQuery(@Param("standard") int standard);

    // == Notes ==
    //Paging and Sorting
    // It makes use of SQL database for retrieving data in pages
    // It uses clauses like LIMIT and OFFSET
    // Page essentially is a type of Class type
    // It wouldn't be faster to perform paging and sorting on application
    //server rather modern DB are very efficient with Paging and sorting.
    //Hence, DB servers can do the P & Sorting and then transfer the specific results
    //to the application server
    // For large databases transferring that data back and forth from DB server
    // is not good practice, hence using DB server for offloading computer work
    // load is good practice.




}
