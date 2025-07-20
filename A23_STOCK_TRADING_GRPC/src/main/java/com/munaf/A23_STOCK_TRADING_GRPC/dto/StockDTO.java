package com.munaf.A23_STOCK_TRADING_GRPC.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StockDTO {

    private Long id;
    private String name;
    private int price;
    private Date stockDate;

}
