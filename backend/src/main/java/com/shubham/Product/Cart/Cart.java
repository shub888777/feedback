package com.shubham.Product.Cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shubham.Product.Products.Products;
import com.shubham.Product.Users.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Users users;
    @ManyToOne
    private Products products;
    private int qty=1;




    private Date date=new Date(System.currentTimeMillis());
}
