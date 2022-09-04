package com.jpa.tutorial.repository;

import com.jpa.tutorial.entity.CourseComposite;
import com.jpa.tutorial.entity.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCompositeRepository extends JpaRepository<CourseComposite, NameId> {
}
