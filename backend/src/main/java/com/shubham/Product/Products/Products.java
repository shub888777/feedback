package com.shubham.Product.Products;

import com.shubham.Product.Users.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private int price;
    private int inventory;
    private boolean isEnable ;
    private String category;


}
