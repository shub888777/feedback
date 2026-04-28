package com.shubham.Product.Order;

import com.shubham.Product.Cart.Cart;
import com.shubham.Product.Products.Products;
import com.shubham.Product.Users.Users;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "myorder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private int price;
    private int qty;
    @ManyToOne(fetch = FetchType.EAGER)
    private Products products;
    @ManyToOne
    private Users users;




}
