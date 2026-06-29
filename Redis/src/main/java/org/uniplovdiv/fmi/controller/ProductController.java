package org.uniplovdiv.fmi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.uniplovdiv.fmi.persistence.Product;
import org.uniplovdiv.fmi.service.ProductService;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{id}")
    public Product get(@PathVariable int id) {

        log.info("=============================");
        log.info("Getting product with id: {}", id);

        Instant start = Instant.now();

        Product product = productService.getProduct(id);

        long durationMs = Duration.between(start, Instant.now()).toMillis();

        log.info("Returned in {} ms product: {}", durationMs, product);

        return product;
    }

    @DeleteMapping("/cache/{id}")
    public String clearCache(@PathVariable int id) {
        productService.clearProductCache(id);
        return "Cache for product " + id + " cleared";
    }

    @DeleteMapping("/cache")
    public String clearAllCache() {
        productService.clearAllProductsCache();
        return "All product cache cleared";
    }
}

