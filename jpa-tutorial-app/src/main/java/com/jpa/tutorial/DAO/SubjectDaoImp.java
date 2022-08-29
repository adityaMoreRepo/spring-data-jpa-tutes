package com.jpa.tutorial.DAO;

import com.jpa.tutorial.entity.Subject;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class SubjectDaoImp implements SubjectDao {
    // == Notes ==
    //here we gonna use jpa apis and not hibernate ones
    //so that if we need to change the ORM tech then we have less  hassle for
    //refactoring the code base.
    //we have EntityMangerFactory which is a jpa equivalent of SessionFactory
    // it is a heavy lifter that is it uses high resources hence should be used once
    //per instance of an application.
    //Others are EntityManger for session and EntityTransaction for transaction etc.

    // == Fields ==
    EntityManagerFactory emt;

    // == Constructor ==
    public SubjectDaoImp(EntityManagerFactory emt) {
        this.emt = emt;
    }

    // == Methods ==
    @Override
    public List<Subject> findAll() {
        TypedQuery<Subject> query = getEntityManger()
                .createQuery("select s from Subject s", Subject.class);
        return query.getResultList();
    }

    // == Notes ==
    //JPQL should be written considering objects of a class and not table
    // hence Object like syntax is used with Class name of the entity.
    @Override
    public Subject findById(int id) {
        return getEntityManger().find(Subject.class, id);
    }

    public List<Subject> findByName(String name){
        return getEntityManger()
                .createQuery("select s from Subject s" +
                " where s.subjectName = :subject_name", Subject.class)
                .setParameter("subject_name", name)
                .getResultList();
    }

    // we have to create a complete transaction in hibernate context otherwise
    //race around conditions may occur
    // this is not mostly used as it is Hibernate making transaction managements
    // but spring boot and Hibernate are not always in sync. Hence, we mostly use
    // spring data jpa annotations i.e. declarative approach.
    // Here we can use @Transactional and @Repository annotations at class level
    // to make each and every method transactional.
    public Subject saveSubject(Subject subject){
        EntityManager em = getEntityManger();
        em.getTransaction().begin();
        em.persist(subject);
        em.flush();
        em.getTransaction().commit();
        return subject;
    }

    public Subject updateSubject(Subject subject){

        EntityManager em = getEntityManger();
        em.getTransaction().begin();//being transaction
        em.merge(subject);// merge is jpa specification for taking existing entity and update it
        em.flush();//update the database in persistence context
        em.getTransaction().commit();//commit the transaction
        em.clear();// evict the first level cache in that thread or process of transaction
        //hence, hibernate not only just do the lazy operations and doesn't really update the
        //database. It might just do these operations in persistence context and keep the cache
        //to show the result in that context without actually reflecting the results in the DB.
        //Therefore, flush() method makes the DB changes and clear() method makes sure it takes a round trip
        //to DB and again find these subjects by ID and not just use the cache memory.
        return em.find(Subject.class, subject.getSubjectId());
    }

    // == Notes ==
    // again as it is a DML operation we need to handle lazy handling by hibernate.
    // as hibernate lazily don't perform these operations while only doing
    // so in the persistence context in cache and our current thread ends
    //making no change in database
    public void deleteSubjectById(int id){
        EntityManager em = getEntityManger();
        em.getTransaction().begin();
        Subject subject = em.find(Subject.class, id);
        em.remove(subject);
        em.flush();
        em.getTransaction().commit();
    }

    // == Native SQL query ==
    //we use try finally to close EntityManger as it will create multiple
    //DB connections in connection pool of DB if not closed properly

    public List<Subject> findByNameNative(String name){
        EntityManager em = getEntityManger();
        try{
            Query query = em.createNativeQuery("SELECT * FROM subject s " +
                    " where s.subject_name = ?", Subject.class).setParameter(1, name);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }

    public EntityManager getEntityManger(){
        return emt.createEntityManager();
    }
}
