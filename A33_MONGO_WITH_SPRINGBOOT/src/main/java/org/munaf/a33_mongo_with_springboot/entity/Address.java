package org.munaf.a33_mongo_with_springboot.entity;

import lombok.Data;

// this is not a document , it is just object to store inside user
@Data
public class Address {

    private String address;
    private String city;
    private String state;

}