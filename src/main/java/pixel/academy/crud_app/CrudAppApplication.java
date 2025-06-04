package pixel.academy.crud_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pixel.academy.crud_app.dao.StudentDAO;
import pixel.academy.crud_app.entity.Student;

import java.beans.BeanProperty;
import java.util.List;

@SpringBootApplication
public class CrudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudAppApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner -> {
			//createStudent(studentDAO);
			//createMultipleStudents(studentDAO);
			//readStudent(studentDAO);

			queryForStudents(studentDAO);

		};
	}

	private void createStudent(StudentDAO studentDAO) {

		// cream un obiect Student
		System.out.println("Creating new student object ...");
		Student newStudent = new Student("John" , "Doe" , "john@pixelacademy.md");

		// salvam obiectul Student in baza de date folosind DAO
		System.out.println("Saving the student ...");
		studentDAO.save(newStudent);

		// afisa ID -ul  studentului salvat
		System.out.println("Saved student. Generated id:" + newStudent.getId());

	}
	private void createMultipleStudents(StudentDAO studentDAO) {
     // cream mai multi studenti
		System.out.println("Creating 3 student objects");
		Student newStudent1 = new Student("Andrei", "Munteanu", "andrei@gmail.com");
		Student newStudent2 = new Student("Iulian", "Vatamanu", "iulic@gmail.com");
		Student newStudent3 = new Student("Maria", "Mirabel", "maria@gmail.com");

		//salvam obiectele student in baza de date
		System.out.println("Saving the students");
		studentDAO.save(newStudent1);
		studentDAO.save(newStudent2);
		studentDAO.save(newStudent3);

	}
	private  void  readStudent(StudentDAO studentDAO) {

		// creaza un obiect de tip student
		System.out.println("Creating new student object ...");
		Student newStudent = new Student("Mircea", "Popescu","mirceap@pixel.academy");

		// salveaza studentul in baza de date
		System.out.println("Saving the student ...");
		studentDAO.save(newStudent);

		// afiseaza id-ul studentului salvat
		int theId = newStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// recupereaza studentul pe baza id-ului
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// afiseaza detaliile studentului
		System.out.println("Found the student: " + myStudent);

	}
	private  void queryForStudents(StudentDAO studentDAO) {

		// obtine lista de studenti
		List<Student> theStudents = studentDAO.findAll();

		// afiseaza lista de studenti
		for (Student newStudents : theStudents) {
			System.out.println(newStudents);
		}
	}
}