package com.example.productpricingservice.services;

import com.example.productpricingservice.model.Quote;
import com.example.productpricingservice.model.ProductQuery;
import reactor.core.publisher.Mono;

public interface IQuoteFinderService {
    Quote getQuote(ProductQuery query);

    Mono<Quote> getRxQuote(ProductQuery query);
}
