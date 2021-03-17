package com.example.productpricingservice.services.impl;

import com.example.productpricingservice.model.Currency;
import com.example.productpricingservice.model.Discount;
import com.example.productpricingservice.model.ProductQuery;
import com.example.productpricingservice.model.Quote;
import com.example.productpricingservice.services.IQuoteFinderService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Service
public class QuoteFinderServiceImpl implements IQuoteFinderService {

    @Override
    public Quote getQuote(ProductQuery query) {
        delay();
        return new Quote(query.getShop(),
                    query.getProductName(),
                (double) Math.round(new Random().nextDouble() * query.getProductName().charAt(0) + query.getShop().charAt(1)),
                Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)],
                Currency.values()[new Random().nextInt(2)]);
    }

    @Override
    public Mono<Quote> getRxQuote(ProductQuery query) {
        System.out.println(query);
        //delay();
        return Mono.just(new Quote(query.getShop(),
                query.getProductName(),
                (double) Math.round(new Random().nextDouble() * query.getProductName().charAt(0) + query.getShop().charAt(1)),
                Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)],
                Currency.values()[new Random().nextInt(2)])).delayElement(Duration.ofMillis(2000 + new Random().nextInt(5000)));
    }

    private void delay(){
        try {
            int delay = 2000 + new Random().nextInt(5000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
