package org.uniplovdiv.fmi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uniplovdiv.fmi.persistence.Product;
import org.uniplovdiv.fmi.service.NumberService;
import org.uniplovdiv.fmi.service.ProductService;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NumberController {

    private final NumberService numberService;

    @PutMapping("/put/{num}")
    public String put(@PathVariable int num) {
        numberService.putNumberInCache(num);
        return "Stored " + num + " in cache";
    }

    @GetMapping("/get/{id}")
    public Integer get(@PathVariable String id) {
        return numberService.getNumberFromCache(id);
    }
}

