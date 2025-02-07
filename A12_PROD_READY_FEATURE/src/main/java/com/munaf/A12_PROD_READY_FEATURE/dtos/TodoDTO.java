package com.munaf.A12_PROD_READY_FEATURE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long userId;
    private Long id;
    private String title;
    private Boolean completed;

}
