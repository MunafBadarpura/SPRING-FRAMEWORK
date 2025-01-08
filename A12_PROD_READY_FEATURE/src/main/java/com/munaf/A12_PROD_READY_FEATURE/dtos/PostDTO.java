package com.munaf.A12_PROD_READY_FEATURE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String name;
    private String description;

}
