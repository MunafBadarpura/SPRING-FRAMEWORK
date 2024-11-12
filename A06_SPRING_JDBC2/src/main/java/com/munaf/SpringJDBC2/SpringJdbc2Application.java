package com.munaf.SpringJDBC2;

import com.munaf.SpringJDBC2.demo.Students;
import com.munaf.SpringJDBC2.service.StudentsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/*

1. JDBC template
2. Service Layer =  BUSINESS LOGIC
3. Repository Layer = DATABASE ACTIONS
4. RowMapper = FOR GETTING DATA

*/

import java.util.List;

@SpringBootApplication
public class SpringJdbc2Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbc2Application.class, args);
		Students s = context.getBean(Students.class);
		s.setRollNo(104);
		s.setName("Munaf");
		s.setMarks(90);

		StudentsService service = context.getBean(StudentsService.class);
//		service.add(s);
//		service.updateStudentMarks(s,100);
//		service.deleteStudent(s);

		List<Students> students = service.getStudents();
		System.out.println("Students data : ");
		System.out.println(students);

	}

}
