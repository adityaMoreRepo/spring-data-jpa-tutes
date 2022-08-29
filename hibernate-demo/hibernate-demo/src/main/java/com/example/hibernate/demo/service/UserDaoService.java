package com.example.hibernate.demo.service;

import com.example.hibernate.demo.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoService {
    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public long insert(User user){
        entityManager.persist(user);//to put in persistence context so entity manager keep track of it
        //in persistent layer
        return user.getId();
    }
}
