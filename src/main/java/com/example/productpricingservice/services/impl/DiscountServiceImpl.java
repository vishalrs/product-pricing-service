package com.example.productpricingservice.services.impl;

import com.example.productpricingservice.model.ProductPrice;
import com.example.productpricingservice.model.Quote;
import com.example.productpricingservice.services.IDiscountService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DiscountServiceImpl implements IDiscountService {
    @Override
    public ProductPrice applyDiscount(Quote quote) {
        ProductPrice productPrice = new ProductPrice(quote.getShop(),
                quote.getProduct(),
                quote.getPrice(),
                (double) Math.round(quote.getPrice() * (100 - quote.getDiscountCode().getPercentage()) / 100));
        return productPrice;
    }

    @Override
    public Mono<ProductPrice> applyRxDiscount(Quote quote) {
        ProductPrice productPrice = new ProductPrice(quote.getShop(),
                quote.getProduct(),
                quote.getPrice(),
                (double) Math.round(quote.getPrice() * (100 - quote.getDiscountCode().getPercentage()) / 100));
        return Mono.just(productPrice);
    }
}
