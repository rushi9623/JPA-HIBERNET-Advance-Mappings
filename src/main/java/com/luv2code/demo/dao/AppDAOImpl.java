package com.luv2code.demo.dao;

import com.luv2code.demo.entity.Course;
import com.luv2code.demo.entity.Instructor;
import com.luv2code.demo.entity.InstructorDetail;
import com.luv2code.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl  implements AppDAO {
    //define entity manager using constructor
    private EntityManager entityManager;

    // inject entity manager using constructor injection

    @Autowired
    public AppDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    @Transactional
    @Override
    public void saveInstructor(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Transactional
    @Override
    public void deleteInstructorById(int theId) {
        Instructor instructor = entityManager.find(Instructor.class, theId);

        // Remove the association from InstructorDetail
        if (instructor.getInstructorDetail() != null) {
            instructor.getInstructorDetail().setInstructor(null);
        }

        // Remove all Courses associated with the Instructor
        List<Course> courses = instructor.getCourses();
        if (courses != null) {
            for (Course course : courses) {
                course.setInstructor(null);
            }
        }

        entityManager.remove(instructor);
    }


    @Override
    public InstructorDetail findInstructorDetailsById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // retrive ins details

        InstructorDetail tempInstructorDetails = entityManager.find(InstructorDetail.class,theId);

        // get the courses

        // delete the instructor details

        entityManager.remove(tempInstructorDetails);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create queary
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);

        query.setParameter("data",theId);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        "join fetch i.courses "
                        + "join fetch i.instructorDetail " +
                        "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Transactional
    @Override
    public void deleteCourseById(int theId) {

        Course tempCourse = entityManager.find(Course.class,theId);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
         entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        //create queary

        TypedQuery<Course> query = entityManager.createQuery(
                "select distinct c from Course c " +
                        "JOIN FETCH c.reviews" +
                        " where c.id = :data", Course.class);
        query.setParameter("data",theId);
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
       //create queary

        TypedQuery<Course> query = entityManager.createQuery(
                "select distinct c from Course c " +
                        "JOIN FETCH c.students " +
                        " where c.id = :data", Course.class);
        query.setParameter("data",theId);
        // excute  query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s  from Student s " +
                        "JOIN FETCH s.courses " +
                        " where s.id = :data", Student.class);

        query.setParameter("data",theId);
        //excute query
        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student tempStudent = entityManager.find(Student.class,theId);
        if (tempStudent != null){
            List<Course> courses = tempStudent.getCourses();

            for (Course tempCourse : courses){
                tempCourse.getStudents().remove(tempStudent);
            }
            entityManager.remove(tempStudent);
        }

    }

}
