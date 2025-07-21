package com.munaf.A26_JPA_NEW;

import com.munaf.A26_JPA_NEW.dto.BloodGroupCount;
import com.munaf.A26_JPA_NEW.dto.PatientDTO;
import com.munaf.A26_JPA_NEW.dto.PatientRecord;
import com.munaf.A26_JPA_NEW.repositories.PatientRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class A26JpaNewApplicationTests {

	@Autowired
	private PatientRepo patientRepo;


    @Test
	void contextLoads() {
	}


	@Test
	void test() {

		PatientRecord patientRecord = patientRepo.findPatientRecordById(1L);
		System.out.println(patientRecord);

		PatientDTO patientDTO = patientRepo.findPatientDTOById(1L);
		System.out.println(patientDTO);

		List<BloodGroupCount> bloodGroupCount = patientRepo.bloodGroupCount();
		System.out.println(bloodGroupCount);


		int updatedRows = patientRepo.updatePatientNameById("Munaf", 1L);
		System.out.println(updatedRows);

	}

}
