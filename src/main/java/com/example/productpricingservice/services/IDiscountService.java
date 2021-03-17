package com.example.productpricingservice.services;

import com.example.productpricingservice.model.ProductPrice;
import com.example.productpricingservice.model.Quote;
import reactor.core.publisher.Mono;

public interface IDiscountService {

    ProductPrice applyDiscount(Quote quote);

    Mono<ProductPrice> applyRxDiscount(Quote quote);

}
