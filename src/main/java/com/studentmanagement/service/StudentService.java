package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class StudentService {

    private final EntityManagerFactory emf;

    public StudentService() {
        emf = Persistence.createEntityManagerFactory("StudentPU");
    }

    //creating student
    public void createStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(student);
            tx.commit();
            System.out.println("Student created successfully: " + student.getName());
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    //find StudentByID
    public Student findStudent(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    //get AllStudents
    public List<Student> getAllStudents() {
        EntityManager em=emf.createEntityManager();
        try {
            return em.createQuery("select s from Student s", Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    //update Student
    public void updateStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(student);
            tx.commit();
            System.out.println("Student updated successfully!");
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //delete Student
    public void deleteStudent(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Student student = em.find(Student.class, id);
            if(student != null) {
                em.remove(student);
                tx.commit();
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //close factory when application ends
    public void close() {
        if(emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}
