package com.example.productpricingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
@ToString
public class Quote {

    final String shop;

    final String product;

    final Double price;

    final Discount.Code discountCode;

    final Currency currency;


}
