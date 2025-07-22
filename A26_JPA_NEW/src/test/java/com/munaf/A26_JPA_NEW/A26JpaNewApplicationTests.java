package com.munaf.A26_JPA_NEW;

import com.munaf.A26_JPA_NEW.dto.BloodGroupCount;
import com.munaf.A26_JPA_NEW.dto.PatientDTO;
import com.munaf.A26_JPA_NEW.dto.PatientRecord;
import com.munaf.A26_JPA_NEW.entities.Patient;
import com.munaf.A26_JPA_NEW.repositories.PatientRepository;
import com.munaf.A26_JPA_NEW.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class A26JpaNewApplicationTests {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientService patientService;

    @Test
	void contextLoads() {
	}


	@Test
	void test() {

		PatientRecord patientRecord = patientRepository.findPatientRecordById(1L);
		System.out.println(patientRecord);

		PatientDTO patientDTO = patientRepository.findPatientDTOById(1L);
		System.out.println(patientDTO);

		List<BloodGroupCount> bloodGroupCount = patientRepository.bloodGroupCount();
		System.out.println(bloodGroupCount);


		int updatedRows = patientRepository.updatePatientNameById("Munaf", 1L);
		System.out.println(updatedRows);

	}


	@Test
	void test2() {
		Patient patient = patientService.updatePatient(1L);
		System.out.println(patient);
	}



}
