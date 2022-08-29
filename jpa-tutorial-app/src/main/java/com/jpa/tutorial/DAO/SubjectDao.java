package com.jpa.tutorial.DAO;

import com.jpa.tutorial.entity.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> findAll();
    Subject findById(int id);
}
