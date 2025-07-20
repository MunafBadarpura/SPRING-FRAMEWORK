package com.munaf.stock_tading_client.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class StockDto {

    private Long id;
    private String name;
    private Long price;
    private Date stockDate;

}
