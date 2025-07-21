package com.munaf.A26_JPA_NEW.dto;

import com.munaf.A26_JPA_NEW.enums.BloodGroup;
import lombok.Data;

@Data
public class BloodGroupCount {

    private BloodGroup bloodGroup;
    private Long bloodGroupCount;

    public BloodGroupCount(BloodGroup bloodGroup, Long bloodGroupCount) {
        this.bloodGroup = bloodGroup;
        this.bloodGroupCount = bloodGroupCount;
    }

}
