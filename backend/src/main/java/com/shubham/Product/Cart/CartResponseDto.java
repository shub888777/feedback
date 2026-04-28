package com.shubham.Product.Cart;

import lombok.Data;

@Data

public class CartResponseDto {
    private int remainingProduct;
    private String Productname;
    private int qty;
}
