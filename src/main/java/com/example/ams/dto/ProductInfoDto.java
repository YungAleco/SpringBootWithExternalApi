package com.example.ams.dto;

import lombok.Data;

@Data
public class ProductInfoDto {

    Long id;
    String name;
    Double price;
    boolean availability;

}
