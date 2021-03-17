package com.example.productpricingservice.controllers;

import com.example.productpricingservice.model.ProductPrice;
import com.example.productpricingservice.model.ProductQuery;
import com.example.productpricingservice.model.Quote;
import com.example.productpricingservice.model.Shop;
import com.example.productpricingservice.services.IDiscountService;
import com.example.productpricingservice.services.IQuoteFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Random;

//@RestController
@RequestMapping("/rx")
public class PriceFinderReactiveController {

    @Autowired
    IQuoteFinderService quoteFinderService;
    @Autowired
    IDiscountService discountService;

    @GetMapping("/quotes")
    public Mono<ResponseEntity<Quote>> getQuote(@RequestParam("shop") String shop,
                                       @RequestParam("product") String product){
        ProductQuery query = new ProductQuery(shop, product);
        return quoteFinderService
                .getRxQuote(query)
                .map(q-> new ResponseEntity(q, HttpStatus.OK));
    }

    @GetMapping("/price")
    public Mono<ResponseEntity<ProductPrice>> getDiscountedPrice(Quote quote){
        return discountService
                .applyRxDiscount(quote)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK));
    }

    @GetMapping("/exchange")
    public Mono<ResponseEntity<Double>> getExchangeRate(){
        Random random = new Random();
        Double rate = random.doubles(0.7,0.8).findFirst().getAsDouble();
        BigDecimal finalRate = new BigDecimal(Double.toString(rate));
        finalRate = finalRate.setScale(3, RoundingMode.HALF_UP);
        return  Mono.just(new ResponseEntity<Double>(finalRate.doubleValue(), HttpStatus.OK));
    }

    @GetMapping("/shops")
    public Flux<Shop> getShops(){
        return Flux.fromIterable(List.of(new Shop("Shop-AA"),
                new Shop("Shop-AA"),
                new Shop("Shop-BB"),
                new Shop("Shop-CC"),
                new Shop("Shop-DD")));
    }

}
