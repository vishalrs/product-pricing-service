package com.example.productpricingservice.controllers;

import com.example.productpricingservice.model.ProductQuery;
import com.example.productpricingservice.model.Quote;
import com.example.productpricingservice.model.Shop;
import com.example.productpricingservice.services.IDiscountService;
import com.example.productpricingservice.services.IQuoteFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@RestController
public class PriceFinderController {

    @Autowired
    IQuoteFinderService quoteFinderService;
    @Autowired
    IDiscountService discountService;

    @GetMapping("/quotes")
    public ResponseEntity<?> getQuotes(@RequestParam("shop") String shop,
                                      @RequestParam("product") String product){
        ProductQuery query = new ProductQuery(shop, product);
        return new ResponseEntity<>(quoteFinderService.getQuote(query), HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<?> getDiscountedPrice(Quote quote){
        return new ResponseEntity<>(discountService.applyDiscount(quote), HttpStatus.OK);
    }

    @GetMapping("/exchange")
    public ResponseEntity<Double> getExchangeRate(){
        Random random = new Random();
        Double rate = random.doubles(0.7,0.8).findFirst().getAsDouble();
        BigDecimal finalRate = new BigDecimal(Double.toString(rate));
        finalRate = finalRate.setScale(3, RoundingMode.HALF_UP);
        return new ResponseEntity<Double>(finalRate.doubleValue(), HttpStatus.OK);
    }

    @GetMapping("/shops")
    public ResponseEntity<?> getShops(){
        return new ResponseEntity<>(List.of(new Shop("Shop-AA"),
                new Shop("Shop-AA"),
                new Shop("Shop-BB"),
                new Shop("Shop-CC"),
                new Shop("Shop-DD")), HttpStatus.OK);
    }

}
