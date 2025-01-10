package com.munaf.A12_PROD_READY_FEATURE;

import com.munaf.A12_PROD_READY_FEATURE.clients.EmployeeClient;
import com.munaf.A12_PROD_READY_FEATURE.clients.impls.EmployeeClientImpl;
import com.munaf.A12_PROD_READY_FEATURE.dtos.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class A12ProdReadyFeatureApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

//	@Test
//	void getEmployeeById(){
//		Long employeeId = 1L;
//		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(employeeId);
//		System.out.println(employeeDTO);
//	}
//
//	@Test
//	void getAllEmployees(){
//		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployee();
//		System.out.println(employeeDTOList);
//	}
//
//	@Test
//	void createNewEmployee(){
//		EmployeeDTO employeeDTO = new EmployeeDTO("MUNAFF", 20, "createy@gmail.com", LocalDate.of(2025, 01,10), true, "ADMIN", 20000D);
//		EmployeeDTO createdEmployee = employeeClient.createNewEmployee(employeeDTO);
//		System.out.println(createdEmployee);
//	}
//
//	@Test
//	void updateEmployee(){
//		Long employeeId = 852L;
//		EmployeeDTO employeeToUpdate = new EmployeeDTO("munafff", 25, "createy06@gmail.com", LocalDate.of(2025, 01,10), true, "ADMIN", 20000D);
//		EmployeeDTO updatedEmployee = employeeClient.updateEmployeeById(employeeId, employeeToUpdate);
//		System.out.println(updatedEmployee);
//	}

	@Test
	void deleteEmployeeById(){
		Long employeeId = 852L;
		Boolean isEmployeeDeleted = employeeClient.deleteEmployeeById(employeeId);
		System.out.println(isEmployeeDeleted);
	}

	// error example
	@Test
	void createNewEmployee(){
		EmployeeDTO employeeDTO = new EmployeeDTO("MUNAFF", 2, "createy@gmail.com", LocalDate.of(2025, 01,10), true, "ADMIN", 20000D);
		EmployeeDTO createdEmployee = employeeClient.createNewEmployee(employeeDTO);
		System.out.println(createdEmployee);
	}

}
