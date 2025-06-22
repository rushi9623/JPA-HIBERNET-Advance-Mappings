package com.luv2code.demo;

import com.luv2code.demo.dao.AppDAO;
import com.luv2code.demo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CurddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
		 	//createInstructor(appDAO);
          //findInstructor(appDAO);
			// deleteInstructor(appDAO);
			// findInstructorDetails(appDAO);
			// deleteInstructorDetails(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCources(appDAO);
			//findCourcesForInstructor(appDAO);
			//findInstructorWithCourcesJoinfetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructor(appDAO);
			//deleteCourse(appDAO);
			//creeateCourseasnreviews(appDAO);
			//retrieveCourseAndReviews(appDAO);
			//deleteCourseandreviews(appDAO);
			//creeateCourseAndStudent(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourses(appDAO);
			//addmoreCoursesForStudent(appDAO);
			//deleteCourse(appDAO);
			deleteStudent(appDAO);

		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theID = 2;
		System.out.println("Deleting student with id :" + theID);

		appDAO.deleteStudentById(theID);
		System.out.println("Done!");
	}

	private void addmoreCoursesForStudent(AppDAO appDAO) {
		int theID = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theID);

		Course tempCourse1 = new Course("Rubiks cube - 3D Printing");
		Course tempCourse2 = new Course("Spring boot - Microservices");

		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Updating the student: " + tempStudent);
		System.out.println(" associated courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);
		System.out.println("done!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);
		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses: " + tempStudent.getCourses());

		System.out.println("done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 10;

		Course tempCourse = appDAO.findCourseAndStudentByCourseId(theId);

		System.out.println("Loaded course: " + tempCourse);
		System.out.println("students: " + tempCourse.getStudents());
		System.out.println("done!");
	}

	private void creeateCourseAndStudent(AppDAO appDAO) {
		// create a course
       Course tempCourse = new Course("Java 8 in 19 Days");
		// create the students
		Student tempStudent1= new Student("John","Doe","jhon@gmail");
		Student tempStudent2 =new Student("mary","Public","mary@gmail");

		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		// save the course and associated students
		System.out.println("saving the course:" + tempCourse);
		System.out.println(" associated students:" + tempCourse.getStudents());

		appDAO.save(tempCourse);
		System.out.println("done!");
	}

	private void deleteCourseandreviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course and reviews with id :" + theId);

		appDAO.deleteCourseById(theId);
		System.out.println("done!" );
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
		System.out.println("tempCourse: " + tempCourse);

		System.out.println(tempCourse.getReviews());

		System.out.println("done!");
	}

	private void creeateCourseasnreviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		// add some reviews
		tempCourse.addReview(new Review("Great course ... loved it!"));
		tempCourse.addReview(new Review("Cool course ... job loved it!"));
		tempCourse.addReview(new Review("What a dumb course ... you are idot!"));

		// save the course
		System.out.println("saving the course: " + tempCourse);
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);
		System.out.println("done!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Finding course by id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);
		// update
		System.out.println("updating course: " + theId);
		tempCourse.setTitle("Java 8 in 20 Days - Updated");
		appDAO.update(tempCourse);
		System.out.println("done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("finding instructor by id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update
		System.out.println("updating instructor:" + theId);
		tempInstructor.setLastName("Teaster");
		appDAO.update(tempInstructor);
		System.out.println("done!");
	}

	private void findInstructorWithCourcesJoinfetch(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id : " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor:" + tempInstructor);
		System.out.println("The associated courses: " + tempInstructor.getCourses());
		System.out.println("done");
	}

	private void findCourcesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("find instructor by id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		tempInstructor.setCourses(courses);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("done");

	}

	private void findInstructorWithCources(AppDAO appDAO) {
		int theId = 1;
		System.out.println("find instructor by id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the assocaiated courses :" + tempInstructor.getCourses());
		System.out.println("done");
	}

	private void createInstructorWithCourses(AppDAO appDAO){

		Instructor tempInstructor =
				new Instructor("Susan","Public","susan@luv2code.com");
		new Instructor("John","Doe","hohn@gmail.com");

		InstructorDetail tempInstructorDetails =
				new InstructorDetail(
						"http://www.chetboss.com",
						"gamer"
				);
		// associate the object
		tempInstructor.setInstructorDetail(tempInstructorDetails);

		// create new cource
		Course tempCourse = new Course("Java 8 in 21 Days");
		Course tempCourse2 = new Course("Spring in 21 Days");

		// add to instructor
		tempInstructor.add(tempCourse);
		tempInstructor.add(tempCourse2);

		// save
		System.out.println("saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.saveInstructor(tempInstructor);
		System.out.println("done!");
	}

	private void deleteInstructorDetails(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructor details with id :" + theId);

		appDAO.deleteInstructorDetailById(theId);
		System.out.println("done!" );
	}

	private void findInstructorDetails(AppDAO appDAO) {
		// get the instructor details obj
		int theId = 2;
		InstructorDetail tempInstructorDetails = appDAO.findInstructorDetailsById(theId);
		System.out.println("tempInstructorDetails: " + tempInstructorDetails);
		System.out.println("the assocaiated instructor: " + tempInstructorDetails.getInstructor());
		System.out.println("done");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("delete instructor by id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("find instructor by id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the assocaiated instructor only :" + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO){
		Instructor tempInstructor =
				new Instructor("chad","Darby","darby@luv2code.com");
		        new Instructor("John","Doe","hohn@gmail.com");

		InstructorDetail tempInstructorDetails =
				new InstructorDetail(
						"http://www.liv2code.com",
						"Coding"
				);
		// associate the object
		tempInstructor.setInstructorDetail(tempInstructorDetails);

		// save the instructor
		System.out.println("saving the instructor..." + tempInstructor);
		appDAO.saveInstructor(tempInstructor);
		System.out.println("done!");
	}


}
