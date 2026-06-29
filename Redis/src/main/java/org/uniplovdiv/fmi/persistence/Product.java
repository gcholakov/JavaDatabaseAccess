package org.uniplovdiv.fmi.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRODUCTS")
@Data
public class Product {

    @Id
    private int productId;

    private String name;
    private double price;
    private String descr;
}
