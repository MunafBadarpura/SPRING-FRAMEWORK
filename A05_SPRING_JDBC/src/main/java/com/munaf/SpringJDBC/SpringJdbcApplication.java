package com.munaf.SpringJDBC;

import com.munaf.SpringJDBC.model.Student;
import com.munaf.SpringJDBC.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/*
*  Service Layer
*  Repository Layer
*  JDBC template
*
* */
@SpringBootApplication
public class SpringJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbcApplication.class, args);

		Student s = context.getBean(Student.class);
		s.setrNo(104);
		s.setName("Munaf");
		s.setAge(18);

		StudentService service = context.getBean(StudentService.class);
		service.addStudent(s);

		List<Student> students = service.getStudents();
		System.out.println(students);

	}

}
