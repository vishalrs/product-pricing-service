package com.example.productpricingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductPrice {

    final String shop;
    final String product;
    final Double originalPrice;
    final Double finalPrice;
}
