package com.shubham.Product.Order;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/superadmin")
public class OrderSuperAdminController
{
    final private OrderService orderService;

    @GetMapping("/order")
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }

}
