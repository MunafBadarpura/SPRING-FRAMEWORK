package com.munaf.A26_JPA_NEW.dto;

import com.munaf.A26_JPA_NEW.enums.BloodGroup;
import lombok.Data;

@Data
public class PatientDTO {


    private String name;

    private String address;

    private BloodGroup bloodGroup;


    public PatientDTO(String name, String address, BloodGroup bloodGroup) {
        this.name = name;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }


}
