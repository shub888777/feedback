package com.shubham.Product.Order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequestDto {
    @Min(1)
    private Long cartid;
    private String address;

}
