package com.munaf.A26_JPA_NEW;

import com.munaf.A26_JPA_NEW.entities.Patient;
import com.munaf.A26_JPA_NEW.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Test
    void test() {

        Patient patient = patientService.updatePatient(1L);

    }


}
