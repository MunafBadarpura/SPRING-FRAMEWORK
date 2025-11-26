package com.munaf.A30_SPRING_AI_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CricketResponse {

    private String playerName;
    private String teamName;
    private int maxScore;
    private int currentAge;
    private String dateOfBirth;

}
