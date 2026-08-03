package com.munaf.A34_ELASTIC_SEARCH.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDocument {

    @Id
    private String id;

    private String brand;
    private String model;
    private Double price;
    private String fuelType;

}
