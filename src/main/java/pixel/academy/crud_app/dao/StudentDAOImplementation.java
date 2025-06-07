package pixel.academy.crud_app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pixel.academy.crud_app.entity.Student;

import java.util.List;

@Repository
public class StudentDAOImplementation implements StudentDAO {

    // Cimp pentru EntityManager (va fi utilizat pentru interactiunea cu baza de date)
    private EntityManager entityManager;

    // Injectarea EntityManager prin constructor (practica recomandata pentru testabilitatea si modularitate)
    @Autowired
    public StudentDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);

    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);

    }

    @Override
    public List<Student> findAll() {
        // creare query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

        // returnam query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {

        // creare query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData",Student.class);


        // setarea parametrilor pentru query
        theQuery.setParameter("theData", theLastName);

        //returnarea rezultatelor query
        return theQuery.getResultList();


    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        // preluam studentul din baza de date
        Student theStudent = entityManager.find(Student.class, id);

        // stergem studentul
        entityManager.remove(theStudent);

    }


}
