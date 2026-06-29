package org.uniplovdiv.fmi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.uniplovdiv.fmi.persistence.Product;
import org.uniplovdiv.fmi.persistence.ProductRepository;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements Serializable {

    private final ProductRepository productRepository;

    @Cacheable(value = "products", key = "#id")
    public Product getProduct(int id) {

        log.info(">>> Querying MSSQL...");

        Product product = productRepository.findById(id).orElseThrow();

        log.info(">>> Product found: {}", product);

        return product;
    }

    @CacheEvict(value = "products", key = "#id")
    public void clearProductCache(int id) {
        log.info(">>> Clearing cache for product with id: {}", id);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void clearAllProductsCache() {
        log.info(">>> Clearing all product cache entries");
    }
}

