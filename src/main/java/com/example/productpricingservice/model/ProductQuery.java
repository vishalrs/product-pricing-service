package com.example.productpricingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ProductQuery {

    String shop;
    String productName;
}
